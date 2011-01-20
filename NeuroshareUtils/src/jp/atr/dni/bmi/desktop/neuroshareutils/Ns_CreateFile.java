/**
 * 
 */
package jp.atr.dni.bmi.desktop.neuroshareutils;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author kharada
 * @version 2011/01/13
 */
public class Ns_CreateFile {

    String outputFileName;
    String intermediateFileNameForInfo;
    String sMagicCode;
    Ns_FileInfo nsFileInfo;
    ArrayList<Ns_EventData> eventData = null;
    ArrayList<Ns_AnalogData> analogData = null;
    ArrayList<Ns_SegmentData> segmentData = null;
    ArrayList<Ns_NeuralEventData> neuralEventData = null;

    /**
     *
     */
    public Ns_CreateFile(String outputFileName) {
        this.intermediateFileNameForInfo = (new Const_values()).getHeader() + (new Const_values()).getFile()
                + ".fileInfo";
        this.outputFileName = outputFileName;
        this.sMagicCode = (new Const_values()).getsMagicCode();
        this.nsFileInfo = new Ns_FileInfo();
    }

    public Ns_EventData newEventData(String szEntityLabel) {
        // Creation if first call.
        if (this.eventData == null) {
            this.eventData = new ArrayList<Ns_EventData>();
        }
        // Cut over length string.
        if (szEntityLabel.length() > (new Const_values()).getChar32()) {
            String tempszEntityLabel = szEntityLabel.substring(0, ((new Const_values()).getChar32()));
            szEntityLabel = tempszEntityLabel;
        }
        // Add EventData
        this.eventData.add(new Ns_EventData(this.eventData.size(), szEntityLabel));
        // Count up EntityCount
        this.nsFileInfo.addDwEntityCount(1);
        return this.eventData.get(this.eventData.size() - 1);
    }

    public Ns_AnalogData newAnalogData(String szEntityLabel) {
        // Creation if first call.
        if (this.analogData == null) {
            this.analogData = new ArrayList<Ns_AnalogData>();
        }
        // Cut over length string.
        if (szEntityLabel.length() > (new Const_values()).getChar32()) {
            String tempszEntityLabel = szEntityLabel.substring(0, ((new Const_values()).getChar32()));
            szEntityLabel = tempszEntityLabel;
        }
        // Add AnalogData
        this.analogData.add(new Ns_AnalogData(this.analogData.size(), szEntityLabel));
        // Count up EntityCount
        this.nsFileInfo.addDwEntityCount(1);
        return this.analogData.get(this.analogData.size() - 1);
    }

    public Ns_SegmentData newSegmentData(String szEntityLabel) {
        // Creation if first call.
        if (this.segmentData == null) {
            this.segmentData = new ArrayList<Ns_SegmentData>();
        }
        // Cut over length string.
        if (szEntityLabel.length() > (new Const_values()).getChar32()) {
            String tempszEntityLabel = szEntityLabel.substring(0, ((new Const_values()).getChar32()));
            szEntityLabel = tempszEntityLabel;
        }
        // Add SegmentData
        this.segmentData.add(new Ns_SegmentData(this.segmentData.size(), szEntityLabel));
        // Count up EntityCount
        this.nsFileInfo.addDwEntityCount(1);
        return this.segmentData.get(this.segmentData.size() - 1);
    }

    public Ns_NeuralEventData newNeuralEventData(String szEntityLabel) {
        // Creation if first call.
        if (this.neuralEventData == null) {
            this.neuralEventData = new ArrayList<Ns_NeuralEventData>();
        }
        // Cut over length string.
        if (szEntityLabel.length() > (new Const_values()).getChar32()) {
            String tempszEntityLabel = szEntityLabel.substring(0, ((new Const_values()).getChar32()));
            szEntityLabel = tempszEntityLabel;
        }
        // Add NeuralEventData
        this.neuralEventData.add(new Ns_NeuralEventData(this.neuralEventData.size(), szEntityLabel));
        // Count up EntityCount
        this.nsFileInfo.addDwEntityCount(1);
        return this.neuralEventData.get(this.neuralEventData.size() - 1);
    }

