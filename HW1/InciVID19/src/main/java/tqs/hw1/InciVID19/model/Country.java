package tqs.hw1.InciVID19.model;


import javax.persistence.*;


@Entity
@Table(name = "city")
public class Country {

    @Id
    @GeneratedValue
    private Long id;

    /* Country details */
    private String name;
    private String slug; // the name used to fetch data in the API
    private Double lat;
    private Double lon;

    /* Covid-related details */
    private int confirmed;
    private int deaths;
    private int recovered;
    private int active;

    /* Last data update in milliseconds since Epoch */
    private long lastUpdate;

    /* Constructors */
    public Country() {
    }

    public Country(String name, String slug) {
        this.name = name;
        this.slug = slug;
    }

    public Country(String name, String slug, Double lat, Double lon, int confirmed, int deaths, int recovered, int active, long lastUpdate) {
        this.name = name;
        this.slug = slug;
        this.lat = lat;
        this.lon = lon;
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovered = recovered;
        this.active = active;
        this.lastUpdate = lastUpdate;
    }

    /* Getters */

    public Long getId() {
        return id;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLon() {
        return lon;
    }

    public int getActive() {
        return active;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getRecovered() {
        return recovered;
    }

    public long getLastUpdate() {
        return lastUpdate;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    @Override
    public String toString() {
        // IntelliJ's auto-generated method
        return "Country { " +
                "id=" + id +
                ", name='" + name + '\'' +
                ", slug='" + slug + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                ", confirmed=" + confirmed +
                ", deaths=" + deaths +
                ", recovered=" + recovered +
                ", active=" + active +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}