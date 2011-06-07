/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.atr.dni.bmi.desktop.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jp.atr.dni.bmi.desktop.neuroshareutils.AnalogData;
import jp.atr.dni.bmi.desktop.neuroshareutils.AnalogInfo;
import jp.atr.dni.bmi.desktop.neuroshareutils.Entity;
import jp.atr.dni.bmi.desktop.neuroshareutils.EventData;
import jp.atr.dni.bmi.desktop.neuroshareutils.EventInfo;
import jp.atr.dni.bmi.desktop.neuroshareutils.NeuralInfo;
import jp.atr.dni.bmi.desktop.neuroshareutils.SegmentData;
import jp.atr.dni.bmi.desktop.neuroshareutils.SegmentInfo;
import jp.atr.dni.bmi.desktop.neuroshareutils.SegmentSourceInfo;
import jp.atr.dni.bmi.desktop.neuroshareutils.TextEventData;
import jp.atr.dni.bmi.desktop.workingfileutils.CSVReader;
import jp.atr.dni.bmi.desktop.workingfileutils.CSVWriter;
import jp.atr.dni.bmi.desktop.workingfileutils.TIData;
import jp.atr.dni.bmi.desktop.workingfileutils.TIHeader;
import jp.atr.dni.bmi.desktop.workingfileutils.TLData;
import jp.atr.dni.bmi.desktop.workingfileutils.TLHeader;
import jp.atr.dni.bmi.desktop.workingfileutils.TOData;
import jp.atr.dni.bmi.desktop.workingfileutils.TOHeader;
import jp.atr.dni.bmi.desktop.workingfileutils.TSData;
import jp.atr.dni.bmi.desktop.workingfileutils.TSHeader;

/**
 *
 * @author Keiji Harada [*1]</br>[*1] ATR Intl. Conputational Neuroscience Labs, Decoding Group
 * @version 2011/04/22
 */
public class Channel {

    private List listeners = Collections.synchronizedList(new LinkedList());

    /**
     *
     * @param pcl
     */
    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        listeners.add(pcl);
    }

    /**
     *
     * @param pcl
     */
    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        listeners.remove(pcl);
    }

    private void fire(String propertyName, Object old, Object neu) {
        PropertyChangeListener[] pcls = (PropertyChangeListener[]) listeners.toArray(new PropertyChangeListener[0]);
        for (int i = 0; i < pcls.length; i++) {
            pcls[i].propertyChange(new PropertyChangeEvent(this, propertyName, old, neu));
        }
    }
    // Channel ID. system orders channels as this value.
    private int channelID;
    // Display Name. system displays this value.
    private String displayName;
    // Data case.
    /** Using strings for types is bad design. Use the Java Enum type instead. */
    private String channelType;
    // Source File Path.
    private String sourceFilePath;
    // Working File Path.
    private String workingFilePath;
    // Edit flag.[ true : is editted. false : is not editted. default : false.]
    private boolean editFlag;
    // Case : Neuroshare
    private Entity entity;

    // Constructor.
    /**
     *
     */
    public Channel() {
        this.channelID = -1;
        this.displayName = "";
        this.channelType = "";
        this.sourceFilePath = "";
        this.workingFilePath = "";
        this.editFlag = false;
    }

    // Constructor. Case : Neuroshare
    /**
     *
     * @param channelID
     * @param entity
     */
    public Channel(int channelID, Entity entity) {
        this.channelID = channelID;
        this.displayName = entity.getEntityInfo().getEntityLabel();
//        this.channelType = "Neuroshare/" + entity.getEntityInfo().getEntityTypeLabel();
        this.channelType = entity.getEntityInfo().getEntityTypeLabelT_();
        this.sourceFilePath = entity.getEntityInfo().getFilePath();
        this.workingFilePath = "";
        this.editFlag = false;
        this.entity = entity;
    }

    /**
     * @return the channelID
     */
    public int getChannelID() {
        return channelID;
    }

    /**
     * @param channelID the channelID to set
     */
