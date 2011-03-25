/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.atr.dni.bmi.desktop.workspace;

import java.io.IOException;
import java.util.ArrayList;
import jp.atr.dni.bmi.desktop.model.Channel;
import jp.atr.dni.bmi.desktop.neuroshareutils.AnalogInfo;
import jp.atr.dni.bmi.desktop.neuroshareutils.Entity;
import jp.atr.dni.bmi.desktop.neuroshareutils.EntityInfo;
import jp.atr.dni.bmi.desktop.neuroshareutils.EventInfo;
import jp.atr.dni.bmi.desktop.workingfileutils.CSVReader;
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
import jp.atr.dni.bmi.desktop.workingfileutils.TIData;
import jp.atr.dni.bmi.desktop.workingfileutils.TLData;
import jp.atr.dni.bmi.desktop.workingfileutils.TOData;
import jp.atr.dni.bmi.desktop.workingfileutils.TSData;

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

            // Reader
            CSVReader nsCsvReader = new CSVReader();

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

                String srcFileFullPath = ch.getWorkingFilePath();

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

                        // Add Event Data
                        // If you want to add multiple rows data, repeat to call add***Data.
                        // int rtnval2 = nsEd.addEventData(dTimestamp, dData);

                        int rtnval2 = 0;

                        // Get EventData from working file.
                        TLData tLData = nsCsvReader.getTLData(srcFileFullPath);
                        int eventDataSize = tLData.getTimeStamps().size();

                        switch ((int) eventInfo.getEventType()) {
                            case 0:
                                // Get Event Data
                                for (int ii = 0; ii < eventDataSize; ii++) {
                                    // ns_EVENT_TEXT
                                    rtnval2 = nsEd.addEventData(tLData.getTimeStamp(ii), tLData.getValue(ii).toString());
                                    if (rtnval2 != 0) {
                                        // add error. - input arg error - or intermediate file i/o error.
                                    }
                                }
                                break;
                            case 1:
                                // ns_EVENT_CSV
                                // Nothing in Model.
                                break;
                            case 2:
                                // Get Event Data
                                for (int ii = 0; ii < eventDataSize; ii++) {
                                    // ns_EVENT_BYTE
                                    rtnval2 = nsEd.addEventData(tLData.getTimeStamp(ii), (Byte) (tLData.getValue(ii)));
                                    if (rtnval2 != 0) {
                                        // add error. - input arg error - or intermediate file i/o error.
                                    }
                                }
                                break;
                            case 3:
                                // Get Event Data
                                for (int ii = 0; ii < eventDataSize; ii++) {
                                    // ns_EVENT_WORD
                                    rtnval2 = nsEd.addEventData(tLData.getTimeStamp(ii), (Short) (tLData.getValue(ii)));
                                    if (rtnval2 != 0) {
                                        // add error. - input arg error - or intermediate file i/o error.
                                    }
                                }
                                break;
                            case 4:
                                // Get Event Data
                                for (int ii = 0; ii < eventDataSize; ii++) {
                                    // ns_EVENT_DWORD
                                    rtnval2 = nsEd.addEventData(tLData.getTimeStamp(ii), (Integer) (tLData.getValue(ii)));
                                    if (rtnval2 != 0) {
                                        // add error. - input arg error - or intermediate file i/o error.
                                    }
                                }
                                break;
                            default:
                                break;

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
                        // Get AnalogData from working file.
                        TSData tSData = nsCsvReader.getTSData(srcFileFullPath);
                        int analogDataSize = tSData.getTimeStamps().size();

                        // Add Analog Data
                        for (int ii = 0; ii < analogDataSize; ii++) {
                            // If you want to add multiple rows data, repeat to call add***Data.
                            // int rtnval6 = nsAd.addAnalogData(dTimestamp_analog, dData_analog);
                            ArrayList<Double> values = tSData.getValues(ii);
                            Object[] vals = values.toArray();
                            double[] dVals = new double[vals.length];
                            for (int jj = 0; jj < vals.length; jj++) {
                                dVals[jj] = (Double) vals[jj];
                            }
                            int rtnval6 = nsAd.addAnalogData(tSData.getTimeStamp(ii), dVals);
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
                        // Get SegmentData from working file.
                        TIData tIData = nsCsvReader.getTIData(srcFileFullPath);
                        int segmentDataSize = tIData.getTimeStamps().size();

                        for (int kk = 0; kk < segmentDataSize; kk++) {
                            ArrayList<Double> values = tIData.getValues(kk);
                            Object[] vals = values.toArray();
                            double[] dVals = new double[vals.length];
                            for (int jj = 0; jj < vals.length; jj++) {
                                dVals[jj] = (Double) vals[jj];
                            }
                            int rtnval6 = nsSD.addSegmentDataWithoutAddingExtraSegSourceInfo(tIData.getTimeStamp(kk), tIData.getUnitID(kk), dVals);
                            if (rtnval6 != 0) {
                                // add error. - input arg error - or intermediate file i/o error.
                            }

                        }
                        // Modify ns_SEGSOURCEINFO.
                        // Get it.
                        // Be care! segSourceID is needed!!!
                        Nsa_SegSourceInfo nsaSegSourceInfo = nsSD.getSegSourceInfo(0);
                        if (nsaSegSourceInfo == null) {
                            // Get SegmentInfo error - input args error.
                        }

                        // Modify members.
                        nsaSegSourceInfo.setDResolution(segmentInfo.getSegSourceInfos().get(0).getResolution());
                        // nsaSegSourceInfo.setDMinVal(3.0); // [Can Edit, ***But not recommend to modify
                        // this.***]
                        // but it WAS updated by addSegmentData
                        // nsaSegSourceInfo.setDMaxVal(5); // [Can Edit, ***But not recommend to modify
                        // this.***]
                        // but it WAS updated by addSegmentData
                        nsaSegSourceInfo.setDSubSampleShift(segmentInfo.getSegSourceInfos().get(0).getSubSampleShift());
                        nsaSegSourceInfo.setDLocationX(segmentInfo.getSegSourceInfos().get(0).getLocationX());
                        nsaSegSourceInfo.setDLocationY(segmentInfo.getSegSourceInfos().get(0).getLocationY());
                        nsaSegSourceInfo.setDLocationZ(segmentInfo.getSegSourceInfos().get(0).getLocationZ());
                        nsaSegSourceInfo.setDLocationUser(segmentInfo.getSegSourceInfos().get(0).getLocationUser());
                        nsaSegSourceInfo.setDHighFreqCorner(segmentInfo.getSegSourceInfos().get(0).getHighFreqCorner());
                        nsaSegSourceInfo.setDwHighFreqOrder((int) segmentInfo.getSegSourceInfos().get(0).getHighFreqOrder());
                        nsaSegSourceInfo.setSzHighFilterType(segmentInfo.getSegSourceInfos().get(0).getHighFilterType());
                        nsaSegSourceInfo.setDLowFreqCorner(segmentInfo.getSegSourceInfos().get(0).getLowFreqCorner());
                        nsaSegSourceInfo.setDwLowFreqOrder((int) segmentInfo.getSegSourceInfos().get(0).getLowFreqOrder());
                        nsaSegSourceInfo.setSzLowFilterType(segmentInfo.getSegSourceInfos().get(0).getLowFilterType());
                        nsaSegSourceInfo.setSzProbeInfo(segmentInfo.getSegSourceInfos().get(0).getProbeInfo());

                        // Set it.
                        // Be care! segSourceID is needed!!!
                        int rtnval11 = nsSD.setSegSourceInfo(0, nsaSegSourceInfo);
                        if (rtnval11 != 0) {
                            // set Error. - nsaSegmentInfo includes error
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
                        // Get NeuralData from working file.
                        TOData tOData = nsCsvReader.getTOData(srcFileFullPath);
                        int neuralDataSize = tOData.getTimeStamps().size();

                        for (int jj = 0; jj < neuralDataSize; jj++) {
                            int rtnval9 = nsNED.addNeuralEventData(tOData.getTimeStamp(jj));
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

    }
}
