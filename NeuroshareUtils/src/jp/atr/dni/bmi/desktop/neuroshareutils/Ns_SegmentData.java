/**
 * 
 */
package jp.atr.dni.bmi.desktop.neuroshareutils;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author kharada
 * @version 2011/01/13
 */
public class Ns_SegmentData {

    String intermediateFileNameForInfo;
    String intermediateFileNameForData;
    int segmentID = 0;
    ArrayList<String> intermediateFileNameForSourceInfo = null;
    Ns_TagElement tagElement;
    Ns_EntityInfo entityInfo;
    Ns_SegmentInfo segmentInfo;
    ArrayList<Ns_SegSourceInfo> segSourceInfo = null;

    /**
     * @param szEntityLabel
     */
    public Ns_SegmentData(int ID, String szEntityLabel) {

        this.intermediateFileNameForInfo = Const_values.FN_HEADER + Const_values.SEGMENT
                + "_" + ID + ".segmentInfo";
        this.intermediateFileNameForData = Const_values.FN_HEADER + Const_values.SEGMENT
                + "_" + ID + ".segmentData";
        this.segmentID = ID;
        this.tagElement = new Ns_TagElement(Const_ns_ENTITY.ns_ENTITY_SEGMENT);
        this.entityInfo = new Ns_EntityInfo(szEntityLabel, Const_ns_ENTITY.ns_ENTITY_SEGMENT);
        this.tagElement.addDwElemLength(40); // Byte Num of ns_ENTITYINFO
        this.segmentInfo = new Ns_SegmentInfo();
        this.tagElement.addDwElemLength(52); // Byte Num of ns_SEGMENTINFO
    }

    /**
     * @return
     */
    public Nsa_SegmentInfo getSegmentInfo() {
        return this.segmentInfo.getMembers();
    }

    /**
     * @param nsaSegmentInfo
     * @return
     */
    public int setSegmentInfo(Nsa_SegmentInfo nsaSegmentInfo) {
        return this.segmentInfo.setMembers(nsaSegmentInfo);
    }

    /**
     * @return
     */
    public Nsa_SegSourceInfo getSegSourceInfo(int segSourceID) {
        return this.segSourceInfo.get(segSourceID).getMembers();
    }

    /**
     * @param nsaSegSourceInfo
     * @return
     */
    public int setSegSourceInfo(int segSourceID, Nsa_SegSourceInfo nsaSegSourceInfo) {
        return this.segSourceInfo.get(segSourceID).setMembers(nsaSegSourceInfo);
    }

