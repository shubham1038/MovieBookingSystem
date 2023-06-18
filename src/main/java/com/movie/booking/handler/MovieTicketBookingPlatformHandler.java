package com.movie.booking.handler;

import com.movie.booking.exception.MovieTicketBookingPlatformException;
import com.movie.booking.model.Booking;
import com.movie.booking.model.Movie;
import com.movie.booking.model.Showtime;
import com.movie.booking.model.Theatre;
import com.movie.booking.service.MovieTicketBookingPlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Class to handle movie booking/cancel/bulk booking/canceling functionality
 */
@Component
public class MovieTicketBookingPlatformHandler {

    @Autowired
    MovieTicketBookingPlatformService movieTicketBookingPlatformService;

    public void handle() throws IllegalAccessException, MovieTicketBookingPlatformException {
        // Onboard theatre partners
        Theatre theatre1 = new Theatre("Theatre 1", "City 1");
        Theatre theatre2 = new Theatre("Theatre 2", "City 2");
        movieTicketBookingPlatformService.addTheatre(theatre1);
        movieTicketBookingPlatformService.addTheatre(theatre2);

        // Add movies to the platform
        Movie movie1 = new Movie("Movie 1", "English", "Action", 120);
        Movie movie2 = new Movie("Movie 2", "Hindi", "Drama", 150);
        movieTicketBookingPlatformService.addMovie(movie1);
        movieTicketBookingPlatformService.addMovie(movie2);

        // Create showtimes for theatres
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 1);
        Date showtime11 = calendar.getTime();
        Calendar newCalendar = Calendar.getInstance();
        newCalendar.add(Calendar.MINUTE, 1);
        Date showtime21 = newCalendar.getTime();
        Showtime showtime1 = new Showtime(movie1, theatre1, showtime11, 100, 10.0);
        Showtime showtime2 = new Showtime(movie2, theatre2, showtime21, 150, 12.0);
        theatre1.addShowtime(showtime1);
        theatre2.addShowtime(showtime2);

        // Search movies
        searchMovies();
        // Search theatres
        searchTheatres(movie1);

        // Book movie tickets
        Booking booking = bookMovieTickets(showtime1);

        // Cancel booking
        movieTicketBookingPlatformService.cancelBooking(booking);

        // Bulk booking and cancellation
        List<Booking> bulkBookings = bulkBookTickets(showtime1, showtime2);

        movieTicketBookingPlatformService.bulkCancelBookings(bulkBookings);

        // Theatres can create, update, and delete shows for the day
        theatre1.addShowtime(showtime1);
        theatre1.updateShowtime(showtime1, new Date());
        theatre1.deleteShowtime(showtime1);

        // Theatres can allocate seat inventory and update them for the show
        theatre1.allocateSeatInventory(showtime1, 100);
        movieTicketBookingPlatformService.applyDiscounts(showtime1);
    }

    /**
     * Method to Search Movie based on provided
     * City, language, genre
     */
    private void searchMovies() {
        List<Movie> searchedMovies = movieTicketBookingPlatformService.searchMovies("City 1", "English", "Action");
        System.out.printf("--------------------------------%n");
        System.out.printf(" Search Movies across different cities, languages         %n");

        System.out.printf("--------------------------------%n");
        System.out.printf("| %-10s |%n ", "Movie");
        for (Movie movie : searchedMovies) {
            System.out.printf("| %-10s |%n", movie.getTitle());
        }
    }

    /**
     * Method is used to search Theatres based on City and provided Movie
     * @param movie1
     * @throws IllegalAccessException
     */
    private void searchTheatres(Movie movie1) throws IllegalAccessException {
        List<Theatre> searchedTheatres = movieTicketBookingPlatformService.searchTheatres("City 1", movie1, new Date());

        System.out.printf("--------------------------------%n");
        System.out.printf(" Search Theatres         %n");

        System.out.printf("--------------------------------%n");
        System.out.printf("| %-10s | %-8s |%n", "Theatre Name", "Theatre City");
        System.out.printf("--------------------------------%n");
        for (Theatre theatre : searchedTheatres) {
            System.out.printf("| %-10s | %-8s |%n", theatre.getName(), theatre.getCity());
            theatre.getShowtimes().stream().forEach(x-> {
                try {
                    System.out.println("Show Time -" + formatDates(x.getTime()));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    /**
     * Method to book the Movies in Bulk
     * @param showtime1
     * @param showtime2
     * @return List<Booking>
     * @throws IllegalAccessException
     */
    private List<Booking> bulkBookTickets(Showtime showtime1, Showtime showtime2) throws IllegalAccessException {
        List<Showtime> showTimesToBulkBook = new ArrayList<>();
        showTimesToBulkBook.add(showtime1);
        showTimesToBulkBook.add(showtime2);

        List<Booking> bulkBookings = movieTicketBookingPlatformService.bulkBookTickets(showTimesToBulkBook, 5);
        if(bulkBookings.size() >0){
            System.out.printf("--------------------------------%n");
            System.out.printf(" Bulk Booking Done successfully!         %n");

            System.out.printf("--------------------------------%n");
            System.out.printf("| %-10s | %-8s | %4s | %4s |%n", "Movie", "Theatre", "Number of Tickets", "Time");
            bulkBookings.stream().forEach(newBooking->{
                if (newBooking != null) {
                    try {
                        System.out.printf("| %-10s | %-8s | %04d | %-8s |%n", newBooking.getShowtime().getMovie().getTitle(), newBooking.getShowtime().getTheatre().getName(),
                                newBooking.getNumberOfTickets(), formatDates(newBooking.getShowtime().getTime()));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    System.out.println("Error: Not enough seats available for Movie --> " + newBooking.getShowtime().getMovie().getTitle());
                }

            });
        }else {
            System.out.println("Error: Not enough seats available for Bulk Booking.");
        }
        System.out.println(" -----------Bulk Booking Done successfully!---------");
        return bulkBookings;
    }

    /**
     * Methos to book the Movie
     * @param showtime1
     * @return Booking
     * @throws MovieTicketBookingPlatformException
     * @throws IllegalAccessException
     */
    private Booking bookMovieTickets(Showtime showtime1) throws MovieTicketBookingPlatformException, IllegalAccessException {
        Booking booking = movieTicketBookingPlatformService.bookTickets(showtime1, 3);
        if (booking != null) {
            System.out.printf("--------------------------------%n");
            System.out.printf(" Tickets booked successfully!         %n");

            System.out.printf("--------------------------------%n");
            System.out.printf("| %-10s | %-8s | %4s | %4s |%n", "Movie", "Theatre", "Number of Tickets", "Time");
            System.out.printf("--------------------------------%n");
            System.out.printf("| %-10s | %-8s | %04d | %-8s |%n", booking.getShowtime().getMovie().getTitle(), booking.getShowtime().getTheatre().getName(),
                    booking.getNumberOfTickets(), formatDates(booking.getShowtime().getTime()));
        } else {
            System.out.println("Error: Not enough seats available.");
            throw new MovieTicketBookingPlatformException("Error: Not enough seats available.");
        }
        return booking;
    }

    public String formatDates(Date date) throws IllegalAccessException {
        if(date==null) {
            throw new IllegalAccessException("Invalid Date");
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return dateFormat.format(date);

    }
}
