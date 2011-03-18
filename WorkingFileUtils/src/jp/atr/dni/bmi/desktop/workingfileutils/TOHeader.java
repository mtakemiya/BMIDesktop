/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.atr.dni.bmi.desktop.workingfileutils;

/**
 *
 * @author kharada
 */
public class TOHeader {

    private int sourceEntityID;
    private int sourceUnitID;
    private String commentAboutThisProbe;

    public TOHeader() {
        this.sourceEntityID = 0;
        this.sourceUnitID = 0;
        this.commentAboutThisProbe = "";
    }

    public TOHeader(int sourceEntityID, int sourceUnitID, String commentAboutThisProbe) {
        this.sourceEntityID = sourceEntityID;
        this.sourceUnitID = sourceUnitID;
        this.commentAboutThisProbe = commentAboutThisProbe;
    }

    /**
     * @return the sourceEntityID
     */
    public int getSourceEntityID() {
        return sourceEntityID;
    }

    /**
     * @param sourceEntityID the sourceEntityID to set
     */
    public void setSourceEntityID(int sourceEntityID) {
        this.sourceEntityID = sourceEntityID;
    }

    /**
     * @return the sourceUnitID
     */
    public int getSourceUnitID() {
        return sourceUnitID;
    }

    /**
     * @param sourceUnitID the sourceUnitID to set
     */
    public void setSourceUnitID(int sourceUnitID) {
        this.sourceUnitID = sourceUnitID;
    }

    /**
     * @return the commentAboutThisProbe
     */
    public String getCommentAboutThisProbe() {
        return commentAboutThisProbe;
    }

    /**
     * @param commentAboutThisProbe the commentAboutThisProbe to set
     */
    public void setCommentAboutThisProbe(String commentAboutThisProbe) {
        this.commentAboutThisProbe = commentAboutThisProbe;
    }
}
