package jp.atr.dni.bmi.desktop.neuroshareutils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Keiji Harada [*1]</br>[*1] ATR Intl. Conputational Neuroscience Labs, Decoding Group
 * @version 2011/04/22
 */
public class BMIUtils {

    /**
     *
     * @param arr
     * @return
     */
    public static double mean(double[] arr) {
        double sum = 0;
        for (int ndx = 0; ndx < arr.length; ndx++) {
            sum += arr[ndx];
        }

        return sum / arr.length;
    }

    /**
     *
     * @param iterationNb
     * @param password
     * @param shio
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
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

    /**
     *
     * @param b1
     * @param b2
     * @return
     */
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
