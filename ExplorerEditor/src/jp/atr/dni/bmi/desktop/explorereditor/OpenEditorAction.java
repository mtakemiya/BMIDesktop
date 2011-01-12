/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jp.atr.dni.bmi.desktop.explorereditor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author kharada
 */
public class OpenEditorAction implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        ExplorerEditor editor = new ExplorerEditor();
        editor.open();
        editor.requestActive();
    }

}
