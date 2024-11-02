import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*; // Probably needed it if u need to add a button to handle Event

/**
 * 
 */
public class SculptureViewerUI extends JFrame implements SculptureTemplate {
    private JFrame viewFrame;
    private JTable sculptureTable;
    private DefaultTableModel defTableModel; // Need this thing for TableRowSorter
    private JTextField searchTextField;
    private TableRowSorter<DefaultTableModel> rowSorter;

    /**
     * 
     * @param data
     */
    public SculptureViewerUI(Object[][] data) {
        // Set up JFrame 
        viewFrame = new JFrame("Sculpture Art Data Viewer");
        JFrame.setDefaultLookAndFeelDecorated(true);
        
        // Set up JTable and DefaultTableModel to fill up the Table content
        defTableModel = new DefaultTableModel(data, FIELD_LABELS); // Parameter is Double Array Object for the Rows and Array Object for the Column
        sculptureTable = new JTable(defTableModel);

        // TableRowSorter for Sorting each attributes
        rowSorter = new TableRowSorter<>(defTableModel); // the parameter is a DefaultTableModel object
        sculptureTable.setRowSorter(rowSorter);

        // Filter field for filtering rows based on text input
        searchTextField = new JTextField();
        add(searchTextField, BorderLayout.NORTH); // put the search field up north of the table
        searchTextField.setColumns(10);
        searchTextField.setToolTipText("Type to search any information from every column");

        // Add DocumentListener to update the filter in real-time
        searchTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateFilter();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateFilter();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateFilter();
            }

            private void updateFilter() {
                String searchText = searchTextField.getText();
                rowSorter.setRowFilter(new MyRowFilter(searchText)); // Set new filter with updated text
            }
        });

        // Add components to frame
        add(new JScrollPane(sculptureTable), BorderLayout.CENTER);

        // Set up for JFrame after the frame has been set up
        viewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400); // Using viewFrame.setSize() cause the screen to be minimized at first. Dont know why
        setVisible(true);
    }
}
