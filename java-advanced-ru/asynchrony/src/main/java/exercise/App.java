package exercise;

import java.util.concurrent.CompletableFuture;
import java.util.Arrays;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.File;
import java.nio.file.StandardOpenOption;

class App {

    // BEGIN
    public static CompletableFuture<String> unionFiles(String filePath1, String filePath2, String resultFilePath) {
        CompletableFuture<String> firstFile = readFile(filePath1);

        CompletableFuture<String> secondFile = readFile(filePath2);

        return firstFile.thenCombine(secondFile, (file1, file2) -> {
            String resultContent = file1 + file2;
            Path resultPath = Paths.get(resultFilePath).toAbsolutePath();

            try {
                Files.writeString(resultPath, resultContent,
                        StandardOpenOption.CREATE, StandardOpenOption.WRITE);
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }

            return resultContent;

        }).exceptionally(ex -> {
            System.out.println("We have an exception - " + ex.getMessage());
            return null;
        });
    }

    private static CompletableFuture<String> readFile(String filePath) {
        return CompletableFuture.supplyAsync(() -> {
            Path path = Paths.get(filePath).toAbsolutePath();

            try {
                return Files.readString(path);
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        });
    }

    public static CompletableFuture<Long> getDirectorySize(String dir) {
        File directory = new File(dir);
        return CompletableFuture.supplyAsync(() -> countSize(directory));
    }

    private static long countSize(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();

            if (files == null) {
                return 0;
            }

            return Arrays.stream(files)
                    .parallel()
                    .mapToLong(App::countSize)
                    .sum();

        } else {
            try {
                return Files.size(file.toPath());
            } catch (Exception e) {
                return 0;
            }
        }
    }

    // END

    public static void main(String[] args) throws Exception {
        // BEGIN
        App.unionFiles(
                "src/main/resources/file1.txt",
                "src/main/resources/file2.txt",
                "src/main/resources/file3.txt"
        ).get();

        System.out.println(App.getDirectorySize("src/main/resources").get());
        // END
    }
}

