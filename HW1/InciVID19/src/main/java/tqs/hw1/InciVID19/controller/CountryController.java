package tqs.hw1.InciVID19.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tqs.hw1.InciVID19.model.Country;
import tqs.hw1.InciVID19.service.CountryService;

import java.io.IOException;
import java.util.List;

@RestController
public class CountryController {

    @Autowired
    CountryService countryService;

    @GetMapping("/{country}")
    public Country getCountry(@PathVariable(value="country")String country) throws IOException{
        return countryService.getCountryLatest(country);
    }

    @GetMapping("/{country}/{date}")
    public Country getCountryWithDate(@PathVariable(value="country")String country, @PathVariable(value="date") String date) throws IOException{
        return countryService.getCountryByNameAndDay(country, date);
    }

}
