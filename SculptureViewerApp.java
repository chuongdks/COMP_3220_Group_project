import javax.swing.*;
import java.util.ArrayList;

public class SculptureViewerApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SculptureDataLoader loader = new SculptureDataLoader("sculptures.txt");
            ArrayList<Sculpture> sculptures = loader.getSculptures();
            SculptureViewerUI viewer = new SculptureViewerUI(sculptures);
            viewer.setVisible(true);
        });
    }
}
