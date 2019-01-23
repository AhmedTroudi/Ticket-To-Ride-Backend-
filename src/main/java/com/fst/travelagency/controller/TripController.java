package com.fst.travelagency.controller;

import com.fst.travelagency.model.City;
import com.fst.travelagency.model.Trip;
import com.fst.travelagency.repository.TripRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class TripController {
    private TripRepository repository;

    public TripController(TripRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/trips")
    public List<Trip> retrieveAllTrips() {
        return repository.findAll();
    }

    @GetMapping("/trips/{cityid}")
    public List<Trip> retrieveTripsByCity(@PathVariable City destination) {
        List<Trip> trips = repository.findByDestination(destination);
        return  trips;
    }


    @PostMapping("/trips")
    public ResponseEntity<Object> createTrip(@RequestBody Trip trip) {
        Trip savedTrip = repository.save(trip);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedTrip.getTrip_id()).toUri();
        return ResponseEntity.created(location).build();

    }

    @PutMapping("/trips/{id}")
    public ResponseEntity<Object> updateTrip(@RequestBody Trip trip, @PathVariable long id) {
        Optional<Trip> tripOptional = repository.findById(id);
        if (!tripOptional.isPresent())
            return ResponseEntity.notFound().build();
        trip.setTrip_id(id);
        repository.save(trip);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/trips/delete/{trip_id}")
    public void deleteTrip(@PathVariable long trip_id) {
        repository.deleteById(trip_id);
    }


}