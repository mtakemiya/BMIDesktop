/**
 * 
 */
package jp.atr.dni.bmi.desktop.neuroshareutils;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 *
 * @author Keiji Harada [*1]</br>[*1] ATR Intl. Conputational Neuroscience Labs, Decoding Group
 * @version 2011/04/22
 */
public class Ns_AnalogData {

    String intermediateFileNameForInfo;
    String intermediateFileNameForData;
    Ns_TagElement tagElement;
    Ns_EntityInfo entityInfo;
    Ns_AnalogInfo analogInfo;

    /**
     * @param ID
     * @param szEntityLabel
     */
    public Ns_AnalogData(int ID, String szEntityLabel) {

        this.intermediateFileNameForInfo = Const_values.FN_HEADER + Const_values.ANALOG
                + "_" + ID + ".analogInfo";
        this.intermediateFileNameForData = Const_values.FN_HEADER + Const_values.ANALOG
                + "_" + ID + ".analogData";
        this.tagElement = new Ns_TagElement(Const_ns_ENTITY.ns_ENTITY_ANALOG);
        this.entityInfo = new Ns_EntityInfo(szEntityLabel, Const_ns_ENTITY.ns_ENTITY_ANALOG);
        this.tagElement.addDwElemLength(40);
        this.analogInfo = new Ns_AnalogInfo();
        this.tagElement.addDwElemLength(264);
    }

    /**
     * @return
     */
    public Nsa_AnalogInfo getAnalogInfo() {
        return this.analogInfo.getMembers();
    }

    /**
     * @param nsaAnalogInfo
     * @return
     */
    public int setAnalogInfo(Nsa_AnalogInfo nsaAnalogInfo) {
        return this.analogInfo.setMembers(nsaAnalogInfo);
    }

