/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.atr.dni.bmi.desktop.workingfileutils.types;

/**
 *
 * @author Keiji Harada [*1]</br>[*1] ATR Intl. Conputational Neuroscience Labs, Decoding Group
 * @version 2011/04/22
 */
public class TIHeader {

    private int sourceCount;
    private int minSampleCount;
    private int maxSampleCount;
    private double samplingRate_Hz;
    private String unitOfValue;
    private double minValue;
    private double maxValue;
    private double resolution;
    private double subSampleShift;
    private double locationX_m;
    private double locationY_m;
    private double locationZ_m;
    private double probeNumber;
    private double highFreqCutoff_Hz;
    private int orderOfHighFreqCutoff;
    private String commentOfHighFreqCutoff;
    private double lowFreqCutoff_Hz;
    private int orderOfLowFreqCutoff;
    private String commentOfLowFreqCutoff;
    private String commentOfThisProbe;

    /**
     *
     */
    public TIHeader() {
        this.sourceCount = 1;
        this.minSampleCount = Integer.MAX_VALUE;
        this.maxSampleCount = Integer.MIN_VALUE;
        this.samplingRate_Hz = 0;
        this.unitOfValue = "";
        this.minValue = Double.MAX_VALUE;
        this.maxValue = Double.MIN_VALUE;
        this.resolution = 0;
        this.subSampleShift = 0;
        this.locationX_m = 0;
        this.locationY_m = 0;
        this.locationZ_m = 0;
        this.probeNumber = 0;
        this.highFreqCutoff_Hz = 0;
        this.orderOfHighFreqCutoff = 0;
        this.commentOfHighFreqCutoff = "";
        this.lowFreqCutoff_Hz = 0;
        this.orderOfLowFreqCutoff = 0;
        this.commentOfLowFreqCutoff = "";
        this.commentOfThisProbe = "";
    }

    /**
     *
     * @param sourceCount
     * @param minSampleCount
     * @param maxSampleCount
     * @param samplingRate_Hz
     * @param unitOfValue
     * @param minValue
     * @param maxValue
     * @param resolution
     * @param subSampleShift
     * @param locationX_m
     * @param locationY_m
     * @param locationZ_m
     * @param probeNumber
     * @param highFreqCutoff_Hz
     * @param orderOfHighFreqCutoff
     * @param commentOfHighFreqCutoff
     * @param lowFreqCutoff_Hz
     * @param orderOfLowFreqCutoff
     * @param commentOfLowFreqCutoff
     * @param commentOfThisProbe
     */
    public TIHeader(int sourceCount, int minSampleCount, int maxSampleCount, double samplingRate_Hz, String unitOfValue, double minValue, double maxValue, double resolution, double subSampleShift, double locationX_m, double locationY_m, double locationZ_m, double probeNumber, double highFreqCutoff_Hz, int orderOfHighFreqCutoff, String commentOfHighFreqCutoff, double lowFreqCutoff_Hz, int orderOfLowFreqCutoff, String commentOfLowFreqCutoff, String commentOfThisProbe) {
        this.sourceCount = sourceCount;
        this.minSampleCount = minSampleCount;
        this.maxSampleCount = maxSampleCount;
        this.samplingRate_Hz = samplingRate_Hz;
        this.unitOfValue = unitOfValue;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.resolution = resolution;
        this.subSampleShift = subSampleShift;
        this.locationX_m = locationX_m;
        this.locationY_m = locationY_m;
        this.locationZ_m = locationZ_m;
        this.probeNumber = probeNumber;
        this.highFreqCutoff_Hz = highFreqCutoff_Hz;
        this.orderOfHighFreqCutoff = orderOfHighFreqCutoff;
        this.commentOfHighFreqCutoff = commentOfHighFreqCutoff;
        this.lowFreqCutoff_Hz = lowFreqCutoff_Hz;
        this.orderOfLowFreqCutoff = orderOfLowFreqCutoff;
        this.commentOfLowFreqCutoff = commentOfLowFreqCutoff;
        this.commentOfThisProbe = commentOfThisProbe;
    }

    /**
     * @return the sourceCount
     */
    public int getSourceCount() {
        return sourceCount;
    }

    /**
     * @param sourceCount the sourceCount to set
     */
    public void setSourceCount(int sourceCount) {
        this.sourceCount = sourceCount;
    }

    /**
     * @return the minSampleCount
     */
    public int getMinSampleCount() {
        return minSampleCount;
    }

    /**
     * @param minSampleCount the minSampleCount to set
     */
    public void setMinSampleCount(int minSampleCount) {
        this.minSampleCount = minSampleCount;
    }

    /**
     * @return the maxSampleCount
     */
    public int getMaxSampleCount() {
        return maxSampleCount;
    }

    /**
     * @param maxSampleCount the maxSampleCount to set
     */
    public void setMaxSampleCount(int maxSampleCount) {
        this.maxSampleCount = maxSampleCount;
    }

    /**
     * @return the samplingRate_Hz
     */
    public double getSamplingRate_Hz() {
        return samplingRate_Hz;
    }

    /**
     * @param samplingRate_Hz the samplingRate_Hz to set
     */
    public void setSamplingRate_Hz(double samplingRate_Hz) {
        this.samplingRate_Hz = samplingRate_Hz;
    }

    /**
     * @return the unitOfValue
     */
    public String getUnitOfValue() {
        return unitOfValue;
    }

