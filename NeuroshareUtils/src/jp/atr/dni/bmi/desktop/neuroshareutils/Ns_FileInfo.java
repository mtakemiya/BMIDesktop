/**
 * 
 */
package jp.atr.dni.bmi.desktop.neuroshareutils;

import java.util.logging.Logger;

/**
 *
 * @author kharada
 * @version 2011/01/13
 */
public class Ns_FileInfo {

    String szFileType;
    int dwEntityCount;
    double dTimeStampResolution;
    double dTimeSpan;
    String szAppName;
    int dwTime_Year;
    int dwTime_Month;
    int dwTime_DayOfWeek;
    int dwTime_Day;
    int dwTime_Hour;
    int dwTime_Min;
    int dwTime_Sec;
    int dwTime_MilliSec;
    String szFileComment;

    /**
     *
     */
    public Ns_FileInfo() {
        this.szFileType = "";
        this.dwEntityCount = 0;
        this.dTimeStampResolution = 0;
        this.dTimeSpan = 0;
        this.szAppName = "";
        this.dwTime_Year = 1900;
        this.dwTime_Month = 1;
        this.dwTime_DayOfWeek = 1;
        this.dwTime_Day = 1;
        this.dwTime_Hour = 0;
        this.dwTime_Min = 0;
        this.dwTime_Sec = 0;
        this.dwTime_MilliSec = 0;
        this.szFileComment = "";
    }

    /**
     * @return the szFileType
     */
    private String getSzFileType() {
        return szFileType;
    }

    /**
     * @param szFileType the szFileType to set
     */
    private void setSzFileType(String szFileType) {
        this.szFileType = szFileType;
    }

    /**
     * @return the dwEntityCount
     */
    public int getDwEntityCount() {
        return dwEntityCount;
    }

    /**
     * @param dwEntityCount the dwEntityCount to set
     */
    public void setDwEntityCount(int dwEntityCount) {
        if (dwEntityCount >= 0) {
            this.dwEntityCount = dwEntityCount;
        }
    }

    /**
     * @return the dTimeStampResolution
     */
    private double getDTimeStampResolution() {
        return dTimeStampResolution;
    }

    /**
     * @param dTimeStampResolution the dTimeStampResolution to set
     */
    private void setDTimeStampResolution(double dTimeStampResolution) {
        this.dTimeStampResolution = dTimeStampResolution;
    }

    /**
     * @return the dTimeSpan
     */
    private double getDTimeSpan() {
        return dTimeSpan;
    }

    /**
     * @param dTimeSpan the dTimeSpan to set
     */
    private void setDTimeSpan(double dTimeSpan) {
        this.dTimeSpan = dTimeSpan;
    }

    /**
     * @return the szAppName
     */
    private String getSzAppName() {
        return szAppName;
    }

    /**
     * @param szAppName the szAppName to set
     */
    private void setSzAppName(String szAppName) {
        this.szAppName = szAppName;
    }

    /**
     * @return the dwTime_Year
     */
    private int getDwTime_Year() {
        return dwTime_Year;
    }

