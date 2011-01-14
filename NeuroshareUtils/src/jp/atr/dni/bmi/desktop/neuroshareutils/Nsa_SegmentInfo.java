/**
 * 
 */
package jp.atr.dni.bmi.desktop.neuroshareutils;

/**
 *
 * @author kharada
 * @version 2011/01/13
 */
public class Nsa_SegmentInfo {

    double dSampleRate;
    String szUnits;

    /**
     * @param dSampleRate
     * @param szUnits
     */
    public Nsa_SegmentInfo(double dSampleRate, String szUnits) {
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
