/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.atr.dni.bmi.desktop.model;

import java.io.File;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import jp.atr.dni.bmi.desktop.neuroshareutils.NeuroshareFile;

/**
 *
 * @author kharada
 * @version 2011/01/24
 */
public class GeneralFileInfo {

    private List listeners = Collections.synchronizedList(new LinkedList());

//    private List listeners = Collections.synchronizedList(new LinkedList());
    public GeneralFileInfo(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            boolean mkdirs = file.mkdirs();
        }
        if (file.isFile()) {
            this.filePath = file.getAbsolutePath();
            this.fileName = file.getName();
            this.fileSize = file.length();
            this.fileType = "Application/" + this.getFileExtension(this.fileName);
            this.modifiedTime = file.lastModified();
            this.nsObj = null;
        } else if (file.isDirectory()) {
            this.filePath = file.getAbsolutePath();
            this.fileName = file.getName();
            this.fileSize = file.length();
            this.fileType = "Directory";
            this.modifiedTime = file.lastModified();
            this.nsObj = null;
        }
    }
    /**
     * file type.
     */
    private String fileType;
    /**
     * file name.
     */
    private String fileName;
    /**
     * file path.
     */
    private String filePath;
    /**
     * file size.
     */
    private long fileSize;
    /**
     * modified time.
     */
    private long modifiedTime;
    /**
     * Neuroshare Object.
     * Notice : this includes only header information ns_***INFO. not ns_***DATA.
     * How to use : nsObj.getEntities().get(i).getEntityInfo(). You can get the entity's ns_ENTITYINFO.
     * See also : NeuroshareUtils.NsnFileModelConverter.java
     */
    private NeuroshareFile nsObj;

    /**
     * @return the fileType
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * @return the extention of the file
     */
    public String getFileExtention() {
        return this.getFileExtension(fileName);
    }

    /**
     * @param fileType the fileType to set
     */
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return the filePath
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * @param filePath the filePath to set
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * @return the fileSize
     */
    public long getFileSize() {
        return fileSize;
    }

    /**
     * @param fileSize the fileSize to set
     */
    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * @return the modifiedTime
     */
    public long getModifiedTime() {
        return modifiedTime;
    }

    /**
     * @return the modifiedTime
     */
    public String getModifiedTimeString() {
        return new Date(modifiedTime).toString();
    }

    /**
     * @param modifiedTime the modifiedTime to set
     */
    public void setModifiedTime(long modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    /**
     * @return the nsObj
     */
    public NeuroshareFile getNsObj() {
        return nsObj;
    }

    /**
     * @param nsObj the nsObj to set
     */
    public void setNsObj(NeuroshareFile nsObj) {
        this.nsObj = nsObj;
    }

    private String getFileExtension(String fileName) {
        if (fileName == null) {
            return null;
        }
        int point = fileName.lastIndexOf(".");
        if (point == -1) {
            return "";
        }
        return fileName.substring(point + 1);
    }
}
