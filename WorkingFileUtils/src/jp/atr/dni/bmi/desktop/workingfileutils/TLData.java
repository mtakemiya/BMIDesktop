/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.atr.dni.bmi.desktop.workingfileutils;

import java.util.ArrayList;

/**
 *
 * @author Keiji Harada [*1]</br>[*1] ATR Intl. Conputational Neuroscience Labs, Decoding Group
 * @version 2011/04/22
 */
public class TLData {

    private final String formatCode = "TL";
    private ArrayList<Double> timeStamps = new ArrayList<Double>();
    private ArrayList<Object> values = new ArrayList<Object>();

    /**
     *
     */
    public TLData() {
    }

    /**
     *
     * @return
     */
    public String getFormatCode() {
        return this.formatCode;
    }

    /**
     *
     * @param rowIndex
     * @return
     */
    public double getTimeStamp(int rowIndex) {
        return this.timeStamps.get(rowIndex);
    }

    /**
     *
     * @param rowIndex
     * @param timestamp
     */
    public void setTimeStamp(int rowIndex, double timestamp) {
        this.timeStamps.set(rowIndex, timestamp);
    }

    /**
     *
     * @param timeStamp
     */
    public void addTimeStamp(double timeStamp) {
        this.timeStamps.add(timeStamp);
    }

    /**
     *
     * @param rowIndex
     */
    public void removeTimeStamp(int rowIndex) {
        this.timeStamps.remove(rowIndex);
    }

    /**
     *
     * @param rowIndex
     * @return
     */
    public Object getValue(int rowIndex) {
        return this.values.get(rowIndex);
    }

    /**
     *
     * @param rowIndex
     * @param value
     */
    public void setValue(int rowIndex, Object value) {
        this.values.set(rowIndex, value);
    }

    /**
     *
     * @param value
     */
    public void addValue(Object value) {
        this.values.add(value);
    }

    /**
     *
     * @param rowIndex
     */
    public void removeValue(int rowIndex) {
        this.values.remove(rowIndex);
    }

    /**
     * @return the timeStamps
     */
    public ArrayList<Double> getTimeStamps() {
        return timeStamps;
    }

    /**
     * @param timeStamps the timeStamps to set
     */
    public void setTimeStamps(ArrayList<Double> timeStamps) {
        this.timeStamps = timeStamps;
    }

    /**
     * @return the values
     */
    public ArrayList<Object> getValues() {
        return values;
    }

    /**
     * @param values the values to set
     */
    public void setValues(ArrayList<Object> values) {
        this.values = values;
    }
}
