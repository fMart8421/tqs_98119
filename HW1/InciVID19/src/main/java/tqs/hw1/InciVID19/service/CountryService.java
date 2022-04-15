package tqs.hw1.InciVID19.service;

import org.springframework.stereotype.Service;
import tqs.hw1.InciVID19.InciVid19Application;
import tqs.hw1.InciVID19.model.Country;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.logging.Logger;

@Service
@Transactional
/**
 * This is the class that commutes with the external API.<br/>
 * All the requests are done within this class. The methods are divided in two cases, the <strong><i>WithoutParameters</i></strong> and the <strong><i>Default</i></strong>, which is with parameters. The WithoutParameters methods are used to get Country information to be displayed like an autocomplete, for example.
* */
public class CountryService {


    Logger log = Logger.getLogger(InciVid19Application.class.getName());

    public List<Country> getCountriesWithoutParameters(){
        return null;
    }

    public Country getCountryByNameDefault(){
        return null;
    }


    protected List<Country> fetchApi(String url) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(
                URI.create(url)
        ).header("get", "application/json")
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();
        return null;
    }

    protected String createUrl(String name, boolean parameters){
        StringBuilder sb = new StringBuilder();

        return sb.toString();
    }

}
