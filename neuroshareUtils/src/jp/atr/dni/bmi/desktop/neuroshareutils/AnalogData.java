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
public class AnalogData {

    private double timeStamp;
    private long dataCount;
    private ArrayList<Double> analogValues;

    /**
     * Default Constructor.
     */
    public AnalogData() {
        super();
    }

    /**
     * @param timeStamp
     * @param dataCount
     * @param analogValues
     */
    public AnalogData(double timeStamp, long dataCount, ArrayList<Double> analogValues) {
        super();
        this.timeStamp = timeStamp;
        this.dataCount = dataCount;
        this.analogValues = analogValues;
    }

    /**
     * @return the timeStamp
     */
    public double getTimeStamp() {
        return timeStamp;
    }

    /**
     * @param timeStamp the timeStamp to set
     */
    public void setTimeStamp(double timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * @return the dataCount
     */
    public long getDataCount() {
        return dataCount;
    }

    /**
     * @param dataCount the dataCount to set
     */
    public void setDataCount(long dataCount) {
        this.dataCount = dataCount;
    }

    /**
     * @return the analogValues
     */
    public ArrayList<Double> getAnalogValues() {
        return analogValues;
    }

    /**
     * @param analogValues the analogValues to set
     */
    public void setAnalogValues(ArrayList<Double> analogValues) {
        this.analogValues = analogValues;
    }
}
