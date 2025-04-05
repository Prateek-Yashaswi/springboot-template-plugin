package helper;

import config.FreemarkerConfig;
import constants.FreemarkerConstants;
import exceptions.ProcessingException;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import model.BasicProjectDetails;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public class DockerfileCreator {

    private static final String DOCKERFILE = "Dockerfile";
    private static final String DOCKER_TEMPLATE_NAME = "docker-file-template.ftl";

    private final Configuration freemarkerConfig = FreemarkerConfig.getConfig();

    public void createDockerfile(Path path, BasicProjectDetails details) {

        var dockerfile = path.resolve(DOCKERFILE);

        try (var writer = new FileWriter(dockerfile.toFile())) {
            var template = freemarkerConfig.getTemplate(DOCKER_TEMPLATE_NAME);
            template.process(createDataModel(details), writer);
        } catch (IOException | TemplateException ex) {
            throw new ProcessingException("Error generating Main.java", ex);
        }
    }

    private Map<String, Object> createDataModel(BasicProjectDetails details) {
        return Map.ofEntries(Map.entry(FreemarkerConstants.JAVA_VERSION, details.javaVersion()));
    }
}
