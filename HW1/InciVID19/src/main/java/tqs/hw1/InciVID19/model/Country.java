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

    public Country(String name) {
        this.name = name;
    }

    public Country(String name, Double lat, Double lon, int confirmed, int deaths, int recovered, int active,Double rate, long lastUpdate) {
        this.name = name;
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


    @Override
    public String toString() {
        // IntelliJ's auto-generated method
        return "Country { " +
                "id=" + id +
                ", name='" + name + '\'' +
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