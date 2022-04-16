package tqs.hw1.InciVID19.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import tqs.hw1.InciVID19.InciVid19Application;
import tqs.hw1.InciVID19.model.Country;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
        List<Country> countryList = new ArrayList<>();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(
                URI.create(url)
        ).header("get", "application/json")
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();

        JSONObject jsonBody = new JSONObject(body);
        JSONArray features = jsonBody.getJSONArray("features");

        for(int i=0; i<features.length();i++){
            JSONObject countryAttributes = features.getJSONObject(i).getJSONObject("attributes");
            JSONObject countryCoordinates = features.getJSONObject(i).getJSONObject("geometry");
            countryList.add(
              new Country(
                      countryAttributes.getString("Country_Region"),
                      countryCoordinates.getDouble("x"),
                      countryCoordinates.getDouble("y"),
                      countryAttributes.getInt("Confirmed"),
                      countryAttributes.getInt("Deaths"),
                      countryAttributes.getInt("Recovered"),
                      countryAttributes.getInt("Active"),
                      countryAttributes.getDouble("Incident_Rate"),
                      countryAttributes.getLong("Last_Update")
                      )
            );
        }

        return countryList;
    }


    protected String createUrl(String name, List<String> parameters){
        StringBuilder sb = new StringBuilder("https://services1.arcgis.com/0MSEUqKaxRlEPj5g/arcgis/rest/services/ncov_cases2_v1/FeatureServer/2/query?where=");
        if(name.equals("")){
            sb.append("1%3D1");
        } else {
            sb.append("Country_Region%20%3D%20'")
                    .append(name.replace(" ", "%20").toUpperCase(Locale.ROOT))
                    .append("'")
            ;
        }
        StringBuilder params = new StringBuilder();
        for (int i = 0; i < parameters.size(); i++) {
            if (i==parameters.size()-1) params.append(parameters.get(i));
            else params.append(parameters.get(i)).append(",");
        }

        sb.append(params)
        .append("&outSR=4326&f=json");

        return sb.toString();
    }

}
