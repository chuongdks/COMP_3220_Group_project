/**
 * Represents a sculpture with fields for ID, title, location, artist, and material and reviews.
 * 
 * Improvement idea: 
 * _ Use HashMap just like Record.java from the demo FileManager
 */
public class Sculpture {
    private int fid;
    private String title;
    private String location;
    private String artist;
    private String material;
    private int rating;

    /**
     * Constructs a new Sculpture instance with specified attributes.
     *
     * @param fid the unique identifier of the sculpture
     * @param review the rating of the sculpture
     * @param title the title of the sculpture
     * @param location the location of the sculpture
     * @param artist the artist who created the sculpture
     * @param material the material used for the sculpture
     */
    public Sculpture(int fid, String title, String location, String artist, String material, int rating) {
        this.fid = fid;
        this.title = title;
        this.location = location;
        this.artist = artist;
        this.material = material;
        this.rating= rating;
    }

    /**
     * Gets the unique identifier (FID) of the sculpture.
     *
     * @return the sculpture's FID
     */
    public int getFid() { return fid; }
    public String getTitle() { return title; }
    public String getLocation() { return location; }
    public String getArtist() { return artist; }
    public String getMaterial() { return material; }
    public int getRating() { return rating; }

    // // Tester for the sculpture class. Uncomment for debugging
    // public static void main(String[] args) {
    //     // Sample data array representing sculptures
    //     Sculpture sculpture = new Sculpture(1, "Statue of Liberty", "New York", "Frédéric Auguste Bartholdi", "Copper");
    //     System.out.println(sculpture.getFid()); 
    //     System.out.println(sculpture.getTitle()); 
    //     System.out.println(sculpture.getLocation()); 
    //     System.out.println(sculpture.getArtist()); 
    //     System.out.println(sculpture.getMaterial()); 
    // }    
}
