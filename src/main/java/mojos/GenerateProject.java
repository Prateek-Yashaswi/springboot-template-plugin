package mojos;


import enums.Choice;
import enums.ConfigurationType;
import enums.Templates;
import model.AdditionalProjectDetails;
import model.BasicProjectDetails;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import service.ProjectService;
import validations.Validations;

@SuppressWarnings("unused")
@Mojo(name = "generate", defaultPhase = LifecyclePhase.INSTALL, requiresProject = false)
public class GenerateProject extends AbstractMojo {

    @Parameter(property = "ProjectName", required = true)
    private String projectName;

    @Parameter(property = "GroupId", required = true)
    private String groupId;

    @Parameter(property = "ArtifactId", required = true)
    private String artifactId;

    @Parameter(property = "Template", defaultValue = "DEFAULT")
    private Templates template;

    @Parameter(property = "SpringVersion", defaultValue = "3.1.0")
    private String springVersion;

    @Parameter(property = "PackageName", defaultValue = "com.example")
    private String packageName;

    @Parameter(property = "JavaVersion", defaultValue = "17")
    private String javaVersion;

    @Parameter(property = "ConfigurationType", defaultValue = "YAML")
    private ConfigurationType configurationType;

    @Parameter(property = "CreateDockerfile", defaultValue = "N")
    private Choice createDockerfile;

    @Parameter(property = "CreateSwagger", defaultValue = "N")
    private Choice createSwagger;

    private final ProjectService projectService = new ProjectService();

    @Override
    public void execute() throws MojoExecutionException {

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
