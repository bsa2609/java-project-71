package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {
    private static String readFileToString(Path path) throws Exception {
        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }

        return Files.readString(path).trim();
    }

    private static Path getPath(String filePath) {
        return Paths.get(filePath).toAbsolutePath().normalize();
    }

    private static Path getResourcesPath(String fileName) {
        return Paths.get("src", "test", "resources", fileName).toAbsolutePath().normalize();
    }

    public static String readResourceFile(String fileName) throws Exception {
        return readFileToString(getResourcesPath(fileName));
    }

    public static String readFile(String filePath) throws Exception {
        return readFileToString(getPath(filePath));
    }

    public static String determineExtensionFromFilePath(String filePath) {
        int lastIndexOfPoint = filePath.lastIndexOf('.');
        return filePath.substring(lastIndexOfPoint).trim().toLowerCase();
    }
}
