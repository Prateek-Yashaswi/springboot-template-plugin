package service;

import enums.Choice;
import enums.Templates;
import helper.*;
import model.BasicProjectDetails;
import org.apache.maven.plugin.MojoExecutionException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static constants.PluginConstants.*;


public class ProjectService {

    private static final String TEST = "/test";
    private static final String POM = "pom.xml";

    private final DockerfileCreator dockerfileCreator = new DockerfileCreator();


    public void createProjectStructure(BasicProjectDetails basicProjectDetails, Templates template, Choice createDockerFile) throws MojoExecutionException {
        var projectPath = Path.of(basicProjectDetails.projectName());

        if (Files.exists(projectPath)) {
            throw new MojoExecutionException("Project '" + basicProjectDetails.projectName() + "' already exists!");
        }

        var packagePath = basicProjectDetails.packageName().replace('.', '/');

        try {
            var javaPath = projectPath.resolve(SRC + MAIN + JAVA + FWD_SLASH + packagePath);
            var resourcesPath = projectPath.resolve(SRC + MAIN + RESOURCES);
            var testPath = projectPath.resolve(SRC + TEST + JAVA + FWD_SLASH + packagePath);

            Files.createDirectories(javaPath);
            Files.createDirectories(resourcesPath);
            Files.createDirectories(testPath);

            var pomFile = projectPath.resolve(POM);
            PomCreator.getInstance().parsePomTemplates(basicProjectDetails, pomFile, template);
            ConfigCreator.getInstance().createApplicationConfigFile(basicProjectDetails, projectPath, template);
            ClassCreator.getInstance().generateMainClass(basicProjectDetails, javaPath);
            PackageCreator.getInstance().createFolderStructure(javaPath);

            if (createDockerFile.equals(Choice.Y)) {
                dockerfileCreator.createDockerfile(projectPath, basicProjectDetails);
            }

        } catch (IOException e) {
            throw new MojoExecutionException("Error creating project structure", e);
        }
    }
}
