/**
 * 
 */
package jp.atr.dni.bmi.desktop.neuroshareutils;

/**
 *
 * @author kharada
 * @version 2011/01/13
 */
public class Nsa_NeuralInfo {

    int dwSourceEntityID;
    int dwSourceUnitID;
    String szProbeInfo;

    /**
     * @param dwSourceEntityID
     * @param dwSourceUnitID
     * @param szProbeInfo
     */
    public Nsa_NeuralInfo(int dwSourceEntityID, int dwSourceUnitID, String szProbeInfo) {
        this.dwSourceEntityID = dwSourceEntityID;
        this.dwSourceUnitID = dwSourceUnitID;
        this.szProbeInfo = szProbeInfo;
    }

    /**
     * @return the dwSourceEntityID
     */
    public int getDwSourceEntityID() {
        return dwSourceEntityID;
    }

    /**
     * @param dwSourceEntityID the dwSourceEntityID to set
     */
    public void setDwSourceEntityID(int dwSourceEntityID) {
        this.dwSourceEntityID = dwSourceEntityID;
    }

    /**
     * @return the dwSourceUnitID
     */
    public int getDwSourceUnitID() {
        return dwSourceUnitID;
    }

    /**
     * @param dwSourceUnitID the dwSourceUnitID to set
     */
    public void setDwSourceUnitID(int dwSourceUnitID) {
        this.dwSourceUnitID = dwSourceUnitID;
    }

    /**
     * @return the szProbeInfo
     */
    public String getSzProbeInfo() {
        return szProbeInfo;
    }

    /**
     * @param szProbeInfo the szProbeInfo to set
     */
    public void setSzProbeInfo(String szProbeInfo) {
        this.szProbeInfo = szProbeInfo;
    }
}
