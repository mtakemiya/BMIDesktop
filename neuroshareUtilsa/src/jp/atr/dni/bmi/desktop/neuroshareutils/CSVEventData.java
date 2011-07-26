/**
 * 
 */
package jp.atr.dni.bmi.desktop.neuroshareutils;

/**
 *
 * @author Keiji Harada [*1]</br>[*1] ATR Intl. Conputational Neuroscience Labs, Decoding Group
 * @version 2011/04/22
 */
public class CSVEventData extends EventData {

    /**
     *
     * @param dTimestamp
     * @param dwDataByteSize
     */
    public CSVEventData(double dTimestamp, long dwDataByteSize) {
        super(dTimestamp, dwDataByteSize);
    }
}
