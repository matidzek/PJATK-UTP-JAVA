package zad3;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.regex.Pattern;

import static java.nio.file.FileVisitResult.CONTINUE;

public class Futil {
    private static final String regex = ".*\\.txt$";
    private static final Pattern pattern = Pattern.compile(regex);
    private static final String inputEncoding = "Cp1250";
    private static final String outputEncoding = "UTF-8";

    public static void processDir(String dirName, String resultFileName) {
        Path startPath = Paths.get(dirName);
        Path resultPath = Paths.get(resultFileName);
        try (BufferedWriter out = Files.newBufferedWriter(resultPath, Charset.forName(outputEncoding))) {
            Files.walkFileTree(startPath, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (Files.isSameFile(file, resultPath)) {
                        return CONTINUE;
                    }
                    if (pattern.matcher(file.getFileName().toString()).matches()) {
                        System.out.println(file);
                        try (BufferedReader in = Files.newBufferedReader(file, Charset.forName(inputEncoding))) {
                            String line;
                            while ((line = in.readLine()) != null) {
                                out.write(line);
                                out.newLine();
                            }
                        }
                    }
                    return CONTINUE;
                }
                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) {
                    return CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}