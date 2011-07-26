/**
 * 
 */
package jp.atr.dni.bmi.desktop.neuroshareutils;

/**
 *
 * @author Keiji Harada [*1]</br>[*1] ATR Intl. Conputational Neuroscience Labs, Decoding Group
 * @version 2011/04/22
 */
public class WordEventData extends EventData {

    private int data;

    /**
     *
     * @param dTimestamp
     * @param dwDataByteSize
     */
    public WordEventData(double dTimestamp, long dwDataByteSize) {
        super(dTimestamp, dwDataByteSize);
    }

    /**
     * @param dTimestamp
     * @param dwDataByteSize
     * @param data
     */
    public WordEventData(double dTimestamp, long dwDataByteSize, int data) {
        super(dTimestamp, dwDataByteSize);
        this.data = data;
    }

    /**
     * @return the data
     */
    public int getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(int data) {
        this.data = data;
    }
}
