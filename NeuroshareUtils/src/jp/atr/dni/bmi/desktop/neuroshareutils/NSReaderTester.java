/**
 * 
 */
package jp.atr.dni.bmi.desktop.neuroshareutils;

/**
 *
 * @author kharada
 * @version 2011/01/24
 */
public class NSReaderTester {
    /*
     * @param args
     */

    public static void main(String[] args) {

        NSReader reader = new NSReader();
        NeuroshareFile nsn = reader.readNSFileAllData("test1.nsn");
    }
}
