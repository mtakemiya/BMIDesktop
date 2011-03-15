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
import jp.atr.dni.bmi.desktop.neuroshareutils.Entity;
import jp.atr.dni.bmi.desktop.neuroshareutils.EventData;
import jp.atr.dni.bmi.desktop.neuroshareutils.SegmentData;
import jp.atr.dni.bmi.desktop.neuroshareutils.TextEventData;
import jp.atr.dni.bmi.desktop.workingfileutils.NSCSVReader;
import jp.atr.dni.bmi.desktop.workingfileutils.NSCSVWriter;

/**
 *
 * @author kharada
 * @version 2011/02/23
 */
public class Channel {

    private List listeners = Collections.synchronizedList(new LinkedList());

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        listeners.add(pcl);
    }

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
    private String channelType;
    // Source File Path.
    private String sourceFilePath;
    // Working File Path.
    private String workingFilePath;
    // Edit flag.[ true : is editted. false : is not editted. default : false.]
    private boolean editFlag;
    // Case : Neuroshare
    private Entity entity;

    // Case : ???
    // Add here.
    // Constructor.
    public Channel() {
        this.channelID = -1;
        this.displayName = "";
        this.channelType = "";
        this.sourceFilePath = "";
        this.workingFilePath = "";
        this.editFlag = false;
    }

    // Constructor. Case : Neuroshare
    public Channel(int channelID, Entity entity) {
        this.channelID = channelID;
        this.displayName = entity.getEntityInfo().getEntityLabel();
        this.channelType = "Neuroshare/" + entity.getEntityInfo().getEntityTypeLabel();
        this.sourceFilePath = entity.getEntityInfo().getFilePath();
        this.workingFilePath = "";
        this.editFlag = false;
        this.entity = entity;

    }

    // Constructor. Case : ???
    // Add here.
    /**
     * @return the channelID
     */
    public int getChannelID() {
        return channelID;
    }

    /**
     * @param channelID the channelID to set
     */
    public void setChannelID(int channelID) {
        int old = this.channelID;
        this.channelID = channelID;
        this.fire("channelID", old, this.channelID);
    }

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
    public void setChannelType(String channelType) {
        String old = this.channelType;
        this.channelType = channelType;
        this.fire("channelType", old, this.channelType);
    }

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
    public ArrayList<AnalogData> getNeuroshareAnalogData() {
        try {
            NSCSVReader nsCsvReader = new NSCSVReader();
            return nsCsvReader.getAnalogData(this.workingFilePath);
        } catch (IOException ex) {
            Logger.getLogger(Channel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ArrayList<Double> getNeuroshareNeuralData() {
        try {
            NSCSVReader nsCsvReader = new NSCSVReader();
            return nsCsvReader.getNeuralData(this.workingFilePath);
        } catch (IOException ex) {
            Logger.getLogger(Channel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ArrayList<TextEventData> getNeuroshareTextEventData() {
        try {
            NSCSVReader nsCsvReader = new NSCSVReader();
            return nsCsvReader.getTextEventData(this.workingFilePath);
        } catch (IOException ex) {
            Logger.getLogger(Channel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public SegmentData getNeuroshareSegmentData() {
        try {
            NSCSVReader nsCsvReader = new NSCSVReader();
            return nsCsvReader.getSegmentData(this.workingFilePath);
        } catch (IOException ex) {
            Logger.getLogger(Channel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    // Set Data methods.
    public boolean setNeuroshareAnalogData(ArrayList<AnalogData> analogData) {
        NSCSVWriter nsCsvWriter = new NSCSVWriter();
        if (nsCsvWriter.overwriteTSFile(this.workingFilePath, analogData, this.entity)) {
            this.setEditFlag(true);
            return true;
        }
        return false;

    }

    public boolean setNeuroshareEventData(ArrayList<EventData> eventData) {
        NSCSVWriter nsCsvWriter = new NSCSVWriter();
        Object eventObject = eventData.clone();
        if (nsCsvWriter.overwriteTLFile(this.workingFilePath, eventObject, this.entity)) {
            this.setEditFlag(true);
            return true;
        }
        return false;
    }

    public boolean setNeuroshareSegmentData(SegmentData segmentData) {
        NSCSVWriter nsCsvWriter = new NSCSVWriter();
        if (nsCsvWriter.overwriteTIFile(this.workingFilePath, segmentData, this.entity)) {
            this.setEditFlag(true);
            return true;
        }
        return false;
    }

    public boolean setNeuroshareNeuralData(ArrayList<Double> neuralData) {
        NSCSVWriter nsCsvWriter = new NSCSVWriter();
        if (nsCsvWriter.overwriteTOFile(this.workingFilePath, neuralData, this.entity)) {
            this.setEditFlag(true);
            return true;
        }
        return false;
    }
}
