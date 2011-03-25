/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.atr.dni.bmi.desktop.workingfileutils;

import java.util.ArrayList;

/**
 *
 * @author kharada
 */
public class TLData {

    private final String formatCode = "TL";
    private ArrayList<Double> timeStamps = new ArrayList<Double>();
    private ArrayList<Object> values = new ArrayList<Object>();

    public TLData() {
    }

    public String getFormatCode() {
        return this.formatCode;
    }

    public double getTimeStamp(int rowIndex) {
        return this.timeStamps.get(rowIndex);
    }

    public void setTimeStamp(int rowIndex, double timestamp) {
        this.timeStamps.set(rowIndex, timestamp);
    }

    public void addTimeStamp(double timeStamp) {
        this.timeStamps.add(timeStamp);
    }

    public void removeTimeStamp(int rowIndex) {
        this.timeStamps.remove(rowIndex);
    }

    public Object getValue(int rowIndex) {
        return this.values.get(rowIndex);
    }

    public void setValue(int rowIndex, Object value) {
        this.values.set(rowIndex, value);
    }

    public void addValue(Object value) {
        this.values.add(value);
    }

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
