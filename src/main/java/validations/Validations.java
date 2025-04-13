package validations;

import constants.ValidationConstants;
import model.BasicProjectDetails;
import org.apache.maven.plugin.MojoExecutionException;

import java.util.Objects;

public class Validations {

    private Validations() {
    }

    public static Validations getInstance() {
        return new Validations();
    }

    public void validate(BasicProjectDetails details) throws MojoExecutionException {
        validateMandatoryParams(details);

        var javaValidation = validateJavaVersion(details.javaVersion());
        if (!javaValidation) {
            throw new MojoExecutionException("This Java Version Is Not Allowed");
        }
    }

    private boolean validateJavaVersion(String javaVersion) {
        return ValidationConstants.ALLOWED_JAVA_VERSIONS.contains(javaVersion);
    }

    private void validateMandatoryParams(BasicProjectDetails details) throws MojoExecutionException {
        if (Objects.isNull(details.groupId()) || details.groupId().isBlank()) {
            throw new MojoExecutionException("Group ID is required");
        }

        if (Objects.isNull(details.projectName()) || details.projectName().isBlank()) {
            throw new MojoExecutionException("Project Name is required");
        }

        if (Objects.isNull(details.artifactId()) || details.artifactId().isBlank()) {
            throw new MojoExecutionException("Artifact ID is required");
        }
    }
}
