/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.atr.dni.bmi.desktop.workspace;

import java.io.IOException;
import java.util.ArrayList;
import jp.atr.dni.bmi.desktop.model.Channel;
import jp.atr.dni.bmi.desktop.neuroshareutils.AnalogData;
import jp.atr.dni.bmi.desktop.neuroshareutils.AnalogInfo;
import jp.atr.dni.bmi.desktop.neuroshareutils.ByteEventData;
import jp.atr.dni.bmi.desktop.neuroshareutils.DWordEventData;
import jp.atr.dni.bmi.desktop.neuroshareutils.Entity;
import jp.atr.dni.bmi.desktop.neuroshareutils.EntityInfo;
import jp.atr.dni.bmi.desktop.neuroshareutils.EventInfo;
import jp.atr.dni.bmi.desktop.workingfileutils.NSCSVReader;
import jp.atr.dni.bmi.desktop.neuroshareutils.NSReader;
import jp.atr.dni.bmi.desktop.neuroshareutils.NeuralInfo;
import jp.atr.dni.bmi.desktop.neuroshareutils.Ns_AnalogData;
import jp.atr.dni.bmi.desktop.neuroshareutils.Ns_CreateFile;
import jp.atr.dni.bmi.desktop.neuroshareutils.Ns_EventData;
import jp.atr.dni.bmi.desktop.neuroshareutils.Ns_NeuralEventData;
import jp.atr.dni.bmi.desktop.neuroshareutils.Ns_SegmentData;
import jp.atr.dni.bmi.desktop.neuroshareutils.Nsa_AnalogInfo;
import jp.atr.dni.bmi.desktop.neuroshareutils.Nsa_EventInfo;
import jp.atr.dni.bmi.desktop.neuroshareutils.Nsa_FileInfo;
import jp.atr.dni.bmi.desktop.neuroshareutils.Nsa_NeuralInfo;
import jp.atr.dni.bmi.desktop.neuroshareutils.Nsa_SegSourceInfo;
import jp.atr.dni.bmi.desktop.neuroshareutils.Nsa_SegmentInfo;
import jp.atr.dni.bmi.desktop.neuroshareutils.SegmentData;
import jp.atr.dni.bmi.desktop.neuroshareutils.SegmentInfo;
import jp.atr.dni.bmi.desktop.neuroshareutils.TextEventData;
import jp.atr.dni.bmi.desktop.neuroshareutils.WordEventData;

/**
 *
 * @author kharada
 */
public class CreateNewNeuroshareFile {

