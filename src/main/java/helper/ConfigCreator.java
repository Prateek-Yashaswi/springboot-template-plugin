package helper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static constants.PluginConstants.*;

public class ConfigCreator {

    private ConfigCreator() {
    }

    public static ConfigCreator getInstance() {
        return new ConfigCreator();
    }

    public void createApplicationConfigFile(String extension, Path projectPath) throws IOException {
        Path configFile = projectPath.resolve(SRC + MAIN + RESOURCES + "/application." + extension);

        // Ensure the directory exists (just in case)
        Files.createDirectories(configFile.getParent());

        // Create an empty file if it doesn't exist
        if (!Files.exists(configFile)) {
            Files.createFile(configFile);
        }
    }
}
