 interface SculptureTemplate {

    public static final int NUMBER_OF_FIELDS = 5;
    public static final String[] FIELD_LABELS = {"FID", "Title", "Location", "Artist", "Material"};
    public static final String[] FIELD_TYPES = {"Integer", "String", "String", "String", "String"};
    public static final String FILE_NAME = "sculptures.txt";
    public static final String DELIMITER = ",";
    public static final int PRIMARY_KEY_FIELD_INDEX = 0; // FID
}
