package com.fst.travelagency.controller;


import com.fst.travelagency.model.Reservation;
import com.fst.travelagency.repository.ReservationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class ReservationController {
    private ReservationRepository repository;

    public ReservationController(ReservationRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/reservations")
    public List<Reservation> retrieveAllRepositories() {
        return repository.findAll();
    }


    @PostMapping("/reservations")
    public ResponseEntity<Object> createRepository(@RequestBody Reservation reservation) {
        Reservation savedReservation = repository.save(reservation);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedReservation.getRes_id()).toUri();
        return ResponseEntity.created(location).build();

    }

    @PutMapping("/reservations/{id}")
    public ResponseEntity<Object> updateReservation(@RequestBody Reservation reservation, @PathVariable long id) {
        Optional<Reservation> reservationOptional = repository.findById(id);
        if (!reservationOptional.isPresent())
            return ResponseEntity.notFound().build();
        reservation.setRes_id(id);
        repository.save(reservation);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/reservations/{id}")
    public void deleteReservation(@PathVariable long id) {
        repository.deleteById(id);
    }


}