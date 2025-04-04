package validations;

import constants.ValidationConstants;
import model.BasicProjectDetails;
import org.apache.maven.plugin.MojoExecutionException;

public class Validations {

    private Validations() {
    }

    public static Validations getInstance() {
        return new Validations();
    }

    public void validate(BasicProjectDetails details) throws MojoExecutionException {
        var javaValidation = validateJavaVersion(details.javaVersion());

        if (!javaValidation) {
            throw new MojoExecutionException("This Java Version Is Not Allowed");
        }
    }

    private boolean validateJavaVersion(String javaVersion) {
        return ValidationConstants.ALLOWED_JAVA_VERSIONS.contains(javaVersion);
    }
}
