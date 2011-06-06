/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.atr.dni.bmi.desktop.model;

import java.util.EventListener;

/**
 *
 * @author Keiji Harada [*1]</br>[*1] ATR Intl. Conputational Neuroscience Labs, Decoding Group
 * @version 2011/04/22
 */
public interface ViewerEventListener extends EventListener {

    /**
     *
     * @param veo
     */
    public void valueChanged(ViewerEventObject veo);
}
