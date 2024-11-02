import javax.swing.RowFilter;

public class MyRowFilter extends RowFilter {
    private String searchText;

    public MyRowFilter (String searchText) {
        this.searchText = searchText.toLowerCase();
    }

    @Override
    public boolean include(Entry entry) {
        // Iterate through all the column
        for (int i = 0; i < entry.getValueCount(); i++) 
        {
            String cellValue = entry.getStringValue(i).toLowerCase();
            // Check if the cell value contains the search text
            if (cellValue.contains(searchText)) 
            {
                return true; // Include row if any cell contains the search text
            }
        }
        return false; // Exclude row if no cells match
        
        // return entry.getStringValue(0).indexOf(searchText) >= 0; // Search the first column only
    }
    
}