    /**
     * Create Neuroshare file to the dstFileFullPath with using the header(metaInfo and channels) and the data(channels indicate).
     *
     * @param dstFileFullPath
     *                  output file path which user inputed at wizard1.
     * @param metaInfo
     *                  meta information which user inputed at wizard2.
     * @param channels
     *                  channels which user selected at wizard3.
     */
    public void createFile(String dstFileFullPath, Nsa_FileInfo metaInfo, ArrayList<Channel> channels) {

        try {
            // Reader
            NSReader nsReader = new NSReader();
            NSCSVReader nsCsvReader = new NSCSVReader();

            // Create the Neuroshare file.
            Ns_CreateFile nsFile = new Ns_CreateFile(dstFileFullPath);

            // Modify ns_FILEINFO.
            // Set it.
            int rtnval3 = nsFile.setFileInfo(metaInfo);
            if (rtnval3 != 0) {
                // set Error.
            }

            // EntityCount.
            int entityCount = (int) channels.size();

            for (int i = 0; i < entityCount; i++) {

                Channel ch = channels.get(i);

                String srcFileFullPath = ch.getSourceFilePath();

                Entity e = ch.getEntity();

                EntityInfo ei = e.getEntityInfo();

                int entityType = (int) ei.getEntityType();

                switch (entityType) {
                    case 0:
                        // Unknown
                        break;
                    case 1:
                        // Event
                        // Create new Event Entity (input arg is ns_ENTITYINFO.szEntityLabel.)
                        Ns_EventData nsEd = nsFile.newEventData(ei.getEntityLabel());
                        if (nsEd == null) {
                            // new Event error - input args error.
                        }

                        // Modify ns_EVENTINFO.
                        // Get it.
                        Nsa_EventInfo nsaEventInfo = nsEd.getEventInfo();
                        if (nsaEventInfo == null) {
                            // Get EventInfo error - input args error.
                        }

                        EventInfo eventInfo = (EventInfo) ch.getEntity();

                        // Modify members.
                        // [can not edit] dwEventType. ::: For consistency of data.
                        // [can not edit] dwMinDataLength. ::: For consistency of data.
                        // [can not edit] dwMaxDataLength. ::: For consistency of data.
                        nsaEventInfo.setSzCSVDesc(eventInfo.getCsvDesc());

                        // Set it.
                        int rtnval1 = nsEd.setEventInfo(nsaEventInfo);
                        if (rtnval1 != 0) {
                            // set Error. - nsaEventInfo includes error
                        }

                        // case : No Record.
                        if (ei.getItemCount() == 0) {
                            continue;
                        }

                        for (int j = 0; j < ei.getItemCount(); j++) {
                            // Add Event Data
                            // If you want to add multiple rows data, repeat to call add***Data.
                            // int rtnval2 = nsEd.addEventData(dTimestamp, dData);

                            int rtnval2 = 0;
                            switch ((int) ei.getEntityType()) {
                                case 0:
                                    // Get Event Data
                                    TextEventData ted = null;
                                    if (!ch.isEditFlag()) {
                                        ted = (TextEventData) (nsReader.getEventData(srcFileFullPath, ei, eventInfo)).get(j);
                                    } else {
                                        // TODO : implement this.
//                                        ted = (TextEventData) (nsCsvReader.getEventData(srcFileFullPath));
                                    }

                                    // ns_EVENT_TEXT
                                    rtnval2 = nsEd.addEventData(ted.getTimestamp(), ted.getData());
                                    if (rtnval2 != 0) {
                                        // add error. - input arg error - or intermediate file i/o error.
                                    }
                                    break;
                                case 1:
                                    // ns_EVENT_CSV
                                    // Nothing in Model.
                                    break;
                                case 2:
                                    // Get Event Data
                                    ByteEventData bed = null;
                                    if (!ch.isEditFlag()) {
                                        bed = (ByteEventData) (nsReader.getEventData(srcFileFullPath, ei, eventInfo)).get(j);
                                    } else {
                                        // TODO : implement this.
//                                        bed = (ByteEventData) (nsCsvReader.getEventData(srcFileFullPath));
                                    }
                                    // ns_EVENT_BYTE
                                    rtnval2 = nsEd.addEventData(bed.getTimestamp(), bed.getData());
                                    if (rtnval2 != 0) {
                                        // add error. - input arg error - or intermediate file i/o error.
                                    }
                                    break;
                                case 3:
                                    // Get Event Data
                                    WordEventData wed = null;
                                    if (!ch.isEditFlag()) {
                                        wed = (WordEventData) (nsReader.getEventData(srcFileFullPath, ei, eventInfo)).get(j);
                                    } else {
                                        // TODO : implement this.
//                                        wed = (WordEventData) (nsCsvReader.getEventData(srcFileFullPath));
                                    }
                                    // ns_EVENT_WORD
                                    rtnval2 = nsEd.addEventData(wed.getTimestamp(), ((Integer) wed.getData()).shortValue());
                                    if (rtnval2 != 0) {
                                        // add error. - input arg error - or intermediate file i/o error.
                                    }
                                    break;
                                case 4:
                                    // Get Event Data
                                    DWordEventData dwed = null;
                                    if (!ch.isEditFlag()) {
                                        dwed = (DWordEventData) (nsReader.getEventData(srcFileFullPath, ei, eventInfo)).get(j);
                                    } else {
                                        // TODO : implement this.
//                                        dwed = (DWordEventData) (nsCsvReader.getEventData(srcFileFullPath));
                                    }
                                    // ns_EVENT_DWORD
                                    rtnval2 = nsEd.addEventData(dwed.getTimestamp(), dwed.getData().intValue());
                                    if (rtnval2 != 0) {
                                        // add error. - input arg error - or intermediate file i/o error.
                                    }
                                    break;
                                default:
                                    break;

                            }
                        }

                        break;
                    case 2:
                        // Analog
                        // Create new Analog Entity (input arg is ns_ENTITYINFO.szEntityLabel.)
                        Ns_AnalogData nsAd = nsFile.newAnalogData(ei.getEntityLabel());
                        if (nsAd == null) {
                            // new Analog error - input args error.
                        }

                        // Modify ns_ANALOGINFO.
                        // Get it.
                        Nsa_AnalogInfo nsaAnalogInfo = nsAd.getAnalogInfo();
                        if (nsaAnalogInfo == null) {
                            // Get AnalogInfo error - input args error.
                        }

                        // Cast to AnalogInfo
                        AnalogInfo analogInfo = (AnalogInfo) ch.getEntity();

                        // Modify members.
                        nsaAnalogInfo.setDSampleRate(analogInfo.getSampleRate());
                        nsaAnalogInfo.setDMinVal(analogInfo.getMinVal()); // [Can Edit] but it will be updated
                        // by addAnalogData
                        nsaAnalogInfo.setDMaxVal(analogInfo.getMaxVal()); // [Can Edit] but it will be updated
                        // by addAnalogData
                        nsaAnalogInfo.setSzUnits(analogInfo.getUnits());
                        nsaAnalogInfo.setDResolution(analogInfo.getResolution());
                        nsaAnalogInfo.setDLocationX(analogInfo.getLocationX());
                        nsaAnalogInfo.setDLocationY(analogInfo.getLocationY());
                        nsaAnalogInfo.setDLocationZ(analogInfo.getLocationZ());
                        nsaAnalogInfo.setDLocationUser(analogInfo.getLocationUser());
                        nsaAnalogInfo.setDHighFreqCorner(analogInfo.getHighFreqCorner());
                        nsaAnalogInfo.setDwHighFreqOrder((int) analogInfo.getHighFreqOrder());
                        nsaAnalogInfo.setSzHighFilterType(analogInfo.getHighFilterType());
                        nsaAnalogInfo.setDLowFreqCorner(analogInfo.getLowFreqCorner());
                        nsaAnalogInfo.setDwLowFreqOrder((int) analogInfo.getLowFreqOrder());
                        nsaAnalogInfo.setSzLowFilterType(analogInfo.getLowFilterType());
                        nsaAnalogInfo.setSzProbeInfo(analogInfo.getProbeInfo());

                        // Set it.
                        int rtnval5 = nsAd.setAnalogInfo(nsaAnalogInfo);
                        if (rtnval5 != 0) {
                            // set Error. - nsaAnalogInfo includes error
                        }

                        // case : No Record.
                        if (ei.getItemCount() == 0) {
                            continue;
                        }

                        // Get Analog Data
                        ArrayList<AnalogData> ad = null;
                        if (!ch.isEditFlag()) {
                            ad = nsReader.getAnalogData(srcFileFullPath, ei);
                        } else {
                            ad = nsCsvReader.getAnalogData(srcFileFullPath);
                        }

                        // Add Analog Data
                        for (int ianalog = 0; ianalog < ad.size(); ianalog++) {
                            // If you want to add multiple rows data, repeat to call add***Data.
                            // int rtnval6 = nsAd.addAnalogData(dTimestamp_analog, dData_analog);
                            double[] analogData = new double[(int) ad.get(ianalog).getDataCount()];
                            for (int j = 0; j < (int) ad.get(ianalog).getDataCount(); j++) {
                                analogData[j] = ad.get(ianalog).getAnalogValues().get(j);
                            }

                            int rtnval6 = nsAd.addAnalogData(ad.get(ianalog).getTimeStamp(), analogData);
                            if (rtnval6 != 0) {
                                // add error. - input arg error - or intermediate file i/o error.
                            }
                        }

                        break;
                    case 3:
                        // Segment
                        // Create new Segment Entity (input arg is ns_ENTITYINFO.szEntityLabel.)
                        Ns_SegmentData nsSD = nsFile.newSegmentData(ei.getEntityLabel());
                        if (nsSD == null) {
                            // new Segment error - input args error.
                        }

                        // Modify ns_SEGMENTINFO.
                        // Get it.
                        Nsa_SegmentInfo nsaSegmentInfo = nsSD.getSegmentInfo();
                        if (nsaSegmentInfo == null) {
                            // Get SegmentInfo error - input args error.
                        }

                        // Cast to SegmentInfo
                        SegmentInfo segmentInfo = (SegmentInfo) ch.getEntity();

                        // Modify members.
                        // [can not edit] dwSourceCount. ::: For consistency of data.
                        // [can not edit] dwMinSampleCount. ::: For consistency of data.
                        // [can not edit] dwMaxSampleCount. ::: For consistency of data.
                        nsaSegmentInfo.setDSampleRate(segmentInfo.getSampleRate());
                        nsaSegmentInfo.setSzUnits(segmentInfo.getUnits());

                        // Set it.
                        int rtnval10 = nsSD.setSegmentInfo(nsaSegmentInfo);
                        if (rtnval10 != 0) {
                            // set Error. - nsaSegmentInfo includes error
                        }

                        // case : No Record.
                        if (ei.getItemCount() == 0) {
                            continue;
                        }

                        // Get Segment Data
                        SegmentData sd = nsReader.getSegmentData(srcFileFullPath, ei, segmentInfo);

                        ArrayList<Double> timestampData = sd.getTimeStamp();
                        ArrayList<Long> unitIDData = sd.getUnitID();
                        ArrayList<ArrayList<Double>> value = sd.getValues();

                        // Add Segment Data
                        // If you want to add multiple rows data, repeat to call add***Data.
                        // Be care! segSourceID is returned!!
                        // int segSourceID = nsSD.addSegmentData(dTimestamp_segment, dwUnitID_segment,
                        // dValue_segment);
                        for (int kk = 0; kk < value.size(); kk++) {

                            double[] segmentValue = new double[(int) value.get(kk).size()];
                            for (int ll = 0; ll < (int) value.get(kk).size(); ll++) {
                                segmentValue[ll] = (double) value.get(kk).get(ll);
                            }
                            // int segSourceID = nsSD.addSegmentData(dTimestamp_segment, dwUnitID_segment,
                            // dValue_segment);
                            int segSourceID = nsSD.addSegmentData((double) timestampData.get(kk),
                                    (int) ((long) unitIDData.get(kk)), segmentValue);
                            if (segSourceID < 0) {
                                // add error. - input arg error - or intermediate file i/o error.
                            }

                            // Modify ns_SEGSOURCEINFO.
                            // Get it.
                            // Be care! segSourceID is needed!!!
                            Nsa_SegSourceInfo nsaSegSourceInfo = nsSD.getSegSourceInfo(segSourceID);
                            if (nsaSegSourceInfo == null) {
                                // Get SegmentInfo error - input args error.
                            }

                            // Modify members.
                            nsaSegSourceInfo.setDResolution(segmentInfo.getSegSourceInfos().get(kk).getResolution());
                            // nsaSegSourceInfo.setDMinVal(3.0); // [Can Edit, ***But not recommend to modify
                            // this.***]
                            // but it WAS updated by addSegmentData
                            // nsaSegSourceInfo.setDMaxVal(5); // [Can Edit, ***But not recommend to modify
                            // this.***]
                            // but it WAS updated by addSegmentData
                            nsaSegSourceInfo.setDSubSampleShift(segmentInfo.getSegSourceInfos().get(kk).getSubSampleShift());
                            nsaSegSourceInfo.setDLocationX(segmentInfo.getSegSourceInfos().get(kk).getLocationX());
                            nsaSegSourceInfo.setDLocationY(segmentInfo.getSegSourceInfos().get(kk).getLocationY());
                            nsaSegSourceInfo.setDLocationZ(segmentInfo.getSegSourceInfos().get(kk).getLocationZ());
                            nsaSegSourceInfo.setDLocationUser(segmentInfo.getSegSourceInfos().get(kk).getLocationUser());
                            nsaSegSourceInfo.setDHighFreqCorner(segmentInfo.getSegSourceInfos().get(kk).getHighFreqCorner());
                            nsaSegSourceInfo.setDwHighFreqOrder((int) segmentInfo.getSegSourceInfos().get(kk).getHighFreqOrder());
                            nsaSegSourceInfo.setSzHighFilterType(segmentInfo.getSegSourceInfos().get(kk).getHighFilterType());
                            nsaSegSourceInfo.setDLowFreqCorner(segmentInfo.getSegSourceInfos().get(kk).getLowFreqCorner());
                            nsaSegSourceInfo.setDwLowFreqOrder((int) segmentInfo.getSegSourceInfos().get(kk).getLowFreqOrder());
                            nsaSegSourceInfo.setSzLowFilterType(segmentInfo.getSegSourceInfos().get(kk).getLowFilterType());
                            nsaSegSourceInfo.setSzProbeInfo(segmentInfo.getSegSourceInfos().get(kk).getProbeInfo());

                            // Set it.
                            // Be care! segSourceID is needed!!!
                            int rtnval11 = nsSD.setSegSourceInfo(segSourceID, nsaSegSourceInfo);
                            if (rtnval11 != 0) {
                                // set Error. - nsaSegmentInfo includes error
                            }
                        }
                        break;
                    case 4:
                        // Neural
                        // Create new Neural Event Entity (input arg is ns_ENTITYINFO.szEntityLabel.)
                        Ns_NeuralEventData nsNED = nsFile.newNeuralEventData(ei.getEntityLabel());
                        if (nsNED == null) {
                            // new Neural error - input args error.
                        }

                        // Modify ns_NEURALINFO.
                        // Get it.
                        Nsa_NeuralInfo nsaNeuralInfo = nsNED.getNeuralInfo();
                        if (nsaNeuralInfo == null) {
                            // Get NeuralInfo error - input args error.
                        }

                        // Cast to NeuralInfo
                        NeuralInfo neuralInfo = (NeuralInfo) ch.getEntity();

                        // Modify members.
                        nsaNeuralInfo.setDwSourceEntityID((int) neuralInfo.getSourceEntityID());
                        nsaNeuralInfo.setDwSourceUnitID((int) neuralInfo.getSourceUnitID());
                        nsaNeuralInfo.setSzProbeInfo(neuralInfo.getProbeInfo());

                        // Set it.
                        int rtnval8 = nsNED.setNeuralInfo(nsaNeuralInfo);
                        if (rtnval8 != 0) {
                            // set Error. - nsaNeuralInfo includes error
                        }

                        // case : No Record.
                        if (ei.getItemCount() == 0) {
                            continue;
                        }

                        // Get Neural Event Data
                        ArrayList<Double> d = null;
                        if (!ch.isEditFlag()) {

                            d = nsReader.getNeuralData(srcFileFullPath, ei);
                        } else {
                            d = nsCsvReader.getNeuralData(srcFileFullPath);
                        }

                        for (int jj = 0; jj < d.size(); jj++) {
                            int rtnval9 = nsNED.addNeuralEventData(d.get(jj));
                            if (rtnval9 != 0) {
                                // add error. - input arg error - or intermediate file i/o error.
                            }
                        }
                        break;
                    case 5:
                        // FileInfo
                        break;
                    default:
                        break;

                }


            }

            // Close and create.
            int rtnval7 = nsFile.closeFile();
            if (rtnval7 != 0) {
                // close error.
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
