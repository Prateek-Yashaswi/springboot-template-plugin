package mojos;

import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static enums.Choice.N;
import static enums.Choice.Y;
import static enums.ConfigurationType.PROPERTIES;
import static enums.ConfigurationType.YAML;
import static enums.Templates.DEFAULT;
import static junit.framework.Assert.*;

class GenerateProjectTest {

    private static final String PROJECT_NAME = "test-project";
    private static final String PACKAGE_NAME = "test.package";
    private static final File SRC_DIR = new File(PROJECT_NAME, "/src");
    private static final File DOCKERFILE_PATH = new File(PROJECT_NAME, "/Dockerfile");
    private static final File POM_PATH = new File(PROJECT_NAME, "/pom.xml");
    private static final File MAIN_DIR = new File(PROJECT_NAME, "/src/main");
    private static final File TEST_DIR = new File(PROJECT_NAME, "/src/test");
    private static final File JAVA_DIR = new File(PROJECT_NAME, "/src/main/java");
    private static final File PACKAGE_DIR = new File(PROJECT_NAME ,"/src/main/java/" + PACKAGE_NAME.replace(".", "/"));
    private static final File PROPERTIES_CONFIG = new File(PROJECT_NAME,"/src/main/resources/application.properties");
    private static final File PROPERTIES_YAML = new File(PROJECT_NAME, "/src/main/resources/application.yaml");


    private GenerateProject mojo;

    @BeforeEach
    void setup() {
        mojo = new GenerateProject();
    }

    @AfterEach
    void cleanup() throws IOException {
        File generatedDir = new File(PROJECT_NAME);
        if (generatedDir.exists()) {
            FileUtils.deleteDirectory(generatedDir);
            System.out.println("✅ Cleaned up: " + generatedDir.getPath());
        }
    }

    @Test
    void generateProjectWithAllParameters() throws MojoExecutionException {

        setField(mojo, "projectName", PROJECT_NAME);
        setField(mojo, "groupId", "com.example");
        setField(mojo, "artifactId", "demo");
        setField(mojo, "springVersion", "3.1.0");
        setField(mojo, "packageName", PACKAGE_NAME);
        setField(mojo, "javaVersion", "17");
        setField(mojo, "template", DEFAULT);
        setField(mojo, "configurationType", YAML);
        setField(mojo, "createDockerfile", Y);
        setField(mojo, "createSwagger", Y);

        mojo.execute();

        File expectedDir = new File(PROJECT_NAME);
        assertTrue("Expected project directory was not created", expectedDir.exists() && expectedDir.isDirectory());
        assertTrue("/src folder wasn't created", SRC_DIR.exists());
        assertTrue("/dockerfile wasn't created", DOCKERFILE_PATH.exists());
        assertTrue("/srv/main folder wasn't created", MAIN_DIR.exists());
        assertTrue("/src/test folder wasn't created", TEST_DIR.exists());
        assertTrue("/pom wasn't created", POM_PATH.exists());
        assertTrue("/main/java/package wasn't created", PACKAGE_DIR.exists());
        assertTrue("/main/java wasn't created", JAVA_DIR.exists());
        assertEquals("Unexpected file count inside /main/java/package", 7, Objects.requireNonNull(PACKAGE_DIR.listFiles()).length);
        assertTrue("YAML Config doesn't exist", PROPERTIES_YAML.exists());
    }

    @Test
    void generateProjectWithoutDockerfile() throws MojoExecutionException {
        setField(mojo, "projectName", PROJECT_NAME);
        setField(mojo, "groupId", "com.example");
        setField(mojo, "artifactId", "demo");
        setField(mojo, "springVersion", "3.1.0");
        setField(mojo, "packageName", PACKAGE_NAME);
        setField(mojo, "javaVersion", "17");
        setField(mojo, "template", DEFAULT);
        setField(mojo, "configurationType", YAML);
        setField(mojo, "createDockerfile", N);
        setField(mojo, "createSwagger", Y);

        mojo.execute();

        assertTrue("Project root was not created", new File(PROJECT_NAME).exists());
        assertFalse("Dockerfile should not be created", DOCKERFILE_PATH.exists());
        assertTrue("YAML Config doesn't exist", PROPERTIES_YAML.exists());
    }

    @Test
    void generateProjectWithPropertiesConfig() throws MojoExecutionException {
        setField(mojo, "projectName", PROJECT_NAME);
        setField(mojo, "groupId", "com.example");
        setField(mojo, "artifactId", "demo");
        setField(mojo, "springVersion", "3.1.0");
        setField(mojo, "packageName", PACKAGE_NAME);
        setField(mojo, "javaVersion", "17");
        setField(mojo, "template", DEFAULT);
        setField(mojo, "configurationType", PROPERTIES);
        setField(mojo, "createDockerfile", N);
        setField(mojo, "createSwagger", Y);

        mojo.execute();

        assertTrue("application.properties was not created", PROPERTIES_CONFIG.exists());
    }

    @Test
    void generateProjectShouldCreateExpectedJavaFiles() throws MojoExecutionException {
        setField(mojo, "projectName", PROJECT_NAME);
        setField(mojo, "groupId", "com.example");
        setField(mojo, "artifactId", "demo");
        setField(mojo, "springVersion", "3.1.0");
        setField(mojo, "packageName", PACKAGE_NAME);
        setField(mojo, "javaVersion", "17");
        setField(mojo, "template", DEFAULT);
        setField(mojo, "configurationType", YAML);
        setField(mojo, "createDockerfile", Y);
        setField(mojo, "createSwagger", Y);

        mojo.execute();

        List<String> foldersInsidePackage = List.of("config", "controllers", "entities", "implementations", "models", "services");
        foldersInsidePackage.forEach(item -> assertTrue(item + " doesn't exist", new File(PACKAGE_DIR, item).exists()));

        File mainAppFile = new File(PACKAGE_DIR, "TestProjectApplication.java");
        assertTrue("MainApplication.java should exist", mainAppFile.exists());
    }

    private void setField(Object target, String fieldName, Object value) {
        try {
            var field = target.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(target, value);
        } catch (Exception e) {
            throw new RuntimeException("Failed to set field " + fieldName, e);
        }
    }
}
