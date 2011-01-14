/**
 * 
 */
package jp.atr.dni.bmi.desktop.neuroshareutils;

/**
 *
 * @author kharada
 * @version 2011/01/13
 */
public class Nsa_FileInfo {

    String szFileType;
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
     * @param szFileType
     * @param dTimeStampResolution
     * @param dTimeSpan
     * @param szAppName
     * @param dwTimeYear
     * @param dwTimeMonth
     * @param dwTimeDayOfWeek
     * @param dwTimeDay
     * @param dwTimeHour
     * @param dwTimeMin
     * @param dwTimeSec
     * @param dwTimeMilliSec
     * @param szFileComment
     */
    public Nsa_FileInfo(String szFileType, double dTimeStampResolution, double dTimeSpan, String szAppName,
            int dwTimeYear, int dwTimeMonth, int dwTimeDayOfWeek, int dwTimeDay, int dwTimeHour, int dwTimeMin,
            int dwTimeSec, int dwTimeMilliSec, String szFileComment) {
        this.szFileType = szFileType;
        this.dTimeStampResolution = dTimeStampResolution;
        this.dTimeSpan = dTimeSpan;
        this.szAppName = szAppName;
        this.dwTime_Year = dwTimeYear;
        this.dwTime_Month = dwTimeMonth;
        this.dwTime_DayOfWeek = dwTimeDayOfWeek;
        this.dwTime_Day = dwTimeDay;
        this.dwTime_Hour = dwTimeHour;
        this.dwTime_Min = dwTimeMin;
        this.dwTime_Sec = dwTimeSec;
        this.dwTime_MilliSec = dwTimeMilliSec;
        this.szFileComment = szFileComment;
    }

    /**
     * @return the szFileType
     */
    public String getSzFileType() {
        return szFileType;
    }

    /**
     * @param szFileType the szFileType to set
     */
    public void setSzFileType(String szFileType) {
        this.szFileType = szFileType;
    }

    /**
     * @return the dTimeStampResolution
     */
    public double getDTimeStampResolution() {
        return dTimeStampResolution;
    }

    /**
     * @param dTimeStampResolution the dTimeStampResolution to set
     */
    public void setDTimeStampResolution(double dTimeStampResolution) {
        this.dTimeStampResolution = dTimeStampResolution;
    }

    /**
     * @return the dTimeSpan
     */
    public double getDTimeSpan() {
        return dTimeSpan;
    }

    /**
     * @param dTimeSpan the dTimeSpan to set
     */
    public void setDTimeSpan(double dTimeSpan) {
        this.dTimeSpan = dTimeSpan;
    }

    /**
     * @return the szAppName
     */
    public String getSzAppName() {
        return szAppName;
    }

    /**
     * @param szAppName the szAppName to set
     */
    public void setSzAppName(String szAppName) {
        this.szAppName = szAppName;
    }

    /**
     * @return the dwTime_Year
     */
    public int getDwTime_Year() {
        return dwTime_Year;
    }

    /**
     * @param dwTimeYear the dwTime_Year to set
     */
    public void setDwTime_Year(int dwTimeYear) {
        dwTime_Year = dwTimeYear;
    }

    /**
     * @return the dwTime_Month
     */
    public int getDwTime_Month() {
        return dwTime_Month;
    }

    /**
     * @param dwTimeMonth the dwTime_Month to set
     */
    public void setDwTime_Month(int dwTimeMonth) {
        dwTime_Month = dwTimeMonth;
    }

    /**
     * @return the dwTime_DayOfWeek
     */
    public int getDwTime_DayOfWeek() {
        return dwTime_DayOfWeek;
    }

    /**
     * @param dwTimeDayOfWeek the dwTime_DayOfWeek to set
     */
    public void setDwTime_DayOfWeek(int dwTimeDayOfWeek) {
        dwTime_DayOfWeek = dwTimeDayOfWeek;
    }

    /**
     * @return the dwTime_Day
     */
    public int getDwTime_Day() {
        return dwTime_Day;
    }

    /**
     * @param dwTimeDay the dwTime_Day to set
     */
    public void setDwTime_Day(int dwTimeDay) {
        dwTime_Day = dwTimeDay;
    }

    /**
     * @return the dwTime_Hour
     */
    public int getDwTime_Hour() {
        return dwTime_Hour;
    }

    /**
     * @param dwTimeHour the dwTime_Hour to set
     */
    public void setDwTime_Hour(int dwTimeHour) {
        dwTime_Hour = dwTimeHour;
    }

    /**
     * @return the dwTime_Min
     */
    public int getDwTime_Min() {
        return dwTime_Min;
    }

    /**
     * @param dwTimeMin the dwTime_Min to set
     */
    public void setDwTime_Min(int dwTimeMin) {
        dwTime_Min = dwTimeMin;
    }

    /**
     * @return the dwTime_Sec
     */
    public int getDwTime_Sec() {
        return dwTime_Sec;
    }

    /**
     * @param dwTimeSec the dwTime_Sec to set
     */
    public void setDwTime_Sec(int dwTimeSec) {
        dwTime_Sec = dwTimeSec;
    }

    /**
     * @return the dwTime_MilliSec
     */
    public int getDwTime_MilliSec() {
        return dwTime_MilliSec;
    }

    /**
     * @param dwTimeMilliSec the dwTime_MilliSec to set
     */
    public void setDwTime_MilliSec(int dwTimeMilliSec) {
        dwTime_MilliSec = dwTimeMilliSec;
    }

    /**
     * @return the szFileComment
     */
    public String getSzFileComment() {
        return szFileComment;
    }

    /**
     * @param szFileComment the szFileComment to set
     */
    public void setSzFileComment(String szFileComment) {
        this.szFileComment = szFileComment;
    }
}
