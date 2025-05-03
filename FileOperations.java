import java.io.*;
import java.util.*;

public class FileOperations {

    // Path to the text file
    static final String FILE_PATH = "sample.txt";

    public static void main(String[] args) {
        try {
            // 1. Write to the file
            writeFile("This is the first line.\nThis is the second line.");

            // 2. Read from the file
            System.out.println("Contents of the file:");
            readFile();

            // 3. Modify the file (e.g., replace a line)
            modifyFile("second", "modified second");

            // 4. Read again to verify modification
            System.out.println("\nContents after modification:");
            readFile();

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    /**
     * Writes the given content to the text file.
     * If the file exists, it will be overwritten.
     */
    public static void writeFile(String content) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));
        writer.write(content);
        writer.close();
    }

    /**
     * Reads the contents of the file line by line and prints to console.
     */
    public static void readFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
    }

    /**
     * Modifies the file by replacing any line containing a specific word
     * with a modified version.
     */
    public static void modifyFile(String search, String replacement) throws IOException {
        File inputFile = new File(FILE_PATH);
        File tempFile = new File("temp.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String line;
        while ((line = reader.readLine()) != null) {
            if (line.contains(search)) {
                line = line.replace(search, replacement);
            }
            writer.write(line + System.lineSeparator());
        }

        reader.close();
        writer.close();

        // Replace original file with the temp file
        if (!inputFile.delete()) {
            System.out.println("Could not delete original file");
            return;
        }
        if (!tempFile.renameTo(inputFile)) {
            System.out.println("Could not rename temp file");
        }
    }
}
