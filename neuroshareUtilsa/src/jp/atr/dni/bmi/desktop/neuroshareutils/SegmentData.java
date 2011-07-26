/**
 * 
 */
package jp.atr.dni.bmi.desktop.neuroshareutils;

import java.util.ArrayList;

/**
 *
 * @author kharada
 * @version 2011/01/13
 */
public class SegmentData {

    private ArrayList<Long> sampleCount;
    private ArrayList<Double> timeStamp;
    private ArrayList<Long> unitID;
    /**
     * This is a 2D matrix of data values for a segment.<br /><br />
     *
     * NOTE: Having a list of lists is a somewhat awkward data type, but I am doing it because I
     * cannot instantiate an array with a long number as the size. I have to use longs to represent
     * the sizes, since the Neuroshare spec uses unsigned ints for them and Java has no unsigned
     * datatype.
     */
    private ArrayList<ArrayList<Double>> values;

    /**
     * Default constructor.
     */
    public SegmentData() {
        super();
    }

    /**
     * @param sampleCount
     * @param timeStamp
     * @param unitID
     * @param values
     */
    public SegmentData(ArrayList<Long> sampleCount, ArrayList<Double> timeStamp,
            ArrayList<Long> unitID, ArrayList<ArrayList<Double>> values) {
        super();
        this.sampleCount = sampleCount;
        this.timeStamp = timeStamp;
        this.unitID = unitID;
        this.values = values;
    }

    /**
     * @return the sampleCount
     */
    public ArrayList<Long> getSampleCount() {
        return sampleCount;
    }

    /**
     * @param sampleCount the sampleCount to set
     */
    public void setSampleCount(ArrayList<Long> sampleCount) {
        this.sampleCount = sampleCount;
    }

    /**
     * @return the timeStamp
     */
    public ArrayList<Double> getTimeStamp() {
        return timeStamp;
    }

    /**
     * @param timeStamp the timeStamp to set
     */
    public void setTimeStamp(ArrayList<Double> timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * @return the unitID
     */
    public ArrayList<Long> getUnitID() {
        return unitID;
    }

    /**
     * @param unitID the unitID to set
     */
    public void setUnitID(ArrayList<Long> unitID) {
        this.unitID = unitID;
    }

    /**
     * @return the values
     */
    public ArrayList<ArrayList<Double>> getValues() {
        return values;
    }

    /**
     * @param values the values to set
     */
    public void setValues(ArrayList<ArrayList<Double>> values) {
        this.values = values;
    }
}
