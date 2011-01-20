/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.atr.dni.bmi.desktop.neuroshareutils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author kharada
 */
public class TocUtils {

    public static void writeEventDataToTocFile(String tocPath, long eventDataType, ArrayList<EventData> data) throws IOException {
        File tocFile = new File(tocPath);
        FileOutputStream fos = null;
        DataOutputStream dos = null;

        if (!tocFile.exists()) {
            tocFile.createNewFile();
        }

        if (!tocFile.isFile() || !tocFile.canWrite()) {
            return;
        }


        for (int ii = 0; ii < data.size(); ii++) {
            fos = new FileOutputStream(tocFile, true);
            dos = new DataOutputStream(fos);

            double timestamp = data.get(ii).getTimestamp();
            int dataByteSize = (int) data.get(ii).getDataByteSize();

            // Write in BIG Endian (JAVA Default)
            dos.writeDouble(timestamp);
            dos.writeInt(dataByteSize);

            // Write in LITTLE Endian (MATLAB Default)
//            dos.writeDouble(timestamp);
//            dos.writeLong(Long.reverseBytes(Double.doubleToLongBits(timestamp)));
//            dos.writeInt(Integer.reverseBytes(dataByteSize));

            if (eventDataType == EventType.EVENT_TEXT.ordinal()) {
                // We are dealing with text
                TextEventData ted = (TextEventData) data.get(ii);
                String d = ted.getData();

                // Write in BIG Endian (JAVA Default)
                dos.writeBytes(d);

                // Write in LITTLE Endian (MATLAB Default)
//                dos.writeBytes(d);

            } else if (eventDataType == EventType.EVENT_BYTE.ordinal()) {
                // We are dealing with 1-byte values
                // NOTE: We use the wordevent data for 1 and 2 byte values, because both are stored as
                // ints.
                ByteEventData wed = (ByteEventData) data.get(ii);
                Byte d = (Byte) wed.getData();

                // Write in BIG Endian (JAVA Default)
                dos.write(d);

                // Write in LITTLE Endian (MATLAB Default)
//                dos.write(d);

            } else if (eventDataType == EventType.EVENT_WORD.ordinal()) {
                // We are dealing with 2-byte values
                WordEventData wed = (WordEventData) data.get(ii);
                Short d = ((Integer) wed.getData()).shortValue();

                // Write in BIG Endian (JAVA Default)
                dos.writeShort(d);

                // Write in LITTLE Endian (MATLAB Default)
//                dos.writeShort(Short.reverseBytes(d));

            } else if (eventDataType == EventType.EVENT_DWORD.ordinal()) {
                // We are dealing with 4-byte values
                DWordEventData dwed = (DWordEventData) data.get(ii);
                Integer d = ((Long) dwed.getData()).intValue();

                // Write in BIG Endian (JAVA Default)
                dos.writeInt(d);

                // Write in LITTLE Endian (MATLAB Default)
//                dos.writeInt(Integer.reverseBytes(d));

            } else {
                // We can't handle it, so just quit.
//            LOGGER.error("An unexpected event type was encountered, so we have to quit.");
                System.exit(1);
            }

            dos.close();
            fos.close();

        }


    }

    public static void writeAnalogDataToTocFile(String tocPath, ArrayList<AnalogData> data) throws IOException {
        File tocFile = new File(tocPath);
        FileOutputStream fos = null;
        DataOutputStream dos = null;

        if (!tocFile.exists()) {
            tocFile.createNewFile();
        }

        if (!tocFile.isFile() || !tocFile.canWrite()) {
            return;
        }

        for (int ii = 0; ii < data.size(); ii++) {
            fos = new FileOutputStream(tocFile, true);
            dos = new DataOutputStream(fos);

            double timestamp = data.get(ii).getTimeStamp();
            int dataCount = (int) data.get(ii).getDataCount();
            ArrayList<Double> ad = data.get(ii).getAnalogValues();

            // Write in BIG Endian (JAVA Default)
            dos.writeDouble(timestamp);
            dos.writeInt(dataCount);
            for (int jj = 0; jj < dataCount; jj++) {
                dos.writeDouble(ad.get(jj));
            }

            // Write in LITTLE Endian (MATLAB Default)
//            dos.writeLong(Long.reverseBytes(Double.doubleToLongBits(timestamp)));
//            dos.writeInt(Integer.reverseBytes(dataByteSize));
//
//            for (int jj = 0; jj < dataByteSize; jj++) {
//                dos.writeLong(Long.reverseBytes(Double.doubleToLongBits(ad.get(jj))));
//            }

            dos.close();
            fos.close();

        }

    }

