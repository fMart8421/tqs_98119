package tqs.hw1.InciVID19.controller;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tqs.hw1.InciVID19.cache.CountryCacheDetails;
import tqs.hw1.InciVID19.model.Country;
import tqs.hw1.InciVID19.service.CountryService;

import java.io.IOException;

@RestController
public class CountryController {

    @Autowired
    CountryService countryService;

    @GetMapping("/api/country")
    public Country getCountry(@RequestParam String name) throws IOException{
        return countryService.getCountryLatest(name);
    }

    @GetMapping("/api/country-date")
    public Country getCountryByDate(@RequestParam String name, @RequestParam String date) throws IOException{
        return countryService.getCountryByNameAndDay(name, date);
    }

    @GetMapping("/api/cache-details")
    public CountryCacheDetails getCacheDetails() throws IOException{
        return countryService.getCacheDetails();
    }

}
