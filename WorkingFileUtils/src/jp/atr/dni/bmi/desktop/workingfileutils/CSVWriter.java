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
import jp.atr.dni.bmi.desktop.neuroshareutils.Const_values;
import jp.atr.dni.bmi.desktop.neuroshareutils.DWordEventData;
import jp.atr.dni.bmi.desktop.neuroshareutils.Entity;
import jp.atr.dni.bmi.desktop.neuroshareutils.EntityInfo;
import jp.atr.dni.bmi.desktop.neuroshareutils.EventInfo;
import jp.atr.dni.bmi.desktop.neuroshareutils.NSReader;
import jp.atr.dni.bmi.desktop.neuroshareutils.SegmentData;
import jp.atr.dni.bmi.desktop.neuroshareutils.SegmentInfo;
import jp.atr.dni.bmi.desktop.neuroshareutils.SegmentSourceInfo;
import jp.atr.dni.bmi.desktop.neuroshareutils.Tag;
import jp.atr.dni.bmi.desktop.neuroshareutils.TextEventData;
import jp.atr.dni.bmi.desktop.neuroshareutils.WordEventData;

/**
 *
 * @author kharada
 */
public class CSVWriter {

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
        ArrayList<TextEventData> textEventData = new ArrayList<TextEventData>();
        ArrayList<ByteEventData> byteEventData = new ArrayList<ByteEventData>();
        ArrayList<WordEventData> wordEventData = new ArrayList<WordEventData>();
        ArrayList<DWordEventData> dwordEventData = new ArrayList<DWordEventData>();

