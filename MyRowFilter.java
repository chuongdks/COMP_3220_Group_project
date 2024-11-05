import javax.swing.RowFilter;

/**
 * Custom row filter for filtering table rows based on a search text.
 * This filter checks each cell in a row to see if any cell contains the specified
 * search text. If a match is found, the row is included in the results.
 */
public class MyRowFilter extends RowFilter {
    private String searchText;

    /**
     * Constructs a new MyRowFilter with the specified search text.
     *
     * @param searchText the text to search for within each row's cells
     */
    public MyRowFilter (String searchText) {
        this.searchText = searchText.toLowerCase();
    }

    /**
     * Determines whether a row should be included based on the search text.
     * Iterates through all cells in a row and includes the row if any cell
     * contains the search text (case-insensitive).
     *
     * @param entry the entry representing the row to be checked
     * @return true if the row contains the search text in any cell, false otherwise
     */
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
