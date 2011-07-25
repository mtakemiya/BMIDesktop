/**
 * 
 */
package jp.atr.dni.bmi.desktop.neuroshareutils.nsn;

import jp.atr.dni.bmi.desktop.neuroshareutils.nsa.NSANeuralInfo;
import java.util.logging.Logger;
import jp.atr.dni.bmi.desktop.neuroshareutils.ConstantMessages;
import jp.atr.dni.bmi.desktop.neuroshareutils.ConstantValues;

/**
 *
 * @author Keiji Harada [*1]</br>[*1] ATR Intl. Conputational Neuroscience Labs, Decoding Group
 * @version 2011/04/22
 */
public class NSNNeuralInfo {

    private int dwSourceEntityID;
    private int dwSourceUnitID;
    private String szProbeInfo;

    /**
     *
     */
    public NSNNeuralInfo() {
        this.dwSourceEntityID = 0;
        this.dwSourceUnitID = 0;
        this.szProbeInfo = "";
    }

    /**
     * @return the dwSourceEntityID
     */
    private int getDwSourceEntityID() {
        return dwSourceEntityID;
    }

    /**
     * @param dwSourceEntityID the dwSourceEntityID to set
     */
    private void setDwSourceEntityID(int dwSourceEntityID) {
        if (dwSourceEntityID >= 0) {
            this.dwSourceEntityID = dwSourceEntityID;
        } else {
            // Display message.
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).info(
                    new ConstantMessages().getOutOfRange((double) dwSourceEntityID));
        }
    }

    /**
     * @return the dwSourceUnitID
     */
    private int getDwSourceUnitID() {
        return dwSourceUnitID;
    }

    /**
     * @param dwSourceUnitID the dwSourceUnitID to set
     */
    private void setDwSourceUnitID(int dwSourceUnitID) {
        if (dwSourceUnitID >= 0) {
            this.dwSourceUnitID = dwSourceUnitID;
        } else {
            // Display message.
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).info(
                    new ConstantMessages().getOutOfRange((double) dwSourceUnitID));
        }
    }

    /**
     * @return the szProbeInfo
     */
    private String getSzProbeInfo() {
        return szProbeInfo;
    }

    /**
     * @param szProbeInfo the szProbeInfo to set
     */
    private void setSzProbeInfo(String szProbeInfo) {
        this.szProbeInfo = szProbeInfo;
    }

    /**
     *
     * @return
     */
    public NSANeuralInfo getMembers() {
        return new NSANeuralInfo(this.getDwSourceEntityID(), this.getDwSourceUnitID(), this.getSzProbeInfo());
    }

    /**
     *
     * @param nsaNeuralInfo
     * @return
     */
    public int setMembers(NSANeuralInfo nsaNeuralInfo) {
        this.setDwSourceEntityID(nsaNeuralInfo.getDwSourceEntityID());
        this.setDwSourceUnitID(nsaNeuralInfo.getDwSourceUnitID());
        this.setSzProbeInfo(nsaNeuralInfo.getSzProbeInfo());
        return ConstantValues.NS_OK;
    }
}
