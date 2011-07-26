package jp.atr.dni.bmi.desktop.explorereditor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Keiji Harada [*1]</br>[*1] ATR Intl. Computational Neuroscience Labs, Decoding Group
 * @version 2011/04/22
 */
public class OpenEditorAction implements ActionListener {

   /**
    *
    * @param e
    */
   @Override
   public void actionPerformed(ActionEvent e) {
      ExplorerEditor editor = new ExplorerEditor();
      editor.open();
      editor.requestActive();
   }
}