    public static void writeSegmentDataToTocFile(String tocPath, SegmentData data) throws IOException {
        File tocFile = new File(tocPath);
        FileOutputStream fos = null;
        DataOutputStream dos = null;

        if (!tocFile.exists()) {
            tocFile.createNewFile();
        }

        if (!tocFile.isFile() || !tocFile.canWrite()) {
            return;
        }

        for (int ii = 0; ii < data.getSampleCount().size(); ii++) {
            fos = new FileOutputStream(tocFile, true);
            dos = new DataOutputStream(fos);

            int sampleCount = (int) data.getSampleCount().get(ii).intValue();
            double timeStamp = data.getTimeStamp().get(ii);
            int unitID = data.getUnitID().get(ii).intValue();
            ArrayList<Double> sd = data.getValues().get(ii);

            // Write in BIG Endian (JAVA Default)
            dos.writeInt(sampleCount);
            dos.writeDouble(timeStamp);
            dos.writeInt(unitID);
            for (int jj = 0; jj < sd.size(); jj++) {
                dos.writeDouble(sd.get(jj));
            }

            // Write in LITTLE Endian (MATLAB Default)
//            dos.writeInt(Integer.reverseBytes(sampleCount));
//            dos.writeLong(Long.reverseBytes(Double.doubleToLongBits(timeStamp)));
//            dos.writeInt(Integer.reverseBytes(unitID));
//
//            for (int jj = 0; jj < sd.size(); jj++) {
//                dos.writeLong(Long.reverseBytes(Double.doubleToLongBits(sd.get(jj))));
//            }

            dos.close();
            fos.close();

        }

    }

    public static void writeNeuralDataToTocFile(String tocPath, ArrayList<Double> data) throws IOException {
        File tocFile = new File(tocPath);
        FileOutputStream fos = null;
        DataOutputStream dos = null;

        if (!tocFile.exists()) {
            tocFile.createNewFile();
        }

        if (!tocFile.isFile() || !tocFile.canWrite()) {
            return;
        }

        for (int ii = 0; ii < data.size(); ii++) {
            fos = new FileOutputStream(tocFile, true);
            dos = new DataOutputStream(fos);

            double d = data.get(ii);

            // Write in BIG Endian (JAVA Default)
            dos.writeDouble(d);

            // Write in LITTLE Endian (MATLAB Default)
//            dos.writeLong(Long.reverseBytes(Double.doubleToLongBits(d)));

            dos.close();
            fos.close();

        }

    }

    public static ArrayList<TextEventData> readTextEventDataFromTocFile(String tocPath) throws FileNotFoundException, IOException {
        ArrayList<TextEventData> rtnData = new ArrayList<TextEventData>();
        File tocFile = new File(tocPath);
        FileInputStream fis = null;
        DataInputStream dis = null;

        if (!tocFile.exists()) {
            return null;
        }

        if (!tocFile.isFile() || !tocFile.canRead()) {
            return null;
        }

        fis = new FileInputStream(tocFile);
        dis = new DataInputStream(fis);

        while (true) {

            double timeStamp = 0;
            int dataByteSize = 0;
            String eventValue = "";

            // Read in BIG Endian (JAVA Default)
            try {
                timeStamp = dis.readDouble();
            } catch (EOFException ex) {
                //correct. break
                //Logger.getLogger(TocUtils.class.getName()).log(Level.SEVERE, null, ex);
                break;
            }
            dataByteSize = dis.readInt();
            eventValue = dis.readUTF();

            // Read in LITTLE Endian (MATLAB Default)
//            try {
//                timeStamp = ((Long)(Long.reverseBytes(dis.readLong()))).doubleValue();
//            } catch (EOFException ex) {
//                //correct. break
//                //Logger.getLogger(TocUtils.class.getName()).log(Level.SEVERE, null, ex);
//                break;
//            }
//            dataByteSize = Integer.reverseBytes(dis.readInt());
//            eventValue = dis.readUTF();
            
            rtnData.add(new TextEventData(timeStamp, dataByteSize, eventValue));
        }

        dis.close();
        fis.close();

        return rtnData;
    }

