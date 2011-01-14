/**
 * 
 */
package jp.atr.dni.bmi.desktop.neuroshareutils;

/**
 *
 * @author kharada
 * @version 2011/01/13
 */
public class Ns_TagElement {

    int dwElementType;
    int dwElemLength;

    /**
     * @param dwElementType
     */
    public Ns_TagElement(Const_ns_ENTITY dwElementType) {
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