        try {
            // BufferedWriter to write.
            bw = new BufferedWriter(new FileWriter(workingFilePath));

            // First Line : TL
            bw.write(formatCode);
            bw.newLine();

            EventInfo ei = (EventInfo) entity;
            // Read Neuroshare Data.
            NSReader nsReader = new NSReader();
            //ArrayList<EventData> eventData = nsReader.getEventData(sourceFilePath, entity.getEntityInfo(), (EventInfo) entity);

            switch ((int) ei.getEventType()) {
                case 0:
                    textEventData = nsReader.getTextEventData(sourceFilePath, entity.getEntityInfo(), (EventInfo) entity);
                    for (int ii = 0; ii < textEventData.size(); ii++) {
                        bw.write(((Double) textEventData.get(ii).getTimestamp()).toString() + co);
                        bw.write(textEventData.get(ii).getData());
                        bw.newLine();
                    }
                    break;
                case 1:
                    textEventData = nsReader.getTextEventData(sourceFilePath, entity.getEntityInfo(), (EventInfo) entity);
                    for (int ii = 0; ii < textEventData.size(); ii++) {
                        bw.write(((Double) textEventData.get(ii).getTimestamp()).toString() + co);
                        bw.write(textEventData.get(ii).getData());
                        bw.newLine();
                    }
                    break;
                case 2:
                    byteEventData = nsReader.getByteEventData(sourceFilePath, entity.getEntityInfo(), (EventInfo) entity);
                    for (int ii = 0; ii < byteEventData.size(); ii++) {
                        bw.write(((Double) textEventData.get(ii).getTimestamp()).toString() + co);
                        bw.write(((Byte) (byteEventData.get(ii).getData())).toString());
                        bw.newLine();
                    }
                    break;
                case 3:
                    wordEventData = nsReader.getWordEventData(sourceFilePath, entity.getEntityInfo(), (EventInfo) entity);
                    for (int ii = 0; ii < wordEventData.size(); ii++) {
                        bw.write(((Double) textEventData.get(ii).getTimestamp()).toString() + co);
                        bw.write(((Integer) (wordEventData.get(ii).getData())).toString());
                        bw.newLine();
                    }
                    break;
                case 4:
                    dwordEventData = nsReader.getDWordEventData(sourceFilePath, entity.getEntityInfo(), (EventInfo) entity);
                    for (int ii = 0; ii < dwordEventData.size(); ii++) {
                        bw.write(((Double) textEventData.get(ii).getTimestamp()).toString() + co);
                        bw.write(((Long) (dwordEventData.get(ii).getData())).toString());
                        bw.newLine();
                    }
                    break;
                default:

                    break;
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

                Double timeStamp = (double) 0;

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
                        bw.write(((Byte) bed.get(ii).getData()).toString());
                        break;
                    case 3:
                        // ns_EVENT_WORD
                        timeStamp = wed.get(ii).getTimestamp();
                        bw.write(timeStamp + co);
                        bw.write(((Integer) wed.get(ii).getData()).toString());
                        break;
                    case 4:
                        // ns_EVENT_DWORD
                        timeStamp = dwed.get(ii).getTimestamp();
                        bw.write(timeStamp + co);
                        bw.write(((Long) dwed.get(ii).getData()).toString());
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

    public Entity overwriteTSFile(String workingFilePath, TSData data, Entity entity) {

        Entity retEntity = entity;

        // if working file didn't exists, then null.
        File file = new File(workingFilePath);
        if (!file.exists()) {
            return null;
        }
        // if it could not delete working file , then null.
        if (!file.delete()) {
            return null;
        }

        String co = ",";
        String formatCode = data.getFormatCode();
        double samplingRate = data.getSamplingRate();
        double maxValue = Double.MIN_VALUE;
        double minValue = Double.MAX_VALUE;
        int rowSize = data.getTimeStamps().size();
        int dataCounter = 0;
        BufferedWriter bw = null;

        // Write data to the workingFilePath.
        try {
            // BufferedWriter to write.
            bw = new BufferedWriter(new FileWriter(workingFilePath));

            // First Line : TS, <SamplingRate>
            bw.write(formatCode + co + samplingRate);
            bw.newLine();

            // Write Data to the workingFile(CSV).
            for (int ii = 0; ii < rowSize; ii++) {

                // 2nd Line : <timestamp>, <AnalogValue[0]>, <AnalogValue[1]>, ... <AnalogValue[n]>
                bw.write(((Double) data.getTimeStamp(ii)).toString());
                ArrayList<Double> analogValues = data.getValues(ii);
                for (int jj = 0; jj < analogValues.size(); jj++) {
                    dataCounter++;
                    bw.write(co + analogValues.get(jj));
                    if (analogValues.get(ii) > maxValue) {
                        maxValue = analogValues.get(ii);
                    }
                    if (analogValues.get(ii) < minValue) {
                        minValue = analogValues.get(ii);
                    }
                }
                bw.newLine();
            }
            bw.close();
        } catch (IOException iOException) {
            return null;
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException iOException) {
                return null;
            }
        }

        // Edit Headers

        // Edit Tag [dwElemLength]
        Tag tag = entity.getTag();
        // elemLength : 40(ns_EntityInfo) + 264(ns_AnalogInfo) + row * 12(dTimestamp and dwDataCount) + dataCount * 8(dAnalogValue)
        int elemLength = Const_values.LENGTH_OF_NS_ENTITYINFO + Const_values.LENGTH_OF_NS_ANALOGINFO + rowSize * 12 + dataCounter * 8;
        tag.setElemLength(elemLength);
        retEntity.setTag(tag);

        // Edit EntityInfo [dwItemCount]
        EntityInfo ei = retEntity.getEntityInfo();
        // dwItemCount : dataCount
        ei.setItemCount(dataCounter);

        // Edit AnalogInfo [dSampleRate, dMinVal, dMaxVal]
        AnalogInfo analogInfo = (AnalogInfo) retEntity;
        analogInfo.setSampleRate(samplingRate);
        analogInfo.setMinVal(minValue);
        analogInfo.setMaxVal(maxValue);
        retEntity = (Entity) analogInfo;

        return retEntity;
    }

    public Entity overwriteTOFile(String workingFilePath, TOData data, Entity entity) {

        Entity retEntity = entity;

        // if working file didn't exists, then null.
        File file = new File(workingFilePath);
        if (!file.exists()) {
            return null;
        }
        // if it could not delete working file , then null.
        if (!file.delete()) {
            return null;
        }

        String co = ",";
        String formatCode = data.getFormatCode();
        int rowSize = data.getTimeStamps().size();
        int dataCounter = 0;
        BufferedWriter bw = null;

        // Write data to the workingFilePath.
        try {
            // BufferedWriter to write.
            bw = new BufferedWriter(new FileWriter(workingFilePath));

            // First Line : TO
            bw.write(formatCode);
            bw.newLine();

            // Write Data to the workingFile(CSV).
            for (int ii = 0; ii < rowSize; ii++) {

                // 2nd Line : <timestamp>
                bw.write(((Double) data.getTimeStamp(ii)).toString());
                dataCounter++;
                bw.newLine();
            }
            bw.close();
        } catch (IOException iOException) {
            return null;
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException iOException) {
                return null;
            }
        }

        // Edit Headers

        // Edit Tag [dwElemLength]
        Tag tag = entity.getTag();
        // elemLength : 40(ns_EntityInfo) + 136(ns_NeuralInfo) + dataCount * 8(dTimeStamp)
        int elemLength = Const_values.LENGTH_OF_NS_ENTITYINFO + Const_values.LENGTH_OF_NS_NEURALINFO + dataCounter * 8;
        tag.setElemLength(elemLength);
        retEntity.setTag(tag);

        // Edit EntityInfo [dwItemCount]
        EntityInfo ei = retEntity.getEntityInfo();
        // dwItemCount : dataCount
        ei.setItemCount(dataCounter);

        // Edit NeuralInfo []
        // Nothing.

        return retEntity;
    }

