package org.teresadev.jobportal.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUploadUtil {

    public static void saveFile(String UploadDir, String filename, MultipartFile file) throws IOException {

        Path uploadDirPath = Paths.get(UploadDir);
        if (!Files.exists(uploadDirPath)) {
            Files.createDirectories(uploadDirPath);
        }

        try (InputStream inputStream = file.getInputStream()) {

            Path path = uploadDirPath.resolve(filename);
            System.out.println("FilePath: " + path.toString());
            System.out.println("Filename: " + filename);

            Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException ioe) {
            throw new IOException("Could not save image file" + filename, ioe);
        }
    }
}
