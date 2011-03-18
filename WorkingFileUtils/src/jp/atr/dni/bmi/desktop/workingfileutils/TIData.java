/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jp.atr.dni.bmi.desktop.workingfileutils;

import java.util.ArrayList;

/**
 *
 * @author kharada
 */
public class TIData {

    private final String formatCode = "TI";
    private double samplingRate;
    private ArrayList<Double> timeStamps = new ArrayList<Double>();
    private ArrayList<Integer> unitIDs = new ArrayList<Integer>();
    private ArrayList<ArrayList<Double>> values = new ArrayList<ArrayList<Double>>();

}
