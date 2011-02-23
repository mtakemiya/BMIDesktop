/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.atr.dni.bmi.desktop.model;

import jp.atr.dni.bmi.desktop.neuroshareutils.Entity;

/**
 *
 * @author kharada
 * @version 2011/02/23
 */
public class Channel {

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
        this.channelID = channelID;
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
        this.displayName = displayName;
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
        this.channelType = channelType;
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
        this.entity = entity;
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
        this.sourceFilePath = sourceFilePath;
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
