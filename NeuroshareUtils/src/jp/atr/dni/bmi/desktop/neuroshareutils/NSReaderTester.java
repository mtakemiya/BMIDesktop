/**
 * 
 */
package jp.atr.dni.bmi.desktop.neuroshareutils;

/**
 *
 * @author kharada
 * @version 2011/01/13
 */
public class NSReaderTester {
    /*
     * @param args
     */

    public static void main(String[] args) {

        NSReader reader = new NSReader();
        NeuroshareFile nsn = reader.readNSFile("test1.nsn", false);
    }
}
