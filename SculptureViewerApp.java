public class SculptureViewerApp {
    public static void main(String[] args) {
        SculptureDataLoader loader = new SculptureDataLoader();
        new SculptureViewerUI(loader.getSculpturesData());
    }
}
