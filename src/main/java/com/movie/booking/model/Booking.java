package com.movie.booking.model;

public class Booking {
    private Showtime showtime;
    private int numberOfTickets;

    public Booking(Showtime showtime, int numberOfTickets) {
        this.showtime = showtime;
        this.numberOfTickets = numberOfTickets;
    }

    public Showtime getShowtime() {
        return showtime;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }
}


