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
public class Ns_SegSourceInfo {

    double dMinVal;
    double dMaxVal;
    double dResolution;
    double dSubSampleShift;
    double dLocationX;
    double dLocationY;
    double dLocationZ;
    double dLocationUser;
    double dHighFreqCorner;
    int dwHighFreqOrder;
    String szHighFilterType;
    double dLowFreqCorner;
    int dwLowFreqOrder;
    String szLowFilterType;
    String szProbeInfo;

    /**
     *
     */
    public Ns_SegSourceInfo() {
        this.dMinVal = Double.MAX_VALUE;
        this.dMaxVal = Double.MIN_VALUE;
        this.dResolution = 0;
        this.dSubSampleShift = 0;
        this.dLocationX = 0;
        this.dLocationY = 0;
        this.dLocationZ = 0;
        this.dLocationUser = 0;
        this.dHighFreqCorner = 0;
        this.dwHighFreqOrder = 0;
        this.szHighFilterType = "";
        this.dLowFreqCorner = 0;
        this.dwLowFreqOrder = 0;
        this.szLowFilterType = "";
        this.szProbeInfo = "";
    }

    /**
     * @return the dMinVal
     */
    public double getDMinVal() {
        return dMinVal;
    }

    /**
     * @param dMinVal the dMinVal to set
     */
    public void setDMinVal(double dMinVal) {
        this.dMinVal = dMinVal;
    }

    /**
     * @return the dMaxVal
     */
    public double getDMaxVal() {
        return dMaxVal;
    }

    /**
     * @param dMaxVal the dMaxVal to set
     */
    public void setDMaxVal(double dMaxVal) {
        this.dMaxVal = dMaxVal;
    }

    /**
     * @return the dResolution
     */
    private double getDResolution() {
        return dResolution;
    }

    /**
     * @param dResolution the dResolution to set
     */
    private void setDResolution(double dResolution) {
        this.dResolution = dResolution;
    }

    /**
     * @return the dSubSampleShift
     */
    private double getDSubSampleShift() {
        return dSubSampleShift;
    }

    /**
     * @param dSubSampleShift the dSubSampleShift to set
     */
    private void setDSubSampleShift(double dSubSampleShift) {
        this.dSubSampleShift = dSubSampleShift;
    }

    /**
     * @return the dLocationX
     */
    private double getDLocationX() {
        return dLocationX;
    }

    /**
     * @param dLocationX the dLocationX to set
     */
    private void setDLocationX(double dLocationX) {
        this.dLocationX = dLocationX;
    }

    /**
     * @return the dLocationY
     */
    private double getDLocationY() {
        return dLocationY;
    }

    /**
     * @param dLocationY the dLocationY to set
     */
    private void setDLocationY(double dLocationY) {
        this.dLocationY = dLocationY;
    }

    /**
     * @return the dLocationZ
     */
    private double getDLocationZ() {
        return dLocationZ;
    }

    /**
     * @param dLocationZ the dLocationZ to set
     */
    private void setDLocationZ(double dLocationZ) {
        this.dLocationZ = dLocationZ;
    }

    /**
     * @return the dLocationUser
     */
    private double getDLocationUser() {
        return dLocationUser;
    }

    /**
     * @param dLocationUser the dLocationUser to set
     */
    private void setDLocationUser(double dLocationUser) {
        this.dLocationUser = dLocationUser;
    }

    /**
     * @return the dHighFreqCorner
     */
    private double getDHighFreqCorner() {
        return dHighFreqCorner;
    }

    /**
     * @param dHighFreqCorner the dHighFreqCorner to set
     */
    private void setDHighFreqCorner(double dHighFreqCorner) {
        this.dHighFreqCorner = dHighFreqCorner;
    }

    /**
     * @return the dwHighFreqOrder
     */
    private int getDwHighFreqOrder() {
        return dwHighFreqOrder;
    }

