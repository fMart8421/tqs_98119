package tqs.hw1.InciVID19;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import tqs.hw1.InciVID19.cache.CountryCacheDetails;
import tqs.hw1.InciVID19.controller.CountryController;
import tqs.hw1.InciVID19.model.Country;
import tqs.hw1.InciVID19.service.CountryService;


@WebMvcTest(CountryController.class)
public class ControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CountryService countryService;

    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    void whenGetCountry_thenRetrieveCountryLatestInfo() throws Exception{
        Country portugal = new Country("Portugal", "Europe", 0, 0, 3791744, 0, 0, 22162, "2022-04-29");

        when(countryService.getCountryLatest("portugal")).thenReturn(portugal);

        mockMvc.perform(get("/api/country").param("name", "portugal").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.name", is("Portugal")));
    }

    @Test
    void whenGetCountryByDate_thenRetrieveCountryInfoForDate() throws Exception {
        Country portugal = new Country("Portugal", "Europe", 0, 482801, 3380263, 2876177, 0, 21285, "2022-03-15");

        when(countryService.getCountryByNameAndDay("portugal", "2022-03-15")).thenReturn(portugal);

        mockMvc.perform(get("/api/country-date").param("name", "portugal").param("date", "2022-03-15").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.name", is("Portugal"))).andExpect(jsonPath("$.day", is("2022-03-15")));

    }

    @Test
    void whenRequestMade_thenCacheDetailsShouldUpdate() throws Exception {
        Country portugal = new Country("Portugal", "Europe", 0, 482801, 3380263, 2876177, 0, 21285, "2022-03-15");
        CountryCacheDetails details = new CountryCacheDetails(1,0,1);
        when(countryService.getCountryByNameAndDay("portugal", "2022-03-15")).thenReturn(portugal);
        when(countryService.getCacheDetails()).thenReturn(details);


        mockMvc.perform(get("/api/country-date").param("name", "portugal").param("date", "2022-03-15").contentType(MediaType.APPLICATION_JSON));
        mockMvc.perform(get("/api/cache-details").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.misses", is(1))).andExpect(jsonPath("$.requests", is(1))).andExpect(jsonPath("$.hits", is(0)));

    }


}