    public Nsa_FileInfo getFileInfo() {
        return this.nsFileInfo.getMembers();
    }

    public int setFileInfo(Nsa_FileInfo nsaFileInfo) {
        return this.nsFileInfo.setMembers(nsaFileInfo);
    }

    public int closeFile() {

        // Check the output file name. [.nsn] is OK. if NG, add extension and go on.
        if (!this.outputFileName.endsWith(".nsn")) {
            // Add extension.
            this.outputFileName = this.outputFileName + ".nsn";
        }

        // Delete Neuroshare file if it exists already.
        File outputFile = new File(this.outputFileName);
        if (outputFile.exists()) {
            outputFile.delete();
        }

        // Create all intermediate files which include ***INFO.
        // sMagicCode, ns_FileInfo
        if ((new Const_values()).getNs_OK() != saveFileInfo()) {
            return (new Const_values()).getNs_FILEERROR();
        }

        // EVENT (ns_TAGELEMENT, ns_ENTITYINFO, ns_EVENTINFO)
        if (!(this.eventData == null)) {
            for (int jj = 0; jj < this.eventData.size(); jj++) {
                if ((new Const_values()).getNs_OK() != this.eventData.get(jj).saveEventInfo()) {
                    return (new Const_values()).getNs_FILEERROR();
                }
            }
        }

        // ANALOG (ns_TAGELEMENT, ns_ENTITYINFO, ns_ANALOGINFO)
        if (!(this.analogData == null)) {
            for (int jj = 0; jj < this.analogData.size(); jj++) {
                if ((new Const_values()).getNs_OK() != this.analogData.get(jj).saveAnalogInfo()) {
                    return (new Const_values()).getNs_FILEERROR();
                }
            }
        }

        // SEGMENT (ns_TAGELEMENT, ns_ENTITYINFO, ns_SEGMENTINFO)
        if (!(this.segmentData == null)) {
            for (int jj = 0; jj < this.segmentData.size(); jj++) {
                if ((new Const_values()).getNs_OK() != this.segmentData.get(jj).saveSegmentInfo()) {
                    return (new Const_values()).getNs_FILEERROR();
                }
                // SEGSOURCE (ns_SEGSOURCEINFO) * num of segSourceInfo
                for (int kk = 0; kk < this.segmentData.get(jj).segSourceInfo.size(); kk++) {
                    if ((new Const_values()).getNs_OK() != this.segmentData.get(jj).saveSegSourceInfo(kk)) {
                        return (new Const_values()).getNs_FILEERROR();
                    }
                }
            }
        }
        // NEURALEVENT (ns_TAGELEMENT, ns_ENTITYINFO, ns_NEURALINFO)
        if (!(this.neuralEventData == null)) {
            for (int jj = 0; jj < this.neuralEventData.size(); jj++) {
                if ((new Const_values()).getNs_OK() != this.neuralEventData.get(jj).saveNeuralInfo()) {
                    return (new Const_values()).getNs_FILEERROR();
                }
            }
        }

        // Integrate all intermediate files. (Create NSNfile.)
        // FILEINFO-EVENT-NEURALEVENT-ANALOG-SEGMENT.
        // Then, delete all intermediate files.

        // sMagicCode, ns_FileInfo
        if ((new Const_values()).getNs_OK() != FileCat(this.outputFileName, this.intermediateFileNameForInfo)) {
            return (new Const_values()).getNs_FILEERROR();
        }

        // Delete the intermediate file. (sMagicCode, ns_FILEINFO)
        if (!(new File(this.intermediateFileNameForInfo)).delete()) {
            return (new Const_values()).getNs_FILEERROR();
        }

        // EVENT

        if (!(this.eventData == null)) {
            for (int jj = 0; jj < this.eventData.size(); jj++) {
                // Integrate the intermediate file.(INFO : ns_TAGELEMENT, ns_ENTITYINFO, ns_EVENTINFO)
                if ((new Const_values()).getNs_OK() != FileCat(this.outputFileName,
                        (this.eventData.get(jj)).intermediateFileNameForInfo)) {
                    return (new Const_values()).getNs_FILEERROR();
                }
                // Delete the intermediate file.
                if (!(new File(this.eventData.get(jj).intermediateFileNameForInfo).delete())) {
                    return (new Const_values()).getNs_FILEERROR();
                }
                // Check existence of the intermediate file.
                File tf = new File(this.eventData.get(jj).intermediateFileNameForData);
                if (!tf.exists()) {
                    continue;
                }
                // Integrate the intermediate file. (DATA : EventData)
                if ((new Const_values()).getNs_OK() != FileCat(this.outputFileName,
                        (this.eventData.get(jj)).intermediateFileNameForData)) {
                    return (new Const_values()).getNs_FILEERROR();
                }
                // Delete the intermediate file.
                if (!(new File(this.eventData.get(jj).intermediateFileNameForData).delete())) {
                    return (new Const_values()).getNs_FILEERROR();
                }
            }
        }

        // NEURALEVENT

        if (!(this.neuralEventData == null)) {
            for (int jj = 0; jj < this.neuralEventData.size(); jj++) {
                // Integrate the intermediate file.(INFO : ns_TAGELEMENT, ns_ENTITYINFO, ns_NEURALINFO)
                if ((new Const_values()).getNs_OK() != FileCat(this.outputFileName,
                        (this.neuralEventData.get(jj)).intermediateFileNameForInfo)) {
                    return (new Const_values()).getNs_FILEERROR();
                }
                // Delete the intermediate file.
                if (!(new File(this.neuralEventData.get(jj).intermediateFileNameForInfo).delete())) {
                    return (new Const_values()).getNs_FILEERROR();
                }
                // Check existence of the intermediate file.
                File tf = new File(this.neuralEventData.get(jj).intermediateFileNameForData);
                if (!tf.exists()) {
                    continue;
                }
                // Integrate the intermediate file. (DATA : EventData)
                if ((new Const_values()).getNs_OK() != FileCat(this.outputFileName,
                        (this.neuralEventData.get(jj)).intermediateFileNameForData)) {
                    return (new Const_values()).getNs_FILEERROR();
                }
                // Delete the intermediate file.
                if (!(new File(this.neuralEventData.get(jj).intermediateFileNameForData).delete())) {
                    return (new Const_values()).getNs_FILEERROR();
                }
            }
        }
        // ANALOG
        if (!(this.analogData == null)) {
            for (int jj = 0; jj < this.analogData.size(); jj++) {

                // Integrate the intermediate file. (INFO : ns_TAGELEMENT, ns_ENTITYINFO, ns_ANALOGINFO)
                if ((new Const_values()).getNs_OK() != FileCat(this.outputFileName,
                        (this.analogData.get(jj)).intermediateFileNameForInfo)) {
                    return (new Const_values()).getNs_FILEERROR();
                }
                // Delete the intermediate file.
                if (!(new File(this.analogData.get(jj).intermediateFileNameForInfo).delete())) {
                    return (new Const_values()).getNs_FILEERROR();
                }
                // Check existence of the intermediate file.
                File tf = new File(this.analogData.get(jj).intermediateFileNameForData);
                if (!tf.exists()) {
                    continue;
                }
                // Integrate the intermediate file. (DATA : AnalogData)
                if ((new Const_values()).getNs_OK() != FileCat(this.outputFileName,
                        (this.analogData.get(jj)).intermediateFileNameForData)) {
                    return (new Const_values()).getNs_FILEERROR();
                }
                // Delete the intermediate file.
                if (!(new File(this.analogData.get(jj).intermediateFileNameForData).delete())) {
                    return (new Const_values()).getNs_FILEERROR();
                }

            }
        }

        // SEGMENT
        if (!(this.segmentData == null)) {
            for (int jj = 0; jj < this.segmentData.size(); jj++) {

                // Integrate the intermediate file. (INFO : ns_TAGELEMENT, ns_ENTITYINFO,
                // ns_SEGMENTINFO)
                if ((new Const_values()).getNs_OK() != FileCat(this.outputFileName,
                        (this.segmentData.get(jj)).intermediateFileNameForInfo)) {
                    return (new Const_values()).getNs_FILEERROR();
                }
                // Delete the intermediate file.
                if (!(new File(this.segmentData.get(jj).intermediateFileNameForInfo).delete())) {
                    return (new Const_values()).getNs_FILEERROR();
                }
                // Check existence of the intermediate file.
                File tf = new File(this.segmentData.get(jj).intermediateFileNameForData);
                if (!tf.exists()) {
                    continue;
                }
                // Integrate intermediate files. (INFO : ns_SEGSOURCEINFO * num of segSourceInfo)
                for (int kk = 0; kk < this.segmentData.get(jj).segSourceInfo.size(); kk++) {
                    if ((new Const_values()).getNs_OK() != FileCat(this.outputFileName,
                            (this.segmentData.get(jj)).intermediateFileNameForSourceInfo.get(kk))) {
                        return (new Const_values()).getNs_FILEERROR();
                    }
                }
                // Delete intermediate files. (INFO : ns_SEGSOURCEINFO * num of segSourceInfo)
                for (int kk = 0; kk < this.segmentData.get(jj).segSourceInfo.size(); kk++) {
                    if (!(new File(this.segmentData.get(jj).intermediateFileNameForSourceInfo.get(kk)).delete())) {
                        return (new Const_values()).getNs_FILEERROR();
                    }
                }
                // Integrate the intermediate file. (DATA : AnalogData)
                if ((new Const_values()).getNs_OK() != FileCat(this.outputFileName,
                        (this.segmentData.get(jj)).intermediateFileNameForData)) {
                    return (new Const_values()).getNs_FILEERROR();
                }
                // Delete the intermediate file.
                if (!(new File(this.segmentData.get(jj).intermediateFileNameForData).delete())) {
                    return (new Const_values()).getNs_FILEERROR();
                }

            }
        }
        // then
        return (new Const_values()).getNs_OK();
    }