    public Entity overwriteTIFile(String workingFilePath, TIData data, Entity entity) {

        Entity retEntity = entity;

        // if working file didn't exists, then null.
        File file = new File(workingFilePath);
        if (!file.exists()) {
            return null;
        }
        // if it could not delete working file , then null.
        if (!file.delete()) {
            return null;
        }

        String co = ",";
        String formatCode = data.getFormatCode();
        double samplingRate = data.getSamplingRate();
        int maxSampleCount = 0;
        int minSampleCount = Integer.MAX_VALUE;
        double maxValue = Double.MIN_VALUE;
        double minValue = Double.MAX_VALUE;
        int rowSize = data.getTimeStamps().size();
        int dataCounter = 0;
        BufferedWriter bw = null;

        // Write data to the workingFilePath.
        try {
            // BufferedWriter to write.
            bw = new BufferedWriter(new FileWriter(workingFilePath));

            // First Line : TI, <SamplingRate>
            bw.write(formatCode + co + samplingRate);
            bw.newLine();

            // Write Data to the workingFile(CSV).
            for (int ii = 0; ii < rowSize; ii++) {

                // 2nd Line : <timestamp>, <unitID>, <SegmentValue[0]>, <SegmentValue[1]>, ... <SegmentValue[n]>
                bw.write(((Double) data.getTimeStamp(ii)).toString());
                bw.write(co + ((Integer) data.getUnitID(ii)).toString());
                ArrayList<Double> segmentValues = data.getValues(ii);
                if (segmentValues.size() > maxSampleCount) {
                    maxSampleCount = segmentValues.size();
                }
                if (segmentValues.size() < minSampleCount) {
                    minSampleCount = segmentValues.size();
                }
                for (int jj = 0; jj < segmentValues.size(); jj++) {
                    dataCounter++;
                    bw.write(co + segmentValues.get(jj));
                    if (segmentValues.get(ii) > maxValue) {
                        maxValue = segmentValues.get(ii);
                    }
                    if (segmentValues.get(ii) < minValue) {
                        minValue = segmentValues.get(ii);
                    }
                }
                bw.newLine();
            }
            bw.close();
        } catch (IOException iOException) {
            return null;
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException iOException) {
                return null;
            }
        }

        // Edit Headers

        // Edit Tag [dwElemLength]
        Tag tag = entity.getTag();
        // elemLength : 40(ns_EntityInfo) + 264(ns_SegmentInfo) + 248(ns_SegSourceInfo) + row * 16(dTimestamp, dwDataCount and dwUnitID) + dataCount * 8(dSegmentValue)
        int elemLength = Const_values.LENGTH_OF_NS_ENTITYINFO + Const_values.LENGTH_OF_NS_SEGMENTINFO + Const_values.LENGTH_OF_NS_SEGSOURCEINFO + rowSize * 16 + dataCounter * 8;
        tag.setElemLength(elemLength);
        retEntity.setTag(tag);

        // Edit EntityInfo [dwItemCount]
        EntityInfo ei = retEntity.getEntityInfo();
        // dwItemCount : dataCount
        ei.setItemCount(dataCounter);

