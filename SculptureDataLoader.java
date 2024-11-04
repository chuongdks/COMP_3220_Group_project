import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Loads sculpture data from a file and stores it in a list of Sculpture objects.
 */
public class SculptureDataLoader implements SculptureTemplate {
    private ArrayList<Sculpture> sculptures;

    /**
     * Constructs a SculptureDataLoader instance and loads data from the specified file.
     */
    public SculptureDataLoader() {
        sculptures  = loadSculptures(FILE_NAME);
    }

    /**
     * Reads data from a file and converts it into a list of Sculpture objects.
     *
     * @param filePath the path to the file containing sculpture data
     * @return a list of Sculpture objects
     */
    private ArrayList<Sculpture> loadSculptures(String filePath) 
    {
        ArrayList<Sculpture> sculptureList  = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line = null;

            while ((line = br.readLine()) != null) 
            {
                String[] fields = line.split(DELIMITER);

                int fid = Integer.parseInt(fields[0]);
                String title = fields[1];
                String location = fields[2];
                String artist = fields[3];
                String material = fields[4];
                sculptureList.add(new Sculpture(fid, title, location, artist, material));

                System.out.println(line); // debugger for FileReader 
            }
        } 
        catch (IOException e) 
        {
            System.err.println(e.getMessage());
            return null; // return nothing if catch Exception
        }
        return sculptureList;
    }
    
    /**
     * Converts the list of Sculpture objects into a 2D array of Object for use in JTable.
     *
     * @return a 2D array containing sculpture data
     */
    public Object[][] getSculpturesData() 
    {
        Object[][] data = new Object[sculptures.size()][NUMBER_OF_FIELDS];
        for (int i = 0; i < sculptures.size(); i++) 
        {
            Sculpture sculpture = sculptures.get(i);
            data[i][0] = sculpture.getFid();
            data[i][1] = sculpture.getTitle();
            data[i][2] = sculpture.getLocation();
            data[i][3] = sculpture.getArtist();
            data[i][4] = sculpture.getMaterial();
        }
        return data;
    }

    /**
     * Gets the list of Sculpture objects.
     *
     * @return the list of sculptures
     */
    public ArrayList<Sculpture> getSculptures() 
    {
        return sculptures;
    }
}