    /**
     * @param unitOfValue the unitOfValue to set
     */
    public void setUnitOfValue(String unitOfValue) {
        this.unitOfValue = unitOfValue;
    }

    /**
     * @return the minValue
     */
    public double getMinValue() {
        return minValue;
    }

    /**
     * @param minValue the minValue to set
     */
    public void setMinValue(double minValue) {
        this.minValue = minValue;
    }

    /**
     * @return the maxValue
     */
    public double getMaxValue() {
        return maxValue;
    }

    /**
     * @param maxValue the maxValue to set
     */
    public void setMaxValue(double maxValue) {
        this.maxValue = maxValue;
    }

    /**
     * @return the resolution
     */
    public double getResolution() {
        return resolution;
    }

    /**
     * @param resolution the resolution to set
     */
    public void setResolution(double resolution) {
        this.resolution = resolution;
    }

    /**
     * @return the subSampleShift
     */
    public double getSubSampleShift() {
        return subSampleShift;
    }

    /**
     * @param subSampleShift the subSampleShift to set
     */
    public void setSubSampleShift(double subSampleShift) {
        this.subSampleShift = subSampleShift;
    }

    /**
     * @return the locationX_m
     */
    public double getLocationX_m() {
        return locationX_m;
    }

    /**
     * @param locationX_m the locationX_m to set
     */
    public void setLocationX_m(double locationX_m) {
        this.locationX_m = locationX_m;
    }

    /**
     * @return the locationY_m
     */
    public double getLocationY_m() {
        return locationY_m;
    }

    /**
     * @param locationY_m the locationY_m to set
     */
    public void setLocationY_m(double locationY_m) {
        this.locationY_m = locationY_m;
    }

    /**
     * @return the locationZ_m
     */
    public double getLocationZ_m() {
        return locationZ_m;
    }

    /**
     * @param locationZ_m the locationZ_m to set
     */
    public void setLocationZ_m(double locationZ_m) {
        this.locationZ_m = locationZ_m;
    }

    /**
     * @return the probeNumber
     */
    public double getProbeNumber() {
        return probeNumber;
    }

    /**
     * @param probeNumber the probeNumber to set
     */
    public void setProbeNumber(double probeNumber) {
        this.probeNumber = probeNumber;
    }

    /**
     * @return the highFreqCutoff_Hz
     */
    public double getHighFreqCutoff_Hz() {
        return highFreqCutoff_Hz;
    }

    /**
     * @param highFreqCutoff_Hz the highFreqCutoff_Hz to set
     */
    public void setHighFreqCutoff_Hz(double highFreqCutoff_Hz) {
        this.highFreqCutoff_Hz = highFreqCutoff_Hz;
    }

    /**
     * @return the orderOfHighFreqCutoff
     */
    public int getOrderOfHighFreqCutoff() {
        return orderOfHighFreqCutoff;
    }

    /**
     * @param orderOfHighFreqCutoff the orderOfHighFreqCutoff to set
     */
    public void setOrderOfHighFreqCutoff(int orderOfHighFreqCutoff) {
        this.orderOfHighFreqCutoff = orderOfHighFreqCutoff;
    }

    /**
     * @return the commentOfHighFreqCutoff
     */
    public String getCommentOfHighFreqCutoff() {
        return commentOfHighFreqCutoff;
    }

    /**
     * @param commentOfHighFreqCutoff the commentOfHighFreqCutoff to set
     */
    public void setCommentOfHighFreqCutoff(String commentOfHighFreqCutoff) {
        this.commentOfHighFreqCutoff = commentOfHighFreqCutoff;
    }

    /**
     * @return the lowFreqCutoff_Hz
     */
    public double getLowFreqCutoff_Hz() {
        return lowFreqCutoff_Hz;
    }

    /**
     * @param lowFreqCutoff_Hz the lowFreqCutoff_Hz to set
     */
    public void setLowFreqCutoff_Hz(double lowFreqCutoff_Hz) {
        this.lowFreqCutoff_Hz = lowFreqCutoff_Hz;
    }

    /**
     * @return the orderOfLowFreqCutoff
     */
    public int getOrderOfLowFreqCutoff() {
        return orderOfLowFreqCutoff;
    }

    /**
     * @param orderOfLowFreqCutoff the orderOfLowFreqCutoff to set
     */
    public void setOrderOfLowFreqCutoff(int orderOfLowFreqCutoff) {
        this.orderOfLowFreqCutoff = orderOfLowFreqCutoff;
    }

    /**
     * @return the commentOfLowFreqCutoff
     */
    public String getCommentOfLowFreqCutoff() {
        return commentOfLowFreqCutoff;
    }

    /**
     * @param commentOfLowFreqCutoff the commentOfLowFreqCutoff to set
     */
    public void setCommentOfLowFreqCutoff(String commentOfLowFreqCutoff) {
        this.commentOfLowFreqCutoff = commentOfLowFreqCutoff;
    }

    /**
     * @return the commentOfThisProbe
     */
    public String getCommentOfThisProbe() {
        return commentOfThisProbe;
    }

    /**
     * @param commentOfThisProbe the commentOfThisProbe to set
     */
    public void setCommentOfThisProbe(String commentOfThisProbe) {
        this.commentOfThisProbe = commentOfThisProbe;
    }
}
