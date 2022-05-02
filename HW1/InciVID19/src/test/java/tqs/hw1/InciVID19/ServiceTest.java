package tqs.hw1.InciVID19;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import tqs.hw1.InciVID19.cache.CountryCache;
import tqs.hw1.InciVID19.model.Country;
import tqs.hw1.InciVID19.service.CountryService;

import java.util.Locale;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {

    @Mock
    private CountryCache countryCache;

    @InjectMocks
    private CountryService countryService;

    @BeforeEach
    public void setUp(){
    }

    @Test
    public void whenGetCountryLatest_cacheShouldBeAccessed(){
        Country portugalLatest = new Country("Portugal", "Europe", 0, 0, 3791744, 0, 0, 22162, "2022-04-29");
        when(countryCache.get("portugal")).thenReturn(portugalLatest);
        assertEquals(portugalLatest, countryService.getCountryLatest("portugal"));
        verify(countryCache, VerificationModeFactory.times(1)).get("portugal");
    }

    @Test
    public void whenGetCountryByNameAndDate_cacheShouldBeAccessed(){
        Country portugal = new Country("Portugal", "Europe", 0, 482801, 3380263, 2876177, 0, 21285, "2022-03-15");
        when(countryCache.get("portugal2022-03-15")).thenReturn(portugal);
        assertEquals(portugal, countryService.getCountryByNameAndDay("portugal", "2022-03-15"));
        verify(countryCache, VerificationModeFactory.times(1)).get(portugal.getName().toLowerCase(Locale.ROOT)+portugal.getDay());
    }

    @Test
    public void whenGetCountryByNameAndDate_apiShouldBeAccessed(){
        Country portugal = new Country("Portugal", "Europe", 0, 482801, 3380263, 2876177, 0, 21285, "2022-03-15");
        assertEquals(portugal, countryService.getCountryByNameAndDay("portugal", "2022-03-15"));
        verify(countryCache, VerificationModeFactory.times(1)).get(portugal.getName().toLowerCase(Locale.ROOT)+portugal.getDay());
    }

    @Test
    public void whenGetTwice_thenCacheIsCalledTwice(){
        Country portugal = new Country("Portugal", "Europe", 0, 482801, 3380263, 2876177, 0, 21285, "2022-03-15");
        Country usa = new Country("USA", "United-States", 0, 482801, 3380263, 2876177, 0, 21285, "2022-03-15");
        when(countryCache.get("portugal")).thenReturn(portugal);
        when(countryCache.get("usa")).thenReturn(usa);
        assertEquals(portugal, countryService.getCountryLatest("portugal"));
        assertEquals(usa, countryService.getCountryLatest("usa"));
        verify(countryCache, VerificationModeFactory.times(1)).get("portugal");
        verify(countryCache, VerificationModeFactory.times(1)).get("usa");
    }




}
