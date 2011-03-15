/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.atr.dni.bmi.desktop.workingfileutils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import jp.atr.dni.bmi.desktop.neuroshareutils.AnalogData;
import jp.atr.dni.bmi.desktop.neuroshareutils.AnalogInfo;
import jp.atr.dni.bmi.desktop.neuroshareutils.ByteEventData;
import jp.atr.dni.bmi.desktop.neuroshareutils.DWordEventData;
import jp.atr.dni.bmi.desktop.neuroshareutils.Entity;
import jp.atr.dni.bmi.desktop.neuroshareutils.EventData;
import jp.atr.dni.bmi.desktop.neuroshareutils.EventInfo;
import jp.atr.dni.bmi.desktop.neuroshareutils.NSReader;
import jp.atr.dni.bmi.desktop.neuroshareutils.SegmentData;
import jp.atr.dni.bmi.desktop.neuroshareutils.SegmentInfo;
import jp.atr.dni.bmi.desktop.neuroshareutils.TextEventData;
import jp.atr.dni.bmi.desktop.neuroshareutils.WordEventData;

/**
 *
 * @author kharada
 */
public class NSCSVWriter {

    public void createCSVFileFromNeuroshare(String workingFilePath, String sourceFilePath, Entity entity) {
        int entityType = (int) entity.getEntityInfo().getEntityType();
        switch (entityType) {
            case 0:
                break;
            case 1:
                // Event
                createTLFile(workingFilePath, sourceFilePath, entity);
                break;
            case 2:
                // Analog
                createTSFile(workingFilePath, sourceFilePath, entity);
                break;
            case 3:
                // Segment
                createTIFile(workingFilePath, sourceFilePath, entity);
                break;
            case 4:
                // Neural
                createTOFile(workingFilePath, sourceFilePath, entity);
                break;
            case 5:
                break;
            default:
                break;
        }
    }

