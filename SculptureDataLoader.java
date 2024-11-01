import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SculptureDataLoader {
    private ArrayList<Sculpture> sculptures;

    // Constructor that loads sculptures from the given file
    public SculptureDataLoader(String filePath) {
        sculptures = new ArrayList<>();
        loadSculptures(filePath);
    }

    private void loadSculptures(String filePath) 
    {
        try {
            String line = null;
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            while ((line = reader.readLine()) != null) 
            {
                // Split the whole String based on "," and empty space character
                String[] data = line.split(",\\s*");
                
                // if the data has 5 arrays in it, that is one tuple already, store it
                if (data.length == 5) 
                {
                    int fid = Integer.parseInt(data[0].trim());
                    String title = data[1].trim();
                    String location = data[2].trim();
                    String artist = data[3].trim();
                    String material = data[4].trim();
                    
                    Sculpture sculpture = new Sculpture(fid, title, location, artist, material);
                    sculptures.add(sculpture);
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    // Getter for sculptures ArrayList
    public ArrayList<Sculpture> getSculptures() {
        return sculptures;
    }
}
