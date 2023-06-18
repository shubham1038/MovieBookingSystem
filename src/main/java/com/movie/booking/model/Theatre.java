package com.movie.booking.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Theatre {
    private String name;
    private String city;
    private List<Showtime> showtimes;

    public Theatre(String name, String city) {
        this.name = name;
        this.city = city;
        this.showtimes = new ArrayList<>();
    }


    public void addShowtime(Showtime showtime) {
        showtimes.add(showtime);
    }

    public void updateShowtime(Showtime showtime, Date newTime) {
        showtime.setTime(newTime);
    }

    public void deleteShowtime(Showtime showtime) {
        showtimes.remove(showtime);
    }

    public void allocateSeatInventory(Showtime showtime, int availableSeats) {
        showtime.setAvailableSeats(availableSeats);
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public List<Showtime> getShowtimes() {
        return showtimes;
    }

    @Override
    public String toString() {
        return "Theatre{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", showtimes=" + showtimes +
                '}';
    }
}


