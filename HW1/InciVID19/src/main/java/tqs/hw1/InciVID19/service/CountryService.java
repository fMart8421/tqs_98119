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
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

@Service
@Transactional
/**
 * This is the class that commutes with the external API.<br/>
 *
 * */
public class CountryService {


    //Logger log = Logger.getLogger(InciVid19Application.class.getName());

    public Country getCountryByNameAndDay(String name, String day){
        return null;
    }
    public Country getCountryLatest(String country){
        country = country.toLowerCase(Locale.ROOT);
        Country result = null;
        try {
            result = fetchApi(createUrl(country));}
        catch(IOException e){
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }


    protected Country fetchApi(String url) throws IOException, InterruptedException, ParseException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(
                URI.create(url))
                .header("X-RapidAPI-Host", "covid-193.p.rapidapi.com")
                .header("X-RapidAPI-Key", "acecd734a4mshef70883ef4d9fecp1eea98jsn284c3cd02d1f")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();

        JSONObject jsonBody = new JSONObject(body);
        JSONObject results = jsonBody.getJSONArray("response").getJSONObject(0);
        JSONObject cases = results.getJSONObject("cases");
        JSONObject deaths = results.getJSONObject("deaths");

        return new Country(
                      results.getString("country"),
                      results.getString("continent"),
                      cases.optInt("new"),
                      cases.optInt("active"),
                      cases.optInt("total"),
                      cases.optInt("recovered"),
                      deaths.optInt("new"),
                      deaths.optInt("total"),
                      results.getString("day")
                      );

    }


    protected String createUrl(String country, String day){
        StringBuilder sb = new StringBuilder("https://covid-193.p.rapidapi.com/history?");
        sb.append("country=").append(country)
                             .append("&day=").append(day);
        return sb.toString();
    }
    protected String createUrl(String country){
        StringBuilder sb = new StringBuilder("https://covid-193.p.rapidapi.com/history?");
        sb.append("country=").append(country);
        return sb.toString();
    }

}