    public static ArrayList<ByteEventData> readByteEventDataFromTocFile(String tocPath) throws FileNotFoundException, IOException {
        ArrayList<ByteEventData> rtnData = new ArrayList<ByteEventData>();
        File tocFile = new File(tocPath);
        FileInputStream fis = null;
        DataInputStream dis = null;

        if (!tocFile.exists()) {
            return null;
        }

        if (!tocFile.isFile() || !tocFile.canRead()) {
            return null;
        }

        fis = new FileInputStream(tocFile);
        dis = new DataInputStream(fis);

        while (true) {

            double timeStamp = 0;
            int dataByteSize = 0;
            byte eventValue = 0;

            // Read in BIG Endian (JAVA Default)
            try {
                timeStamp = dis.readDouble();
            } catch (EOFException ex) {
                //correct. break
                //Logger.getLogger(TocUtils.class.getName()).log(Level.SEVERE, null, ex);
                break;
            }
            dataByteSize = dis.readInt();
            eventValue = dis.readByte();

            // Read in LITTLE Endian (MATLAB Default)
//            try {
//                timeStamp = ((Long)(Long.reverseBytes(dis.readLong()))).doubleValue();
//            } catch (EOFException ex) {
//                //correct. break
//                //Logger.getLogger(TocUtils.class.getName()).log(Level.SEVERE, null, ex);
//                break;
//            }
//            dataByteSize = Integer.reverseBytes(dis.readInt());
//            eventValue = dis.readByte();

            rtnData.add(new ByteEventData(timeStamp, dataByteSize, eventValue));
        }

        dis.close();
        fis.close();

        return rtnData;
    }

    public static ArrayList<WordEventData> readWordEventDataFromTocFile(String tocPath) throws FileNotFoundException, IOException {
        ArrayList<WordEventData> rtnData = new ArrayList<WordEventData>();
        File tocFile = new File(tocPath);
        FileInputStream fis = null;
        DataInputStream dis = null;

        if (!tocFile.exists()) {
            return null;
        }

        if (!tocFile.isFile() || !tocFile.canRead()) {
            return null;
        }

        fis = new FileInputStream(tocFile);
        dis = new DataInputStream(fis);

        while (true) {

            double timeStamp = 0;
            int dataByteSize = 0;
            short eventValue = 0;

            // Read in BIG Endian (JAVA Default)
            try {
                timeStamp = dis.readDouble();
            } catch (EOFException ex) {
                //correct. break
                //Logger.getLogger(TocUtils.class.getName()).log(Level.SEVERE, null, ex);
                break;
            }
            dataByteSize = dis.readInt();
            eventValue = dis.readShort();

            // Read in LITTLE Endian (MATLAB Default)
//            try {
//                timeStamp = ((Long)(Long.reverseBytes(dis.readLong()))).doubleValue();
//            } catch (EOFException ex) {
//                //correct. break
//                //Logger.getLogger(TocUtils.class.getName()).log(Level.SEVERE, null, ex);
//                break;
//            }
//            dataByteSize = Integer.reverseBytes(dis.readInt());
//            eventValue = Short.reverseBytes(dis.readShort());

            rtnData.add(new WordEventData(timeStamp, dataByteSize, eventValue));
        }

        dis.close();
        fis.close();

        return rtnData;
    }