    /**
     * @param dwTimeYear the dwTime_Year to set
     */
    private void setDwTime_Year(int dwTimeYear) {
        if (dwTimeYear >= 0) {
            dwTime_Year = dwTimeYear;
        } else {
            // Display message.
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).info(
                    new Const_messages().getOutOfRange((double) dwTimeYear));
        }
    }

    /**
     * @return the dwTime_Month
     */
    private int getDwTime_Month() {
        return dwTime_Month;
    }

    /**
     * @param dwTimeMonth the dwTime_Month to set
     */
    private void setDwTime_Month(int dwTimeMonth) {
        if (dwTimeMonth >= 1 && dwTimeMonth <= 12) {
            dwTime_Month = dwTimeMonth;
        } else {
            // Display message.
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).info(
                    new Const_messages().getOutOfRange((double) dwTimeMonth));
        }
    }

    /**
     * @return the dwTime_DayOfWeek
     */
    private int getDwTime_DayOfWeek() {
        return dwTime_DayOfWeek;
    }

    /**
     * @param dwTimeDayOfWeek the dwTime_DayOfWeek to set
     */
    private void setDwTime_DayOfWeek(int dwTimeDayOfWeek) {
        if (dwTimeDayOfWeek >= 0 && dwTimeDayOfWeek <= 6) {
            dwTime_DayOfWeek = dwTimeDayOfWeek;
        } else {
            // Display message.
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).info(
                    new Const_messages().getOutOfRange((double) dwTimeDayOfWeek));
        }
    }

    /**
     * @return the dwTime_Day
     */
    private int getDwTime_Day() {
        return dwTime_Day;
    }

    /**
     * @param dwTimeDay the dwTime_Day to set
     */
    private void setDwTime_Day(int dwTimeDay) {
        if (dwTimeDay >= 1 && dwTimeDay <= 31) {
            dwTime_Day = dwTimeDay;
        } else {
            // Display message.
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).info(
                    new Const_messages().getOutOfRange((double) dwTimeDay));
        }
    }

    /**
     * @return the dwTime_Hour
     */
    private int getDwTime_Hour() {
        return dwTime_Hour;
    }

    /**
     * @param dwTimeHour the dwTime_Hour to set
     */
    private void setDwTime_Hour(int dwTimeHour) {
        if (dwTimeHour >= 0 && dwTimeHour <= 23) {
            dwTime_Hour = dwTimeHour;
        } else {
            // Display message.
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).info(
                    new Const_messages().getOutOfRange((double) dwTimeHour));
        }
    }

    /**
     * @return the dwTime_Min
     */
    private int getDwTime_Min() {
        return dwTime_Min;
    }

    /**
     * @param dwTimeMin the dwTime_Min to set
     */
    private void setDwTime_Min(int dwTimeMin) {
        if (dwTimeMin >= 0 && dwTimeMin <= 59) {
            dwTime_Min = dwTimeMin;
        } else {
            // Display message.
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).info(
                    new Const_messages().getOutOfRange((double) dwTimeMin));
        }
    }

    /**
     * @return the dwTime_Sec
     */
    private int getDwTime_Sec() {
        return dwTime_Sec;
    }

    /**
     * @param dwTimeSec the dwTime_Sec to set
     */
    private void setDwTime_Sec(int dwTimeSec) {
        if (dwTimeSec >= 0 && dwTimeSec <= 59) {
            dwTime_Sec = dwTimeSec;
        } else {
            // Display message.
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).info(
                    new Const_messages().getOutOfRange((double) dwTimeSec));
        }
    }

    /**
     * @return the dwTime_MilliSec
     */
    private int getDwTime_MilliSec() {
        return dwTime_MilliSec;
    }

    /**
     * @param dwTimeMilliSec the dwTime_MilliSec to set
     */
    private void setDwTime_MilliSec(int dwTimeMilliSec) {
        if (dwTimeMilliSec >= 0 && dwTimeMilliSec <= 1000) {
            dwTime_MilliSec = dwTimeMilliSec;
        } else {
            // Display message.
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).info(
                    new Const_messages().getOutOfRange((double) dwTimeMilliSec));
        }
    }

    /**
     * @return the szFileComment
     */
    private String getSzFileComment() {
        return szFileComment;
    }

    /**
     * @param szFileComment the szFileComment to set
     */
    private void setSzFileComment(String szFileComment) {
        this.szFileComment = szFileComment;
    }

    /**
     * @param dwEntityCount the dwEntityCount to add
     */
    public void addDwEntityCount(int dwEntityCount) {
        this.dwEntityCount += dwEntityCount;
    }

    /**
     * @return members
     */
    public Nsa_FileInfo getMembers() {
        return new Nsa_FileInfo(this.getSzFileType(), this.getDTimeStampResolution(), this.getDTimeSpan(), this.getSzAppName(), this.getDwTime_Year(), this.getDwTime_Month(), this.getDwTime_DayOfWeek(), this.getDwTime_Day(), this.getDwTime_Hour(), this.getDwTime_Min(), this.getDwTime_Sec(), this.getDwTime_MilliSec(), this.getSzFileComment());
    }

    /**
     * @param nsaFileInfo
     * @return
     */
    public int setMembers(Nsa_FileInfo nsaFileInfo) {
        this.setSzFileType(nsaFileInfo.getSzFileType());
        this.setDTimeStampResolution(nsaFileInfo.getDTimeStampResolution());
        this.setDTimeSpan(nsaFileInfo.getDTimeSpan());
        this.setSzAppName(nsaFileInfo.getSzAppName());
        this.setDwTime_Year(nsaFileInfo.getDwTime_Year());
        this.setDwTime_Month(nsaFileInfo.getDwTime_Month());
        this.setDwTime_DayOfWeek(nsaFileInfo.getDwTime_DayOfWeek());
        this.setDwTime_Day(nsaFileInfo.getDwTime_Day());
        this.setDwTime_Hour(nsaFileInfo.getDwTime_Hour());
        this.setDwTime_Min(nsaFileInfo.getDwTime_Min());
        this.setDwTime_Sec(nsaFileInfo.getDwTime_Sec());
        this.setDwTime_MilliSec(nsaFileInfo.getDwTime_MilliSec());
        this.setSzFileComment(nsaFileInfo.getSzFileComment());
        return Const_values.NS_OK;
    }
}
