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
public class TOData {

    private final String formatCode = "TO";
    private ArrayList<Double> timeStamps = new ArrayList<Double>();

    public TOData() {
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

    /**
     * @return the formatCode
     */
    public String getFormatCode() {
        return formatCode;
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
}