    public int addSegmentData(double dTimestamp, int dwUnitID, double[] dValue) {

        int segSourceID = -1;
        File tempFile = null;
        FileOutputStream fos = null;
        DataOutputStream dos = null;

        try {
            // Collect values to rewrite object.
            double[] dCopyValue = dValue.clone();
            Arrays.sort(dCopyValue);
            int dwSampleCount = dCopyValue.length;
            double dMaxInTheArray = dCopyValue[dCopyValue.length - 1];
            double dMinInTheArray = dCopyValue[0];

            // Open the intermediatefile. (use FileOutputStream, DataOutputStream.)
            tempFile = new File(this.intermediateFileNameForData);
            fos = new FileOutputStream(tempFile, true);
            dos = new DataOutputStream(fos);

            // Add dwSampleCount, dTimestamp, dwUnitID, dValue[0] - dAnalogValue[dwSampleCount - 1]
            // Write in BIG Endian (JAVA Default)
         /*
             * dos.writeInt(dwSampleCount); dos.writeDouble(dTimestamp); dos.writeInt(dwUnitID); for
             * (int jj = 0; jj < dValue.length; jj++) { dos.writeDouble(dValue[jj]); }
             */

            // Write in LITTLE Endian (MATLAB Default)
            dos.writeInt(Integer.reverseBytes(dwSampleCount));
            dos.writeLong(Long.reverseBytes(Double.doubleToLongBits(dTimestamp)));
            dos.writeInt(Integer.reverseBytes(dwUnitID));
            for (int jj = 0; jj < dValue.length; jj++) {
                dos.writeLong(Long.reverseBytes(Double.doubleToLongBits(dValue[jj])));
            }

            // Close the intermediatefile. (use FileOutputStream, DataOutputStream.)
            dos.close();
            fos.close();

            // Add this.tagElement.addDwElemLength(some value).
            this.tagElement.addDwElemLength(4 + 8 + 4 + 8 * dwSampleCount);

            // Add this.entityInfo.addDwItemCount(some value).
            this.entityInfo.addDwItemCount(1);

            // Set this.segmentInfo.dwMaxSampleCount as dwSamplecount (if dwSamplecount is bigger).
            // Set this.segmentInfo.dwMinSampleCount as dwSampleCount (if dwSamplecount is smaller).
            if (this.segmentInfo.getDwMaxSampleCount() < dwSampleCount) {
                this.segmentInfo.setDwMaxSampleCount(dwSampleCount);
            }
            if (this.segmentInfo.getDwMinSampleCount() > dwSampleCount) {
                this.segmentInfo.setDwMinSampleCount(dwSampleCount);
            }

            // Create ns_SEGSOURCEINFO if first call.
            if (this.segSourceInfo == null) {
                this.segSourceInfo = new ArrayList<Ns_SegSourceInfo>();
                this.intermediateFileNameForSourceInfo = new ArrayList<String>();
            }

            // Add ns_SegSourceInfo
            this.segSourceInfo.add(new Ns_SegSourceInfo());

            // segSourceID : identification num of ns_SEGSOURCEINFO. [ 0,1,2... ]
            segSourceID = this.segSourceInfo.size() - 1;

            // Define intermediate FILE name for ns_SEGSOURCEINFO.
            this.intermediateFileNameForSourceInfo.add(Const_values.FN_HEADER
                    + Const_values.SEGMENT + "_" + this.segmentID + "_" + segSourceID
                    + ".segSourceInfo");
            this.tagElement.addDwElemLength(248); // Byte Num of ns_SEGSOURCEINFO
            this.segmentInfo.addDwSourceCount(1); // Byte Num of ns_SEGSOURCEINFO

            // Set this.segSourceInfo.dMaxVal as dMaxInTheArray (if dMaxInTheArray is bigger).
            // Set this.segSourceInfo.dMinVal as dMinInTheArray (if dMinInTheArray is smaller).
            if (this.segSourceInfo.get(segSourceID).getDMaxVal() < dMaxInTheArray) {
                this.segSourceInfo.get(segSourceID).setDMaxVal(dMaxInTheArray);
            }
            if (this.segSourceInfo.get(segSourceID).getDMinVal() > dMinInTheArray) {
                this.segSourceInfo.get(segSourceID).setDMinVal(dMinInTheArray);
            }

            // Then, return segSourceID(num of segSource - 1).

        } catch (FileNotFoundException e) {
            // File Not Found.
            e.printStackTrace();

            // Then, NS_FILEERROR.
            segSourceID = Const_values.NS_FILEERROR;

        } catch (IOException e) {
            // File I/O error.
            e.printStackTrace();

            // Then, NS_FILEERROR.
            segSourceID = Const_values.NS_FILEERROR;

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
                segSourceID = Const_values.NS_FILEERROR;

            }
        }

