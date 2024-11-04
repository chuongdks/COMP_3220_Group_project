/**
 * Represents a sculpture with fields for ID, title, location, artist, and material.
 */
public class Sculpture {
    private int fid;
    private String title;
    private String location;
    private String artist;
    private String material;

    /**
     * Constructs a new Sculpture instance with specified attributes.
     *
     * @param fid the unique identifier of the sculpture
     * @param title the title of the sculpture
     * @param location the location of the sculpture
     * @param artist the artist who created the sculpture
     * @param material the material used for the sculpture
     */
    public Sculpture(int fid, String title, String location, String artist, String material) {
        this.fid = fid;
        this.title = title;
        this.location = location;
        this.artist = artist;
        this.material = material;
    }

    /**
     * Gets the unique identifier (FID) of the sculpture.
     *
     * @return the sculpture's FID
     */
    public int getFid() { 
        return fid; 
    }
    public String getTitle() { 
        return title; 
    }
    public String getLocation() { 
        return location; 
    }
    public String getArtist() { 
        return artist; 
    }
    public String getMaterial() { 
        return material; 
    }
}
