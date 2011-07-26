/**
 * 
 */
package jp.atr.dni.bmi.desktop.neuroshareutils.nsn;

/**
 *
 * @author kharada
 * @version 2011/01/13
 */
public class NSNTagElement {

    private int dwElementType;
    private int dwElemLength;

    /**
     * @param dwElementType
     */
    public NSNTagElement(NSNEntityType dwElementType) {
        this.dwElementType = dwElementType.ordinal();
        this.dwElemLength = 0;
    }

    /**
     * @return the dwElementType
     */
    public int getDwElementType() {
        return dwElementType;
    }

    /**
     * @return the dwElemLength
     */
    public int getDwElemLength() {
        return dwElemLength;
    }

    /**
     * @param dwElemLength the dwElemLength to add
     */
    public void addDwElemLength(int dwElemLength) {
        this.dwElemLength += dwElemLength;
    }
}
