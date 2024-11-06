import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Loads sculpture data from a file and stores it in a list of Sculpture objects.
 */
public class SculptureManager implements SculptureTemplate {
    private ArrayList<Sculpture> sculptures;

    /**
     * Constructs a SculptureManager instance and loads data from the specified file.
     */
    public SculptureManager() {
        sculptures  = loadSculptures(FILE_NAME); // Load a file from the FILE_NAME template
    }

    /**
     * Reads data from a file and converts it into a list of Sculpture objects.
     *
     * @param filePath the path to the file containing sculpture data
     * @return a list of Sculpture objects
     */
    private ArrayList<Sculpture> loadSculptures(String filePath) 
    {
        ArrayList<Sculpture> sculptureList  = new ArrayList<Sculpture>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line = null;

            while ((line = br.readLine()) != null) 
            {
                String[] fields = line.split(DELIMITER); // Split the string into arrays based on delimiter string
                
                // Store them into the Sculpture class
                int fid = Integer.parseInt(fields[0]);
                String title = fields[1];
                String location = fields[2];
                String artist = fields[3];
                String material = fields[4];
                sculptureList.add(new Sculpture(fid, title, location, artist, material));

                System.out.println(line); // debugger to see if File Reader read correctly
            }
        } 
        catch (IOException e) 
        {
            System.err.println(e.getMessage());
            return null; // return nothing if catch Exception
        }
        return sculptureList;
    }

    // Tester for the SculptureManager class
    public static void main(String[] args) {
        SculptureManager loader = new SculptureManager(); // load the txt file 
    }      
}
