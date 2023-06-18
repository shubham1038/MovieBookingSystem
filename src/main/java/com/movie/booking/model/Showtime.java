package com.movie.booking.model;

import java.util.Date;

public class Showtime {
    private Movie movie;
    private Theatre theatre;
    private Date time;
    private int availableSeats;
    private double price;

    public Showtime(Movie movie, Theatre theatre, Date time, int availableSeats, double price) {
        this.movie = movie;
        this.theatre = theatre;
        this.time = time;
        this.availableSeats = availableSeats;
        this.price = price;
    }

    public Movie getMovie() {
        return movie;
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Showtime{" +
                "movie=" + movie +
                ", theatre=" + theatre +
                ", time=" + time +
                ", availableSeats=" + availableSeats +
                ", price=" + price +
                '}';
    }
}


