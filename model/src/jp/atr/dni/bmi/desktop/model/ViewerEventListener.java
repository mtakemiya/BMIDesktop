/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jp.atr.dni.bmi.desktop.model;

import java.util.EventListener;

/**
 *
 * @author kharada
 */
public interface ViewerEventListener extends EventListener{

    public void valueChanged(ViewerEventObject veo);
}
