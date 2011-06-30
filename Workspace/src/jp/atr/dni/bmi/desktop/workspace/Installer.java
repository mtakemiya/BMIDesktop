/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.atr.dni.bmi.desktop.workspace;

import jp.atr.dni.bmi.desktop.model.Workspace;
import org.openide.modules.ModuleInstall;

/**
 * Manages a module's lifecycle. Remember that an installer is optional and
 * often not needed at all.
 */
public class Installer extends ModuleInstall {

    @Override
    public void restored() {
        // Note : This method will be called when User open or re-open the application. 
        // ( Not open or re-open the Workspace window!!! Be Care! )
        // Nothing to do.
       System.setProperty("sun.awt.noerasebackground", "true");
    }

    @Override
    public void close() {

        // Note : This method will be called when User close the application. 
        // ( Not close the Workspace window!!! Be Care! )

        // Delete Channels and Working Files which exist on the workspace.
        Workspace.removeAllChannels();
    }
}
