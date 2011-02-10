/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.atr.dni.bmi.desktop.model;

import java.util.ArrayList;

/**
 *
 * @author kharada
 * @version 2011/02/09
 */
public class Workspace {

    // Channels on the workspace.
    public static ArrayList<Channel> channels = new ArrayList<Channel>();

    public static ArrayList<Channel> getChannels() {
        return channels;
    }

    public static void setChannels(ArrayList<Channel> channels) {
        Workspace.channels = channels;
    }

    public static void removeChannel(Channel ch) {
        Workspace.channels.remove(ch);
    }

    public static void addChannel(Channel ch) {
        int a = Workspace.channels.indexOf(ch);
        if (a == -1) {
            Workspace.channels.add(ch);
        }
    }
}
