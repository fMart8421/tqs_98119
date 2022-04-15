package tqs.hw1.InciVID19.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tqs.hw1.InciVID19.model.Country;

import java.util.List;


@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    @Override
    List<Country> findAll();

    Country findByName(String name);

}
