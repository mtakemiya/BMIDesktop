package jp.atr.dni.bmi.desktop.model.api.data;

import java.util.ArrayList;
import java.util.Collection;
import jp.atr.dni.bmi.desktop.model.api.data.APIDataProvider;
import java.util.HashMap;
import java.util.List;

/**
 *TODO: make iterable!
 * 
 * @author Makoto Takemiya - [武宮 誠] <br />
 * <a href="http://www.atr.jp">ATR - [株式会社・国際電氣通信基礎技術研究所]</a>
 *
 * @version 2011/07/25
 */
public final class APIList<E> {

   /** Number of elements in this list. */
   private int size;
   /** Counts the number of times data is not in the cache and must be read in, 
    * similiar to page faults in memory.
   TODO: use this to grow/shrink the data chunk size*/
//    private int dataFaults;
   private APIDataProvider<E> dataProvider;
   //Inclusive
   private int startIndex;
   //Exclusive
   private int maxIndex;
   private static final int INCR_SIZE = 5000;
   private List<E> data;
//   private HashMap<Integer, E> data;
//    private APIListIterator<E> listIterator;

//    private cache
   //private victimCache
   public APIList(APIDataProvider<E> dataProvider) {
      this.dataProvider = dataProvider;
      this.size = dataProvider.size();
   }

   /**
    * @return the data at index ndx
    */
   public synchronized E get(int ndx) {
      if (ndx >= startIndex && ndx < maxIndex && data != null) {
         return data.get(startIndex + ndx);
         
      } else {
         //DataFault
         startIndex = ndx;
         maxIndex = startIndex + INCR_SIZE;
         data = dataProvider.getData(startIndex, maxIndex);
      }
      //      data.

      /*if (data.containsKey(ndx)) {
      return data.get(ndx);
      } else {
      // Check the cache
      // Check the victim cache
      // Go read in the data and set to current
      dataProvider.getData(ndx, ndx + 5000);
      //            dataFaults++;
      }*/
      
      return null;
   }
   
   public synchronized boolean add(E e) {
      size++;
      return true;
   }

//   public synchronized boolean remove(E e) {
//      //First search for the object, then remove it
//      
//   }
   public synchronized boolean remove(int ndx) {
//      data.remove(ndx);
      return true;
   }
   
   public synchronized int size() {
      return size;
   }
}
