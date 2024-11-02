import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class SculptureViewerUI extends JFrame implements SculptureTemplate{
    private JFrame viewFrame;
    private JTable sculptureTable;
    private DefaultTableModel tableModel;

    private JScrollPane scrollPane;
    private TableRowSorter<DefaultTableModel> rowSorter;
    private JTextField filterField;

    /**
     * 
     * @param data
     */
    public SculptureViewerUI(Object[][] data) {
        // Initialize table with column names
        viewFrame = new JFrame("Sculpture Art Data Viewer");
        sculptureTable = new JTable(data, FIELD_LABELS);

        // Scroll pane for table
        scrollPane = new JScrollPane(sculptureTable);

        // 
        viewFrame.add(scrollPane);
        viewFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        viewFrame.pack();
        viewFrame.setVisible(true);
        viewFrame.setSize(600, 400);

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
        viewFrame.add(filterField, BorderLayout.NORTH);
    }
}
