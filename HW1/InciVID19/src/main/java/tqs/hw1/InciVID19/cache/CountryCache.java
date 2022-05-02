package tqs.hw1.InciVID19.cache;

import org.springframework.stereotype.Component;
import tqs.hw1.InciVID19.model.Country;

import java.util.*;
import java.util.logging.Logger;

@Component
public class CountryCache {

    private final Map<String, Country> cache;
    private List<String> orderedKeys;
    private int size;
    private final int capacity;
    private int hits;                                       // number of items requested that were in the cache
    private int misses;                                     // number of items requested that were not in the cache
    private int requests;

    private final Logger log = Logger.getLogger(CountryCache.class.getName());
    public CountryCache(){
        capacity = 15;
        cache = new HashMap<>(capacity);
        orderedKeys = new ArrayList<>();
    }

    public CountryCache(int capacity){
        this.capacity=capacity;
        cache = new HashMap<>(capacity);
        orderedKeys = new ArrayList<>();
    }

    public void put(Country value){
        if(value == null) {
            log.warning("Cannot write a null object to cache");
            return;
        }
        if(!cache.containsKey(value.getName().toLowerCase(Locale.ROOT))){
            log.info("Putting object with key: "+value.getName().toLowerCase(Locale.ROOT));
            if(size()>=capacity){
                log.warning("Exceeded capacity, removing item with key: "+orderedKeys.get(0));
                cache.remove(orderedKeys.get(0));
                orderedKeys.remove(0);
            }
            else{
                size++;
            }
            orderedKeys.add(value.getName().toLowerCase(Locale.ROOT));
            cache.put(value.getName().toLowerCase(Locale.ROOT),value);
        }
    }
    public void put(String key,Country value){

        if(value == null) {
            log.warning("Cannot write a null object to cache");
            return;
        }
        if(!cache.containsKey(key)){
            log.info("Putting object with key: "+key);
            if(size()>=capacity){
                cache.remove(orderedKeys.get(0));
                orderedKeys.remove(0);
            }
            else{
                size++;
            }
            orderedKeys.add(key);
            cache.put(key,value);
        }
    }

    public Country get(String key){
        key = key.toLowerCase(Locale.ROOT);
        log.info("Getting object with key: {0}"+key);
        requests++;
        if(!cache.containsKey(key)){
            log.warning("Error -> No object with key: "+key);
            misses++;
            return null;
        }
        onGetRefreshOrder(orderedKeys.indexOf(key));
        hits++;
        return cache.get(key);
    }

    public int size() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }

    public Map<String, Country> getCache() {
        return cache;
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
        if(index>0) {
            String lastGet = orderedKeys.get(index);
            List<String> temporaryList = new ArrayList<>();
            temporaryList.add(lastGet);
            for (int i = 0; i < size; i++) {
                if (i != index) temporaryList.add(orderedKeys.get(i));
            }
            orderedKeys = temporaryList;
        }
    }
}
