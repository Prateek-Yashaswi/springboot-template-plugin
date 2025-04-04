package mojos;


import enums.ConfigurationType;
import enums.Templates;
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

    @Parameter(property = "projectName", required = true)
    private String projectName;

    @Parameter(property = "groupId", required = true)
    private String groupId;

    @Parameter(property = "artifactId", required = true)
    private String artifactId;

    @Parameter(property = "template", defaultValue = "DEFAULT")
    private Templates template;

    @Parameter(property = "springVersion", defaultValue = "3.1.0")
    private String springVersion;

    @Parameter(property = "packageName", defaultValue = "com.example")
    private String packageName;

    @Parameter(property = "javaVersion", defaultValue = "17")
    private String javaVersion;

    @Parameter(property = "configurationType", defaultValue = "YAML")
    private ConfigurationType configurationType;

    private final ProjectService projectService = new ProjectService();

    @Override
    public void execute() throws MojoExecutionException {

        BasicProjectDetails basicProjectDetails = new BasicProjectDetails(springVersion, projectName, groupId, artifactId, packageName, javaVersion, configurationType);
        Validations.getInstance().validate(basicProjectDetails);

        getLog().info("Generating Spring Boot Project: " + projectName);
        getLog().info("Using Template: " + template);
        getLog().info("Group ID: " + groupId);
        getLog().info("Artifact ID: " + artifactId);
        getLog().info("Spring Boot Version: " + springVersion);
        getLog().info("Package Name: " + packageName);
        getLog().info("Java Version: " + javaVersion);
        getLog().info("Generating application config file as: application." + configurationType.getExtension());

        projectService.createProjectStructure(basicProjectDetails, template);
    }
}
