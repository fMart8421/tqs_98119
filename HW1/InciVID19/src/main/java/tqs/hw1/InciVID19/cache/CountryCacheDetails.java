package tqs.hw1.InciVID19.cache;

public class CountryCacheDetails {
    private final int misses, hits, requests;

    public CountryCacheDetails(int misses, int hits, int requests){
        this.hits = hits;
        this.misses = misses;
        this.requests = requests;
    }

    public int getRequests() {
        return requests;
    }

    public int getMisses() {
        return misses;
    }

    public int getHits() {
        return hits;
    }
}
