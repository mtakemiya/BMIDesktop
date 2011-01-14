/**
 * 
 */
package jp.atr.dni.bmi.desktop.neuroshareutils;

/**
 *
 * @author kharada
 * @version 2011/01/13
 */
public class DWordEventData extends EventData {

    /** To store 4-byte unsigned values in a signed type (like Java types) you have to use 8 bytes */
    private Long data;

    /**
     * Default constructor.
     *
     * @param dTimestamp
     * @param dwDataByteSize
     */
    public DWordEventData(double dTimestamp, long dwDataByteSize) {
        super(dTimestamp, dwDataByteSize);
    }

    /**
     * @param dTimestamp
     * @param dwDataByteSize
     * @param data
     */
    public DWordEventData(double dTimestamp, long dwDataByteSize, Long data) {
        super(dTimestamp, dwDataByteSize);
        this.data = data;
    }

    /**
     * @return the data
     */
    public Long getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Long data) {
        this.data = data;
    }
}
