/**
 * 
 */
package jp.atr.dni.bmi.desktop.neuroshareutils;

/**
 *
 * @author kharada
 * @version 2011/01/13
 */
public class Const_messages {

    final String c = " : ";
    final String error = "ERROR : ";
    final String fileCreateError = error + "file creation error occur.";
    final String warning = "WARNING : ";
    final String outOfLength = "OutOfLength : ";
    final String outOfRange = "OutOfRange : ";
    final String theMemberIsNotModified = "The member is not Modified.";

    /**
     *
     */
    public Const_messages() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @return the sMagicCode
     */
    /**
     * @return the error
     */
    public String getError() {
        return error;
    }

    /**
     * @return the fileCreateError
     */
    public String getFileCreateError() {
        return fileCreateError;
    }

    /**
     * @param value
     * @return
     */
    public String getOutOfLength(String value) {
        return this.outOfLength + value + this.c + this.theMemberIsNotModified;
    }

    /**
     * @param value
     * @return
     */
    public String getOutOfRange(double value) {
        return this.outOfRange + value + this.c + this.theMemberIsNotModified;
    }
}
