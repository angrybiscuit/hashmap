import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {
    public long[] readFile(String fileName) {
        String basePath = new File(fileName).getAbsolutePath();
        Scanner s = null;
        BufferedReader br = null;

        ArrayList<Long> arr = new ArrayList<>();

        try {
            br = new BufferedReader(new java.io.FileReader(basePath));
            s = new Scanner(new BufferedReader(new java.io.FileReader(basePath)));
            while (s.hasNextLine()) {
                arr.add(Long.parseLong(br.readLine()));
                s.nextLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            s.close();
        }
        return arr.stream().mapToLong(i -> i).toArray();
    }
}
