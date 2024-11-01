import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class SculptureViewerUI extends JFrame implements SculptureTemplate{
    private JTable sculptureTable;
    private DefaultTableModel tableModel;
    private JTextField filterField;
    private ArrayList<Sculpture> sculptures;  // Original list of sculptures

    public SculptureViewerUI(ArrayList<Sculpture> sculptures) {
        this.sculptures = sculptures;

        setTitle("Sculpture Art Data Viewer");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Initialize filter panel
        JPanel filterPanel = new JPanel();
        filterPanel.add(new JLabel("Filter by Artist/Material:"));
        filterField = new JTextField(20);
        filterPanel.add(filterField);
        JButton filterButton = new JButton("Apply Filter");
        filterPanel.add(filterButton);
        add(filterPanel, BorderLayout.NORTH);

        // Initialize table with column names
        tableModel = new DefaultTableModel(FIELD_LABELS, 0);
        sculptureTable = new JTable(tableModel);

        // Add TableRowSorter to enable sorting by column
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        sculptureTable.setRowSorter(sorter);

        // Populate table with initial data
        populateTable(sculptures);

        // Scroll pane for table
        JScrollPane scrollPane = new JScrollPane(sculptureTable);
        add(scrollPane, BorderLayout.CENTER);

        // Filter button action
        filterButton.addActionListener(this::applyFilter);
    }

    private void populateTable(ArrayList<Sculpture> sculptures) 
    {
        // Clear the table before adding new rows
        tableModel.setRowCount(0);
        for (Sculpture sculpture : sculptures) 
        {
            tableModel.addRow(sculpture.toTableRow());
        }
    }

    private void applyFilter(ActionEvent event) 
    {
        String filterText = filterField.getText().trim().toLowerCase();

        // Filter the sculptures based on artist or material matching the filter text
        ArrayList<Sculpture> filteredSculptures = (ArrayList<Sculpture>) sculptures.stream()
            .filter(sculpture -> sculpture.getArtist().toLowerCase().contains(filterText) ||
                                 sculpture.getMaterial().toLowerCase().contains(filterText))
            .collect(Collectors.toList());

        // Populate table with filtered data
        populateTable(filteredSculptures);
    }
}
