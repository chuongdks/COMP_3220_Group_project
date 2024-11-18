import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Loads sculpture data from a file and stores it in a list of Sculpture objects. 
 * 
 * Improvement idea: 
 * _ Create another copy of the .csv file when turn it to .txt instead of replacing it
 * _ 
 */
public class SculptureManager implements SculptureTemplate {
    private ArrayList<Sculpture> sculptures;

    /**
     * Constructs a SculptureManager instance and loads data from the specified file.
     */
    public SculptureManager() {
        // System.out.println(FILE_NAME); 
        String processedFileName = checkFileName(FILE_NAME);
        sculptures  = loadSculptures(processedFileName); // Load a file from the FILE_NAME template
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
                int rating= Integer.parseInt(fields[5]);
                sculptureList.add(new Sculpture(fid, title, location, artist, material, rating));

                // System.out.println(line); // debugger to see if File Reader read correctly
                
            }
            br.close();
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
            data[i][5] = sculpture.getRating();
        }
        return data;
    }

    /**
     * Check the file type. Only accept .csv and .txt file
     * @param filePath
     * @return
     */
    private String checkFileName(String filePath) 
    {
        File file = new File(filePath);
        if (filePath.endsWith(".csv")) 
        {
            // Rename the file to .txt
            String newFilePath = filePath.replace(".csv", ".txt");
            File newFile = new File(newFilePath); // cant rename with creating a new abstract file?
            file.renameTo(newFile);
            System.out.println("File renamed to: " + newFilePath);
            return newFilePath;
        } 
        else if (!filePath.endsWith(".txt")) 
        {
            System.err.println("Unsupported file type. Only .txt or .cvs files are allowed.");
            return null;
        }
        return filePath;
    }

    // // Tester for the SculptureManager class. Uncomment for debugging
    // public static void main(String[] args) {
    //     SculptureManager loader = new SculptureManager(); // load the txt file 
    //     Object[][] sculpturesData = loader.getSculpturesData();

    //     // Check if data was loaded successfully
    //     for (Object[] row : sculpturesData) 
    //     {
    //         for (Object field : row) 
    //         {
    //             System.out.print(field + " "); // Print the content in the row first
    //         }
    //         System.out.println(); // Newline after each sculpture row
    //     }

    // }      
}
