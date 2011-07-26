/**
 * 
 */
package jp.atr.dni.bmi.desktop.neuroshareutils.nsa;

/**
 *
 * @author kharada
 * @version 2011/01/13
 */
public class NSASegmentInfo {

    private double dSampleRate;
    private String szUnits;

    /**
     * @param dSampleRate
     * @param szUnits
     */
    public NSASegmentInfo(double dSampleRate, String szUnits) {
        this.dSampleRate = dSampleRate;
        this.szUnits = szUnits;
    }

    /**
     * @return the dSampleRate
     */
    public double getDSampleRate() {
        return dSampleRate;
    }

    /**
     * @param dSampleRate the dSampleRate to set
     */
    public void setDSampleRate(double dSampleRate) {
        this.dSampleRate = dSampleRate;
    }

    /**
     * @return the szUnits
     */
    public String getSzUnits() {
        return szUnits;
    }

    /**
     * @param szUnits the szUnits to set
     */
    public void setSzUnits(String szUnits) {
        this.szUnits = szUnits;
    }
}
