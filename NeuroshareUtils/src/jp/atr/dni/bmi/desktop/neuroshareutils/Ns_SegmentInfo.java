/**
 * 
 */
package jp.atr.dni.bmi.desktop.neuroshareutils;

import java.util.logging.Logger;

/**
 *
 * @author kharada
 * @version 2011/01/13
 */
public class Ns_SegmentInfo {

    int dwSourceCount;
    int dwMinSampleCount;
    int dwMaxSampleCount;
    double dSampleRate;
    String szUnits;

    /**
     *
     */
    public Ns_SegmentInfo() {
        this.dwSourceCount = 0;
        this.dwMinSampleCount = Integer.MAX_VALUE;
        this.dwMaxSampleCount = 0;
        this.dSampleRate = 0;
        this.szUnits = "";
    }

    /**
     * @return the dwSourceCount
     */
    public int getDwSourceCount() {
        return dwSourceCount;
    }

    /**
     * @param dwSourceCount the dwSourceCount to set
     */
    public void setDwSourceCount(int dwSourceCount) {
        if (dwSourceCount >= 0) {
            this.dwSourceCount = dwSourceCount;
        } else {
            // Display message.
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).info(
                    new Const_messages().getOutOfRange((double) dwSourceCount));
        }
    }

    /**
     * @return the dwMinSampleCount
     */
    public int getDwMinSampleCount() {
        return dwMinSampleCount;
    }

    /**
     * @param dwMinSampleCount the dwMinSampleCount to set
     */
    public void setDwMinSampleCount(int dwMinSampleCount) {
        if (dwMinSampleCount >= 0) {
            this.dwMinSampleCount = dwMinSampleCount;
        } else {
            // Display message.
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).info(
                    new Const_messages().getOutOfRange((double) dwMinSampleCount));
        }
    }

    /**
     * @return the dwMaxSampleCount
     */
    public int getDwMaxSampleCount() {
        return dwMaxSampleCount;
    }

    /**
     * @param dwMaxSampleCount the dwMaxSampleCount to set
     */
    public void setDwMaxSampleCount(int dwMaxSampleCount) {
        if (dwMaxSampleCount >= 0) {
            this.dwMaxSampleCount = dwMaxSampleCount;
        } else {
            // Display message.
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).info(
                    new Const_messages().getOutOfRange((double) dwMaxSampleCount));
        }
    }

    /**
     * @return the dSampleRate
     */
    private double getDSampleRate() {
        return dSampleRate;
    }

    /**
     * @param dSampleRate the dSampleRate to set
     */
    private void setDSampleRate(double dSampleRate) {
        this.dSampleRate = dSampleRate;
    }

    /**
     * @return the szUnits
     */
    private String getSzUnits() {
        return szUnits;
    }

    /**
     * @param szUnits the szUnits to set
     */
    private void setSzUnits(String szUnits) {
        this.szUnits = szUnits;
    }

    /**
     * @param dwSourceCount
     */
    public void addDwSourceCount(int dwSourceCount) {
        this.dwSourceCount += dwSourceCount;
    }

    /**
     * @return members
     */
    public Nsa_SegmentInfo getMembers() {
        return new Nsa_SegmentInfo(this.getDSampleRate(), this.getSzUnits());
    }

    /**
     * @param nsaSegmentInfo
     * @return
     */
    public int setMembers(Nsa_SegmentInfo nsaSegmentInfo) {
        this.setDSampleRate(nsaSegmentInfo.getDSampleRate());
        this.setSzUnits(nsaSegmentInfo.getSzUnits());
        return Const_values.NS_OK;
    }
}
