import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Provides a graphical user interface for viewing and filtering sculpture data.
 */
public class SculptureViewerUI extends JFrame implements SculptureTemplate {
    private JFrame viewFrame;
    private JTable sculptureTable;
    private DefaultTableModel defTableModel; // Need this thing for TableRowSorter
    private JTextField searchTextField;
    private TableRowSorter<DefaultTableModel> rowSorter;

    // Input fields and button to add a row
    private JTextField textFieldFID;
    private JTextField textFieldTitle;
    private JTextField textFieldLocation;
    private JTextField textFieldArtist;
    private JTextField textFieldMaterial;
    private JButton addButton;

    /**
     * Constructs a new SculptureViewerUI instance with the given data to display in the table.
     *
     * @param data a 2D array containing sculpture data to populate the table
     */
    public SculptureViewerUI(Object[][] data) {
/*
 * This part is for Setting up JFrame, JTable and add a TableRowSorter
 */
        // Set up JFrame 
        viewFrame = new JFrame("Sculpture Art Data Viewer");
        JFrame.setDefaultLookAndFeelDecorated(true);
        
        // Set up JTable and DefaultTableModel to fill up the Table content
        defTableModel = new DefaultTableModel(data, FIELD_LABELS); // Parameter is Double Array Object for the Rows and Array Object for the Column
        sculptureTable = new JTable(defTableModel);
        
        // TableRowSorter for Sorting each attributes
        rowSorter = new TableRowSorter<>(defTableModel); // the parameter is a DefaultTableModel object
        sculptureTable.setRowSorter(rowSorter);

/*
 * This part is for the Search Bar
 */
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

/*
 * This part is for the Update Table Panel
 */
        // Set up input fields and button for adding new rows
        JPanel inputPanel = new JPanel(new GridLayout(6, 2)); 

        textFieldFID = new JTextField();
        textFieldTitle = new JTextField();
        textFieldLocation = new JTextField();
        textFieldArtist = new JTextField();
        textFieldMaterial = new JTextField();
        addButton = new JButton("Add");

        inputPanel.add(new JLabel("FID"));
        inputPanel.add(textFieldFID);
        inputPanel.add(new JLabel("Title"));
        inputPanel.add(textFieldTitle);
        inputPanel.add(new JLabel("Location"));
        inputPanel.add(textFieldLocation);
        inputPanel.add(new JLabel("Artist"));
        inputPanel.add(textFieldArtist);
        inputPanel.add(new JLabel("Material"));
        inputPanel.add(textFieldMaterial);
        inputPanel.add(new JLabel()); // Empty cell for spacing
        inputPanel.add(addButton);

        add(inputPanel, BorderLayout.WEST);
        add(new JScrollPane(sculptureTable));

        // Action listener for the Add button to insert data from text fields into the table
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addRowToTable();
            }
        });
/*
 * Set up for JFrame after all is done. Must be put at bottom dont know why
 */
        // Set up for JFrame after the frame has been set up
        viewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400); // Using viewFrame.setSize() cause the screen to be minimized at first. Dont know why
        setVisible(true);
    }

    // addRowToTable method. Gonna put this in a differnt file
    private void addRowToTable() 
    {
        // Get the data from text fields
        String fid = textFieldFID.getText();
        String title = textFieldTitle.getText();
        String location = textFieldLocation.getText();
        String artist = textFieldArtist.getText();
        String material = textFieldMaterial.getText();

        // Add the data as a new row in the table model
        defTableModel.addRow(new Object[]{fid, title, location, artist, material});

        // Clear the text fields after adding
        textFieldFID.setText("");
        textFieldTitle.setText("");
        textFieldLocation.setText("");
        textFieldArtist.setText("");
        textFieldMaterial.setText("");
    }

    // Tester for the SculptureViewerUI class. Uncomment for debugging
    public static void main(String[] args) {
        // Sample data array representing sculptures
        Object[][] data = {
            {0, "R.H. Herb Gray Bust"   , "Dieppe Gardens"  , "Unknown"         , "Bronze",         "1"   },
            {1, "Dieppe Gardens Plaque" , "Jackson Park"    , "Leo Rosenthal"   , "Painted Steel",  "2"   },
            {2, "Eternal Flame"         , "City Hall"       , "Jane Sculpture"  , "Granite",        "3"   }
        };

        // Instantiate the SculptureViewerUI with sample data
        new SculptureViewerUI(data);
    }
}
