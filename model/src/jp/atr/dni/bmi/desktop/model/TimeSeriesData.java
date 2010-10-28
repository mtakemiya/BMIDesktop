/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jp.atr.dni.bmi.desktop.model;

import java.util.ArrayList;

/**
 *
 * @author kharada
 */
public class TimeSeriesData {

    private String label;
    private double resolution;
    private ArrayList<Double> values;

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
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
     * @return the values
     */
    public ArrayList<Double> getValues() {
        return values;
    }

    /**
     * @param values the values to set
     */
    public void setValues(ArrayList<Double> values) {
        this.values = values;
    }



}
