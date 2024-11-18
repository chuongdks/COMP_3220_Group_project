/**
 * SculptureTemplate is an interface intended to maintain a global list of constants (final) accessible throughout the program. 
 * Naturally, nearly every class that needs the details of the file or fields would need to  implement it. 
 * This is a way around "global variables" done C/C++
 * 
 * Improvement ideas:
 * _ Make use of the FIELD_TYPES and add more Global variable if possible
 */
public interface SculptureTemplate {
    public static final int NUMBER_OF_FIELDS = 6;
    public static final String[] FIELD_LABELS = {"FID", "Title", "Location", "Artist", "Material", "Rating"}; // Column Name data
    public static final String[] FIELD_TYPES = {"Integer", "String", "String", "String", "String", "Integer"}; // Not used in this project. Could be use in Sculpture.java
    public static final String FILE_NAME = "sculptures_2.csv"; // File Name (.txt, .csv, .mp5). REMEMBER to delete SculptureManager.class after changing this
    public static final String DELIMITER = ","; // Delimiter
    public static final int PRIMARY_KEY_FIELD_INDEX = 0; // Not used in this project
}
