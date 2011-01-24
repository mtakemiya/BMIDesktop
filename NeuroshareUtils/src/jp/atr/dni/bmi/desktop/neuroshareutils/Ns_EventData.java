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
 * @author kharada
 * @version 2011/01/13
 */
public class Ns_EventData {

    String intermediateFileNameForInfo;
    String intermediateFileNameForData;
    Ns_TagElement tagElement;
    Ns_EntityInfo entityInfo;
    Ns_EventInfo eventInfo;

    /**
     * @param szEntityLabel
     */
    public Ns_EventData(int ID, String szEntityLabel) {

        this.intermediateFileNameForInfo = Const_values.FN_HEADER + Const_values.EVENT
                + "_" + ID + ".eventInfo";
        this.intermediateFileNameForData = Const_values.FN_HEADER + Const_values.EVENT
                + "_" + ID + ".eventData";
        this.tagElement = new Ns_TagElement(Const_ns_ENTITY.ns_ENTITY_EVENT);
        this.entityInfo = new Ns_EntityInfo(szEntityLabel, Const_ns_ENTITY.ns_ENTITY_EVENT);
        this.tagElement.addDwElemLength(40); // Byte Num of ns_ENTITYINFO
        this.eventInfo = new Ns_EventInfo();
        this.tagElement.addDwElemLength(140); // Byte Num of ns_EVENTINFO
    }

    /**
     * @return
     */
    public Nsa_EventInfo getEventInfo() {
        return this.eventInfo.getMembers();
    }

    /**
     * @param nsaEventInfo
     * @return
     */
    public int setEventInfo(Nsa_EventInfo nsaEventInfo) {
        return this.eventInfo.setMembers(nsaEventInfo);
    }

    public int addEventData(double dTimestamp, byte EventData) {
        return this.addEventData(dTimestamp, EventData, Const_ns_EVENT.ns_EVENT_BYTE);
    }

    public int addEventData(double dTimestamp, short EventData) {
        return this.addEventData(dTimestamp, EventData, Const_ns_EVENT.ns_EVENT_WORD);
    }

    public int addEventData(double dTimestamp, int EventData) {
        return this.addEventData(dTimestamp, EventData, Const_ns_EVENT.ns_EVENT_DWORD);
    }

    public int addEventData(double dTimestamp, String EventData) {
        return this.addEventData(dTimestamp, EventData, Const_ns_EVENT.ns_EVENT_TEXT);
    }