    private void createTSFile(String workingFilePath, String sourceFilePath, Entity entity) {

        String co = ",";
        String formatCode = "TS";
        double samplingRate = ((AnalogInfo) entity).getSampleRate();
        BufferedWriter bw = null;

        try {
            // BufferedWriter to write.
            bw = new BufferedWriter(new FileWriter(workingFilePath));

            // First Line : TS, <SamplingRate>
            bw.write(formatCode + co + samplingRate);
            bw.newLine();

            // Read Neuroshare Data.
            NSReader nsReader = new NSReader();
            ArrayList<AnalogData> analogData = nsReader.getAnalogData(sourceFilePath, entity.getEntityInfo());

            // Write Data to the workingFile(CSV).
            for (int ii = 0; ii < analogData.size(); ii++) {

                // 2nd Line : <timestamp>, <AnalogValue[0]>, <AnalogValue[1]>, ... <AnalogValue[n]>
                AnalogData ad = analogData.get(ii);
                bw.write(((Double) ad.getTimeStamp()).toString());
                ArrayList<Double> analogValues = ad.getAnalogValues();
                for (int jj = 0; jj < analogValues.size(); jj++) {
                    bw.write(co + analogValues.get(jj));
                }
                bw.newLine();
            }
            bw.close();
        } catch (IOException iOException) {
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException iOException) {
            }
        }
    }

    private void createTIFile(String workingFilePath, String sourceFilePath, Entity entity) {

        String co = ",";
        String formatCode = "TI";
        double samplingRate = ((SegmentInfo) entity).getSampleRate();
        BufferedWriter bw = null;

        try {
            // BufferedWriter to write.
            bw = new BufferedWriter(new FileWriter(workingFilePath));

            // First Line : TI, <SamplingRate>
            bw.write(formatCode + co + samplingRate);
            bw.newLine();

            // Read Neuroshare Data.
            NSReader nsReader = new NSReader();
            SegmentData segmentData = nsReader.getSegmentData(sourceFilePath, entity.getEntityInfo(), (SegmentInfo) entity);

            // Write Data to the workingFile(CSV).
            for (int ii = 0; ii < segmentData.getTimeStamp().size(); ii++) {
                // 2nd Line : <timestamp>, <dwUnitID>, <SegmentValue[0]>, <SegmentValue[1]>, ... <SegmentValue[n]>
                Double timeStamp = segmentData.getTimeStamp().get(ii);
                bw.write(timeStamp + co);
                Long unitID = segmentData.getUnitID().get(ii);
                bw.write(unitID.toString());

                ArrayList<Double> segmentValues = segmentData.getValues().get(ii);
                for (int jj = 0; jj < segmentValues.size(); jj++) {
                    bw.write(co + segmentValues.get(jj));
                }
                bw.newLine();
            }
            bw.close();
        } catch (IOException iOException) {
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException iOException) {
            }
        }
    }

    private void createTLFile(String workingFilePath, String sourceFilePath, Entity entity) {

        String co = ",";
        String formatCode = "TL";
        BufferedWriter bw = null;

        try {
            // BufferedWriter to write.
            bw = new BufferedWriter(new FileWriter(workingFilePath));

            // First Line : TL
            bw.write(formatCode);
            bw.newLine();

            // Read Neuroshare Data.
            NSReader nsReader = new NSReader();
            ArrayList<EventData> eventData = nsReader.getEventData(sourceFilePath, entity.getEntityInfo(), (EventInfo) entity);

            // Write Data to the workingFile(CSV).
            for (int ii = 0; ii < eventData.size(); ii++) {
                // 2nd Line : <timestamp>, <EventValue>
                Double timeStamp = eventData.get(ii).getTimestamp();
                bw.write(timeStamp + co);

                EventInfo ei = (EventInfo) entity;

                switch ((int) ei.getEventType()) {
                    case 0:
                        // Get Event Data
                        TextEventData ted = (TextEventData) eventData.get(ii);
                        // ns_EVENT_TEXT
                        bw.write(ted.getData());
                        break;
                    case 1:
                        // ns_EVENT_CSV
                        // Nothing in Model.
                        break;
                    case 2:
                        // Get Event Data
                        ByteEventData bed = (ByteEventData) eventData.get(ii);
                        // ns_EVENT_BYTE
                        bw.write(((Byte) bed.getData()).toString());
                        break;
                    case 3:
                        // Get Event Data
                        WordEventData wed = (WordEventData) eventData.get(ii);
                        // ns_EVENT_WORD
                        bw.write(((Integer) wed.getData()).toString());
                        break;
                    case 4:
                        // Get Event Data
                        DWordEventData dwed = (DWordEventData) eventData.get(ii);
                        // ns_EVENT_DWORD
                        bw.write(dwed.getData().toString());
                        break;
                    default:
                        break;

                }
                bw.newLine();
            }
            bw.close();
        } catch (IOException iOException) {
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException iOException) {
            }
        }
    }

    private void createTOFile(String workingFilePath, String sourceFilePath, Entity entity) {

        String co = ",";
        String formatCode = "TO";
        BufferedWriter bw = null;

        try {
            // BufferedWriter to write.
            bw = new BufferedWriter(new FileWriter(workingFilePath));

            // First Line : TO
            bw.write(formatCode);
            bw.newLine();

            // Read Neuroshare Data.
            NSReader nsReader = new NSReader();
            ArrayList<Double> neuralData = nsReader.getNeuralData(sourceFilePath, entity.getEntityInfo());

            // Write Data to the workingFile(CSV).
            for (int ii = 0; ii < neuralData.size(); ii++) {

                // 2nd Line : <timestamp>
                bw.write(neuralData.get(ii).toString());
                bw.newLine();
            }
            bw.close();
        } catch (IOException iOException) {
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException iOException) {
            }
        }
    }

    public boolean overwriteTSFile(String workingFilePath, ArrayList<AnalogData> analogData, Entity entity) {

        // if working file didn't exists or cannot be deleted, then false.
        File file = new File(workingFilePath);
        if (!file.exists()) {
            return false;
        }
        if (!file.delete()) {
            return false;
        }

        boolean result = true;
        String co = ",";
        String formatCode = "TS";
        double samplingRate = ((AnalogInfo) entity).getSampleRate();
        BufferedWriter bw = null;

        try {
            // BufferedWriter to write.
            bw = new BufferedWriter(new FileWriter(workingFilePath));

            // First Line : TS, <SamplingRate>
            bw.write(formatCode + co + samplingRate);
            bw.newLine();

            // Write Data to the workingFile(CSV).
            for (int ii = 0; ii < analogData.size(); ii++) {

                // 2nd Line : <timestamp>, <AnalogValue[0]>, <AnalogValue[1]>, ... <AnalogValue[n]>
                AnalogData ad = analogData.get(ii);
                bw.write(((Double) ad.getTimeStamp()).toString());
                ArrayList<Double> analogValues = ad.getAnalogValues();
                for (int jj = 0; jj < analogValues.size(); jj++) {
                    bw.write(co + analogValues.get(jj));
                }
                bw.newLine();
            }
            bw.close();
        } catch (IOException iOException) {
            result = false;
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException iOException) {
                result = false;
            }
        }

        return result;
    }

    public boolean overwriteTIFile(String workingFilePath, SegmentData segmentData, Entity entity) {

        // if working file didn't exists or cannot be deleted, then false.
        File file = new File(workingFilePath);
        if (!file.exists()) {
            return false;
        }
        if (!file.delete()) {
            return false;
        }

        boolean result = true;
        String co = ",";
        String formatCode = "TI";
        double samplingRate = ((SegmentInfo) entity).getSampleRate();
        BufferedWriter bw = null;

        try {
            // BufferedWriter to write.
            bw = new BufferedWriter(new FileWriter(workingFilePath));

            // First Line : TI, <SamplingRate>
            bw.write(formatCode + co + samplingRate);
            bw.newLine();

            // Write Data to the workingFile(CSV).
            for (int ii = 0; ii < segmentData.getTimeStamp().size(); ii++) {

                // 2nd Line : <timestamp>, <dwUnitID>, <SegmentValue[0]>, <SegmentValue[1]>, ... <SegmentValue[n]>
                Double timeStamp = segmentData.getTimeStamp().get(ii);
                bw.write(timeStamp + co);
                Long unitID = segmentData.getUnitID().get(ii);
                bw.write(unitID.toString());

                ArrayList<Double> segmentValues = segmentData.getValues().get(ii);
                for (int jj = 0; jj < segmentValues.size(); jj++) {
                    bw.write(co + segmentValues.get(jj));
                }
                bw.newLine();
            }
            bw.close();
        } catch (IOException iOException) {
            result = false;
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException iOException) {
                result = false;
            }
        }

        return result;
    }

    public boolean overwriteTLFile(String workingFilePath, Object eventObject, Entity entity) {

        // if working file didn't exists or cannot be deleted, then false.
        File file = new File(workingFilePath);
        if (!file.exists()) {
            return false;
        }
        if (!file.delete()) {
            return false;
        }

        boolean result = true;
        String co = ",";
        String formatCode = "TL";
        BufferedWriter bw = null;
        ArrayList<TextEventData> ted = null;
        ArrayList<ByteEventData> bed = null;
        ArrayList<WordEventData> wed = null;
        ArrayList<DWordEventData> dwed = null;

        int eventDataSize = 0;

        switch ((int) ((EventInfo) entity).getEventType()) {
            case 0:
                // ns_EVENT_TEXT
                ted = (ArrayList<TextEventData>) eventObject;
                eventDataSize = ted.size();
                break;
            case 1:
                // ns_EVENT_CSV
                return false;
            case 2:
                // ns_EVENT_BYTE
                bed = (ArrayList<ByteEventData>) eventObject;
                eventDataSize = bed.size();
                break;
            case 3:
                // ns_EVENT_WORD
                wed = (ArrayList<WordEventData>) eventObject;
                eventDataSize = wed.size();
                break;
            case 4:
                // ns_EVENT_DWORD
                dwed = (ArrayList<DWordEventData>) eventObject;
                eventDataSize = dwed.size();
                break;
            default:
                return false;
        }


        try {
            // BufferedWriter to write.
            bw = new BufferedWriter(new FileWriter(workingFilePath));

            // First Line : TL
            bw.write(formatCode);
            bw.newLine();

            // Write Data to the workingFile(CSV).
            for (int ii = 0; ii < eventDataSize; ii++) {
                // 2nd Line : <timestamp>, <EventValue>

                Double timeStamp = (double)0;

                switch ((int) ((EventInfo) entity).getEventType()) {
                    case 0:
                        // ns_EVENT_TEXT
                        timeStamp = ted.get(ii).getTimestamp();
                        bw.write(timeStamp + co);
                        bw.write(ted.get(ii).getData());
                        break;
                    case 1:
                        // ns_EVENT_CSV
                        return false;
                    case 2:
                        // ns_EVENT_BYTE
                        timeStamp = bed.get(ii).getTimestamp();
                        bw.write(timeStamp + co);
                        bw.write(((Byte)bed.get(ii).getData()).toString());
                        break;
                    case 3:
                        // ns_EVENT_WORD
                        timeStamp = wed.get(ii).getTimestamp();
                        bw.write(timeStamp + co);
                        bw.write(((Integer)wed.get(ii).getData()).toString());
                        break;
                    case 4:
                        // ns_EVENT_DWORD
                        timeStamp = dwed.get(ii).getTimestamp();
                        bw.write(timeStamp + co);
                        bw.write(((Long)dwed.get(ii).getData()).toString());
                        break;
                    default:
                        return false;

                }
                bw.newLine();
            }
            bw.close();
        } catch (IOException iOException) {
            result = false;
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException iOException) {
                result = false;
            }
        }

        return result;
    }

    public boolean overwriteTOFile(String workingFilePath, ArrayList<Double> neuralData, Entity entity) {

        // if working file didn't exists or cannot be deleted, then false.
        File file = new File(workingFilePath);
        if (!file.exists()) {
            return false;
        }
        if (!file.delete()) {
            return false;
        }

        boolean result = true;
        String co = ",";
        String formatCode = "TO";
        BufferedWriter bw = null;

        try {
            // BufferedWriter to write.
            bw = new BufferedWriter(new FileWriter(workingFilePath));

            // First Line : TO
            bw.write(formatCode);
            bw.newLine();

            // Write Data to the workingFile(CSV).
            for (int ii = 0; ii < neuralData.size(); ii++) {

                // 2nd Line : <timestamp>
                bw.write(neuralData.get(ii).toString());
                bw.newLine();
            }
            bw.close();
        } catch (IOException iOException) {
            result = false;
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException iOException) {
                result = false;
            }
        }

        return result;
    }
}
