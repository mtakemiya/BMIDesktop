/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.atr.dni.bmi.desktop.neuroshareutils;

/**
 *
 * @author Keiji Harada [*1]</br>[*1] ATR Intl. Conputational Neuroscience Labs, Decoding Group
 * @version 2011/04/22
 */
public class ByteEventData extends EventData {

    private byte data;

    /**
     *
     * @param dTimestamp
     * @param dwDataByteSize
     */
    public ByteEventData(double dTimestamp, long dwDataByteSize) {
        super(dTimestamp, dwDataByteSize);
    }

    /**
     * @param dTimestamp
     * @param dwDataByteSize
     * @param data
     */
    public ByteEventData(double dTimestamp, long dwDataByteSize, byte data) {
        super(dTimestamp, dwDataByteSize);
        this.data = data;
    }

    /**
     * @return the data
     */
    public byte getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(byte data) {
        this.data = data;
    }
}
