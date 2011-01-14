/**
 * 
 */
package jp.atr.dni.bmi.desktop.neuroshareutils;

/**
 *
 * @author kharada
 * @version 2011/01/13
 */
public class Nsa_EventInfo {

    String szCSVDesc;

    /**
     * @param szCSVDesc
     */
    public Nsa_EventInfo(String szCSVDesc) {
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
