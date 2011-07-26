/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.atr.dni.bmi.desktop.workspace;

import java.awt.Component;
import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.WizardValidationException;
import org.openide.util.HelpCtx;

public class CreateNewFileWizardPanel2 implements WizardDescriptor.ValidatingPanel {

    /**
     * The visual component that displays this panel. If you need to access the
     * component from this class, just use getComponent().
     */
    private CreateNewFileVisualPanel2 component;

    // Get the visual component for the panel. In this template, the component
    // is kept separate. This can be more efficient: if the wizard is created
    // but never displayed, or not all panels are displayed, it is better to
    // create only those which really need to be visible.
    public Component getComponent() {
        if (component == null) {
            component = new CreateNewFileVisualPanel2();
        }
        return component;
    }

    public HelpCtx getHelp() {
        // Show no Help button for this panel:
        return HelpCtx.DEFAULT_HELP;
        // If you have context help:
        // return new HelpCtx(SampleWizardPanel1.class);
    }

    public boolean isValid() {
        // If it is always OK to press Next or Finish, then:
        return true;
        // If it depends on some condition (form filled out...), then:
        // return someCondition();
        // and when this condition changes (last form field filled in...) then:
        // fireChangeEvent();
        // and uncomment the complicated stuff below.
    }

    public final void addChangeListener(ChangeListener l) {
    }

    public final void removeChangeListener(ChangeListener l) {
    }
    /*
    private final Set<ChangeListener> listeners = new HashSet<ChangeListener>(1); // or can use ChangeSupport in NB 6.0
    public final void addChangeListener(ChangeListener l) {
    synchronized (listeners) {
    listeners.add(l);
    }
    }
    public final void removeChangeListener(ChangeListener l) {
    synchronized (listeners) {
    listeners.remove(l);
    }
    }
    protected final void fireChangeEvent() {
    Iterator<ChangeListener> it;
    synchronized (listeners) {
    it = new HashSet<ChangeListener>(listeners).iterator();
    }
    ChangeEvent ev = new ChangeEvent(this);
    while (it.hasNext()) {
    it.next().stateChanged(ev);
    }
    }
     */

    // You can use a settings object to keep track of state. Normally the
    // settings object will be the WizardDescriptor, so you can use
    // WizardDescriptor.getProperty & putProperty to store information entered
    // by the user.
    public void readSettings(Object settings) {
        
        // Set showed tab.
        Object index = ((WizardDescriptor) settings).getProperty("fileFormat");
        Integer indexValue = (Integer)index;
        component.setShowedTabIndex(indexValue);
    }

    public void storeSettings(Object settings) {

        try {
            validate();
            ((WizardDescriptor) settings).putProperty("fileType", ((CreateNewFileVisualPanel2) getComponent()).getNeuroshareFileType());
            ((WizardDescriptor) settings).putProperty("timeStampResolution", Double.valueOf(((CreateNewFileVisualPanel2) getComponent()).getNeuroshareTimeStampResolution()));
            ((WizardDescriptor) settings).putProperty("timeSpan", Double.valueOf(((CreateNewFileVisualPanel2) getComponent()).getNeuroshareTimeSpan()));
            ((WizardDescriptor) settings).putProperty("applicationName", ((CreateNewFileVisualPanel2) getComponent()).getNeuroshareApplicationName());
            ((WizardDescriptor) settings).putProperty("year", Integer.valueOf(((CreateNewFileVisualPanel2) getComponent()).getNeuroshareYear()));
            ((WizardDescriptor) settings).putProperty("month", Integer.valueOf(((CreateNewFileVisualPanel2) getComponent()).getNeuroshareMonth()));
            ((WizardDescriptor) settings).putProperty("day", Integer.valueOf(((CreateNewFileVisualPanel2) getComponent()).getNeuroshareDay()));
            ((WizardDescriptor) settings).putProperty("hour", Integer.valueOf(((CreateNewFileVisualPanel2) getComponent()).getNeuroshareHour()));
            ((WizardDescriptor) settings).putProperty("min", Integer.valueOf(((CreateNewFileVisualPanel2) getComponent()).getNeuroshareMin()));
            ((WizardDescriptor) settings).putProperty("sec", Integer.valueOf(((CreateNewFileVisualPanel2) getComponent()).getNeuroshareSec()));
            ((WizardDescriptor) settings).putProperty("milliSec", Integer.valueOf(((CreateNewFileVisualPanel2) getComponent()).getNeuroshareMilliSec()));
            ((WizardDescriptor) settings).putProperty("comments", ((CreateNewFileVisualPanel2) getComponent()).getNeuroshareComments());

        } catch (WizardValidationException ex) {
        }
    }

    @Override
    public void validate() throws WizardValidationException {
               String buffer = "";

        try {
            buffer = component.getNeuroshareTimeStampResolution();
            Double v = Double.valueOf(buffer);
        } catch (NumberFormatException e) {
            throw new WizardValidationException(null, "Invalid TimeStampResolution value", null);
        }

        try {
            buffer = component.getNeuroshareTimeSpan();
            Double v = Double.valueOf(buffer);
        } catch (NumberFormatException e) {
            throw new WizardValidationException(null, "Invalid TimeSpan value", null);
        }

        try {
            buffer = component.getNeuroshareYear();
            Integer v = Integer.valueOf(buffer);
        } catch (NumberFormatException e) {
            throw new WizardValidationException(null, "Invalid Year value", null);
        }

        try {
            buffer = component.getNeuroshareMonth();
            Integer v = Integer.valueOf(buffer);
        } catch (NumberFormatException e) {
            throw new WizardValidationException(null, "Invalid Month value", null);
        }

        try {
            buffer = component.getNeuroshareDay();
            Integer v = Integer.valueOf(buffer);
        } catch (NumberFormatException e) {
            throw new WizardValidationException(null, "Invalid Day value", null);
        }

        try {
            buffer = component.getNeuroshareHour();
            Integer v = Integer.valueOf(buffer);
        } catch (NumberFormatException e) {
            throw new WizardValidationException(null, "Invalid Hour value", null);
        }

        try {
            buffer = component.getNeuroshareMin();
            Integer v = Integer.valueOf(buffer);
        } catch (NumberFormatException e) {
            throw new WizardValidationException(null, "Invalid Min value", null);
        }

        try {
            buffer = component.getNeuroshareSec();
            Integer v = Integer.valueOf(buffer);
        } catch (NumberFormatException e) {
            throw new WizardValidationException(null, "Invalid Sec value", null);
        }

        try {
            buffer = component.getNeuroshareMilliSec();
            Integer v = Integer.valueOf(buffer);
        } catch (NumberFormatException e) {
            throw new WizardValidationException(null, "Invalid MilliSec value", null);
        }

    }
}