    /**
     * @param dwHighFreqOrder the dwHighFreqOrder to set
     */
    private void setDwHighFreqOrder(int dwHighFreqOrder) {
        if (dwHighFreqOrder >= 0) {
            this.dwHighFreqOrder = dwHighFreqOrder;
        } else {
            // Display message.
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).info(
                    new Const_messages().getOutOfRange((double) dwHighFreqOrder));
        }
    }

    /**
     * @return the szHighFilterType
     */
    private String getSzHighFilterType() {
        return szHighFilterType;
    }

    /**
     * @param szHighFilterType the szHighFilterType to set
     */
    private void setSzHighFilterType(String szHighFilterType) {
        this.szHighFilterType = szHighFilterType;
    }

    /**
     * @return the dLowFreqCorner
     */
    private double getDLowFreqCorner() {
        return dLowFreqCorner;
    }

    /**
     * @param dLowFreqCorner the dLowFreqCorner to set
     */
    private void setDLowFreqCorner(double dLowFreqCorner) {
        this.dLowFreqCorner = dLowFreqCorner;
    }

    /**
     * @return the dwLowFreqOrder
     */
    private int getDwLowFreqOrder() {
        return dwLowFreqOrder;
    }

    /**
     * @param dwLowFreqOrder the dwLowFreqOrder to set
     */
    private void setDwLowFreqOrder(int dwLowFreqOrder) {
        if (dwLowFreqOrder >= 0) {
            this.dwLowFreqOrder = dwLowFreqOrder;
        } else {
            // Display message.
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).info(
                    new Const_messages().getOutOfRange((double) dwLowFreqOrder));
        }
    }

    /**
     * @return the szLowFilterType
     */
    private String getSzLowFilterType() {
        return szLowFilterType;
    }

    /**
     * @param szLowFilterType the szLowFilterType to set
     */
    private void setSzLowFilterType(String szLowFilterType) {
        this.szLowFilterType = szLowFilterType;
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
     * @return members
     */
    public Nsa_SegSourceInfo getMembers() {
        return new Nsa_SegSourceInfo(this.getDResolution(), this.getDMinVal(), this.getDMaxVal(), this.getDSubSampleShift(), this.getDLocationX(), this.getDLocationY(), this.getDLocationZ(), this.getDLocationUser(), this.getDHighFreqCorner(), this.getDwHighFreqOrder(), this.getSzHighFilterType(), this.getDLowFreqCorner(), this.getDwLowFreqOrder(), this.getSzLowFilterType(), this.getSzProbeInfo());
    }

    public int setMembers(Nsa_SegSourceInfo nsaSegSourceInfo) {
        this.setDResolution(nsaSegSourceInfo.getDResolution());
        this.setDMinVal(nsaSegSourceInfo.getDMinVal());
        this.setDMaxVal(nsaSegSourceInfo.getDMaxVal());
        this.setDSubSampleShift(nsaSegSourceInfo.getDSubSampleShift());
        this.setDLocationX(nsaSegSourceInfo.getDLocationX());
        this.setDLocationY(nsaSegSourceInfo.getDLocationY());
        this.setDLocationZ(nsaSegSourceInfo.getDLocationZ());
        this.setDLocationUser(nsaSegSourceInfo.getDLocationUser());
        this.setDHighFreqCorner(nsaSegSourceInfo.getDHighFreqCorner());
        this.setDwHighFreqOrder(nsaSegSourceInfo.getDwHighFreqOrder());
        this.setSzHighFilterType(nsaSegSourceInfo.getSzHighFilterType());
        this.setDLowFreqCorner(nsaSegSourceInfo.getDLowFreqCorner());
        this.setDwLowFreqOrder(nsaSegSourceInfo.getDwLowFreqOrder());
        this.setSzLowFilterType(nsaSegSourceInfo.getSzLowFilterType());
        this.setSzProbeInfo(nsaSegSourceInfo.getSzProbeInfo());
        return new Const_values().getNs_OK();
    }
}