    public static ArrayList<DWordEventData> readDWordEventDataFromTocFile(String tocPath) throws FileNotFoundException, IOException {
        ArrayList<DWordEventData> rtnData = new ArrayList<DWordEventData>();
        File tocFile = new File(tocPath);
        FileInputStream fis = null;
        DataInputStream dis = null;

        if (!tocFile.exists()) {
            return null;
        }

        if (!tocFile.isFile() || !tocFile.canRead()) {
            return null;
        }

        fis = new FileInputStream(tocFile);
        dis = new DataInputStream(fis);

        while (true) {

            double timeStamp = 0;
            int dataByteSize = 0;
            int eventValue = 0;

            // Read in BIG Endian (JAVA Default)
            try {
                timeStamp = dis.readDouble();
            } catch (EOFException ex) {
                //correct. break
                //Logger.getLogger(TocUtils.class.getName()).log(Level.SEVERE, null, ex);
                break;
            }
            dataByteSize = dis.readInt();
            eventValue = dis.readInt();

            // Read in LITTLE Endian (MATLAB Default)
//            try {
//                timeStamp = ((Long)(Long.reverseBytes(dis.readLong()))).doubleValue();
//            } catch (EOFException ex) {
//                //correct. break
//                //Logger.getLogger(TocUtils.class.getName()).log(Level.SEVERE, null, ex);
//                break;
//            }
//            dataByteSize = Integer.reverseBytes(dis.readInt());
//            eventValue = Integer.reverseBytes(dis.readInt());

            rtnData.add(new DWordEventData(timeStamp, dataByteSize, ((Integer)eventValue).longValue()));
        }

        dis.close();
        fis.close();

        return rtnData;
    }

    public static ArrayList<AnalogData> readAnalogDataFromTocFile(String tocPath) throws FileNotFoundException, IOException {
        ArrayList<AnalogData> rtnData = new ArrayList<AnalogData>();
        File tocFile = new File(tocPath);
        FileInputStream fis = null;
        DataInputStream dis = null;

        if (!tocFile.exists()) {
            return null;
        }

        if (!tocFile.isFile() || !tocFile.canRead()) {
            return null;
        }

        fis = new FileInputStream(tocFile);
        dis = new DataInputStream(fis);

        while (true) {

            double timeStamp = 0;
            int dataCount = 0;
            ArrayList<Double> analogValue = new ArrayList<Double>();

            // Read in BIG Endian (JAVA Default)
            try {
                timeStamp = dis.readDouble();
            } catch (EOFException ex) {
                //correct. break
                //Logger.getLogger(TocUtils.class.getName()).log(Level.SEVERE, null, ex);
                break;
            }
            dataCount = dis.readInt();
            for (int jj = 0; jj < dataCount; jj++) {
                analogValue.add(dis.readDouble());
            }

            // Read in LITTLE Endian (MATLAB Default)
//            try {
//                timeStamp = ((Long)(Long.reverseBytes(dis.readLong()))).doubleValue();
//            } catch (EOFException ex) {
//                //correct. break
//                //Logger.getLogger(TocUtils.class.getName()).log(Level.SEVERE, null, ex);
//                break;
//            }
//            dataByteSize = Integer.reverseBytes(dis.readInt());
//            for (int jj = 0; jj < dataByteSize; jj++) {
//                analogValue.add(((Long)(Long.reverseBytes(dis.readLong()))).doubleValue());
//            }

            rtnData.add(new AnalogData(timeStamp, dataCount, analogValue));
        }

        dis.close();
        fis.close();

        return rtnData;
    }

