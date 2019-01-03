package com.fst.travelagency.controller;

import com.fst.travelagency.model.City;
import com.fst.travelagency.model.Flight;
import com.fst.travelagency.repository.FlightRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class FlightController {
    private FlightRepository repository;

    public FlightController(FlightRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/flights")
    public List<Flight> retrieveAllFlights() {
        return repository.findAll();
    }

    @GetMapping("/flights/{cityid}")
    public List<Flight> retrieveFlightsByCity(@PathVariable City city_id) {
        List<Flight> flights = repository.findByCity(city_id);
        return  flights;
    }


    @PostMapping("/flights")
    public ResponseEntity<Object> createFlight(@RequestBody Flight flight) {
        Flight savedFlight = repository.save(flight);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedFlight.getId()).toUri();
        return ResponseEntity.created(location).build();

    }

    @PostMapping("/flights/{id}")
    public ResponseEntity<Object> updateFlight(@RequestBody Flight flight, @PathVariable long id) {
        Optional<Flight> flightOptional = repository.findById(id);
        if (!flightOptional.isPresent())
            return ResponseEntity.notFound().build();
        flight.setId(id);
        repository.save(flight);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/flights/{id}")
    public void deleteFlight(@PathVariable long id) {
        repository.deleteById(id);
    }


}