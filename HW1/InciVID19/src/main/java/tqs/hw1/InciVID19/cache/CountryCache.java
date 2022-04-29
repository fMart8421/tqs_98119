package tqs.hw1.InciVID19.cache;

import tqs.hw1.InciVID19.model.Country;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountryCache {

    private final Map<Integer, Country> cache;
    private List<Country> orderedValues;
    private int size;
    private final int capacity;
    private int hits;                                       // number of items requested that were in the cache
    private int misses;                                     // number of items requested that were not in the cache
    private int requests;

    public CountryCache(int capacity){
        this.capacity=capacity;
        cache = new HashMap<>(capacity);
        orderedValues = new ArrayList<>();
    }

    public void put(Country value){
        if(value == null){
            System.err.println("Cannot write a null object to cache");
            return;
        }
        if(!cache.containsKey(value.hashCode())){
            if(size()>=capacity){
                cache.remove(orderedValues.get(0).hashCode());
                orderedValues.remove(0);
                orderedValues.add(value);
            }
            else{
                size++;
            }
            orderedValues.add(value);
            cache.put(value.hashCode(),value);
        }
    }

    public Country get(int key){
        requests++;
        if(!cache.containsKey(key)){
            misses++;
            return null;
        }
        onGetRefreshOrder(orderedValues.indexOf(cache.get(key)));
        return cache.get(key);
    }

    public int size() {
        return size;
    }

    public int getHits() {
        return hits;
    }

    public int getMisses() {
        return misses;
    }

    public int getRequests() {
        return requests;
    }

    protected void onGetRefreshOrder(int index){
        Country lastGet = orderedValues.get(index);
        List<Country> temporaryList = new ArrayList<>();
        temporaryList.add(lastGet);
        for (int i = 0; i < size; i++) {
            if(i!=index) temporaryList.add(orderedValues.get(i));
        }
        orderedValues = temporaryList;
    }
}
