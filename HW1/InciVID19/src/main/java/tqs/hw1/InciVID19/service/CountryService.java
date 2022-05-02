package tqs.hw1.InciVID19.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tqs.hw1.InciVID19.cache.CountryCache;
import tqs.hw1.InciVID19.cache.CountryCacheDetails;
import tqs.hw1.InciVID19.model.Country;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.ParseException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@Transactional
public class CountryService {

    Logger log = Logger.getLogger(CountryService.class.getName());

    @Autowired
    private CountryCache countryCache;

    public Country getCountryByNameAndDay(String country, String day){
        country = country.toLowerCase(Locale.ROOT);
        Country result = countryCache.get(country+day);
        if(result==null){
            try {
                result = fetchApi(createUrl(country, day));
                countryCache.put(result.getName().toLowerCase(Locale.ROOT)+result.getDay(), result);
            }
            catch(IOException | ParseException e){
                log.log(Level.SEVERE, "There was an exception: ", e);

            } catch(InterruptedException e){
                log.log(Level.SEVERE, "The thread was interrupted: ", e);
                Thread.currentThread().interrupt();
            }
        }

        return result;
    }
    public Country getCountryLatest(String country){
        country = country.toLowerCase(Locale.ROOT);
        Country result = countryCache.get(country);
        if (result==null){
            try {
                result = fetchApi(createUrl(country));
                countryCache.put(result);
            }
            catch(IOException | ParseException e){
                log.log(Level.SEVERE, "An exception occurred: ", e);

            } catch(InterruptedException e){
                log.log(Level.SEVERE, "The thread was interrupted: ", e);
                Thread.currentThread().interrupt();
            }
        }

        return result;
    }

    public CountryCacheDetails getCacheDetails(){
        int hits, misses, requests;
        hits= countryCache.getHits();
        misses =countryCache.getMisses();
        requests = countryCache.getRequests();
        return new CountryCacheDetails(misses, hits, requests);
    }



    ///
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
        int results = jsonBody.getInt("results");
        Country countryResult=null;
        if (results > 0) {
            JSONObject responseItems = jsonBody.getJSONArray("response").getJSONObject(0);
            JSONObject cases = responseItems.getJSONObject("cases");
            JSONObject deaths = responseItems.getJSONObject("deaths");
            try{
                countryResult = new Country(
                        responseItems.getString("country"),
                        responseItems.getString("continent"),
                        Integer.parseInt(cases.optString("new", "0").replace("+", "")),
                        cases.optInt("active"),
                        cases.optInt("total"),
                        cases.optInt("recovered"),
                        Integer.parseInt(deaths.optString("new", "0").replace("+", "")),
                        deaths.optInt("total"),
                        responseItems.getString("day")
                );
            } catch (NumberFormatException e){
                countryResult = new Country(
                        responseItems.getString("country"),
                        responseItems.getString("continent"),
                        cases.optInt("new"),
                        cases.optInt("active"),
                        cases.optInt("total"),
                        cases.optInt("recovered"),
                        deaths.optInt("new"),
                        deaths.optInt("total"),
                        responseItems.getString("day")
                );
            }

        }
        return countryResult;
    }


    protected String createUrl(String country, String day){
        StringBuilder sb = new StringBuilder("https://covid-193.p.rapidapi.com/history?");
        sb.append("country=").append(country)
                             .append("&day=").append(day);
        log.info("Fetched: "+ sb);
        return sb.toString();
    }
    protected String createUrl(String country){
        StringBuilder sb = new StringBuilder("https://covid-193.p.rapidapi.com/history?");
        sb.append("country=").append(country);
        log.info("Fetched: "+ sb);
        return sb.toString();
    }



}
