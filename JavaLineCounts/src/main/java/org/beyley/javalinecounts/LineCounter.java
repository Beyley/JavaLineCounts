package org.beyley.javalinecounts;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.List;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class LineCounter {
    public List<String> allowedExtensions = new ArrayList<String>();

    public static final List<String> javaExtensions = Arrays.asList(new String[] { "java" });
    public static final List<String> cExtensions = Arrays.asList(new String[] { "c", "h" });

    public String directory;
    public long lines;

    private boolean isDone = false;

    public LineCounter(String directory) {
        this.directory = directory;
    }

    public void start() {
        if (isDone)
            throw new IllegalStateException("Cannot start a line counter while its finished!");

        // Reading the folder and getting Stream.
        try (Stream<Path> walk = Files.walk(Paths.get(directory))) {
            // Filtering the paths by a regular file and adding into a list.
            List<String> fileNamesList = walk.filter(Files::isRegularFile).map(x -> x.toString())
                    .collect(Collectors.toList());

            // printing the file nams
            for (String file : fileNamesList) {
                boolean allowed = false;
                for (String extension : allowedExtensions)
                    if (file.endsWith(extension))
                        allowed = true;

                if (allowed)
                    countFile(file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void countFile(String filePath) throws FileNotFoundException {
        File myObj = new File(filePath);
        Scanner fileReader = new Scanner(myObj);

        while (fileReader.hasNextLine()) {
            String currentLine = fileReader.nextLine();

            if (currentLine.strip().length() == 0)
                continue;

            lines++;

            // System.out.println(data);
        }

        fileReader.close();
    }
}
