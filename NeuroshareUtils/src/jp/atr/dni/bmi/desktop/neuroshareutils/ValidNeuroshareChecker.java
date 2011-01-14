package jp.atr.dni.bmi.desktop.neuroshareutils;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author kharada
 * @version 2011/01/13
 */
public class ValidNeuroshareChecker {

    public static boolean isValidNSNFile(String path) {
        RandomAccessFile file = null;
        try {
            file = new RandomAccessFile(path, "r");
            file.seek(0);

            String magicCode = "";
            for (int i = 0; i < 16; i++) {
                magicCode += (char) file.readByte();
            }
            return magicCode.equals("NSN ver00000010 ");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
