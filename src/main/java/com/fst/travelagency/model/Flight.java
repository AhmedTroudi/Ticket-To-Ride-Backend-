package com.fst.travelagency.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Flight implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date tripStartingDate;
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date tripEndingDate;
    private int price;
    private int availableSeats;

    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName="city_id")
    private City city;

    public Flight() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTripStartingDate() {
        return tripStartingDate;
    }

    public void setTripStartingDate(Date tripStartingDate) {
        this.tripStartingDate = tripStartingDate;
    }

    public Date getTripEndingDate() {
        return tripEndingDate;
    }

    public void setTripEndingDate(Date tripEndingDate) {
        this.tripEndingDate = tripEndingDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", tripStartingDate=" + tripStartingDate +
                ", tripEndingDate=" + tripEndingDate +
                ", price=" + price +
                ", availableSeats=" + availableSeats +
                ", city=" + city +
                '}';
    }
}
