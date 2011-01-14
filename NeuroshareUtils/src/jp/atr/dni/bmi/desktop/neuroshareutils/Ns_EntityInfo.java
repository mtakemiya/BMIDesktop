/**
 * 
 */
package jp.atr.dni.bmi.desktop.neuroshareutils;

/**
 *
 * @author kharada
 * @version 2011/01/13
 */
public class Ns_EntityInfo {

    String szEntityLabel;
    int dwEntityType;
    int dwItemCount;

    /**
     * @param szEntityLabel
     * @param dwEntityType
     */
    public Ns_EntityInfo(String szEntityLabel, Const_ns_ENTITY dwEntityType) {
        this.szEntityLabel = szEntityLabel;
        this.dwEntityType = dwEntityType.ordinal();
        this.dwItemCount = 0;
    }

    /**
     * @return the szEntityLabel
     */
    public String getSzEntityLabel() {
        return szEntityLabel;
    }

    /**
     * @return the dwEntityType
     */
    public int getDwEntityType() {
        return dwEntityType;
    }

    /**
     * @return the dwItemCount
     */
    public int getDwItemCount() {
        return dwItemCount;
    }

    /**
     * @param dwItemCount the dwItemCount to add
     */
    public void addDwItemCount(int dwItemCount) {
        this.dwItemCount += dwItemCount;
    }
}
