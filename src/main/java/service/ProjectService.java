package service;

import helper.ClassCreator;
import helper.ConfigCreator;
import helper.PomCreator;
import model.BasicProjectDetails;
import model.enums.Templates;
import org.apache.maven.plugin.MojoExecutionException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static constants.PluginConstants.*;


public class ProjectService {

    private static final String TEST = "/test";
    private static final String POM = "pom.xml";


    public void createDefaultProjectStructure(BasicProjectDetails basicProjectDetails, Templates template) throws MojoExecutionException {
        var projectPath = Path.of(basicProjectDetails.projectName());
        var packagePath = basicProjectDetails.packageName().replace('.', '/');

        try {
            var javaPath = projectPath.resolve(SRC + MAIN + JAVA + "/" + packagePath);
            var resourcesPath = projectPath.resolve(SRC + MAIN + RESOURCES);
            var testPath = projectPath.resolve(SRC + TEST + JAVA + "/" + packagePath);

            Files.createDirectories(javaPath);
            Files.createDirectories(resourcesPath);
            Files.createDirectories(testPath);

            var pomFile = projectPath.resolve(POM);
            PomCreator.getInstance().parsePomTemplates(basicProjectDetails, pomFile, template);
            ConfigCreator.getInstance().createApplicationConfigFile(basicProjectDetails.configurationType().getExtension(), projectPath);
            ClassCreator.getInstance().generateMainClass(basicProjectDetails, javaPath);

        } catch (IOException e) {
            throw new MojoExecutionException("Error creating project structure", e);
        }
    }
}
