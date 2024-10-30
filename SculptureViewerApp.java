import javax.swing.*;
import java.util.List;

public class SculptureViewerApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SculptureDataLoader loader = new SculptureDataLoader();
            List<Sculpture> sculptures = loader.loadSculptures("sculptures.txt");
            SculptureViewerUI viewer = new SculptureViewerUI(sculptures);
            viewer.setVisible(true);
        });
    }
}
