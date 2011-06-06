/**
 * 
 */
package jp.atr.dni.bmi.desktop.neuroshareutils;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Keiji Harada [*1]</br>[*1] ATR Intl. Conputational Neuroscience Labs, Decoding Group
 * @version 2011/04/22
 */
public class Ns_NeuralEventData {

    String intermediateFileNameForInfo;
    String intermediateFileNameForData;
    Ns_TagElement tagElement;
    Ns_EntityInfo entityInfo;
    Ns_NeuralInfo neuralInfo;

    /**
     * @param ID
     * @param szEntityLabel
     */
    public Ns_NeuralEventData(int ID, String szEntityLabel) {

        this.intermediateFileNameForInfo = Const_values.FN_HEADER + Const_values.NEURAL
                + "_" + ID + ".neuralEventInfo";
        this.intermediateFileNameForData = Const_values.FN_HEADER + Const_values.NEURAL
                + "_" + ID + ".neuralEventData";
        this.tagElement = new Ns_TagElement(Const_ns_ENTITY.ns_ENTITY_NEURAL);
        this.entityInfo = new Ns_EntityInfo(szEntityLabel, Const_ns_ENTITY.ns_ENTITY_NEURAL);
        this.tagElement.addDwElemLength(40); // Byte Num of ns_ENTITYINFO
        this.neuralInfo = new Ns_NeuralInfo();
        this.tagElement.addDwElemLength(136); // Byte Num of ns_NeuralINFO
    }

    /**
     * @return
     */
    public Nsa_NeuralInfo getNeuralInfo() {
        return this.neuralInfo.getMembers();
    }

    /**
     * @param nsaNeuralInfo
     * @return
     */
    public int setNeuralInfo(Nsa_NeuralInfo nsaNeuralInfo) {
        return this.neuralInfo.setMembers(nsaNeuralInfo);
    }

    /**
     *
     * @param dTimestamp
     * @return
     */
    public int addNeuralEventData(double dTimestamp) {

        int rtnVal = Const_values.NS_OK;
        File tempFile = null;
        FileOutputStream fos = null;
        DataOutputStream dos = null;

        try {

            // Open the intermediatefile. (use FileOutputStream, DataOutputStream.)
            tempFile = new File(this.intermediateFileNameForData);
            fos = new FileOutputStream(tempFile, true);
            dos = new DataOutputStream(fos);

            // Add dTimestamp[0] - dTimestamp[dwItemCount - 1]
            // Write in BIG Endian (JAVA Default)
         /*
             * dos.writeDouble(dTimestamp[jj]);
             */

            // Write in LITTLE Endian (MATLAB Default)
            dos.writeLong(Long.reverseBytes(Double.doubleToLongBits(dTimestamp)));

            // Close the intermediatefile. (use FileOutputStream, DataOutputStream.)
            dos.close();
            fos.close();

            // Add this.tagElement.addDwElemLength(some value).
            this.tagElement.addDwElemLength(8);

            // Add this.entityInfo.addDwItemCount(some value).
            this.entityInfo.addDwItemCount(1);

            // Then, NS_OK.
            rtnVal = Const_values.NS_OK;

        } catch (FileNotFoundException e) {
            // File Not Found.
            e.printStackTrace();

            // Then, NS_FILEERROR.
            rtnVal = Const_values.NS_FILEERROR;

        } catch (IOException e) {
            // File I/O error.
            e.printStackTrace();

            // Then, NS_FILEERROR.
            rtnVal = Const_values.NS_FILEERROR;

        } finally {
            try {
                if (!dos.equals(null)) {
                    dos.close();
                }
                if (!fos.equals(null)) {
                    fos.close();
                }

            } catch (IOException e) {
                // May be sequence doesn't reach here.
                e.printStackTrace();
                rtnVal = Const_values.NS_FILEERROR;

            }
        }

        // return the value.
        return rtnVal;
    }

    /**
     *
     * @return
     */
    public int saveNeuralInfo() {

        int rtnVal = Const_values.NS_OK;
        File tempFile = null;
        FileOutputStream fos = null;
        DataOutputStream dos = null;

        try {
            // Open the intermediatefile. (use FileOutputStream, DataOutputStream.)
            tempFile = new File(this.intermediateFileNameForInfo);
            fos = new FileOutputStream(tempFile, true);
            dos = new DataOutputStream(fos);

            // Add ns_NeuralInfo.
            // Write in BIG Endian (JAVA Default)
         /*
             * dos.writeInt(this.tagElement.getDwElementType());
             * dos.writeInt(this.tagElement.getDwElemLength()); String szEntityLabel =
             * (this.entityInfo.getSzEntityLabel() + (new Const_values()).getBlank32()) .substring(0,
             * (new Const_values()).getChar32()); dos.writeBytes(szEntityLabel);
             * dos.writeInt(this.entityInfo.getDwEntityType());
             * dos.writeInt(this.entityInfo.getDwItemCount());
             * dos.writeInt(this.neuralInfo.getMembers().getDwSourceEntityID());
             * dos.writeInt(this.neuralInfo.getMembers().getDwSourceUnitID()); String szProbeInfo =
             * (this.neuralInfo.getMembers().getSzProbeInfo() + (new Const_values())
             * .getBlank128()).substring(0, (new Const_values()).getChar128());
             * dos.writeBytes(szProbeInfo);
             */

            // Write in LITTLE Endian (MATLAB Default)
            dos.writeInt(Integer.reverseBytes(this.tagElement.getDwElementType()));
            dos.writeInt(Integer.reverseBytes(this.tagElement.getDwElemLength()));
            String szEntityLabel = (this.entityInfo.getSzEntityLabel() + Const_values.BLANK_CHAR32).substring(0, Const_values.LENGTH_OF_CHAR32);
            dos.writeBytes(szEntityLabel);
            dos.writeInt(Integer.reverseBytes(this.entityInfo.getDwEntityType()));
            dos.writeInt(Integer.reverseBytes(this.entityInfo.getDwItemCount()));
            dos.writeInt(Integer.reverseBytes(this.neuralInfo.getMembers().getDwSourceEntityID()));
            dos.writeInt(Integer.reverseBytes(this.neuralInfo.getMembers().getDwSourceUnitID()));
            String szProbeInfo = (this.neuralInfo.getMembers().getSzProbeInfo() + Const_values.BLANK_CHAR128).substring(0, Const_values.LENGTH_OF_CHAR128);
            dos.writeBytes(szProbeInfo);

            // Then, NS_OK.
            rtnVal = Const_values.NS_OK;

        } catch (FileNotFoundException e) {
            // File Not Found.
            e.printStackTrace();

            // Then, NS_FILEERROR.
            rtnVal = Const_values.NS_FILEERROR;

        } catch (IOException e) {
            // File I/O error.
            e.printStackTrace();

            // Then, NS_FILEERROR.
            rtnVal = Const_values.NS_FILEERROR;

        } finally {
            try {
                if (!dos.equals(null)) {
                    dos.close();
                }
                if (!fos.equals(null)) {
                    fos.close();
                }

            } catch (IOException e) {
                // May be sequence doesn't reach here.
                e.printStackTrace();
                rtnVal = Const_values.NS_FILEERROR;

            }
        }

        // return the value.
        return rtnVal;
    }
}
