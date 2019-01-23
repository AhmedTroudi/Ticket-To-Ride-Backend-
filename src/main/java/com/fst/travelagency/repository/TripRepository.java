package com.fst.travelagency.repository;

import com.fst.travelagency.model.City;
import com.fst.travelagency.model.Flight;
import com.fst.travelagency.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findByDestination(City destination);
    List<Trip> findAll();

}
