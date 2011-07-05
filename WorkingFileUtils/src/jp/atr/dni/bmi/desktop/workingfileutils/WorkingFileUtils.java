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
 * @author Keiji Harada [*1]</br>[*1] ATR Intl. Conputational Neuroscience Labs, Decoding Group
 * @version 2011/04/22
 */
public class WorkingFileUtils {

   private final String WS_HOME = "." + File.separator + "workingfiles" + File.separator;
   private String workingFilePath;

   /**
    *
    */
   public WorkingFileUtils() {
      this.workingFilePath = "";
   }

   /**
    *
    * @param workingFilePath
    */
   public WorkingFileUtils(String workingFilePath) {
      this.workingFilePath = workingFilePath;
   }

   /**
    *
    * @param sourceFilePath
    * @param entity
    * @return
    * @throws IOException
    */
   public boolean createWorkingFileFromNeuroshare(String sourceFilePath, Entity entity) throws IOException {
      String tmpFilePath = WS_HOME + System.currentTimeMillis() + "_" + hashCode() + ".csv";
      File file = new File(tmpFilePath);

      // Create workingfiles dir if it  does not exist.
      File dir = file.getParentFile();
      if (!dir.exists()) {
         dir.mkdirs();
      }

      // Create the file.
      if (!file.createNewFile()) {
         return false;
      }

      // Write Neuroshare Data to the working file.
      WorkingFileWriter nsCsvWriter = new WorkingFileWriter();
      nsCsvWriter.createWorkingFileFromNeuroshare(file.getAbsolutePath(), sourceFilePath, entity);

      this.workingFilePath = file.getAbsolutePath();
      return true;
   }

   /**
    *
    * @param sourceFilePath
    * @param entity
    * @return
    * @throws IOException
    */
   public boolean createWorkingFileFromPlexon(String sourceFilePath, Entity entity) throws IOException {
      String tmpFilePath = WS_HOME + System.currentTimeMillis() + "_" + hashCode() + ".csv";
      File file = new File(tmpFilePath);

      // Create workingfiles dir if it  does not exist.
      File dir = file.getParentFile();
      if (!dir.exists()) {
         dir.mkdirs();
      }

      // Create the file.
      if (!file.createNewFile()) {
         return false;
      }

      // Write Neuroshare Data to the working file.
      WorkingFileWriter nsCsvWriter = new WorkingFileWriter();
      nsCsvWriter.createWorkingFileFromPlexon(file.getAbsolutePath(), sourceFilePath, entity);

      this.workingFilePath = file.getAbsolutePath();
      return true;
   }

   /**
    *
    * @param sourceFilePath
    * @param entity
    * @return
    * @throws IOException
    */
   public boolean createWorkingFileFromATRCsv(String sourceFilePath, Entity entity) throws IOException {
      String tmpFilePath = WS_HOME + System.currentTimeMillis() + "_" + hashCode() + ".csv";
      File file = new File(tmpFilePath);

      // Create workingfiles dir if it  does not exist.
      File dir = file.getParentFile();
      if (!dir.exists()) {
         dir.mkdirs();
      }

      // Create the file.
      if (!file.createNewFile()) {
         return false;
      }

      // Write Neuroshare Data to the working file.
      WorkingFileWriter nsCsvWriter = new WorkingFileWriter();
      nsCsvWriter.createWorkingFileFromATRCsv(file.getAbsolutePath(), sourceFilePath, entity);

      this.workingFilePath = file.getAbsolutePath();
      return true;
   }

   /**
    *
    * @param sourceFilePath
    * @param entity
    * @return
    * @throws IOException
    */
   public boolean createWorkingFileFromBlackRockNev(String sourceFilePath, Entity entity) throws IOException {
      String tmpFilePath = WS_HOME + System.currentTimeMillis() + "_" + hashCode() + ".csv";
      File file = new File(tmpFilePath);

      // Create workingfiles dir if it  does not exist.
      File dir = file.getParentFile();
      if (!dir.exists()) {
         dir.mkdirs();
      }

      // Create the file.
      if (!file.createNewFile()) {
         return false;
      }

      // Write Neuroshare Data to the working file.
      WorkingFileWriter nsCsvWriter = new WorkingFileWriter();
      nsCsvWriter.createWorkingFileFromBlackRockNev(file.getAbsolutePath(), sourceFilePath, entity);

      this.workingFilePath = file.getAbsolutePath();
      return true;
   }

   /**
    *
    * @param sourceFilePath
    * @param entity
    * @return
    * @throws IOException
    */
   public boolean createWorkingFileFromBlackRockNsx(String sourceFilePath, Entity entity) throws IOException {
      String tmpFilePath = WS_HOME + System.currentTimeMillis() + "_" + hashCode() + ".csv";
      File file = new File(tmpFilePath);

      // Create workingfiles dir if it  does not exist.
      File dir = file.getParentFile();
      if (!dir.exists()) {
         dir.mkdirs();
      }

      // Create the file.
      if (!file.createNewFile()) {
         return false;
      }

      // Write Neuroshare Data to the working file.
      WorkingFileWriter nsCsvWriter = new WorkingFileWriter();
      nsCsvWriter.createWorkingFileFromBlackRockNsx(file.getAbsolutePath(), sourceFilePath, entity);

      this.workingFilePath = file.getAbsolutePath();
      return true;
   }

   /**
    *
    * @return
    */
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
