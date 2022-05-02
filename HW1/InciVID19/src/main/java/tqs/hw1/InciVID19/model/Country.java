package tqs.hw1.InciVID19.model;


import javax.persistence.*;
import java.util.Date;
import java.util.Objects;


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

    /* Date of the data retrieved */
    private String day;

    /* Constructors */

    public Country(String name,String continent, int newCases, int activeCases,int totalCases,int recovered, int newDeaths, int totalDeaths, String day) {
        this.name = name;
        this.continent = continent;
        this.newCases = newCases;
        this.activeCases = activeCases;
        this.totalCases = totalCases;
        this.newDeaths = newDeaths;
        this.totalDeaths = totalDeaths;
        this.recovered = recovered;
        this.day = day;
    }

    /* Getters */

    public Long getId() {
        return id;
    }

    public String getDay() {
        return day;
    }

    public int getActiveCases() {
        return activeCases;
    }

    public int getTotalCases() {
        return totalCases;
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
                ", day='" + day +
                "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return newCases == country.newCases && activeCases == country.activeCases && totalCases == country.totalCases && recovered == country.recovered && newDeaths == country.newDeaths && totalDeaths == country.totalDeaths && name.equals(country.name) && continent.equals(country.continent) && day.equals(country.day);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, continent, newCases, activeCases, totalCases, recovered, newDeaths, totalDeaths, day);
    }
}