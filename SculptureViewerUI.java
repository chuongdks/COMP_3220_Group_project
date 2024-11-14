import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Provides a graphical user interface for viewing and filtering sculpture data.
 */
public class SculptureViewerUI extends JFrame implements SculptureTemplate {
    private JFrame viewFrame;
    private JTable sculptureTable;
    private DefaultTableModel defTableModel; // Need this thing for TableRowSorter
    private JTextField searchTextField;
    private TableRowSorter<DefaultTableModel> rowSorter;

    // First Panel: Input fields and button to add a row
    private JTextField textFieldFID;
    private JTextField textFieldTitle;
    private JTextField textFieldLocation;
    private JTextField textFieldArtist;
    private JTextField textFieldMaterial;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;

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
 * This part is for the Display Panel (Table Name, Search bar, Table)
 */
        // Display Panel (Label, Search Bar, Table)
        JPanel displayPanel = new JPanel(new BorderLayout()); 
        
        // Title Label for the table
        JLabel titleLabel = new JLabel("SCULPTURE DATA TABLE", JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16)); // Set a larger font for the title

        // Filter field for filtering rows based on text input
        searchTextField = new JTextField();
        searchTextField.setColumns(10);
        searchTextField.setFont(new java.awt.Font("Segoe UI", 2, 12)); 
        searchTextField.setText("Search Here");
        searchTextField.setToolTipText("Type to search any information from every column");

        // Set up the Display Panel
        displayPanel.add(titleLabel, BorderLayout.NORTH);
        displayPanel.add(searchTextField, BorderLayout.CENTER);
        displayPanel.add(new JScrollPane(sculptureTable), BorderLayout.SOUTH);

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
 * This part is for the Add, Update and Delete Table Panel
 */
        // Set up input fields and button for adding new rows
        JPanel manipulationPanel = new JPanel(new GridLayout(0, 2)); 

        textFieldFID = new JTextField();
        textFieldTitle = new JTextField();
        textFieldLocation = new JTextField();
        textFieldArtist = new JTextField();
        textFieldMaterial = new JTextField();
        addButton = new JButton("Add");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");

        // Add components to the Panel
        manipulationPanel.add(new JLabel("FID"));
        manipulationPanel.add(textFieldFID);
        manipulationPanel.add(new JLabel("Title"));
        manipulationPanel.add(textFieldTitle);
        manipulationPanel.add(new JLabel("Location"));
        manipulationPanel.add(textFieldLocation);
        manipulationPanel.add(new JLabel("Artist"));
        manipulationPanel.add(textFieldArtist);
        manipulationPanel.add(new JLabel("Material"));
        manipulationPanel.add(textFieldMaterial);
        manipulationPanel.add(new JLabel());
        manipulationPanel.add(new JLabel());
        manipulationPanel.add(addButton);
        manipulationPanel.add(updateButton);
        manipulationPanel.add(deleteButton);

        // Action listener for the Add button to insert data from text fields into the table
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addRowToTable();
            }
        });
        // Add mouse listener to the table to capture row selection
        sculptureTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                displaySelectedRow();
            }
        });
        // Action listener for the Add button to insert data from text fields into the table
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateRowToTable();
            }
        });        
        // Action listener for the Add button to insert data from text fields into the table
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteRowToTable();
            }
        });        

/*
 * Display all the panels. Set up all the Layout
 */
        setLayout(new BorderLayout());
        add(displayPanel, BorderLayout.CENTER);
        add(manipulationPanel, BorderLayout.SOUTH);

/*
 * Set up for JFrame after all is done. Must be put at bottom dont know why
 */
        // Set up for JFrame after the frame has been set up
        viewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1080, 720); // Using viewFrame.setSize() cause the screen to be minimized at first. Dont know why
        setVisible(true);
    }

    /**
     * 
     */
    // addRowToTable method
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

        // Show a success message
        JOptionPane.showMessageDialog(this, "Row added successfully!");
    }

    /**
     * 
     */
    // updateRowToTable method
    private void updateRowToTable() 
    {
        // Get the selected row index
        int selectedRow = sculptureTable.getSelectedRow();
        
        if (selectedRow >= 0) 
        {
            // Update values in the selected row with the new data from the text fields
            defTableModel.setValueAt(textFieldFID.getText(), selectedRow, 0);
            defTableModel.setValueAt(textFieldTitle.getText(), selectedRow, 1);
            defTableModel.setValueAt(textFieldLocation.getText(), selectedRow, 2);
            defTableModel.setValueAt(textFieldArtist.getText(), selectedRow, 3);
            defTableModel.setValueAt(textFieldMaterial.getText(), selectedRow, 4);
            
            // Show a success message
            JOptionPane.showMessageDialog(this, "Row updated successfully!");
        } 
        else 
        {
            // Show an error message if no row is selected
            JOptionPane.showMessageDialog(this, "Please select a row to update.");
        }
    }

    /**
     * 
     */
    // Method to display the selected row's details in the display panel
    private void displaySelectedRow() 
    {
        // Get the selected row index
        int selectedRow = sculptureTable.getSelectedRow();

        // Display values of the selected row to the text fields
        if (selectedRow >= 0) 
        {
            textFieldFID.setText(defTableModel.getValueAt(selectedRow, 0).toString());
            textFieldTitle.setText(defTableModel.getValueAt(selectedRow, 1).toString());
            textFieldLocation.setText(defTableModel.getValueAt(selectedRow, 2).toString());
            textFieldArtist.setText(defTableModel.getValueAt(selectedRow, 3).toString());
            textFieldMaterial.setText(defTableModel.getValueAt(selectedRow, 4).toString());
        }
    }

    /**
     * 
     */
    // updateRowToTable method
    private void deleteRowToTable() 
    {
        // Get the selected row index
        int selectedRow = sculptureTable.getSelectedRow();
        
        try
        {
            defTableModel.removeRow(selectedRow);
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.");
        }
    }

    // // Tester for the SculptureViewerUI class. Uncomment for debugging
    // public static void main(String[] args) {
    //     // Sample data array representing sculptures
    //     Object[][] data = {
    //         {0, "R.H. Herb Gray Bust"   , "Dieppe Gardens"  , "Unknown"         , "Bronze",         "1"   },
    //         {1, "Dieppe Gardens Plaque" , "Jackson Park"    , "Leo Rosenthal"   , "Painted Steel",  "2"   },
    //         {2, "Eternal Flame"         , "City Hall"       , "Jane Sculpture"  , "Granite",        "3"   }
    //     };

    //     // Instantiate the SculptureViewerUI with sample data
    //     new SculptureViewerUI(data);
    // }
}
