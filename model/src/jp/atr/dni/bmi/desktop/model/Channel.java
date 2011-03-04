/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.atr.dni.bmi.desktop.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import jp.atr.dni.bmi.desktop.neuroshareutils.Entity;

/**
 *
 * @author kharada
 * @version 2011/02/23
 */
public class Channel {

    private List listeners = Collections.synchronizedList(new LinkedList());

    public void addPropertyChangeListener(PropertyChangeListener pcl){
        listeners.add(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl){
        listeners.remove(pcl);
    }

    private void fire(String propertyName, Object old, Object neu){
        PropertyChangeListener[] pcls = (PropertyChangeListener[])listeners.toArray(new PropertyChangeListener[0]);
        for (int i=0; i<pcls.length; i++){
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
    }

    // Constructor. Case : Neuroshare
    public Channel(int channelID, Entity entity) {
        this.channelID = channelID;
        this.displayName = entity.getEntityInfo().getEntityLabel();
        this.channelType = "Neuroshare/" + entity.getEntityInfo().getEntityTypeLabel();
        this.sourceFilePath = entity.getEntityInfo().getFilePath();
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
     * Override is needed to display displayName on the ChannelList module.
     * @return displayName
     */
    @Override
    public String toString() {
        return this.displayName;
    }
}
