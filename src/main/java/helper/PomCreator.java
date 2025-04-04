package helper;

import config.FreemarkerConfig;
import constants.FreemarkerConstants;
import exceptions.ProcessingException;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import model.BasicProjectDetails;
import enums.Templates;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class PomCreator {

    private static final String DEFAULT_POM_TEMPLATE_NAME = "/default-pom.ftl";

    private final Configuration freemarkerConfig = FreemarkerConfig.getConfig();

    private PomCreator() {
    }

    public static PomCreator getInstance() {
        return new PomCreator();
    }

    public void parsePomTemplates(BasicProjectDetails basicProjectDetails, Path pomPath, Templates template) {

        var dataModel = switch (template) {
            case DEFAULT -> getDefaultTemplateDataModel(basicProjectDetails);
            case DATABASE -> getDefaultTemplateDataModel(basicProjectDetails);
        };

        var templateName = switch (template) {
            case DEFAULT -> DEFAULT_POM_TEMPLATE_NAME;
            case DATABASE -> "TEST";
        };

        try {
            var ftl = freemarkerConfig.getTemplate(templateName);
            var writer = new FileWriter(pomPath.toFile());
            ftl.process(dataModel, writer);
        } catch (IOException | TemplateException exception) {
            throw new ProcessingException("Something went wrong while parsing pom templates", exception);
        }
    }

    private Map<String, Object> getDefaultTemplateDataModel(BasicProjectDetails basicProjectDetails) {

        Map<String, Object> freemarkerModel = new HashMap<>();
        addCommonData(freemarkerModel, basicProjectDetails);

        return freemarkerModel;
    }

    private void addCommonData(Map<String, Object> freemarkerModel, BasicProjectDetails basicProjectDetails) {
        freemarkerModel.put(FreemarkerConstants.SPRING_VERSION, basicProjectDetails.springVersion());
        freemarkerModel.put(FreemarkerConstants.ARTIFACT_ID, basicProjectDetails.artifactId());
        freemarkerModel.put(FreemarkerConstants.GROUP_ID, basicProjectDetails.groupId());
        freemarkerModel.put(FreemarkerConstants.JAVA_VERSION, basicProjectDetails.javaVersion());
        freemarkerModel.put(FreemarkerConstants.PROJECT_NAME, basicProjectDetails.projectName());
    }
}
