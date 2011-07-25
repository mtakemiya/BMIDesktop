/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.atr.dni.bmi.desktop.workingfileutils.types;

/**
 *
 * @author Keiji Harada [*1]</br>[*1] ATR Intl. Computational Neuroscience Labs, Decoding Group
 * @version 2011/04/22
 */
public class TLHeader {

    private int eventType;
    private int minDataLength;
    private int maxDataLength;
    private String commentAboutThisProbe;

    /**
     *
     */
    public TLHeader() {
        this.eventType = 0;
        this.minDataLength = 0;
        this.maxDataLength = 0;
        this.commentAboutThisProbe = "";
    }

    /**
     *
     * @param eventType
     * @param minDataLength
     * @param maxDataLength
     * @param commentAboutThisProbe
     */
    public TLHeader(int eventType, int minDataLength, int maxDataLength, String commentAboutThisProbe) {
        this.eventType = eventType;
        this.minDataLength = minDataLength;
        this.maxDataLength = maxDataLength;
        this.commentAboutThisProbe = commentAboutThisProbe;
    }

    /**
     * @return the eventType
     */
    public int getEventType() {
        return eventType;
    }

    /**
     * @param eventType the eventType to set
     */
    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    /**
     * @return the minDataLength
     */
    public int getMinDataLength() {
        return minDataLength;
    }

    /**
     * @param minDataLength the minDataLength to set
     */
    public void setMinDataLength(int minDataLength) {
        this.minDataLength = minDataLength;
    }

    /**
     * @return the maxDataLength
     */
    public int getMaxDataLength() {
        return maxDataLength;
    }

    /**
     * @param maxDataLength the maxDataLength to set
     */
    public void setMaxDataLength(int maxDataLength) {
        this.maxDataLength = maxDataLength;
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
