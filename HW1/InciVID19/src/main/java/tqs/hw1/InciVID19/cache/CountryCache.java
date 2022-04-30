package tqs.hw1.InciVID19.cache;

import org.springframework.stereotype.Component;
import tqs.hw1.InciVID19.model.Country;

import java.util.*;

@Component
public class CountryCache {

    private final Map<String, Country> cache;
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
        if(value == null) {
            System.err.println("Cannot write a null object to cache");
            return;
        }
        if(!cache.containsKey(value.getName().toLowerCase(Locale.ROOT))){
            if(size()>=capacity){
                cache.remove(orderedValues.get(0).getName().toLowerCase(Locale.ROOT));
                orderedValues.remove(0);
                orderedValues.add(value);
            }
            else{
                size++;
            }
            orderedValues.add(value);
            cache.put(value.getName().toLowerCase(Locale.ROOT),value);
        }
    }

    public Country get(String key){
        requests++;
        if(!cache.containsKey(key)){
            misses++;
            return null;
        }
        onGetRefreshOrder(orderedValues.indexOf(cache.get(key)));
        hits++;
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
