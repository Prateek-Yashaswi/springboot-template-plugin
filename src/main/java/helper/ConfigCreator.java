package helper;

import config.FreemarkerConfig;
import constants.FreemarkerConstants;
import enums.ConfigurationType;
import exceptions.ProcessingException;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import model.BasicProjectDetails;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

import static constants.PluginConstants.*;

public class ConfigCreator {

    private static final String DEFAULT_YAML = "default-application-yaml.ftl";
    private static final String DEFAULT_PROPERTIES = "default-application-properties.ftl";

    private final Configuration freemarkerConfig = FreemarkerConfig.getConfig();

    private ConfigCreator() {
    }

    public static ConfigCreator getInstance() {
        return new ConfigCreator();
    }

    public void createApplicationConfigFile(BasicProjectDetails basicProjectDetails, Path projectPath) throws IOException {
        Path configFile = projectPath.resolve(SRC + MAIN + RESOURCES + "/application." + basicProjectDetails.configurationType().getExtension());
        Files.createDirectories(configFile.getParent());

        var dataModel = new HashMap<String, Object>();
        dataModel.put(FreemarkerConstants.PROJECT_NAME, basicProjectDetails.projectName());

        var templateName = getTemplateByConfigurationType(basicProjectDetails.configurationType());

        try (var writer = new FileWriter(configFile.toFile())) {
            var template = freemarkerConfig.getTemplate(templateName);
            template.process(dataModel, writer);
        } catch (IOException | TemplateException ex) {
            throw new ProcessingException("Error generating Main.java", ex);
        }
    }

    private String getTemplateByConfigurationType(ConfigurationType configurationType) {
        return switch (configurationType) {
            case YAML -> DEFAULT_YAML;
            case PROPERTIES -> DEFAULT_PROPERTIES;
        };
    }
}
