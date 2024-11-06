/**
 * The main application entry point for displaying the Sculpture Viewer UI.
 */
public class SculptureMainApplication {
    public static void main(String[] args) {
        SculptureManager loader = new SculptureManager();
        new SculptureViewerUI(loader.getSculpturesData());
    }
}
