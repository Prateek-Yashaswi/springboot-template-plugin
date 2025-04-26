package helper;

import config.FreemarkerConfig;
import constants.FreemarkerConstants;
import enums.Templates;
import exceptions.ProcessingException;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import model.AdditionalProjectDetails;
import model.BasicProjectDetails;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PomCreator {

    private static final String DEFAULT_POM_TEMPLATE_NAME = "/pom-template.ftl";

    private final Configuration freemarkerConfig = FreemarkerConfig.getConfig();

    private PomCreator() {
    }

    public static PomCreator getInstance() {
        return new PomCreator();
    }

    public void parsePomTemplates(BasicProjectDetails basicProjectDetails, AdditionalProjectDetails additionalProjectDetails, Path pomPath, Set<Templates> templates) {

        var dataModel = constructTemplateDataModel(basicProjectDetails, additionalProjectDetails, templates);

        try (var writer = new FileWriter(pomPath.toFile())) {
            var ftl = freemarkerConfig.getTemplate(DEFAULT_POM_TEMPLATE_NAME);
            ftl.process(dataModel, writer);
        } catch (IOException | TemplateException exception) {
            throw new ProcessingException("Something went wrong while parsing pom templates", exception);
        }
    }

    private Map<String, Object> constructTemplateDataModel(BasicProjectDetails basicProjectDetails, AdditionalProjectDetails additionalProjectDetails, Set<Templates> templates) {
        Map<String, Object> freemarkerModel = new HashMap<>();

        freemarkerModel.put(FreemarkerConstants.SPRING_VERSION, basicProjectDetails.springVersion());
        freemarkerModel.put(FreemarkerConstants.ARTIFACT_ID, basicProjectDetails.artifactId());
        freemarkerModel.put(FreemarkerConstants.GROUP_ID, basicProjectDetails.groupId());
        freemarkerModel.put(FreemarkerConstants.JAVA_VERSION, basicProjectDetails.javaVersion());
        freemarkerModel.put(FreemarkerConstants.PROJECT_NAME, basicProjectDetails.projectName());
        freemarkerModel.put(FreemarkerConstants.SWAGGER_ENABLED, additionalProjectDetails.createSwagger().getFlag());
        freemarkerModel.put(FreemarkerConstants.JPA_ENABLED, templates.contains(Templates.DATABASE_JPA));
        freemarkerModel.put(FreemarkerConstants.MAIL_ENABLED, templates.contains(Templates.MAIL));

        return freemarkerModel;
    }
}
