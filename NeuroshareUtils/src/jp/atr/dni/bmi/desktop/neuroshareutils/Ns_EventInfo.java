/**
 * 
 */
package jp.atr.dni.bmi.desktop.neuroshareutils;

/**
 *
 * @author kharada
 * @version 2011/01/13
 */
public class Ns_EventInfo {

    int dwEventType;
    int dwMinDataLength;
    int dwMaxDataLength;
    String szCSVDesc;

    /**
     *
     */
    public Ns_EventInfo() {
        this.dwEventType = Const_ns_EVENT.ns_EVENT_TEXT.ordinal();
        this.dwMinDataLength = Integer.MAX_VALUE;
        this.dwMaxDataLength = 0;
    }

    /**
     * @return the dwEventType
     */
    public int getDwEventType() {
        return dwEventType;
    }

    /**
     * @param dwEventType the dwEventType to set
     */
    public void setDwEventType(Const_ns_EVENT dwEventType) {
        this.dwEventType = dwEventType.ordinal();
    }

    /**
     * @return the dwMinDataLength
     */
    public int getDwMinDataLength() {
        return dwMinDataLength;
    }

    /**
     * @param dwMinDataLength the dwMinDataLength to set
     */
    public void setDwMinDataLength(int dwMinDataLength) {
        this.dwMinDataLength = dwMinDataLength;
    }

    /**
     * @return the dwMaxDataLength
     */
    public int getDwMaxDataLength() {
        return dwMaxDataLength;
    }

    /**
     * @param dwMaxDataLength the dwMaxDataLength to set
     */
    public void setDwMaxDataLength(int dwMaxDataLength) {
        this.dwMaxDataLength = dwMaxDataLength;
    }

    /**
     * @return the szCSVDesc
     */
    private String getSzCSVDesc() {
        return szCSVDesc;
    }

    /**
     * @param szCSVDesc the szCSVDesc to set
     */
    private void setSzCSVDesc(String szCSVDesc) {
        this.szCSVDesc = szCSVDesc;
    }

    /**
     * @return members
     */
    public Nsa_EventInfo getMembers() {
        return new Nsa_EventInfo(this.getSzCSVDesc());
    }

    public int setMembers(Nsa_EventInfo nsaEventInfo) {
        this.setSzCSVDesc(nsaEventInfo.getSzCSVDesc());
        return new Const_values().getNs_OK();
    }
}
