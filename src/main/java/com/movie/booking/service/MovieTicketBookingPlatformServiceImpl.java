package com.movie.booking.service;

import com.movie.booking.exception.MovieTicketBookingPlatformException;
import com.movie.booking.model.Booking;
import com.movie.booking.model.Movie;
import com.movie.booking.model.Showtime;
import com.movie.booking.model.Theatre;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MovieTicketBookingPlatformServiceImpl implements MovieTicketBookingPlatformService{
    private List<Movie> movies= new ArrayList<>();
    private List<Theatre> theatres= new ArrayList<>();
    private List<Booking> bookings= new ArrayList<>();

    /**
     * Method to add a new Movie
     * @param movie
     */
    @Override
    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    /**
     * Method to onboard a new Theatre
     * @param theatre
     */
    @Override
    public void addTheatre(Theatre theatre) {
        theatres.add(theatre);
    }

    /**
     * Method to Search Movie based on provided
     * City, language, genre
     * @param city
     * @param language
     * @param genre
     * @return
     */
    @Override
    public List<Movie> searchMovies(String city, String language, String genre) {
        List<Movie> result = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.getLanguage().equalsIgnoreCase(language) &&
                    movie.getGenre().equalsIgnoreCase(genre)) {
                result.add(movie);
            }
        }
        return result;
    }

    /**
     * Method is used to search Theatres based on City ,Date and provided Movie
     * @param city
     * @param movie
     * @param date
     * @return
     */
    @Override
    public List<Theatre> searchTheatres(String city, Movie movie, Date date) {
        List<Theatre> result = new ArrayList<>();
        for (Theatre theatre : theatres) {
            List<Showtime> showtimes = theatre.getShowtimes();
            for (Showtime showtime : showtimes) {
                if (showtime.getMovie().equals(movie) &&
                        showtime.getTime().after(date)) {
                    result.add(theatre);
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Method is book Tickets based on provided Show Time and no of tickets
     * @param showtime
     * @param numberOfTickets
     * @return
     */
    @Override
    public Booking bookTickets(Showtime showtime, int numberOfTickets) throws MovieTicketBookingPlatformException {
        if (showtime.getAvailableSeats() >= numberOfTickets) {
            showtime.setAvailableSeats(showtime.getAvailableSeats() - numberOfTickets);
            Booking booking = new Booking(showtime, numberOfTickets);
            bookings.add(booking);
            return booking;
        }
        throw new MovieTicketBookingPlatformException("Not enough seats available.");
        //System.out.println("Not enough seats available.");
        //return null;
    }

    /**
     * To cancel the Booking
     * @param booking
     */
    @Override
    public void cancelBooking(Booking booking) {
        Showtime showtime = booking.getShowtime();
        showtime.setAvailableSeats(showtime.getAvailableSeats() + booking.getNumberOfTickets());
        bookings.remove(booking);
    }

    /**
     * To Book the tickets in Bulk
     * @param showtimes
     * @param numberOfTickets
     * @return
     */
    @Override
    public List<Booking> bulkBookTickets(List<Showtime> showtimes, int numberOfTickets) {
        List<Booking> bulkBookings = new ArrayList<>();
        for (Showtime showtime : showtimes) {
            try {
                Booking booking = bookTickets(showtime, numberOfTickets);
                if (booking != null) {
                    bulkBookings.add(booking);
                }
            }catch (MovieTicketBookingPlatformException e) {
                System.out.println(e.getMessage());
            }

        }
        return bulkBookings;
    }

    /**
     * Cancel bulk booking
     * @param bookings
     */
    @Override
    public void bulkCancelBookings(List<Booking> bookings) {
        for (Booking booking : bookings) {
            cancelBooking(booking);
        }
    }

    /**
     * Logic to apply discount/ offer
     * @param showtime
     */
    @Override
    public void applyDiscounts(Showtime showtime) {
        Movie movie = showtime.getMovie();
        if (theatres.size() > 1) {
            Double discountedVal = showtime.getPrice() * 0.5;
            Double truncatedDouble = BigDecimal.valueOf(discountedVal)
                    .setScale(2, RoundingMode.HALF_UP)
                    .doubleValue();
            showtime.setPrice(truncatedDouble);
        }
        if (showtime.getTime().getHours() >= 12 && showtime.getTime().getHours() <= 17) {
            Double discountedVal = showtime.getPrice() * 0.8;
            Double truncatedDouble = BigDecimal.valueOf(discountedVal)
                    .setScale(2, RoundingMode.HALF_UP)
                    .doubleValue();
            showtime.setPrice(truncatedDouble);
        }
    }
    @Override
    public List<Movie> getMovies() {
        return movies;
    }
    @Override
    public List<Theatre> getTheatres() {
        return theatres;
    }
    @Override
    public List<Booking> getBookings() {
        return bookings;
    }
}