    /**
     *
     * @param dTimestamp
     * @param dAnalogValue
     * @return
     */
    public int addAnalogData(double dTimestamp, double[] dAnalogValue) {

        int rtnVal = Const_values.NS_OK;
        File tempFile = null;
        FileOutputStream fos = null;
        DataOutputStream dos = null;

        try {
            // Collect values to rewrite object.
            double[] dCopyAnalogValue = dAnalogValue.clone();
            Arrays.sort(dCopyAnalogValue);
            int dwDataCount = dCopyAnalogValue.length;
            double dMaxInTheArray = dCopyAnalogValue[dCopyAnalogValue.length - 1];
            double dMinInTheArray = dCopyAnalogValue[0];

            // Open the intermediatefile. (use FileOutputStream, DataOutputStream.)
            tempFile = new File(this.intermediateFileNameForData);
            fos = new FileOutputStream(tempFile, true);
            dos = new DataOutputStream(fos);

            // Add dTimestamp, dwDataCount, dAnalogValue[0] - dAnalogValue[dwDataCount - 1]
            // Write in BIG Endian (JAVA Default)
         /*
             * dos.writeDouble(dTimestamp); dos.writeInt(dwDataCount); for (int jj = 0; jj <
             * dAnalogValue.length; jj++) { dos.writeDouble(dAnalogValue[jj]); }
             */

            // Write in LITTLE Endian (MATLAB Default)
            dos.writeLong(Long.reverseBytes(Double.doubleToLongBits(dTimestamp)));
            dos.writeInt(Integer.reverseBytes(dwDataCount));
            for (int jj = 0; jj < dAnalogValue.length; jj++) {
                dos.writeLong(Long.reverseBytes(Double.doubleToLongBits(dAnalogValue[jj])));
            }

            // Close the intermediatefile. (use FileOutputStream, DataOutputStream.)
            dos.close();
            fos.close();

            // Add this.tagElement.addDwElemLength(some value).
            this.tagElement.addDwElemLength(8 + 4 + 8 * dwDataCount);

            // Add this.entityInfo.addDwItemCount(some value).
            this.entityInfo.addDwItemCount(dwDataCount);

            // Set this.analogInfo.dMaxVal as dMaxInTheArray (if dMaxInTheArray is bigger).
            // Set this.analogInfo.dMinVal as dMinInTheArray (if dMinInTheArray is smaller).
            if (this.analogInfo.getDMaxVal() < dMaxInTheArray) {
                this.analogInfo.setDMaxVal(dMaxInTheArray);
            }
            if (this.analogInfo.getDMinVal() > dMinInTheArray) {
                this.analogInfo.setDMinVal(dMinInTheArray);
            }

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
    public int saveAnalogInfo() {

        int rtnVal = Const_values.NS_OK;
        File tempFile = null;
        FileOutputStream fos = null;
        DataOutputStream dos = null;

        try {
            // Open the intermediatefile. (use FileOutputStream, DataOutputStream.)
            tempFile = new File(this.intermediateFileNameForInfo);
            fos = new FileOutputStream(tempFile, true);
            dos = new DataOutputStream(fos);

            // Add ns_AnalogInfo.
            // Write in BIG Endian (JAVA Default)
         /*
             * dos.writeInt(this.tagElement.getDwElementType());
             * dos.writeInt(this.tagElement.getDwElemLength()); String szEntityLabel =
             * (this.entityInfo.getSzEntityLabel() + (new Const_values()).getBlank32()) .substring(0,
             * (new Const_values()).getChar32()); dos.writeBytes(szEntityLabel);
             * dos.writeInt(this.entityInfo.getDwEntityType());
             * dos.writeInt(this.entityInfo.getDwItemCount());
             * dos.writeDouble(this.analogInfo.getMembers().getDSampleRate());
             * dos.writeDouble(this.analogInfo.getMembers().getDMinVal());
             * dos.writeDouble(this.analogInfo.getMembers().getDMaxVal()); String szUnits =
             * (this.analogInfo.getMembers().getSzUnits() + (new Const_values()).getBlank16())
             * .substring(0, (new Const_values()).getChar16()); dos.writeBytes(szUnits);
             * dos.writeDouble(this.analogInfo.getMembers().getDResolution());
             * dos.writeDouble(this.analogInfo.getMembers().getDLocationX());
             * dos.writeDouble(this.analogInfo.getMembers().getDLocationY());
             * dos.writeDouble(this.analogInfo.getMembers().getDLocationZ());
             * dos.writeDouble(this.analogInfo.getMembers().getDLocationUser());
             * dos.writeDouble(this.analogInfo.getMembers().getDHighFreqCorner());
             * dos.writeInt(this.analogInfo.getMembers().getDwHighFreqOrder()); String szHighFilterType
             * = (this.analogInfo.getMembers().getSzHighFilterType() + (new Const_values())
             * .getBlank16()).substring(0, (new Const_values()).getChar16());
             * dos.writeBytes(szHighFilterType);
             * dos.writeDouble(this.analogInfo.getMembers().getDLowFreqCorner());
             * dos.writeInt(this.analogInfo.getMembers().getDwLowFreqOrder()); String szLowFilterType =
             * (this.analogInfo.getMembers().getSzLowFilterType() + (new Const_values())
             * .getBlank16()).substring(0, (new Const_values()).getChar16());
             * dos.writeBytes(szLowFilterType); String szProbeInfo =
             * (this.analogInfo.getMembers().getSzProbeInfo() + (new Const_values())
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

            dos.writeLong(Long.reverseBytes(Double.doubleToLongBits(this.analogInfo.getMembers().getDSampleRate())));
            dos.writeLong(Long.reverseBytes(Double.doubleToLongBits(this.analogInfo.getMembers().getDMinVal())));
            dos.writeLong(Long.reverseBytes(Double.doubleToLongBits(this.analogInfo.getMembers().getDMaxVal())));
            String szUnits = (this.analogInfo.getMembers().getSzUnits() + Const_values.BLANK_CHAR16).substring(0, Const_values.LENGTH_OF_CHAR16);
            dos.writeBytes(szUnits);
            dos.writeLong(Long.reverseBytes(Double.doubleToLongBits(this.analogInfo.getMembers().getDResolution())));
            dos.writeLong(Long.reverseBytes(Double.doubleToLongBits(this.analogInfo.getMembers().getDLocationX())));
            dos.writeLong(Long.reverseBytes(Double.doubleToLongBits(this.analogInfo.getMembers().getDLocationY())));
            dos.writeLong(Long.reverseBytes(Double.doubleToLongBits(this.analogInfo.getMembers().getDLocationZ())));
            dos.writeLong(Long.reverseBytes(Double.doubleToLongBits(this.analogInfo.getMembers().getDLocationUser())));
            dos.writeLong(Long.reverseBytes(Double.doubleToLongBits(this.analogInfo.getMembers().getDHighFreqCorner())));
            dos.writeInt(Integer.reverseBytes(this.analogInfo.getMembers().getDwHighFreqOrder()));
            String szHighFilterType = (this.analogInfo.getMembers().getSzHighFilterType() + Const_values.BLANK_CHAR16).substring(0, Const_values.LENGTH_OF_CHAR16);
            dos.writeBytes(szHighFilterType);
            dos.writeLong(Long.reverseBytes(Double.doubleToLongBits(this.analogInfo.getMembers().getDLowFreqCorner())));
            dos.writeInt(Integer.reverseBytes(this.analogInfo.getMembers().getDwLowFreqOrder()));
            String szLowFilterType = (this.analogInfo.getMembers().getSzLowFilterType() + Const_values.BLANK_CHAR16).substring(0, Const_values.LENGTH_OF_CHAR16);
            dos.writeBytes(szLowFilterType);
            String szProbeInfo = (this.analogInfo.getMembers().getSzProbeInfo() + Const_values.BLANK_CHAR128).substring(0, Const_values.LENGTH_OF_CHAR128);
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
