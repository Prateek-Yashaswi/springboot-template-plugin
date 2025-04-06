package helper;

import config.FreemarkerConfig;
import constants.FreemarkerConstants;
import enums.ConfigurationType;
import enums.Templates;
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

    private static final String DEFAULT_TEMPLATE = "/default-application";
    private static final String JPA_TEMPLATE = "/jpa-application";

    private final Configuration freemarkerConfig = FreemarkerConfig.getConfig();

    private ConfigCreator() {
    }

    public static ConfigCreator getInstance() {
        return new ConfigCreator();
    }

    public void createApplicationConfigFile(BasicProjectDetails basicProjectDetails, Path projectPath, Templates configTemplate) throws IOException {
        Path configFile = projectPath.resolve(SRC + MAIN + RESOURCES + "/application." + basicProjectDetails.configurationType().getExtension());
        Files.createDirectories(configFile.getParent());

        var dataModel = new HashMap<String, Object>();
        dataModel.put(FreemarkerConstants.PROJECT_NAME, basicProjectDetails.projectName());

        var templateName = getTemplateByConfigurationType(basicProjectDetails.configurationType(), configTemplate);

        try (var writer = new FileWriter(configFile.toFile())) {
            var template = freemarkerConfig.getTemplate(templateName);
            template.process(dataModel, writer);
        } catch (IOException | TemplateException ex) {
            throw new ProcessingException("Error generating Main.java", ex);
        }
    }

    private String getTemplateByConfigurationType(ConfigurationType configurationType, Templates configTemplate) {

        var templateName = switch (configTemplate) {
            case DEFAULT -> DEFAULT_TEMPLATE;
            case DATABASE_JPA -> JPA_TEMPLATE;
        };

        return templateName + "-" + configurationType.getExtension() + ".ftl";
    }
}
