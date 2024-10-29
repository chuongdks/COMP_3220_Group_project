import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.stream.Collectors;

public class SculptureViewerUI extends JFrame {
    private JTable sculptureTable;
    private DefaultTableModel tableModel;
    private JTextField filterField;
    private List<Sculpture> sculptures;  // Original list of sculptures

    public SculptureViewerUI(List<Sculpture> sculptures) {
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
        String[] columnNames = {"FID", "Title", "Location", "Artist", "Material"};
        tableModel = new DefaultTableModel(columnNames, 0);
        sculptureTable = new JTable(tableModel);
        populateTable(sculptures);

        // Scroll pane for table
        JScrollPane scrollPane = new JScrollPane(sculptureTable);
        add(scrollPane, BorderLayout.CENTER);

        // Filter button action
        filterButton.addActionListener(this::applyFilter);
    }

    private void populateTable(List<Sculpture> sculptures) {
        // Clear the table before adding new rows
        tableModel.setRowCount(0);
        for (Sculpture sculpture : sculptures) {
            tableModel.addRow(sculpture.toTableRow());
        }
    }

    private void applyFilter(ActionEvent event) {
        String filterText = filterField.getText().trim().toLowerCase();
        
        // Filter the sculptures based on artist or material matching the filter text
        List<Sculpture> filteredSculptures = sculptures.stream()
            .filter(sculpture -> sculpture.getArtist().toLowerCase().contains(filterText) ||
                                 sculpture.getMaterial().toLowerCase().contains(filterText))
            .collect(Collectors.toList());

        // Populate table with filtered data
        populateTable(filteredSculptures);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SculptureDataLoader loader = new SculptureDataLoader();
            List<Sculpture> sculptures = loader.loadSculptures("sculptures.txt");
            SculptureViewerUI viewer = new SculptureViewerUI(sculptures);
            viewer.setVisible(true);
        });
    }
}
