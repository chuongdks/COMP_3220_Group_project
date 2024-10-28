import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SculptureDataLoader{
    public List<Sculpture> loadSculptures(String fileName) {
        List<Sculpture> sculptures = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(", ");
                if (data.length == 5) {
                    try {
                        int fid = Integer.parseInt(data[0]);
                        String title = data[1];
                        String location = data[2];
                        String artist = data[3];
                        String material = data[4];
                        sculptures.add(new Sculpture(fid, title, location, artist, material));
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid FID format in line: " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading file: " + e.getMessage());
        }
        return sculptures;
    }
}
