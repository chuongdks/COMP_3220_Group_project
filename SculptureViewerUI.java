import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;

/**
 * 
 */
public class SculptureViewerUI extends JFrame implements SculptureTemplate {
    private JTable sculptureTable;
    private DefaultTableModel tableModel;
    private JTextField filterField;
    private TableRowSorter<DefaultTableModel> rowSorter;

    /**
     * 
     * @param data
     */
    public SculptureViewerUI(Object[][] data) {
        // Set up JFrame
        setTitle("Sculpture Art Data Viewer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize table with data and column names
        tableModel = new DefaultTableModel(data, FIELD_LABELS);
        sculptureTable = new JTable(tableModel);
        sculptureTable.setFillsViewportHeight(true);

        // TableRowSorter for sorting and filtering
        rowSorter = new TableRowSorter<>(tableModel);
        sculptureTable.setRowSorter(rowSorter);

        // Filter field for filtering rows based on text input
        filterField = new JTextField();
        filterField.setToolTipText("Type to filter...");
        filterField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String text = filterField.getText();
                if (text.trim().isEmpty()) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
        });

        // Add components to frame
        add(new JScrollPane(sculptureTable), BorderLayout.CENTER);
        add(filterField, BorderLayout.NORTH);

        setSize(600, 400);
        setVisible(true);
    }
}