    private int addEventData(double dTimestamp, Object eventData, Const_ns_EVENT eventType) {

        // Check consistency about the type of Event Data.
        // It is no need to check If the record is 1st.
        if (0 != this.entityInfo.getDwItemCount()) {
            // It is no need to check If the record is 1st.
            if (this.eventInfo.getDwEventType() != eventType.ordinal()) {
                return Const_values.NS_WRONGDATA;
            }
        }

        int rtnVal = Const_values.NS_OK;
        File tempFile = null;
        FileOutputStream fos = null;
        DataOutputStream dos = null;

        try {

            // Open the intermediatefile. (use FileOutputStream, DataOutputStream.)
            tempFile = new File(this.intermediateFileNameForData);
            fos = new FileOutputStream(tempFile, true);
            dos = new DataOutputStream(fos);
            Integer eventDataByteSize = 0;

            // Add dTimestamp, dwDataByteSize, EventValue
            // Write in BIG Endian (JAVA Default)
         /*
             * switch (eventType) { case ns_EVENT_TEXT: dos.writeDouble(dTimestamp); eventDataByteSize
             * = eventData.toString().length(); dos.writeInt(eventDataByteSize);
             * dos.writeBytes(eventData.toString()); break; case ns_EVENT_BYTE:
             * dos.writeDouble(dTimestamp); eventDataByteSize = 1; dos.writeInt(eventDataByteSize);
             * dos.write((Byte) eventData); break; case ns_EVENT_WORD: dos.writeDouble(dTimestamp);
             * eventDataByteSize = 2; dos.writeInt(eventDataByteSize); dos.writeShort((Short)
             * eventData); break; case ns_EVENT_DWORD: dos.writeDouble(dTimestamp); eventDataByteSize =
             * 4; dos.writeInt(eventDataByteSize); dos.writeInt((Integer) eventData); break; }
             */

            // Write in LITTLE Endian (MATLAB Default)
            switch (eventType) {
                case ns_EVENT_TEXT:
                    dos.writeLong(Long.reverseBytes(Double.doubleToLongBits(dTimestamp)));
                    eventDataByteSize = eventData.toString().length();
                    dos.writeInt(Integer.reverseBytes(eventDataByteSize));
                    dos.writeBytes(eventData.toString());
                    break;
                case ns_EVENT_BYTE:
                    dos.writeLong(Long.reverseBytes(Double.doubleToLongBits(dTimestamp)));
                    eventDataByteSize = 1;
                    dos.writeInt(Integer.reverseBytes(eventDataByteSize));
                    dos.write((Byte) eventData);
                    break;
                case ns_EVENT_WORD:
                    dos.writeLong(Long.reverseBytes(Double.doubleToLongBits(dTimestamp)));
                    eventDataByteSize = 2;
                    dos.writeInt(Integer.reverseBytes(eventDataByteSize));
                    dos.writeShort(Short.reverseBytes((Short) eventData));
                    break;
                case ns_EVENT_DWORD:
                    dos.writeLong(Long.reverseBytes(Double.doubleToLongBits(dTimestamp)));
                    eventDataByteSize = 4;
                    dos.writeInt(Integer.reverseBytes(eventDataByteSize));
                    dos.writeInt(Integer.reverseBytes((Integer) eventData));
                    break;
            }

            // Close the intermediatefile. (use FileOutputStream, DataOutputStream.)
            dos.close();
            fos.close();

            // Add this.tagElement.addDwElemLength(some value).
            this.tagElement.addDwElemLength(8 + 4 + eventDataByteSize);

            // Add this.entityInfo.addDwItemCount(some value).
            this.entityInfo.addDwItemCount(1);

            // Set this.eventInfo.dwMaxDataLength as eventDataByteSize (if eventDataByteSize is
            // bigger).
            // Set this.eventInfo.dwMinDataLength as eventDataByteSize (if eventDataByteSize is
            // smaller).
            if (this.eventInfo.getDwMaxDataLength() < eventDataByteSize) {
                this.eventInfo.setDwMaxDataLength(eventDataByteSize);
            }
            if (this.eventInfo.getDwMinDataLength() > eventDataByteSize) {
                this.eventInfo.setDwMinDataLength(eventDataByteSize);
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

    public int saveEventInfo() {

        int rtnVal = Const_values.NS_OK;
        File tempFile = null;
        FileOutputStream fos = null;
        DataOutputStream dos = null;

        try {
            // Open the intermediatefile. (use FileOutputStream, DataOutputStream.)
            tempFile = new File(this.intermediateFileNameForInfo);
            fos = new FileOutputStream(tempFile, true);
            dos = new DataOutputStream(fos);

            // Add ns_EventInfo.
            // Write in BIG Endian (JAVA Default)
         /*
             * dos.writeInt(this.tagElement.getDwElementType());
             * dos.writeInt(this.tagElement.getDwElemLength()); String szEntityLabel =
             * (this.entityInfo.getSzEntityLabel() + (new Const_values()).getBlank32()) .substring(0,
             * (new Const_values()).getChar32()); dos.writeBytes(szEntityLabel);
             * dos.writeInt(this.entityInfo.getDwEntityType());
             * dos.writeInt(this.entityInfo.getDwItemCount());
             * dos.writeInt(this.eventInfo.getDwEventType());
             * dos.writeInt(this.eventInfo.getDwMinDataLength());
             * dos.writeInt(this.eventInfo.getDwMaxDataLength()); String szCSVDesc =
             * (this.eventInfo.getMembers().getSzCSVDesc() + (new Const_values()).getBlank128())
             * .substring(0, (new Const_values()).getChar128()); dos.writeBytes(szCSVDesc);
             */

            // Write in LITTLE Endian (MATLAB Default)
            dos.writeInt(Integer.reverseBytes(this.tagElement.getDwElementType()));
            dos.writeInt(Integer.reverseBytes(this.tagElement.getDwElemLength()));
            String szEntityLabel = (this.entityInfo.getSzEntityLabel() + Const_values.BLANK_CHAR32).substring(0, Const_values.LENGTH_OF_CHAR32);
            dos.writeBytes(szEntityLabel);
            dos.writeInt(Integer.reverseBytes(this.entityInfo.getDwEntityType()));
            dos.writeInt(Integer.reverseBytes(this.entityInfo.getDwItemCount()));

            dos.writeInt(Integer.reverseBytes(this.eventInfo.getDwEventType()));
            dos.writeInt(Integer.reverseBytes(this.eventInfo.getDwMinDataLength()));
            dos.writeInt(Integer.reverseBytes(this.eventInfo.getDwMaxDataLength()));
            String szCSVDesc = (this.eventInfo.getMembers().getSzCSVDesc() + Const_values.BLANK_CHAR128).substring(0, Const_values.LENGTH_OF_CHAR128);
            dos.writeBytes(szCSVDesc);

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
