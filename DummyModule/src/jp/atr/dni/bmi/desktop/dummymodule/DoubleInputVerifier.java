/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.atr.dni.bmi.desktop.dummymodule;

import java.awt.Color;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 *
 * @author kharada
 */
public class DoubleInputVerifier extends InputVerifier {

    @Override
    public boolean verify(JComponent jc) {
        boolean rtnValue = false;
        JTextField jt = (JTextField) jc;
        try {
            Double.parseDouble(jt.getText());
            rtnValue = true;
            jc.setBackground(Color.WHITE);
        } catch (NumberFormatException e) {
            jc.setBackground(Color.PINK);
            UIManager.getLookAndFeel().provideErrorFeedback(jc);
        }
        return rtnValue;
    }
}
