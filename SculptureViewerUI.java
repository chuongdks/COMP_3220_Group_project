import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class SculptureViewerUI extends JFrame {
    private JTable sculptureTable;

    public SculptureViewerUI(List<Sculpture> sculptures) {
        setTitle("Sculpture Art Data Viewer");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize table with column names
        String[] columnNames = {"FID", "Title", "Location", "Artist", "Material"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        sculptureTable = new JTable(tableModel);
        
        // Populate table with data
        populateTable(sculptures, tableModel);

        // Scroll pane for table
        JScrollPane scrollPane = new JScrollPane(sculptureTable);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void populateTable(List<Sculpture> sculptures, DefaultTableModel tableModel) {
        for (Sculpture sculpture : sculptures) {
            tableModel.addRow(sculpture.toTableRow());
        }
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
