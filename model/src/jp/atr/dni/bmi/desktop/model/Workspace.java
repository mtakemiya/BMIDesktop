/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.atr.dni.bmi.desktop.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import jp.atr.dni.bmi.desktop.workingfileutils.WorkingFileUtils;

/**
 *
 * @author Keiji Harada [*1]</br>[*1] ATR Intl. Conputational Neuroscience Labs, Decoding Group
 * @version 2011/04/22
 */
public class Workspace {

    // List of Property Change Listener.
    private static List listeners = Collections.synchronizedList(new LinkedList());

    // Add the listener.
    // Call this when you open ***TopComponent. [in your ***TopComponent.componentOpened]
    /**
     *
     * @param pcl
     */
    public static void addPropertyChangeListener(PropertyChangeListener pcl) {
        listeners.add(pcl);
    }

    // Remove the listener.
    // Call this when you close ***TopComponent. [in your ***TopComponent.componentClosed]
    /**
     *
     * @param pcl
     */
    public static void removePropertyChangeListener(PropertyChangeListener pcl) {
        listeners.remove(pcl);
    }

    // Fire the property change event. (All listeners receive this event.) [in your propertyChange method]
    private static void fire(String propertyName, Object old, Object neu) {
        PropertyChangeListener[] pcls = (PropertyChangeListener[]) listeners.toArray(new PropertyChangeListener[0]);
        for (int i = 0; i < pcls.length; i++) {
            pcls[i].propertyChange(new PropertyChangeEvent("Workspace.firePropertyChange", propertyName, old, neu));
        }
    }
    // Channels.
    private static ArrayList<Channel> channels = new ArrayList<Channel>();

    // Supplymentary Channels.
    // TODO : implement it here.
    // Get Channels.
    /**
     *
     * @return
     */
    public static ArrayList<Channel> getChannels() {
        return channels;
    }

    // Set Channels. (then fire "SetChannels" event.)
    /**
     *
     * @param chs
     */
    public static void setChannels(ArrayList<Channel> chs) {
        ArrayList<Channel> old = getChannels();
        channels = chs;
        fire("SetChannels", old, channels);
    }

    // Remove Channel. (then fire "RemoveChannel" event.)
    /**
     *
     * @param channel
     */
    public static void removeChannel(Channel channel) {

        ArrayList<Channel> old = getChannels();

        // If ChannelID, DisplayName, ChannelType and SourceFilePath is same, then remove.
        // Notice : it can't use remove(ch).
        for (int ii = 0; ii < channels.size(); ii++) {
            Channel ch = channels.get(ii);
            if (ch.getChannelID() == channel.getChannelID()) {
                if (ch.getDisplayName().equals(channel.getDisplayName())) {
                    if (ch.getChannelType().equals(channel.getChannelType())) {
                        if (ch.getSourceFilePath().equals(channel.getSourceFilePath())) {
                            channels.remove(ch);
                            // remove workingfile.
                            WorkingFileUtils wfu = new WorkingFileUtils(channel.getWorkingFilePath());
                            wfu.removeWorkingFile();
                            fire("RemoveChannel", old, channels);
                            return;
                        }
                    }
                }
            }
        }
    }

    // Add Channel. (then fire "AddChannel" event.)
    /**
     *
     * @param channel
     */
    public static void addChannel(Channel channel) {

        ArrayList<Channel> old = getChannels();

        //remove channel if it exits already.
        removeChannel(channel);
        channels.add(channel);
        fire("AddChannel", old, channels);

    }

    public static void removeAllChannels() {

        ArrayList<Channel> old = getChannels();
        int size = channels.size();

        // remove all channels.
        for (int ii = 0; ii < size; ii++) {

            // remove the channel.
            Channel ch = channels.get(0);
            channels.remove(ch);

            // remove the workingfile.
            WorkingFileUtils wfu = new WorkingFileUtils(ch.getWorkingFilePath());
            wfu.removeWorkingFile();

            // fire event.
            fire("RemoveChannel", old, channels);

        }
    }
}
