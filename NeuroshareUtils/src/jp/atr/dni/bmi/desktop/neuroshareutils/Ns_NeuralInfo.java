/**
 * 
 */
package jp.atr.dni.bmi.desktop.neuroshareutils;

import java.util.logging.Logger;

/**
 *
 * @author Keiji Harada [*1]</br>[*1] ATR Intl. Conputational Neuroscience Labs, Decoding Group
 * @version 2011/04/22
 */
public class Ns_NeuralInfo {

    int dwSourceEntityID;
    int dwSourceUnitID;
    String szProbeInfo;

    /**
     *
     */
    public Ns_NeuralInfo() {
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
                    new Const_messages().getOutOfRange((double) dwSourceEntityID));
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
                    new Const_messages().getOutOfRange((double) dwSourceUnitID));
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
    public Nsa_NeuralInfo getMembers() {
        return new Nsa_NeuralInfo(this.getDwSourceEntityID(), this.getDwSourceUnitID(), this.getSzProbeInfo());
    }

    /**
     *
     * @param nsaNeuralInfo
     * @return
     */
    public int setMembers(Nsa_NeuralInfo nsaNeuralInfo) {
        this.setDwSourceEntityID(nsaNeuralInfo.getDwSourceEntityID());
        this.setDwSourceUnitID(nsaNeuralInfo.getDwSourceUnitID());
        this.setSzProbeInfo(nsaNeuralInfo.getSzProbeInfo());
        return Const_values.NS_OK;
    }
}
