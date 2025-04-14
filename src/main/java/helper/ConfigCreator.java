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
import java.util.Map;
import java.util.Set;

import static constants.PluginConstants.*;

public class ConfigCreator {

    private static final String YAML_TEMPLATE = "/application-yaml-template.ftl";
    private static final String PROPERTIES_TEMPLATE = "/application-properties-template.ftl";

    private final Configuration freemarkerConfig = FreemarkerConfig.getConfig();

    private ConfigCreator() {
    }

    public static ConfigCreator getInstance() {
        return new ConfigCreator();
    }

    public void createApplicationConfigFile(BasicProjectDetails basicProjectDetails, Path projectPath, Set<Templates> configTemplates) throws IOException {
        Path configFile = projectPath.resolve(SRC + MAIN + RESOURCES + "/application." + basicProjectDetails.configurationType().getExtension());
        Files.createDirectories(configFile.getParent());

        var dataModel = constructDataModel(basicProjectDetails, configTemplates);
        var templateName = getTemplateByConfigurationType(basicProjectDetails.configurationType());

        try (var writer = new FileWriter(configFile.toFile())) {
            var template = freemarkerConfig.getTemplate(templateName);
            template.process(dataModel, writer);
        } catch (IOException | TemplateException ex) {
            throw new ProcessingException("Error generating Main.java", ex);
        }
    }

    private Map<String, Object> constructDataModel(BasicProjectDetails basicProjectDetails, Set<Templates> templates) {
        Map<String, Object> dataModel = new HashMap<>();

        dataModel.put(FreemarkerConstants.PROJECT_NAME, basicProjectDetails.projectName());
        dataModel.put(FreemarkerConstants.JPA_ENABLED, templates.contains(Templates.DATABASE_JPA));

        return dataModel;
    }

    private String getTemplateByConfigurationType(ConfigurationType configurationType) {
        return switch (configurationType) {
            case YAML -> YAML_TEMPLATE;
            case PROPERTIES -> PROPERTIES_TEMPLATE;
        };
    }
}
