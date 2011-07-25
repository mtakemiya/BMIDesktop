/**
 * 
 */
package jp.atr.dni.bmi.desktop.neuroshareutils;

/**
 *
 * @author kharada
 * @version 2011/01/13
 */
public class ConstantMessages {

    public static final String COLON = " : ";
    public static final String ERROR = "ERROR : ";
    public static final String FILE_CREATION_ERROR = ERROR + "file creation error occur.";
    public static final String WARNING = "WARNING : ";
    /**TODO: 意味あまり分かりません */
    public static final String OUT_OF_LENGTH = "OutOfLength : ";
    public static final String OUT_OF_RANGE = "OutOfRange : ";
    public static final String MEMBER_NOT_MODIFIED = "The member is not modified.";

    /**
     * @param value
     * @return
     */
    public static String getOutOfLength(String value) {
        return OUT_OF_LENGTH + value + COLON + MEMBER_NOT_MODIFIED;
    }

    /**
     * @param value
     * @return
     */
    public static String getOutOfRange(double value) {
        return OUT_OF_RANGE + value + COLON + MEMBER_NOT_MODIFIED;
    }
}
