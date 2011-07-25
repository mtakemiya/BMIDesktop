/**
 * 
 */
package jp.atr.dni.bmi.desktop.neuroshareutils.nsn;

/**
 *
 * @author kharada
 * @version 2011/01/13
 */
public class NSNEntityInfo {

    private String szEntityLabel;
    private int dwEntityType;
    private int dwItemCount;

    /**
     * @param szEntityLabel
     * @param dwEntityType
     */
    public NSNEntityInfo(String szEntityLabel, NSNEntityType dwEntityType) {
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
