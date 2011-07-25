package jp.atr.dni.bmi.desktop.model.api.data;

/**
 *
 * @author makoto
 */
public  final class NSNEventData implements APIData {

    private APIList<Double> timeStamps;
    private APIList<Object> values;

    /**
     * @return the timeStamps
     */
    public APIList<Double> getTimeStamps() {
        return timeStamps;
    }

    /**
     * @param timeStamps the timeStamps to set
     */
    public void setTimeStamps(APIList<Double> timeStamps) {
        this.timeStamps = timeStamps;
    }

    /**
     * @return the values
     */
    public APIList<Object> getValues() {
        return values;
    }

    /**
     * @param values the values to set
     */
    public void setValues(APIList<Object> values) {
        this.values = values;
    }
    
    
}
