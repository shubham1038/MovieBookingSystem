package com.movie.booking.service;

import com.movie.booking.exception.MovieTicketBookingPlatformException;
import com.movie.booking.model.Booking;
import com.movie.booking.model.Movie;
import com.movie.booking.model.Showtime;
import com.movie.booking.model.Theatre;

import java.util.Date;
import java.util.List;


public interface MovieTicketBookingPlatformService {

    /**
     * Method to add a new Movie
     * @param movie
     */
    public void addMovie(Movie movie) ;

    /**
     * Method to onboard a new Theatre
     * @param theatre
     */
    public void addTheatre(Theatre theatre) ;

    /**
     * Method to Search Movie based on provided
     * City, language, genre
     * @param city
     * @param language
     * @param genre
     * @return List<Movie>
     */
    public List<Movie> searchMovies(String city, String language, String genre);

    /**
     * Method is used to search Theatres based on City ,Date and provided Movie
     * @param city
     * @param movie
     * @param date
     * @return List<Theatre>
     */
    public List<Theatre> searchTheatres(String city, Movie movie, Date date);

    /**
     * Method is book Tickets based on provided Show Time and no of tickets
     * @param showtime
     * @param numberOfTickets
     * @return
     */
    public Booking bookTickets(Showtime showtime, int numberOfTickets) throws MovieTicketBookingPlatformException;

    /**
     * To cancel the Booking
     * @param booking
     */
    public void cancelBooking(Booking booking);

    /**
     * To Book the tickets in Bulk
     * @param showtimes
     * @param numberOfTickets
     * @return
     */
    public List<Booking> bulkBookTickets(List<Showtime> showtimes, int numberOfTickets);

    /**
     * To Cancel bulk booking
     * @param bookings
     */
    public void bulkCancelBookings(List<Booking> bookings);

    /**
     * To apply discount/ offer
     * @param showtime
     */
    public void applyDiscounts(Showtime showtime);

    public List<Movie> getMovies();

    public List<Theatre> getTheatres();

    public List<Booking> getBookings();

}


