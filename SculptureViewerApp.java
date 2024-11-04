/**
 * The main application entry point for displaying the Sculpture Viewer UI.
 */
public class SculptureViewerApp {
    public static void main(String[] args) {
        SculptureDataLoader loader = new SculptureDataLoader();
        SculptureViewerUI viewer = new SculptureViewerUI(loader.getSculpturesData());
    }
}
