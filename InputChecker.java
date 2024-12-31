/**
 * Utility class for input validation
 * Improvement ideas: 
 * _ Add a checker for fid and rating. Turn them to number, then check (fid number should be unique and rating limits from 0 to 5 stars)
 */
public class InputChecker {

    /**
     * Check if input is a number or not
     * @param str
     * @return true if String is a number, else false
     */
    public static boolean isNumeric(String str) {
        try 
        {
            Double.parseDouble(str); // Parse as a double to check for float number just in case
            return true;
        } 
        catch (NumberFormatException e) 
        {
            return false;
        }
    }

    /**
     * Validate the input and give a response
     * @param fid
     * @param title
     * @param location
     * @param artist
     * @param material
     * @param rating
     * @return a response depend on the input
     */
    public static String validateInputs(String fid, String title, String location, String artist, String material, String rating) 
    {
        if (fid.isEmpty() || title.isEmpty() || location.isEmpty() || artist.isEmpty() || material.isEmpty() || rating.isEmpty()) {
            return "All text fields must be filled.";
        }
        if (!isNumeric(fid)) {
            return "FID must be a valid number.";
        }
        if (!isNumeric(rating)) {
            return "Rating must be a valid number.";
        }
        return null; // No Error Message
    }
}
