/**
 * 
 */
package jp.atr.dni.bmi.desktop.neuroshareutils;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author Keiji Harada [*1]</br>[*1] ATR Intl. Conputational Neuroscience Labs, Decoding Group
 * @version 2011/04/22
 */
public class ReaderUtils {

   /**
    *
    * @param file
    * @return
    * @throws IOException
    */
   public static short readShort(RandomAccessFile file) throws IOException {
      short myInt = 0;
      int tempInt;
      byte[] uInt = new byte[2];
      file.readFully(uInt, 0, 2);
      for (int i = 1; i >= 0; i--) {
         tempInt = (int) uInt[i] & 0xFF;
         myInt <<= 8;
         myInt |= tempInt & 0xFFL;
      }
      return myInt;
   }

   /**
    *
    * @param file
    * @return
    * @throws IOException
    */
   public static long readUnsignedInt(RandomAccessFile file) throws IOException {
      long myLong = 0;
      int myInt;
      byte[] uInt = new byte[4];

      file.readFully(uInt, 0, 4);
      for (int i = 3; i >= 0; i--) {
         myInt = (int) uInt[i] & 0xFF;
         myLong <<= 8;
         myLong |= (long) myInt & 0xffffffffL;
      }
      return myLong;
   }

   /**
    *
    * @param file
    * @return
    * @throws IOException
    */
   public static int readInt(RandomAccessFile file) throws IOException {
      int temp = 0;
      int myInt;
      byte[] inttemp = new byte[4];

      file.readFully(inttemp, 0, 4);
      for (int i = 3; i >= 0; i--) {
         myInt = inttemp[i] & 0xFF;
         temp <<= 8;
         temp |= myInt & 0xffffffffL;
      }
      return temp;
   }

   /**
    *
    * @param file
    * @return
    * @throws IOException
    */
   public static double readDouble(RandomAccessFile file) throws IOException {
      long myLong = 0;
      int myInt;
      byte[] uInt = new byte[8];

      file.readFully(uInt, 0, 8);
      for (int i = 7; i >= 0; i--) {
         myInt = (int) uInt[i] & 0xFF;
         myLong <<= 8;
         myLong |= (long) myInt;
      }

      double d = Double.longBitsToDouble(myLong);
      if (d == Double.NaN) {
         d = 0;
//         System.out.println(d);
      }

      // file.readDouble();
      // System.out.println(Long.toBinaryString(myLong));
      return d;
   }
}
