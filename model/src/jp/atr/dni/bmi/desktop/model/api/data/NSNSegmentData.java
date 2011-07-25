package jp.atr.dni.bmi.desktop.model.api.data;

/**
 *
 * @author makoto
 */
public final class NSNSegmentData implements APIData {

   private APIList<Double> timeStamps;
   private APIList<Integer> unitIds;
   private APIList<APIList<Double>> values;

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
    * @return the unitIDs
    */
   public APIList<Integer> getUnitIds() {
      return unitIds;
   }

   /**
    * @param unitIDs the unitIDs to set
    */
   public void setUnitIds(APIList<Integer> unitIds) {
      this.unitIds = unitIds;
   }

   /**
    * @return the values
    */
   public APIList<APIList<Double>> getValues() {
      return values;
   }

   /**
    * @param values the values to set
    */
   public void setValues(APIList<APIList<Double>> values) {
      this.values = values;
   }
}
