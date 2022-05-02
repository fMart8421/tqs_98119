package tqs.hw1.InciVID19;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tqs.hw1.InciVID19.cache.CountryCache;
import tqs.hw1.InciVID19.model.Country;

import static org.junit.jupiter.api.Assertions.*;

public class CacheTest {

    private CountryCache countryCache;
    private Country portugal;


    @BeforeEach
    public void setUp(){
        countryCache = new CountryCache(4);
        portugal = new Country("Portugal", "Europe", 0, 482801, 3380263, 2876177, 0, 21285, "2022-03-15");
    }

    @AfterEach
    public void tearDown(){
        countryCache = null;
    }

    @Test
    public void whenPutInCache_thenCacheShouldHaveItem(){
        countryCache.put(portugal);
        assertEquals(portugal, countryCache.get(portugal.getName()));
    }

    @Test
    public void whenPutOneItem_thenSizeShouldBeOne(){
        countryCache.put(portugal);
        assertEquals(1,countryCache.size());
    }

    @Test
    public void whenGetItem_thenCacheShouldHaveRequest(){
        countryCache.put(portugal);
        countryCache.get("portugal");
        assertEquals(1, countryCache.getRequests());
    }

    @Test
    public void whenItemNotInCache_thenGetGeneratesMiss(){
        countryCache.get("portugal");
        assertEquals(1, countryCache.getRequests());
        assertEquals(1, countryCache.getMisses());

    }

    @Test
    public void whenItemInCache_thenGetGeneratesHit(){
        countryCache.put(portugal);
        countryCache.get("portugal");
        assertEquals(1, countryCache.getRequests());
        assertEquals(1, countryCache.getHits());
    }

    @Test
    public void whenPutMoreThanCapacity_thenFirstPutShouldBeRemoved(){
        Country canada = new Country("Canada", "North-America", 3057, 111922, 3370076, 3221170, 48, 36984, "2022-03-15");
        Country usa = new Country("USA", "United-States", 18714, 23854698, 81236146, 56389479, 643, 991969, "2022-03-15");
        Country spain = new Country("Spain", "Europe", 0, 828445, 11223974, 10294394, 0, 101135, "2022-03-15");
        Country france = new Country("France", "Europe", 0, 1131591, 23532997, 22261112, 0, 140294, "2022-03-15");

        countryCache.put(portugal);
        countryCache.put(canada);
        countryCache.put(spain);
        countryCache.put(france);
        countryCache.put(usa);

        assertEquals(4, countryCache.size());
        assertNull(countryCache.get(portugal.getName()));
        assertEquals(1, countryCache.getMisses());
        assertEquals(canada, countryCache.get(canada.getName()));
        assertEquals(1, countryCache.getHits());
    }


}
