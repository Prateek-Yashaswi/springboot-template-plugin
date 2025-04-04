package helper;

import config.FreemarkerConfig;
import exceptions.ProcessingException;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import model.BasicProjectDetails;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import static constants.FreemarkerConstants.MAIN_CLASS_FILE_NAME;
import static constants.FreemarkerConstants.PACKAGE_NAME;

public class ClassCreator {

    private ClassCreator() {
    }

    public static ClassCreator getInstance() {
        return new ClassCreator();
    }

    private static final String MAIN_CLASS_FTL = "/main-class.ftl";

    private final Configuration freemarkerConfig = FreemarkerConfig.getConfig();

    public void generateMainClass(BasicProjectDetails basicProjectDetails, Path projectPath) throws IOException {
        Map<String, Object> model = new HashMap<>();
        var className = getApplicationClassName(basicProjectDetails.projectName());

        model.put(PACKAGE_NAME, basicProjectDetails.packageName());
        model.put(MAIN_CLASS_FILE_NAME, className);

        var mainJavaFile = projectPath.resolve(className + ".java");

        try (var writer = new FileWriter(mainJavaFile.toFile())) {
            var template = freemarkerConfig.getTemplate(MAIN_CLASS_FTL);
            template.process(model, writer);
        } catch (IOException | TemplateException ex) {
            throw new ProcessingException("Error generating Main.java", ex);
        }
    }

    private String getApplicationClassName(String projectName) {
        String[] parts = projectName.split("[-_\\s]");
        StringBuilder sb = new StringBuilder();
        for (String part : parts) {
            if (!part.isBlank()) {
                sb.append(Character.toUpperCase(part.charAt(0)));
                sb.append(part.substring(1));
            }
        }
        sb.append("Application");
        return sb.toString();
    }
}
