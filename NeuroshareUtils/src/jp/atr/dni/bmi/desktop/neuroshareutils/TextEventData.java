/**
 * 
 */
package jp.atr.dni.bmi.desktop.neuroshareutils;

/**
 *
 * @author Keiji Harada [*1]</br>[*1] ATR Intl. Conputational Neuroscience Labs, Decoding Group
 * @version 2011/04/22
 */
public class TextEventData extends EventData {

    private String data;

    /**
     * @param dTimestamp
     * @param dwDataByteSize
     */
    public TextEventData(double dTimestamp, long dwDataByteSize) {
        super(dTimestamp, dwDataByteSize);
    }

    /**
     * @param dTimestamp
     * @param dwDataByteSize
     * @param data
     */
    public TextEventData(double dTimestamp, long dwDataByteSize, String data) {
        super(dTimestamp, dwDataByteSize);
        this.data = data;
    }

    /**
     * @return the data
     */
    public String getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(String data) {
        this.data = data;
    }
}