        // Edit SegmentInfo [dwMinSampleCount, dwMaxSampleCount, dSampleRate]
        // Edit SegSourceInfo [dMinVal, dMaxVal]
        SegmentInfo segmentInfo = (SegmentInfo) retEntity;
        segmentInfo.setMinSampleCount(minSampleCount);
        segmentInfo.setMaxSampleCount(maxSampleCount);
        segmentInfo.setSampleRate(samplingRate);
        ArrayList<SegmentSourceInfo> segSourceInfos = segmentInfo.getSegSourceInfos();
        SegmentSourceInfo ssi = segSourceInfos.get(0);
        ssi.setMinVal(minValue);
        ssi.setMaxVal(maxValue);
        segmentInfo.setSegSourceInfos(segSourceInfos);
        retEntity = (Entity) segmentInfo;

        return retEntity;
    }

    public Entity overwriteTLFile(String workingFilePath, TLData data, Entity entity) {

        Entity retEntity = entity;

        // if working file didn't exists, then null.
        File file = new File(workingFilePath);
        if (!file.exists()) {
            return null;
        }
        // if it could not delete working file , then null.
        if (!file.delete()) {
            return null;
        }

        String co = ",";
        String formatCode = data.getFormatCode();
        int minDataLength = Integer.MAX_VALUE;
        int maxDataLength = 0;
        int valueSize = 0;
        int totalValueSize = 0;
        int rowSize = data.getTimeStamps().size();
        int dataCounter = 0;
        BufferedWriter bw = null;

        // Write data to the workingFilePath.
        try {
            // BufferedWriter to write.
            bw = new BufferedWriter(new FileWriter(workingFilePath));

            // First Line : TL
            bw.write(formatCode);
            bw.newLine();

            // Write Data to the workingFile(CSV).
            for (int ii = 0; ii < rowSize; ii++) {
                dataCounter++;

                // 2nd Line : <timestamp>, <EventValue>
                bw.write(((Double) data.getTimeStamp(ii)).toString() + co);

                switch ((int) ((EventInfo) entity).getEventType()) {
                    case 0:
                        bw.write(data.getValue(ii).toString());
                        valueSize = data.getValue(ii).toString().length();
                        break;
                    case 1:
                        bw.write(data.getValue(ii).toString());
                        valueSize = data.getValue(ii).toString().length();
                        break;
                    case 2:
                        bw.write(((Byte) data.getValue(ii)).toString());
                        valueSize = 1;
                        break;
                    case 3:
                        bw.write(((Short) data.getValue(ii)).toString());
                        valueSize = 2;
                        break;
                    case 4:
                        bw.write(((Integer) data.getValue(ii)).toString());
                        valueSize = 4;
                        break;
                    default:
                        return null;
                }
                if (valueSize > maxDataLength) {
                    maxDataLength = valueSize;
                }
                if (valueSize < minDataLength) {
                    minDataLength = valueSize;
                }
                totalValueSize = totalValueSize + valueSize;

                bw.newLine();
            }
            bw.close();
        } catch (IOException iOException) {
            return null;
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException iOException) {
                return null;
            }
        }

        // Edit Headers

        // Edit Tag [dwElemLength]
        Tag tag = entity.getTag();
        // elemLength : 40(ns_EntityInfo) + 140(ns_EventInfo) + row * 12(dTimestamp + dwDataByteSize) + totalDataSize
        int elemLength = Const_values.LENGTH_OF_NS_ENTITYINFO + Const_values.LENGTH_OF_NS_EVENTINFO + rowSize * 12 + totalValueSize;
        tag.setElemLength(elemLength);
        retEntity.setTag(tag);

        // Edit EntityInfo [dwItemCount]
        EntityInfo ei = retEntity.getEntityInfo();
        // dwItemCount : dataCount
        ei.setItemCount(dataCounter);

        // Edit EventInfo [dwMinDataLength, dwMaxDataLength]
        EventInfo eventInfo = (EventInfo) retEntity;
        eventInfo.setMinDataLength(minDataLength);
        eventInfo.setMaxDataLength(maxDataLength);
        retEntity = (Entity) eventInfo;

        return retEntity;
    }
}
