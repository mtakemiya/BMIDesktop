/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.atr.dni.bmi.desktop.model;

/**
 *
 * @author kharada
 */
public class TimeSeriesData {

    /**
     * Label for this series of data
     */
    private String label;
    /**
     * Description of the data. This can include things like frequency or type of data.
     */
    private String description;
    /**
     * Time, value pairs of data.
     */
    private TimeVal[] values;

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
     * @return the values
     */
    public TimeVal[] getValues() {
        return values;
    }

    /**
     * @param values the values to set
     */
    public void setValues(TimeVal[] values) {
        this.values = values;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
