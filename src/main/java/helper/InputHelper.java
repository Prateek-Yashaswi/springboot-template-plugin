package helper;

import enums.Choice;
import enums.ConfigurationType;
import enums.Templates;
import org.apache.maven.plugin.MojoExecutionException;

import java.io.Console;
import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

public class InputHelper {

    private static final String DEFAULT_PACKAGE_NAME = "com.example";
    private static final String DEFAULT_SPRING_VERSION = "3.1.0";
    private static final String DEFAULT_JAVA_VERSION = "17";

    public String getProjectName(Console lineReader, String projectName) {
        if (Objects.isNull(projectName) || projectName.isBlank()) {
            return lineReader.readLine("Enter Project Name (required): ");
        }

        return projectName;
    }

    public String getGroupId(Console lineReader, String groupId) {
        if (Objects.isNull(groupId) || groupId.isBlank()) {
            return lineReader.readLine("Enter Group ID (required): ");
        }

        return groupId;
    }

    public String getArtifactId(Console lineReader, String artifactId) {
        if (Objects.isNull(artifactId) || artifactId.isBlank()) {
            return lineReader.readLine("Enter Artifact ID (required): ");
        }

        return artifactId;
    }

    public String getPackageName(Console lineReader, String packageName) {
        if (Objects.isNull(packageName) || packageName.isBlank()) {
            packageName = lineReader.readLine("Enter Package Name (default = " + DEFAULT_PACKAGE_NAME + "): ");

            if (packageName.isBlank()) {
                return DEFAULT_PACKAGE_NAME;
            }
        }

        return packageName;
    }

    public String getSpringVersion(Console lineReader, String springVersion) {
        if (Objects.isNull(springVersion) || springVersion.isBlank()) {
            springVersion = lineReader.readLine("Enter Spring Version (default = " + DEFAULT_SPRING_VERSION + "): ");

            if (springVersion.isBlank()) {
                return DEFAULT_SPRING_VERSION;
            }
        }

        return springVersion;
    }

    public String getJavaVersion(Console lineReader, String javaVersion) {
        if (Objects.isNull(javaVersion) || javaVersion.isBlank()) {
            javaVersion = lineReader.readLine("Enter Java Version (default = " + DEFAULT_JAVA_VERSION + "): ");

            if (javaVersion.isBlank()) {
                return DEFAULT_JAVA_VERSION;
            }
        }

        return javaVersion;
    }

    public ConfigurationType getConfigurationType(Console lineReader, ConfigurationType configurationType) {
        if (Objects.isNull(configurationType)) {
            var configurationInput = lineReader.readLine("Choose config type (YAML/PROPERTIES) [default: YAML]: ");
            configurationType = configurationInput.equalsIgnoreCase("PROPERTIES") ? ConfigurationType.PROPERTIES : ConfigurationType.YAML;
        }

        return configurationType;
    }

    public Choice getDockerChoice(Console lineReader, Choice dockerChoice) {
        if (Objects.isNull(dockerChoice)) {
            var dockerChoiceInput = lineReader.readLine("Create Dockerfile? (Y/N) [default: N]: ");
            dockerChoice = dockerChoiceInput.equalsIgnoreCase("Y") ? Choice.Y : Choice.N;
        }

        return dockerChoice;
    }

    public Choice getSwaggerChoice(Console lineReader, Choice swaggerChoice) {
        if (Objects.isNull(swaggerChoice)) {
            var swaggerChoiceInput = lineReader.readLine("Include Swagger? (Y/N) [default: N]: ");
            swaggerChoice = swaggerChoiceInput.equalsIgnoreCase("Y") ? Choice.Y : Choice.N;
        }

        return swaggerChoice;
    }

    public Set<Templates> getAndParseTemplates(Console lineReader, String templateInput) throws MojoExecutionException {
        if (Objects.isNull(templateInput) || templateInput.isBlank()) {
            templateInput = lineReader.readLine("Enter Templates (comma separated, WEB template is added by default): ");

            if (Objects.isNull(templateInput) || templateInput.isBlank()) {
                System.out.println("Using Default Templates");
                return EnumSet.of(Templates.WEB);
            }
        }

        var selectedTemplates = EnumSet.noneOf(Templates.class);
        selectedTemplates.add(Templates.WEB);
        var selectedInputs = templateInput.trim().split(",");

        for (var input : selectedInputs) {
            try {
                selectedTemplates.add(Templates.valueOf(input.trim().toUpperCase()));
            } catch (IllegalArgumentException e) {
                throw new MojoExecutionException("Invalid Template Name: " + input);
            }
        }

        if (selectedTemplates.isEmpty()) {
            System.out.println("No valid templates selected. Using WEB.");
            return EnumSet.of(Templates.WEB);
        }

        return selectedTemplates;
    }
}
