package tqs.hw1.InciVID19.model;


import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "city")
public class Country {

    @Id
    @GeneratedValue
    private Long id;

    /* Country details */
    private String name;
    private String continent;

    /* Covid-related details */
    private int newCases;
    private int activeCases;
    private int totalCases;
    private int recovered;
    private int newDeaths;
    private int totalDeaths;

    /* Last data update in milliseconds since Epoch */
    private Date dataDate;

    /* Constructors */
    public Country() {
    }

    public Country(String name, String continent) {
        this.name = name;
        this.continent = continent;
    }

    public Country(String name,String continent, int newCases, int activeCases,int totalCases,int recovered, int newDeaths, int totalDeaths, Date dataDate) {
        this.name = name;
        this.continent = continent;
        this.newCases = newCases;
        this.activeCases = activeCases;
        this.totalCases = totalCases;
        this.newDeaths = newDeaths;
        this.totalDeaths = totalDeaths;
        this.recovered = recovered;
        this.dataDate = dataDate;
    }

    /* Getters */

    public Long getId() {
        return id;
    }

    public Date getDataDate() {
        return dataDate;
    }

    public int getActiveCases() {
        return activeCases;
    }

    public int getNewCases() {
        return newCases;
    }

    public int getNewDeaths() {
        return newDeaths;
    }

    public int getTotalDeaths() {
        return totalDeaths;
    }

    public int getRecovered() {
        return recovered;
    }


    public String getName() {
        return name;
    }

    public String getContinent() {
        return continent;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", continent='" + continent + '\'' +
                ", newCases=" + newCases +
                ", activeCases=" + activeCases +
                ", totalCases=" + totalCases +
                ", recovered=" + recovered +
                ", newDeaths=" + newDeaths +
                ", totalDeaths=" + totalDeaths +
                ", dataDate='" + dataDate +
                "'}";
    }
}