/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.atr.dni.bmi.desktop.model;

import java.util.ArrayList;

/**
 *
 * @author kharada
 * @version 2011/02/23
 */
public class Workspace {

    // Channels on the workspace.
    private static ArrayList<Channel> channels = new ArrayList<Channel>();

    public static ArrayList<Channel> getChannels() {
        return channels;
    }

    public static void setChannels(ArrayList<Channel> chs) {
        channels = chs;
    }

    public static void removeChannel(Channel channel) {
        // If ChannelID, DisplayName, ChannelType and SourceFilePath is same, then remove.
        // Notice : it can't use remove(ch).
        for (int ii = 0; ii < channels.size(); ii++) {
            Channel ch = channels.get(ii);
            if (ch.getChannelID() == channel.getChannelID()) {
                if (ch.getDisplayName().equals(channel.getDisplayName())) {
                    if (ch.getChannelType().equals(channel.getChannelType())) {
                        if (ch.getSourceFilePath().equals(channel.getSourceFilePath())) {
                            channels.remove(ch);
                            return;
                        }
                    }
                }
            }
        }
    }

    public static void addChannel(Channel channel) {
        //remove channel if it exits already.
        removeChannel(channel);
        channels.add(channel);
    }
}
