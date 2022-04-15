package tqs.hw1.InciVID19.service;

import tqs.hw1.InciVID19.model.Country;

import java.util.List;


/**
 * This is the class that commutes with the external API.<br/>
 * All the requests are done within this class. The methods are divided in two cases, the <strong><i>WithoutParameters</i></strong> and the <strong><i>Default</i></strong>, which is with parameters. The WithoutParameters methods are used to get Country information to be displayed like an autocomplete, for example.
* */
public class CountryService {


    public List<Country> getCountriesWithoutParameters(){
        return null;
    }

    public Country getCountryByNameDefault(){
        return null;
    }


    protected List<Country> fetchApi(String name, boolean parameters){
        return null;
    }

    protected String createUrl(){
        StringBuilder sb = new StringBuilder();

        return sb.toString();
    }

}
