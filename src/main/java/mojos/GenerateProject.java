package mojos;


import enums.Choice;
import enums.ConfigurationType;
import enums.Templates;
import helper.InputHelper;
import model.AdditionalProjectDetails;
import model.BasicProjectDetails;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import service.ProjectService;
import validations.Validations;

import java.io.Console;

@SuppressWarnings("unused")
@Mojo(name = "generate", defaultPhase = LifecyclePhase.INSTALL, requiresProject = false)
public class GenerateProject extends AbstractMojo {

    @Parameter(property = "ProjectName")
    private String projectName;

    @Parameter(property = "GroupId")
    private String groupId;

    @Parameter(property = "ArtifactId")
    private String artifactId;

    @Parameter(property = "Template")
    private Templates template;

    @Parameter(property = "SpringVersion")
    private String springVersion;

    @Parameter(property = "PackageName")
    private String packageName;

    @Parameter(property = "JavaVersion")
    private String javaVersion;

    @Parameter(property = "ConfigurationType")
    private ConfigurationType configurationType;

    @Parameter(property = "CreateDockerfile")
    private Choice createDockerfile;

    @Parameter(property = "CreateSwagger")
    private Choice createSwagger;

    private final ProjectService projectService = new ProjectService();
    private final InputHelper inputHelper = new InputHelper();

    @Override
    public void execute() throws MojoExecutionException {

        Console reader = System.console();

        projectName = inputHelper.getProjectName(reader, projectName);
        template = inputHelper.getTemplate(reader, template);
        groupId = inputHelper.getGroupId(reader, groupId);
        artifactId = inputHelper.getArtifactId(reader, artifactId);
        springVersion = inputHelper.getSpringVersion(reader, springVersion);
        packageName = inputHelper.getPackageName(reader, packageName);
        javaVersion = inputHelper.getJavaVersion(reader, javaVersion);
        configurationType = inputHelper.getConfigurationType(reader, configurationType);
        createDockerfile = inputHelper.getDockerChoice(reader, createDockerfile);
        createSwagger = inputHelper.getSwaggerChoice(reader, createSwagger);

        process();
    }

    private void process() throws MojoExecutionException {
        BasicProjectDetails basicProjectDetails = new BasicProjectDetails(springVersion, projectName, groupId, artifactId, packageName, javaVersion, configurationType);
        AdditionalProjectDetails additionalProjectDetails = new AdditionalProjectDetails(createDockerfile, createSwagger);
        Validations.getInstance().validate(basicProjectDetails);

        getLog().info("Generating Spring Boot Project: " + projectName);
        getLog().info("Using Template: " + template);
        getLog().info("Group ID: " + groupId);
        getLog().info("Artifact ID: " + artifactId);
        getLog().info("Spring Boot Version: " + springVersion);
        getLog().info("Package Name: " + packageName);
        getLog().info("Java Version: " + javaVersion);
        getLog().info("Generating application config file as: application." + configurationType.getExtension());
        getLog().info("Generating Dockerfile: " + createDockerfile.getValue());
        getLog().info("Generating Swagger: " + createSwagger.getValue() + " | If Yes, Check Compatibility With Spring Boot Before Using");

        projectService.createProjectStructure(basicProjectDetails, template, additionalProjectDetails);
    }
}