        // return the value.
        return segSourceID;
    }

    public int addSegmentDataWithoutAddingExtraSegSourceInfo(double dTimestamp, int dwUnitID, double[] dValue) {

        int rtnVal = Const_values.NS_OK;
        int segSourceID = -1;
        File tempFile = null;
        FileOutputStream fos = null;
        DataOutputStream dos = null;

        try {
            // Collect values to rewrite object.
            double[] dCopyValue = dValue.clone();
            Arrays.sort(dCopyValue);
            int dwSampleCount = dCopyValue.length;
            double dMaxInTheArray = dCopyValue[dCopyValue.length - 1];
            double dMinInTheArray = dCopyValue[0];

            // Open the intermediatefile. (use FileOutputStream, DataOutputStream.)
            tempFile = new File(this.intermediateFileNameForData);
            fos = new FileOutputStream(tempFile, true);
            dos = new DataOutputStream(fos);

            // Add dwSampleCount, dTimestamp, dwUnitID, dValue[0] - dAnalogValue[dwSampleCount - 1]
            // Write in BIG Endian (JAVA Default)
         /*
             * dos.writeInt(dwSampleCount); dos.writeDouble(dTimestamp); dos.writeInt(dwUnitID); for
             * (int jj = 0; jj < dValue.length; jj++) { dos.writeDouble(dValue[jj]); }
             */

            // Write in LITTLE Endian (MATLAB Default)
            dos.writeInt(Integer.reverseBytes(dwSampleCount));
            dos.writeLong(Long.reverseBytes(Double.doubleToLongBits(dTimestamp)));
            dos.writeInt(Integer.reverseBytes(dwUnitID));
            for (int jj = 0; jj < dValue.length; jj++) {
                dos.writeLong(Long.reverseBytes(Double.doubleToLongBits(dValue[jj])));
            }

            // Close the intermediatefile. (use FileOutputStream, DataOutputStream.)
            dos.close();
            fos.close();

            // Add this.tagElement.addDwElemLength(some value).
            this.tagElement.addDwElemLength(4 + 8 + 4 + 8 * dwSampleCount);

            // Add this.entityInfo.addDwItemCount(some value).
            this.entityInfo.addDwItemCount(1);

            // Set this.segmentInfo.dwMaxSampleCount as dwSamplecount (if dwSamplecount is bigger).
            // Set this.segmentInfo.dwMinSampleCount as dwSampleCount (if dwSamplecount is smaller).
            if (this.segmentInfo.getDwMaxSampleCount() < dwSampleCount) {
                this.segmentInfo.setDwMaxSampleCount(dwSampleCount);
            }
            if (this.segmentInfo.getDwMinSampleCount() > dwSampleCount) {
                this.segmentInfo.setDwMinSampleCount(dwSampleCount);
            }
            // Create ns_SEGSOURCEINFO if first call.
            if (this.segSourceInfo == null) {
                this.segSourceInfo = new ArrayList<Ns_SegSourceInfo>();
                this.intermediateFileNameForSourceInfo = new ArrayList<String>();
            }

            // Add ns_SegSourceInfo (ONLY FIRST CALL!!!)
            if (this.segSourceInfo.isEmpty()) {

                this.segSourceInfo.add(new Ns_SegSourceInfo());

                // segSourceID : identification num of ns_SEGSOURCEINFO. [ 0,1,2... ]
                segSourceID = this.segSourceInfo.size() - 1;

                // Define intermediate FILE name for ns_SEGSOURCEINFO.
                this.intermediateFileNameForSourceInfo.add(Const_values.FN_HEADER
                        + Const_values.SEGMENT + "_" + this.segmentID + "_" + segSourceID
                        + ".segSourceInfo");
                this.tagElement.addDwElemLength(248); // Byte Num of ns_SEGSOURCEINFO
                this.segmentInfo.addDwSourceCount(1); // Byte Num of ns_SEGSOURCEINFO

                // Set this.segSourceInfo.dMaxVal as dMaxInTheArray (if dMaxInTheArray is bigger).
                // Set this.segSourceInfo.dMinVal as dMinInTheArray (if dMinInTheArray is smaller).
                if (this.segSourceInfo.get(segSourceID).getDMaxVal() < dMaxInTheArray) {
                    this.segSourceInfo.get(segSourceID).setDMaxVal(dMaxInTheArray);
                }
                if (this.segSourceInfo.get(segSourceID).getDMinVal() > dMinInTheArray) {
                    this.segSourceInfo.get(segSourceID).setDMinVal(dMinInTheArray);
                }

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
        return rtnVal;

    }

    public int saveSegmentInfo() {

        int rtnVal = Const_values.NS_OK;
        File tempFile = null;
        FileOutputStream fos = null;
        DataOutputStream dos = null;

        try {
            // Open the intermediatefile. (use FileOutputStream, DataOutputStream.)
            tempFile = new File(this.intermediateFileNameForInfo);
            fos = new FileOutputStream(tempFile, true);
            dos = new DataOutputStream(fos);

            // Add ns_SegmentInfo.
            // Write in BIG Endian (JAVA Default)
         /*
             * dos.writeInt(this.tagElement.getDwElementType());
             * dos.writeInt(this.tagElement.getDwElemLength()); String szEntityLabel =
             * (this.entityInfo.getSzEntityLabel() + (new Const_values()).getBlank32()) .substring(0,
             * (new Const_values()).getChar32()); dos.writeBytes(szEntityLabel);
             * dos.writeInt(this.entityInfo.getDwEntityType());
             * dos.writeInt(this.entityInfo.getDwItemCount());
             * dos.writeInt(this.segmentInfo.getDwSourceCount());
             * dos.writeInt(this.segmentInfo.getDwMinSampleCount());
             * dos.writeInt(this.segmentInfo.getDwMaxSampleCount());
             * dos.writeDouble(this.segmentInfo.getMembers().getDSampleRate()); String szUnits =
             * (this.segmentInfo.getMembers().getSzUnits() + (new Const_values()).getBlank32())
             * .substring(0, (new Const_values()).getChar32()); dos.writeBytes(szUnits);
             */

            // Write in LITTLE Endian (MATLAB Default)
            dos.writeInt(Integer.reverseBytes(this.tagElement.getDwElementType()));
            dos.writeInt(Integer.reverseBytes(this.tagElement.getDwElemLength()));
            String szEntityLabel = (this.entityInfo.getSzEntityLabel() + (Const_values.BLANK_CHAR32)).substring(0, Const_values.LENGTH_OF_CHAR32);
            dos.writeBytes(szEntityLabel);
            dos.writeInt(Integer.reverseBytes(this.entityInfo.getDwEntityType()));
            dos.writeInt(Integer.reverseBytes(this.entityInfo.getDwItemCount()));

            dos.writeInt(Integer.reverseBytes(this.segmentInfo.getDwSourceCount()));
            dos.writeInt(Integer.reverseBytes(this.segmentInfo.getDwMinSampleCount()));
            dos.writeInt(Integer.reverseBytes(this.segmentInfo.getDwMaxSampleCount()));
            dos.writeLong(Long.reverseBytes(Double.doubleToLongBits(this.segmentInfo.getMembers().getDSampleRate())));
            String szUnits = (this.segmentInfo.getMembers().getSzUnits() + Const_values.BLANK_CHAR32).substring(0, Const_values.LENGTH_OF_CHAR32);
            dos.writeBytes(szUnits);

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

    public int saveSegSourceInfo(int segSourceID) {
        int rtnVal = Const_values.NS_OK;
        File tempFile = null;
        FileOutputStream fos = null;
        DataOutputStream dos = null;

        try {

            // Open the intermediatefile. (use FileOutputStream, DataOutputStream.)
            tempFile = new File(this.intermediateFileNameForSourceInfo.get(segSourceID));
            fos = new FileOutputStream(tempFile, true);
            dos = new DataOutputStream(fos);

            // Add ns_SegSourceInfo.
            // Write in BIG Endian (JAVA Default)
         /*
             * dos.writeDouble(this.segSourceInfo.get(segSourceID).getDMinVal());
             * dos.writeDouble(this.segSourceInfo.get(segSourceID).getDMaxVal());
             * dos.writeDouble(this.segSourceInfo.get(segSourceID).getMembers().getDResolution());
             * dos.writeDouble(this.segSourceInfo.get(segSourceID).getMembers().getDSubSampleShift());
             * dos.writeDouble(this.segSourceInfo.get(segSourceID).getMembers().getDLocationX());
             * dos.writeDouble(this.segSourceInfo.get(segSourceID).getMembers().getDLocationY());
             * dos.writeDouble(this.segSourceInfo.get(segSourceID).getMembers().getDLocationZ());
             * dos.writeDouble(this.segSourceInfo.get(segSourceID).getMembers().getDLocationUser());
             * dos.writeDouble(this.segSourceInfo.get(segSourceID).getMembers().getDHighFreqCorner());
             * dos.writeInt(this.segSourceInfo.get(segSourceID).getMembers().getDwHighFreqOrder());
             * String szHighFilterType =
             * (this.segSourceInfo.get(segSourceID).getMembers().getSzHighFilterType() + (new
             * Const_values()) .getBlank16()).substring(0, (new Const_values()).getChar16());
             * dos.writeBytes(szHighFilterType);
             * dos.writeDouble(this.segSourceInfo.get(segSourceID).getMembers().getDLowFreqCorner());
             * dos.writeInt(this.segSourceInfo.get(segSourceID).getMembers().getDwLowFreqOrder());
             * String szLowFilterType =
             * (this.segSourceInfo.get(segSourceID).getMembers().getSzLowFilterType() + (new
             * Const_values()) .getBlank16()).substring(0, (new Const_values()).getChar16());
             * dos.writeBytes(szLowFilterType); String szProbeInfo =
             * (this.segSourceInfo.get(segSourceID).getMembers().getSzProbeInfo() + (new
             * Const_values()) .getBlank128()).substring(0, (new Const_values()).getChar128());
             * dos.writeBytes(szProbeInfo);
             */

            // Write in LITTLE Endian (MATLAB Default)
            dos.writeLong(Long.reverseBytes(Double.doubleToLongBits(this.segSourceInfo.get(segSourceID).getDMinVal())));
            dos.writeLong(Long.reverseBytes(Double.doubleToLongBits(this.segSourceInfo.get(segSourceID).getDMaxVal())));
            dos.writeLong(Long.reverseBytes(Double.doubleToLongBits(this.segSourceInfo.get(segSourceID).getMembers().getDResolution())));
            dos.writeLong(Long.reverseBytes(Double.doubleToLongBits(this.segSourceInfo.get(segSourceID).getMembers().getDSubSampleShift())));
            dos.writeLong(Long.reverseBytes(Double.doubleToLongBits(this.segSourceInfo.get(segSourceID).getMembers().getDLocationX())));
            dos.writeLong(Long.reverseBytes(Double.doubleToLongBits(this.segSourceInfo.get(segSourceID).getMembers().getDLocationY())));
            dos.writeLong(Long.reverseBytes(Double.doubleToLongBits(this.segSourceInfo.get(segSourceID).getMembers().getDLocationZ())));
            dos.writeLong(Long.reverseBytes(Double.doubleToLongBits(this.segSourceInfo.get(segSourceID).getMembers().getDLocationUser())));
            dos.writeLong(Long.reverseBytes(Double.doubleToLongBits(this.segSourceInfo.get(segSourceID).getMembers().getDHighFreqCorner())));
            dos.writeInt(Integer.reverseBytes(this.segSourceInfo.get(segSourceID).getMembers().getDwHighFreqOrder()));
            String szHighFilterType = (this.segSourceInfo.get(segSourceID).getMembers().getSzHighFilterType() + Const_values.BLANK_CHAR16).substring(0, Const_values.LENGTH_OF_CHAR16);
            dos.writeBytes(szHighFilterType);
            dos.writeLong(Long.reverseBytes(Double.doubleToLongBits(this.segSourceInfo.get(segSourceID).getMembers().getDLowFreqCorner())));
            dos.writeInt(Integer.reverseBytes(this.segSourceInfo.get(segSourceID).getMembers().getDwLowFreqOrder()));
            String szLowFilterType = (this.segSourceInfo.get(segSourceID).getMembers().getSzLowFilterType() + Const_values.BLANK_CHAR16).substring(0, Const_values.LENGTH_OF_CHAR16);
            dos.writeBytes(szLowFilterType);
            String szProbeInfo = (this.segSourceInfo.get(segSourceID).getMembers().getSzProbeInfo() + Const_values.BLANK_CHAR128).substring(0, Const_values.LENGTH_OF_CHAR128);
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
