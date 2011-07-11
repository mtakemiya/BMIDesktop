package jp.atr.dni.bmi.desktop.model;

import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.collections.list.LazyList;

/**
 *
 * @author Makoto Takemiya - [武宮 誠] <br />
 * <a href="http://www.atr.jp">ATR - [株式会社・国際電氣通信基礎技術研究所]</a>
 *
 * @version 2011/07/11
 */
public class APIList<T> extends Iterable<E> {
    
    private int size;
    
    /** Counts the number of times data is not in the cache and must be read in, 
     * similiar to page faults in memory.
     
     TODO: use this to grow/shrink the data chunk size*/
    private int dataFaults;
    
    private boolean edited;
    
    private APIDataProvider<T> dataProvider;
    
    private HashMap<Integer, T> data;
    
    private APIListIterator<E> listIterator;

//    private cache
    //private victimCache
    
    public APIList(APIDataProvider dataProvider) {
        this.dataProvider=dataProvider;
        this.size = dataProvider.size();
    }
    
    //TODO: get the generics library
//    private LazyList data;

    /**
     * @return the data
     */
    public T get(int ndx) {
        data.
        if (data.containsKey(ndx)) {
            return data.get(ndx);
        } else {
            // Check the cache
            // Check the victim cache
            // Go read in the data and set to current
            dataProvider.getData(ndx, ndx + 5000);
            dataFaults++;
        }
    }

    /**
     * 
     * @param data the data to set
     */
    public void set(int ndx, T data) {
        // This is not a normal setter! For efficiency, we only want to save 
        // changes, not modify the immutable data.
        
    }

    public int size() {
       return size;
    }

    @Override
    public Iterator<E> iterator() {
        return listIterator!=null?listIterator:new APIListIterator<E>(); 
    }
    
    private class APIListIterator implements Iterator<E>{

        @Override
        public boolean hasNext() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public E next() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        
    }
}
