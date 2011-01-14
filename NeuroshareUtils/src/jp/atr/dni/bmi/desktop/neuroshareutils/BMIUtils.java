package jp.atr.dni.bmi.desktop.neuroshareutils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author kharada
 * @version 2011/01/13
 */
public class BMIUtils {

    public static double mean(double[] arr) {
        double sum = 0;
        for (int ndx = 0; ndx < arr.length; ndx++) {
            sum += arr[ndx];
        }

        return sum / arr.length;
    }

    public static byte[] getHash(int iterationNb, String password, byte[] shio)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest digest = MessageDigest.getInstance("SHA-512");
        digest.reset();
        digest.update(shio);
        byte[] input = digest.digest(password.getBytes("UTF-8"));
        for (int i = 0; i < iterationNb; i++) {
            digest.reset();
            input = digest.digest(input);
        }
        return input;
    }

    public static boolean areEqual(byte[] b1, byte[] b2) {
        if (b1 == null || b2 == null || b1.length != b2.length) {
            return false;
        }

        for (int i = 0; i < b1.length; i++) {
            if (b1[i] != b2[i]) {
                return false;
            }
        }
        return true;
    }
}