    public static SegmentData readSegmentDataFromTocFile(String tocPath) throws FileNotFoundException, IOException {
        SegmentData rtnData = new SegmentData();
        File tocFile = new File(tocPath);
        FileInputStream fis = null;
        DataInputStream dis = null;

        if (!tocFile.exists()) {
            return null;
        }

        if (!tocFile.isFile() || !tocFile.canRead()) {
            return null;
        }

        fis = new FileInputStream(tocFile);
        dis = new DataInputStream(fis);

        while (true) {

            int sampleCount = 0;
            double timeStamp = 0;
            int unitID = 0;
            ArrayList<Double> segmentValue = new ArrayList<Double>();

            // Read in BIG Endian (JAVA Default)
            try {
                sampleCount = dis.readInt();
            } catch (EOFException ex) {
                //correct. break
                //Logger.getLogger(TocUtils.class.getName()).log(Level.SEVERE, null, ex);
                break;
            }
            timeStamp = dis.readDouble();
            unitID = dis.readInt();
            for (int jj = 0; jj < sampleCount; jj++) {
                segmentValue.add(dis.readDouble());
            }
            // Read in LITTLE Endian (MATLAB Default)
//            try {
//                sampleCount = Integer.reverseBytes(dis.readInt());
//            } catch (EOFException ex) {
//                //correct. break
//                //Logger.getLogger(TocUtils.class.getName()).log(Level.SEVERE, null, ex);
//                break;
//            }
//            timeStamp = ((Long) (Long.reverseBytes(dis.readLong()))).doubleValue();
//            unitID = Integer.reverseBytes(dis.readInt());
//            for (int jj = 0; jj < sampleCount; jj++) {
//                segmentValue.add(((Long) (Long.reverseBytes(dis.readLong()))).doubleValue());
//            }

            ArrayList<Long> arraySampleCount = rtnData.getSampleCount();
            ArrayList<Double> arrayTimeStamp = rtnData.getTimeStamp();
            ArrayList<Long> arrayUnitID = rtnData.getUnitID();
            ArrayList<ArrayList<Double>> arraySegmentValues = rtnData.getValues();

            if (arraySampleCount == null) {
                arraySampleCount = new ArrayList<Long>();
            }
            if (arrayTimeStamp == null) {
                arrayTimeStamp = new ArrayList<Double>();
            }
            if (arrayUnitID == null) {
                arrayUnitID = new ArrayList<Long>();
            }
            if (arraySegmentValues == null) {
                arraySegmentValues = new ArrayList<ArrayList<Double>>();
            }

            arraySampleCount.add(((Integer) sampleCount).longValue());
            arrayTimeStamp.add(timeStamp);
            arrayUnitID.add(((Integer) unitID).longValue());
            arraySegmentValues.add(segmentValue);

            rtnData.setSampleCount(arraySampleCount);
            rtnData.setTimeStamp(arrayTimeStamp);
            rtnData.setUnitID(arrayUnitID);
            rtnData.setValues(arraySegmentValues);
        }

        dis.close();
        fis.close();

        return rtnData;
    }

    public static ArrayList<Double> readNeuralDataFromTocFile(String tocPath) throws FileNotFoundException, IOException {
        ArrayList<Double> rtnData = new ArrayList<Double>();
        File tocFile = new File(tocPath);
        FileInputStream fis = null;
        DataInputStream dis = null;

        if (!tocFile.exists()) {
            return null;
        }

        if (!tocFile.isFile() || !tocFile.canRead()) {
            return null;
        }

        fis = new FileInputStream(tocFile);
        dis = new DataInputStream(fis);

        while (true) {

            double neuralValue = 0;

            // Read in BIG Endian (JAVA Default)
            try {
                neuralValue = dis.readDouble();
            } catch (EOFException ex) {
                //correct. break
                //Logger.getLogger(TocUtils.class.getName()).log(Level.SEVERE, null, ex);
                break;
            }

            // Read in LITTLE Endian (MATLAB Default)
//            try {
//               neuralValue = ((Long)(Long.reverseBytes(dis.readLong()))).doubleValue();
//            } catch (EOFException ex) {
//                //correct. break
//                //Logger.getLogger(TocUtils.class.getName()).log(Level.SEVERE, null, ex);
//                break;
//            }

            rtnData.add(neuralValue);
        }

        dis.close();
        fis.close();

        return rtnData;
    }
}
