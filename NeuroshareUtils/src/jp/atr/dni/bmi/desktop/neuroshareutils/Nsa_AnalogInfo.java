/**
 * 
 */
package jp.atr.dni.bmi.desktop.neuroshareutils;

/**
 *
 * @author kharada
 * @version 2011/01/13
 */
public class Nsa_AnalogInfo {

    double dSampleRate;
    double dMinVal;
    double dMaxVal;
    String szUnits;
    double dResolution;
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
     * @param dSampleRate
     * @param dMinVal
     * @param dMaxVal
     * @param szUnits
     * @param dResolution
     * @param dLocationX
     * @param dLocationY
     * @param dLocationZ
     * @param dLocationUser
     * @param dHighFreqCorner
     * @param dwHighFreqOrder
     * @param szHighFilterType
     * @param dLowFreqCorner
     * @param dwLowFreqOrder
     * @param szLowFilterType
     * @param szProbeInfo
     */
    public Nsa_AnalogInfo(double dSampleRate, double dMinVal, double dMaxVal, String szUnits,
            double dResolution, double dLocationX, double dLocationY, double dLocationZ, double dLocationUser,
            double dHighFreqCorner, int dwHighFreqOrder, String szHighFilterType, double dLowFreqCorner,
            int dwLowFreqOrder, String szLowFilterType, String szProbeInfo) {
        this.dSampleRate = dSampleRate;
        this.dMinVal = dMinVal;
        this.dMaxVal = dMaxVal;
        this.szUnits = szUnits;
        this.dResolution = dResolution;
        this.dLocationX = dLocationX;
        this.dLocationY = dLocationY;
        this.dLocationZ = dLocationZ;
        this.dLocationUser = dLocationUser;
        this.dHighFreqCorner = dHighFreqCorner;
        this.dwHighFreqOrder = dwHighFreqOrder;
        this.szHighFilterType = szHighFilterType;
        this.dLowFreqCorner = dLowFreqCorner;
        this.dwLowFreqOrder = dwLowFreqOrder;
        this.szLowFilterType = szLowFilterType;
        this.szProbeInfo = szProbeInfo;
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

    /**
     * @return the dResolution
     */
    public double getDResolution() {
        return dResolution;
    }

    /**
     * @param dResolution the dResolution to set
     */
    public void setDResolution(double dResolution) {
        this.dResolution = dResolution;
    }

    /**
     * @return the dLocationX
     */
    public double getDLocationX() {
        return dLocationX;
    }

    /**
     * @param dLocationX the dLocationX to set
     */
    public void setDLocationX(double dLocationX) {
        this.dLocationX = dLocationX;
    }

    /**
     * @return the dLocationY
     */
    public double getDLocationY() {
        return dLocationY;
    }

    /**
     * @param dLocationY the dLocationY to set
     */
    public void setDLocationY(double dLocationY) {
        this.dLocationY = dLocationY;
    }

    /**
     * @return the dLocationZ
     */
    public double getDLocationZ() {
        return dLocationZ;
    }

    /**
     * @param dLocationZ the dLocationZ to set
     */
    public void setDLocationZ(double dLocationZ) {
        this.dLocationZ = dLocationZ;
    }

    /**
     * @return the dLocationUser
     */
    public double getDLocationUser() {
        return dLocationUser;
    }

    /**
     * @param dLocationUser the dLocationUser to set
     */
    public void setDLocationUser(double dLocationUser) {
        this.dLocationUser = dLocationUser;
    }

    /**
     * @return the dHighFreqCorner
     */
    public double getDHighFreqCorner() {
        return dHighFreqCorner;
    }

    /**
     * @param dHighFreqCorner the dHighFreqCorner to set
     */
    public void setDHighFreqCorner(double dHighFreqCorner) {
        this.dHighFreqCorner = dHighFreqCorner;
    }

    /**
     * @return the dwHighFreqOrder
     */
    public int getDwHighFreqOrder() {
        return dwHighFreqOrder;
    }

    /**
     * @param dwHighFreqOrder the dwHighFreqOrder to set
     */
    public void setDwHighFreqOrder(int dwHighFreqOrder) {
        this.dwHighFreqOrder = dwHighFreqOrder;
    }

    /**
     * @return the szHighFilterType
     */
    public String getSzHighFilterType() {
        return szHighFilterType;
    }

    /**
     * @param szHighFilterType the szHighFilterType to set
     */
    public void setSzHighFilterType(String szHighFilterType) {
        this.szHighFilterType = szHighFilterType;
    }

    /**
     * @return the dLowFreqCorner
     */
    public double getDLowFreqCorner() {
        return dLowFreqCorner;
    }

    /**
     * @param dLowFreqCorner the dLowFreqCorner to set
     */
    public void setDLowFreqCorner(double dLowFreqCorner) {
        this.dLowFreqCorner = dLowFreqCorner;
    }

    /**
     * @return the dwLowFreqOrder
     */
    public int getDwLowFreqOrder() {
        return dwLowFreqOrder;
    }

    /**
     * @param dwLowFreqOrder the dwLowFreqOrder to set
     */
    public void setDwLowFreqOrder(int dwLowFreqOrder) {
        this.dwLowFreqOrder = dwLowFreqOrder;
    }

    /**
     * @return the szLowFilterType
     */
    public String getSzLowFilterType() {
        return szLowFilterType;
    }

    /**
     * @param szLowFilterType the szLowFilterType to set
     */
    public void setSzLowFilterType(String szLowFilterType) {
        this.szLowFilterType = szLowFilterType;
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
