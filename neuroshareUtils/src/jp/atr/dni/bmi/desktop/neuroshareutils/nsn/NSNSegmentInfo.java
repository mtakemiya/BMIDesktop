/**
 * 
 */
package jp.atr.dni.bmi.desktop.neuroshareutils.nsn;

import jp.atr.dni.bmi.desktop.neuroshareutils.nsa.NSASegmentInfo;
import java.util.logging.Logger;
import jp.atr.dni.bmi.desktop.neuroshareutils.ConstantMessages;
import jp.atr.dni.bmi.desktop.neuroshareutils.ConstantValues;

/**
 *
 * @author kharada
 * @version 2011/01/13
 */
public class NSNSegmentInfo {

    private int dwSourceCount;
    private int dwMinSampleCount;
    private int dwMaxSampleCount;
    private double dSampleRate;
    private String szUnits;

    /**
     *
     */
    public NSNSegmentInfo() {
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
                    new ConstantMessages().getOutOfRange((double) dwSourceCount));
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
                    new ConstantMessages().getOutOfRange((double) dwMinSampleCount));
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
                    new ConstantMessages().getOutOfRange((double) dwMaxSampleCount));
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
    public NSASegmentInfo getMembers() {
        return new NSASegmentInfo(this.getDSampleRate(), this.getSzUnits());
    }

    /**
     * @param nsaSegmentInfo
     * @return
     */
    public int setMembers(NSASegmentInfo nsaSegmentInfo) {
        this.setDSampleRate(nsaSegmentInfo.getDSampleRate());
        this.setSzUnits(nsaSegmentInfo.getSzUnits());
        return ConstantValues.NS_OK;
    }
}
