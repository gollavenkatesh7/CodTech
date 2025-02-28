import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * A utility class to demonstrate file handling operations in Java.
 * This program can read, write, and modify text files.
 */
public class FileHandler {
    
    private static final String FILE_PATH = "sample.txt";
    
    public static void main(String[] args) {
        try {
            // Writing to a file
            writeToFile("This is a sample text file.\nWelcome to Java file handling.\n");
            
            // Reading from a file
            System.out.println("File Content:");
            readFile();
            
            // Modifying a file
            modifyFile("Welcome", "Hello");
            
            // Reading after modification
            System.out.println("\nFile Content After Modification:");
            readFile();
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
    
    /**
     * Writes content to the specified file.
     * @param content The text to write.
     */
    public static void writeToFile(String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write(content);
            System.out.println("Data written to file successfully.");
        }
    }
    
    /**
     * Reads and displays the content of the specified file.
     */
    public static void readFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
    
    /**
     * Modifies the content of the file by replacing a target word with a new word.
     * @param target The word to be replaced.
     * @param replacement The new word.
     */
    public static void modifyFile(String target, String replacement) throws IOException {
        Path path = Paths.get(FILE_PATH);
        List<String> lines = new ArrayList<>(Files.readAllLines(path));
        
        for (int i = 0; i < lines.size(); i++) {
            lines.set(i, lines.get(i).replace(target, replacement));
        }
        
        Files.write(path, lines);
        System.out.println("File modified successfully.");
    }
}
