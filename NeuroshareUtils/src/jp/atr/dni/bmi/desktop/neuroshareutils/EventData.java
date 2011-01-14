/**
 * 
 */
package jp.atr.dni.bmi.desktop.neuroshareutils;

/**
 *
 * @author kharada
 * @version 2011/01/13
 */
public abstract class EventData {

    /**
     * Time of the event in seconds.
     */
    private double timestamp;
    /**
     * Size in bytes of the following data value.
     */
    private long dataByteSize;

    /**
     * @param timestamp
     * @param dataByteSize
     */
    public EventData(double timestamp, long dataByteSize) {
        super();
        this.timestamp = timestamp;
        this.dataByteSize = dataByteSize;
    }

    /**
     * @return the timestamp
     */
    public double getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(double timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @return the dataByteSize
     */
    public long getDataByteSize() {
        return dataByteSize;
    }

    /**
     * @param dataByteSize the dataByteSize to set
     */
    public void setDataByteSize(long dataByteSize) {
        this.dataByteSize = dataByteSize;
    }
}
