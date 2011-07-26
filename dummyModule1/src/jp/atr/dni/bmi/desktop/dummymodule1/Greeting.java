/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jp.atr.dni.bmi.desktop.dummymodule1;

/**
 * Dummy class to test all.
 * @author Keiji Harada [*1]</br>[*1] ATR Intl. Conputational Neuroscience Labs, Decoding Group
 * @version 2011/04/22
 */
public class Greeting {

    // Greeting String.
    private String greeting ="";

    /**
     * Java Constructor.
     */
    public Greeting(){
        this.greeting = "Hello world!";

    }
    
    // You can modify this class FREELY!!!
    // Try to add / mod / del / members and methods.
    // If you are familier with Java, please write down Javadoc with your methods.

    // I wrote some hints about NetBeans and Javadoc below.
    // Please see below contents.

    // NetBeans and Javadoc Hints.
    // 1. How to configure src's comment format.
    // 2. How to create Javadoc automatically.

    // 1. How to configure src's comment format.
    // You can configure src's format.
    // (1) choose Tools - Templates
    // (2) select Java - Java Class
    // (3) type strings what you want.

    // Ref: also you can modify @author's name.
    // Choose Tools - Templates - UserProperty - User.properties
    // Type user=Keiji Harada [*1]</br>[*1]... and save it.
    // Re-create comment automatically (see below).

    // 2. How to create Javadoc automatically.
    // You can create Javadoc automatically like batch.
    // (1)choose Tools - Analize Javadoc (Analizer will be displayed.)
    // (2)select contents and click button below the list.


    /**
     * @return the greeting
     */
    public String getGreeting() {
        return greeting;
    }

    /**
     * @param greeting the greeting to set
     */
    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

}
