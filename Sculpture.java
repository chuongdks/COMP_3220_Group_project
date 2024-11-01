public class Sculpture {
    private int fid;
    private String title;
    private String location;
    private String artist;
    private String material;

    // 
    public Sculpture(int fid, String title, String location, String artist, String material) {
        this.fid = fid;
        this.title = title;
        this.location = location;
        this.artist = artist;
        this.material = material;
    }

    // Getter class for each Attributes
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

    // 
    public Object[] toTableRow() {
        return new Object[] { fid, title, location, artist, material };
    }
}