    private int FileCat(String NeuroshareFilePath, String intermediateFilePath) {
        // Integrate two files.

        int rtnVal = (new Const_values()).getNs_OK();
        File fDst = new File(NeuroshareFilePath);
        File fSrc = new File(intermediateFilePath);
        FileOutputStream fosDst = null;
        FileInputStream fisSrc = null;

        try {
            fosDst = new FileOutputStream(fDst, true);
            fisSrc = new FileInputStream(fSrc);
            byte[] buffer = new byte[1000 * 1024]; // 1MB for buffer.
            int len = 0;

            while ((len = fisSrc.read(buffer, 0, buffer.length)) != -1) {
                fosDst.write(buffer, 0, len);
            }
            fosDst.flush();

            fosDst.close();
            fisSrc.close();

            // Then, ns_OK.
            rtnVal = new Const_values().getNs_OK();

        } catch (FileNotFoundException e) {
            // File Not Found.
            e.printStackTrace();

            // Then, ns_FILEERROR.
            rtnVal = new Const_values().getNs_FILEERROR();

        } catch (IOException e) {
            // File I/O error.
            e.printStackTrace();

            // Then, ns_FILEERROR.
            rtnVal = new Const_values().getNs_FILEERROR();

        } finally {

            try {
                if (!fosDst.equals(null)) {
                    fosDst.close();
                }
                if (!fisSrc.equals(null)) {
                    fisSrc.close();
                }

            } catch (IOException e) {
                // May be sequence doesn't reach here.
                e.printStackTrace();
                rtnVal = new Const_values().getNs_FILEERROR();

            }

        }

        // return the value.
        return rtnVal;
    }