//    public void setChannelID(int channelID) {
    //      int old = this.channelID;
    //    this.channelID = channelID;
    //    this.fire("channelID", old, this.channelID);
    // }
    /**
     * @return the displayName
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * @param displayName the displayName to set
     */
    public void setDisplayName(String displayName) {
        String old = this.displayName;
        this.displayName = displayName;
        this.fire("displayName", old, this.displayName);
    }

    /**
     * @return the channelType
     */
    public String getChannelType() {
        return channelType;
    }

    /**
     * @param channelType the channelType to set
     */
//    public void setChannelType(String channelType) {
    //       String old = this.channelType;
    //      this.channelType = channelType;
    //     this.fire("channelType", old, this.channelType);
    // }
    /**
     * @return the entity
     */
    public Entity getEntity() {
        return entity;
    }

    /**
     * @param entity the entity to set
     */
    public void setEntity(Entity entity) {
        Entity old = this.entity;
        this.entity = entity;
        this.fire("entity", old, this.entity);
    }

    /**
     * @return the sourceFilePath
     */
    public String getSourceFilePath() {
        return sourceFilePath;
    }

    /**
     * @param sourceFilePath the sourceFilePath to set
     */
    public void setSourceFilePath(String sourceFilePath) {
        String old = this.sourceFilePath;
        this.sourceFilePath = sourceFilePath;
        this.fire("sourceFilePath", old, this.sourceFilePath);
    }

    /**
     * @return the workingFilePath
     */
    public String getWorkingFilePath() {
        return workingFilePath;
    }

    /**
     * @param workingFilePath the workingFilePath to set
     */
    public void setWorkingFilePath(String workingFilePath) {
        String old = this.workingFilePath;
        this.workingFilePath = workingFilePath;
        this.fire("workingFilePath", old, this.workingFilePath);
    }

    /**
     * @return the editFlag
     */
    public boolean isEditFlag() {
        return editFlag;
    }

    /**
     * @param editFlag the editFlag to set
     */
    public void setEditFlag(boolean editFlag) {
        this.editFlag = editFlag;
    }

    /**
     * Override is needed to display displayName on the ChannelList module.
     * @return displayName
     */
    @Override
    public String toString() {
        return this.displayName;
    }

    // Get Data methods.
    /**
     *
     * @return
     */
    public ArrayList<AnalogData> getNeuroshareAnalogData() {
        try {
            CSVReader nsCsvReader = new CSVReader();
            return nsCsvReader.getAnalogData(this.workingFilePath);
        } catch (IOException ex) {
            Logger.getLogger(Channel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     *
     * @return
     */
    public ArrayList<Double> getNeuroshareNeuralData() {
        try {
            CSVReader nsCsvReader = new CSVReader();
            return nsCsvReader.getNeuralData(this.workingFilePath);
        } catch (IOException ex) {
            Logger.getLogger(Channel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     *
     * @return
     */
    public ArrayList<TextEventData> getNeuroshareTextEventData() {
        try {
            CSVReader nsCsvReader = new CSVReader();
            return nsCsvReader.getTextEventData(this.workingFilePath);
        } catch (IOException ex) {
            Logger.getLogger(Channel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     *
     * @return
     */
    public SegmentData getNeuroshareSegmentData() {
        try {
            CSVReader nsCsvReader = new CSVReader();
            return nsCsvReader.getSegmentData(this.workingFilePath);
        } catch (IOException ex) {
            Logger.getLogger(Channel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    // Set Data methods.
    /**
     *
     * @param analogData
     * @return
     */
    public boolean setNeuroshareAnalogData(ArrayList<AnalogData> analogData) {
        CSVWriter nsCsvWriter = new CSVWriter();
        if (nsCsvWriter.overwriteTSFile(this.workingFilePath, analogData, this.entity)) {
            this.setEditFlag(true);
            return true;
        }
        return false;

    }

    /**
     *
     * @param eventData
     * @return
     */
    public boolean setNeuroshareEventData(ArrayList<EventData> eventData) {
        CSVWriter nsCsvWriter = new CSVWriter();
        Object eventObject = eventData.clone();
        if (nsCsvWriter.overwriteTLFile(this.workingFilePath, eventObject, this.entity)) {
            this.setEditFlag(true);
            return true;
        }
        return false;
    }

    /**
     *
     * @param segmentData
     * @return
     */
    public boolean setNeuroshareSegmentData(SegmentData segmentData) {
        CSVWriter nsCsvWriter = new CSVWriter();
        if (nsCsvWriter.overwriteTIFile(this.workingFilePath, segmentData, this.entity)) {
            this.setEditFlag(true);
            return true;
        }
        return false;
    }

    /**
     *
     * @param neuralData
     * @return
     */
    public boolean setNeuroshareNeuralData(ArrayList<Double> neuralData) {
        CSVWriter nsCsvWriter = new CSVWriter();
        if (nsCsvWriter.overwriteTOFile(this.workingFilePath, neuralData, this.entity)) {
            this.setEditFlag(true);
            return true;
        }
        return false;
    }

    /**
     *
     * @return
     */
    public TSHeader getTSHeader() {
        return convertEntityToTSHeader();
    }

    /**
     *
     * @return
     */
    public TOHeader getTOHeader() {
        return convertEntityToTOHeader();
    }

    /**
     *
     * @return
     */
    public TIHeader getTIHeader() {
        return convertEntityToTIHeader();
    }

    /**
     *
     * @return
     */
    public TLHeader getTLHeader() {
        return convertEntityToTLHeader();
    }

    /**
     *
     * @return
     */
    public TSData getTSData() {
        CSVReader csvReader = new CSVReader();
        return csvReader.getTSData(this.workingFilePath);
    }

    /**
     *
     * @return
     */
    public TOData getTOData() {
        CSVReader csvReader = new CSVReader();
        return csvReader.getTOData(this.workingFilePath);
    }

    /**
     *
     * @return
     */
    public TIData getTIData() {
        CSVReader csvReader = new CSVReader();
        return csvReader.getTIData(this.workingFilePath);
    }

    /**
     *
     * @return
     */
    public TLData getTLData() {
        CSVReader csvReader = new CSVReader();
        return csvReader.getTLData(this.workingFilePath);
    }

    /**
     *
     * @param data
     * @return
     */
    public boolean setTSData(TSData data) {
        CSVWriter csvWriter = new CSVWriter();
        Entity e = csvWriter.overwriteTSFile(this.workingFilePath, data, this.entity);
        if (e == null) {
            // Case : The workingFile does not exist(delete manually).
            // Case : It can not delete the workingFile(other module is editing it).
            // Case : IOException occur(format error).
            return false;
        }
        this.setEntity(e);
        this.editFlag = true;
        return true;
    }

    /**
     *
     * @param data
     * @return
     */
    public boolean setTOData(TOData data) {
        CSVWriter csvWriter = new CSVWriter();
        Entity e = csvWriter.overwriteTOFile(this.workingFilePath, data, this.entity);
        if (e == null) {
            // Case : The workingFile does not exist(delete manually).
            // Case : It can not delete the workingFile(other module is editing it).
            // Case : IOException occur(format error).
            return false;
        }
        this.setEntity(e);
        this.editFlag = true;
        return true;
    }

    /**
     *
     * @param data
     * @return
     */
    public boolean setTIData(TIData data) {
        CSVWriter csvWriter = new CSVWriter();
        Entity e = csvWriter.overwriteTIFile(this.workingFilePath, data, this.entity);
        if (e == null) {
            // Case : The workingFile does not exist(delete manually).
            // Case : It can not delete the workingFile(other module is editing it).
            // Case : IOException occur(format error).
            return false;
        }
        this.setEntity(e);
        this.editFlag = true;
        return true;
    }

    /**
     *
     * @param data
     * @return
     */
    public boolean setTLData(TLData data) {
        CSVWriter csvWriter = new CSVWriter();
        Entity e = csvWriter.overwriteTLFile(this.workingFilePath, data, this.entity);
        if (e == null) {
            // Case : The workingFile does not exist(delete manually).
            // Case : It can not delete the workingFile(other module is editing it).
            // Case : IOException occur(format error).
            return false;
        }
        this.setEntity(e);
        this.editFlag = true;
        return true;
    }

    // Channnel's header can not modify.
//    public void setTSHeader(TSHeader tsHeader) {
//        this.setEntity(convertTSHeaderToEntity(tsHeader));
//        this.editFlag = true;
//    }
    // Channnel's header can not modify.
//    public void setTOHeader(TOHeader toHeader) {
//        this.setEntity(convertTOHeaderToEntity(toHeader));
//        this.editFlag = true;
//    }
    // Channnel's header can not modify.
//    public void setTIHeader(TIHeader tiHeader) {
//        this.setEntity(convertTIHeaderToEntity(tiHeader));
//        this.editFlag = true;
//    }
    // Channnel's header can not modify.
//    public void setTLHeader(TSLeader tlHeader) {
//        this.setEntity(convertTLHeaderToEntity(tlHeader));
//        this.editFlag = true;
//    }
    private TSHeader convertEntityToTSHeader() {
        AnalogInfo ai = (AnalogInfo) entity;
        return new TSHeader(ai.getSampleRate(), ai.getMinVal(), ai.getMaxVal(), ai.getUnits(), ai.getResolution(), ai.getLocationX(), ai.getLocationY(), ai.getLocationZ(), ai.getLocationUser(), ai.getHighFreqCorner(), ((Long) ai.getHighFreqOrder()).intValue(), ai.getHighFilterType(), ai.getLowFreqCorner(), ((Long) ai.getLowFreqOrder()).intValue(), ai.getLowFilterType(), ai.getProbeInfo());
    }

    private TOHeader convertEntityToTOHeader() {
        NeuralInfo ni = (NeuralInfo) entity;
        return new TOHeader(((Long) ni.getSourceEntityID()).intValue(), ((Long) ni.getSourceUnitID()).intValue(), ni.getProbeInfo());
    }

    private TIHeader convertEntityToTIHeader() {
        SegmentInfo si = (SegmentInfo) entity;
        SegmentSourceInfo ssi = si.getSegSourceInfos().get(0);
        return new TIHeader(((Long) si.getSourceCount()).intValue(), ((Long) si.getMinSampleCount()).intValue(), ((Long) si.getMaxSampleCount()).intValue(), si.getSampleRate(), si.getUnits(), ssi.getMinVal(), ssi.getMaxVal(), ssi.getResolution(), ssi.getSubSampleShift(), ssi.getLocationX(), ssi.getLocationY(), ssi.getLocationZ(), ssi.getLocationUser(), ssi.getHighFreqCorner(), ((Long) ssi.getHighFreqOrder()).intValue(), ssi.getHighFilterType(), ssi.getLowFreqCorner(), ((Long) ssi.getLowFreqOrder()).intValue(), ssi.getLowFilterType(), ssi.getProbeInfo());
    }

    private TLHeader convertEntityToTLHeader() {
        EventInfo ei = (EventInfo) entity;
        return new TLHeader(((Long) ei.getEventType()).intValue(), ((Long) ei.getMinDataLength()).intValue(), ((Long) ei.getMaxDataLength()).intValue(), ei.getCsvDesc());
    }

    private Entity convertTSHeaderToEntity(TSHeader tsHeader) {
        AnalogInfo ai = (AnalogInfo) entity;
        ai.setSampleRate(tsHeader.getSamplingRate_Hz());
        ai.setMinVal(tsHeader.getMinValue());
        ai.setMaxVal(tsHeader.getMaxValue());
        ai.setUnits(tsHeader.getUnitOfValue());
        ai.setResolution(tsHeader.getResolution());
        ai.setLocationX(tsHeader.getLocationX_m());
        ai.setLocationY(tsHeader.getLocationY_m());
        ai.setLocationZ(tsHeader.getLocationZ_m());
        ai.setLocationUser(tsHeader.getProbeNumber());
        ai.setHighFreqCorner(tsHeader.getHighFreqCutoff_Hz());
        ai.setHighFreqOrder(tsHeader.getOrderOfHighFreqCutoff());
        ai.setHighFilterType(tsHeader.getCommentOfHighFreqCutoff());
        ai.setLowFreqCorner(tsHeader.getLowFreqCutoff_Hz());
        ai.setLowFreqOrder(tsHeader.getOrderOfLowFreqCutoff());
        ai.setLowFilterType(tsHeader.getCommentOfLowFreqCutoff());
        ai.setProbeInfo(tsHeader.getCommentOfThisProbe());
        return (Entity) ai;
    }

    private Entity convertTOHeaderToEntity(TOHeader toHeader) {
        NeuralInfo ni = (NeuralInfo) entity;
        ni.setSourceEntityID(toHeader.getSourceEntityID());
        ni.setSourceUnitID(toHeader.getSourceUnitID());
        ni.setProbeInfo(toHeader.getCommentAboutThisProbe());
        return (Entity) ni;
    }

    private Entity convertTIHeaderToEntity(TIHeader tiHeader) {
        SegmentInfo si = (SegmentInfo) entity;
        ArrayList<SegmentSourceInfo> segSourceInfos = si.getSegSourceInfos();
        SegmentSourceInfo ssi = segSourceInfos.get(0);
        si.setSourceCount(tiHeader.getSourceCount());
        si.setMinSampleCount(tiHeader.getMinSampleCount());
        si.setMaxSampleCount(tiHeader.getMaxSampleCount());
        si.setSampleRate(tiHeader.getSamplingRate_Hz());
        si.setUnits(tiHeader.getUnitOfValue());
        ssi.setMinVal(tiHeader.getMinValue());
        ssi.setMaxVal(tiHeader.getMaxValue());
        ssi.setResolution(tiHeader.getResolution());
        ssi.setLocationX(tiHeader.getLocationX_m());
        ssi.setLocationY(tiHeader.getLocationY_m());
        ssi.setLocationZ(tiHeader.getLocationZ_m());
        ssi.setLocationUser(tiHeader.getProbeNumber());
        ssi.setHighFreqCorner(tiHeader.getHighFreqCutoff_Hz());
        ssi.setHighFreqOrder(tiHeader.getOrderOfHighFreqCutoff());
        ssi.setHighFilterType(tiHeader.getCommentOfHighFreqCutoff());
        ssi.setLowFreqCorner(tiHeader.getLowFreqCutoff_Hz());
        ssi.setLowFreqOrder(tiHeader.getOrderOfLowFreqCutoff());
        ssi.setLowFilterType(tiHeader.getCommentOfLowFreqCutoff());
        ssi.setProbeInfo(tiHeader.getCommentOfThisProbe());
        segSourceInfos.set(0, ssi);
        si.setSegSourceInfos(segSourceInfos);
        return (Entity) si;
    }

    private Entity convertTLHeaderToEntity(TLHeader tlHeader) {
        EventInfo ei = (EventInfo) entity;
        ei.setEventType(tlHeader.getEventType());
        ei.setMinDataLength(tlHeader.getMinDataLength());
        ei.setMaxDataLength(tlHeader.getMaxDataLength());
        ei.setCsvDesc(tlHeader.getCommentAboutThisProbe());
        return (Entity) ei;
    }
}
