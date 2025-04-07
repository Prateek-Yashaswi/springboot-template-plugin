package helper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PackageCreator {

    private PackageCreator() {
    }

    public static PackageCreator getInstance() {
        return new PackageCreator();
    }

    private static final String[] folders = {
            "controllers",
            "services",
            "implementations",
            "models",
            "entities",
            "config"
    };

    public void createFolderStructure(Path path) throws IOException {
        for (String folder : folders) {
            var folderPath = path.resolve(folder);
            Files.createDirectories(folderPath);
        }
    }
}
