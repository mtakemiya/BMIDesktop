/**
 * 
 */
package jp.atr.dni.bmi.desktop.neuroshareutils.readers;

import jp.atr.dni.bmi.desktop.neuroshareutils.NeuroshareFile;

/**
 *
 * @author Keiji Harada [*1]</br>[*1] ATR Intl. Conputational Neuroscience Labs, Decoding Group
 * @version 2011/04/22
 */
public class NSReaderTester {
    /*
     * @param args
     */

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        NSReader reader = new NSReader();
        NeuroshareFile nsn = reader.readNSFileAllData("test1.nsn");
    }
}
