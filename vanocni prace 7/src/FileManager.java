import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * this class reads the words from the file
 */
public class FileManager {
    private File file;


    public ArrayList<String> toArrayList() {
        ArrayList<String> list = new ArrayList<>();

        if (file == null) {
            throw new IllegalArgumentException("File is null");
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split("\\s+");
                list.addAll(Arrays.asList(words));
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + e.getMessage(), e);
        }

        return list;
    }

    public FileManager(File file) {
        this.file = file;
    }
}