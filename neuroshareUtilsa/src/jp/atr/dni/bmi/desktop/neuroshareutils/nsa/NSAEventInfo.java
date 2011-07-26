/**
 * 
 */
package jp.atr.dni.bmi.desktop.neuroshareutils.nsa;

/**
 *
 * @author kharada
 * @version 2011/01/13
 */
public class NSAEventInfo {

    private String szCSVDesc;

    /**
     * @param szCSVDesc
     */
    public NSAEventInfo(String szCSVDesc) {
        this.szCSVDesc = szCSVDesc;
    }

    /**
     * @return the szCSVDesc
     */
    public String getSzCSVDesc() {
        return szCSVDesc;
    }

    /**
     * @param szCSVDesc the szCSVDesc to set
     */
    public void setSzCSVDesc(String szCSVDesc) {
        this.szCSVDesc = szCSVDesc;
    }
}
