import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SculptureDataLoader implements SculptureTemplate {
    private Object[][] sculptureData;

    // Constructor that loads sculptures from the given file
    public SculptureDataLoader() {
        sculptureData  = loadSculptures(FILE_NAME);
    }

    private Object[][] loadSculptures(String filePath) 
    {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            ArrayList<String> list = new ArrayList<>();
            String line = null;

            while ((line = br.readLine()) != null) 
            {
                list.add(line);
                System.out.println(line); // debugger for FileReader 
            }
            Object[][] data = new Object[list.size()][NUMBER_OF_FIELDS];

            // I use the list Array (String) to Populating the data array (row)
            for (int i = 0; i < list.size(); i++)
            {
                data[i] = list.get(i).split(DELIMITER);
            }
            br.close();

            return data;

        } 
        catch (IOException e) 
        {
            System.err.println(e.getMessage());
            return null; // return nothing if catch Exception
        }
    }

    // Getter for sculptures data
    public Object[][] getSculpturesData() {

        return sculptureData;
    }
}
