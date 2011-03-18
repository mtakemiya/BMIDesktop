/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.atr.dni.bmi.desktop.workingfileutils;

import java.io.File;
import java.io.IOException;
import jp.atr.dni.bmi.desktop.neuroshareutils.Entity;

/**
 *
 * @author kharada
 */
public class WorkingFileUtils {

    private final String WS_HOME = "." + File.separator + "workingfiles" + File.separator;
    private String workingFilePath;

    public WorkingFileUtils() {
        this.workingFilePath = "";
    }

    public WorkingFileUtils(String workingFilePath) {
        this.workingFilePath = workingFilePath;
    }

    public boolean createWorkingFileFromNeuroshare(String sourceFilePath, Entity entity) throws IOException {
        String tmpFilePath = WS_HOME + System.currentTimeMillis() + "_" + hashCode() + ".csv";
        File file = new File(tmpFilePath);

        if (!file.createNewFile()) {
            return false;
        }

        CSVWriter nsCsvWriter = new CSVWriter();
        nsCsvWriter.createCSVFileFromNeuroshare(file.getAbsolutePath(), sourceFilePath, entity);

        this.workingFilePath = file.getAbsolutePath();
        return true;
    }

    public boolean removeWorkingFile() {
        return new File(workingFilePath).delete();
    }

    /**
     * @return the workingFilePath
     */
    public String getWorkingFilePath() {
        return workingFilePath;
    }

    /**
     * @param workingFilePath the workingFilePath to set
     */
    public void setWorkingFilePath(String workingFilePath) {
        this.workingFilePath = workingFilePath;
    }
}
