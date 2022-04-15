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

    @GetMapping("no_parameters/cities")
    public List<Country> getAllCountries() throws IOException{
        return countryService.getCountriesWithoutParameters();
    }

    @GetMapping("/cities/{name}")
    public List<Country> getCountryByName(@PathVariable(value = "name")String name) throws IOException{
        return countryService.getCountriesWithoutParameters();
    }


}