    private int saveFileInfo() {

        int rtnVal = (new Const_values()).getNs_OK();
        File tempFile = null;
        FileOutputStream fos = null;
        DataOutputStream dos = null;

        try {
            // Open the intermediatefile. (use FileOutputStream, DataOutputStream.)
            tempFile = new File(this.intermediateFileNameForInfo);
            fos = new FileOutputStream(tempFile, true);
            dos = new DataOutputStream(fos);

            // Add ns_FileInfo.
            // Write in BIG Endian (JAVA Default)
         /*
             * dos.writeBytes(this.sMagicCode); dos.writeByte(0x00); String szFileType =
             * (this.nsFileInfo.getMembers().getSzFileType() + (new Const_values()).getBlank32())
             * .substring(0, (new Const_values()).getChar32()); dos.writeBytes(szFileType);
             * dos.writeInt(this.nsFileInfo.getDwEntityCount());
             * dos.writeDouble(this.nsFileInfo.getMembers().getDTimeStampResolution());
             * dos.writeDouble(this.nsFileInfo.getMembers().getDTimeSpan()); String szAppName =
             * (this.nsFileInfo.getMembers().getSzAppName() + (new Const_values()).getBlank64())
             * .substring(0, (new Const_values()).getChar64()); dos.writeBytes(szAppName);
             * dos.writeInt(this.nsFileInfo.getMembers().getDwTime_Year());
             * dos.writeInt(this.nsFileInfo.getMembers().getDwTime_Month());
             * dos.writeInt(this.nsFileInfo.getMembers().getDwTime_DayOfWeek());
             * dos.writeInt(this.nsFileInfo.getMembers().getDwTime_Day());
             * dos.writeInt(this.nsFileInfo.getMembers().getDwTime_Hour());
             * dos.writeInt(this.nsFileInfo.getMembers().getDwTime_Min());
             * dos.writeInt(this.nsFileInfo.getMembers().getDwTime_Sec());
             * dos.writeInt(this.nsFileInfo.getMembers().getDwTime_MilliSec()); String szFileComment =
             * (this.nsFileInfo.getMembers().getSzFileComment() + (new Const_values())
             * .getBlank256()).substring(0, (new Const_values()).getChar256());
             * dos.writeBytes(szFileComment);
             */

            // Write in LITTLE Endian (MATLAB Default)
            dos.writeBytes(this.sMagicCode);
            dos.writeByte(0x00);
            String szFileType = (this.nsFileInfo.getMembers().getSzFileType() + (new Const_values()).getBlank32()).substring(0, (new Const_values()).getChar32());
            dos.writeBytes(szFileType);
            dos.writeInt(Integer.reverseBytes(this.nsFileInfo.getDwEntityCount()));
            dos.writeLong(Long.reverseBytes(Double.doubleToLongBits(this.nsFileInfo.getMembers().getDTimeStampResolution())));
            dos.writeLong(Long.reverseBytes(Double.doubleToLongBits(this.nsFileInfo.getMembers().getDTimeSpan())));
            String szAppName = (this.nsFileInfo.getMembers().getSzAppName() + (new Const_values()).getBlank64()).substring(0, (new Const_values()).getChar64());
            dos.writeBytes(szAppName);
            dos.writeInt(Integer.reverseBytes(this.nsFileInfo.getMembers().getDwTime_Year()));
            dos.writeInt(Integer.reverseBytes(this.nsFileInfo.getMembers().getDwTime_Month()));
            dos.writeInt(Integer.reverseBytes(this.nsFileInfo.getMembers().getDwTime_DayOfWeek()));
            dos.writeInt(Integer.reverseBytes(this.nsFileInfo.getMembers().getDwTime_Day()));
            dos.writeInt(Integer.reverseBytes(this.nsFileInfo.getMembers().getDwTime_Hour()));
            dos.writeInt(Integer.reverseBytes(this.nsFileInfo.getMembers().getDwTime_Min()));
            dos.writeInt(Integer.reverseBytes(this.nsFileInfo.getMembers().getDwTime_Sec()));
            dos.writeInt(Integer.reverseBytes(this.nsFileInfo.getMembers().getDwTime_MilliSec()));
            String szFileComment = (this.nsFileInfo.getMembers().getSzFileComment() + (new Const_values()).getBlank256()).substring(0, (new Const_values()).getChar256());
            dos.writeBytes(szFileComment);

            dos.close();
            fos.close();

            // Then, ns_OK.
            rtnVal = new Const_values().getNs_OK();

        } catch (FileNotFoundException e) {
            // File Not Found.
            e.printStackTrace();

            // Then, ns_FILEERROR.
            rtnVal = new Const_values().getNs_FILEERROR();

        } catch (IOException e) {
            // File I/O error.
            e.printStackTrace();

            // Then, ns_FILEERROR.
            rtnVal = new Const_values().getNs_FILEERROR();

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
                rtnVal = new Const_values().getNs_FILEERROR();

            }
        }

        // return the value.
        return rtnVal;
    }
}
