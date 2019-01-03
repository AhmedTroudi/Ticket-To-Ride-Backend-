package com.fst.travelagency.repository;

import com.fst.travelagency.model.City;
import com.fst.travelagency.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByCity(City city);
}