package com.movie.booking.service;

import com.movie.booking.exception.MovieTicketBookingPlatformException;
import com.movie.booking.model.Booking;
import com.movie.booking.model.Movie;
import com.movie.booking.model.Showtime;
import com.movie.booking.model.Theatre;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class MovieTicketBookingPlatformServiceTest {

    private MovieTicketBookingPlatformServiceImpl platform;
    private Theatre theatre1;
    private Theatre theatre2;
    private Movie movie1;
    private Movie movie2;
    private Showtime showtime1;
    private Showtime showtime2;

    @BeforeEach
    void setUp() {
        platform = new MovieTicketBookingPlatformServiceImpl();
        theatre1 = new Theatre("Theatre 1", "City 1");
        theatre2 = new Theatre("Theatre 2", "City 2");
        platform.addTheatre(theatre1);
        platform.addTheatre(theatre2);
        movie1 = new Movie("Movie 1", "English", "Action", 120);
        movie2 = new Movie("Movie 2", "Hindi", "Drama", 150);
        platform.addMovie(movie1);
        platform.addMovie(movie2);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 1);
        //calendar.set(2023, Calendar.JUNE, 17, 10, 0);
        Date showtime11 = calendar.getTime();
        Calendar newCalendar = Calendar.getInstance();
        newCalendar.add(Calendar.MINUTE, 1);
        //calendar.set(2023, Calendar.JUNE, 17, 13, 0);
        Date showtime21 = newCalendar.getTime();

        showtime1 = new Showtime(movie1, theatre1, showtime11, 100, 10.0);
        showtime2 = new Showtime(movie2, theatre2, showtime21, 150, 12.0);
        theatre1.addShowtime(showtime1);
        theatre2.addShowtime(showtime2);
    }

    @Test
    void testSearchMovies() {
        List<Movie> searchedMovies = platform.searchMovies("City 1", "English", "Action");
        Assertions.assertEquals(1, searchedMovies.size());
        Assertions.assertEquals("Movie 1", searchedMovies.get(0).getTitle());
    }

    @Test
    void testSearchTheatres() {
        List<Theatre> searchedTheatres = platform.searchTheatres("City 1", movie1, new Date());
        Assertions.assertEquals(1, searchedTheatres.size());
        Assertions.assertEquals("Theatre 1", searchedTheatres.get(0).getName());
    }

    @Test
    void testBookTickets() throws MovieTicketBookingPlatformException {
        Booking booking = platform.bookTickets(showtime1, 2);
        Assertions.assertNotNull(booking);
        Assertions.assertEquals(showtime1, booking.getShowtime());
        Assertions.assertEquals(2, booking.getNumberOfTickets());
        Assertions.assertEquals(98, showtime1.getAvailableSeats());
    }

    @Test()
    void testBookTickets_MoreThanAvailableSeats() throws MovieTicketBookingPlatformException {

        Exception expectedException =
                assertThrows(
                        MovieTicketBookingPlatformException.class,
                        () -> {
                            platform.bookTickets(showtime1, 102);
                        });
        Assertions.assertEquals("Not enough seats available.", expectedException.getMessage());
    }

    @Test
    void testCancelBooking() throws MovieTicketBookingPlatformException {
        Booking booking = platform.bookTickets(showtime1, 2);
        platform.cancelBooking(booking);
        Assertions.assertEquals(100, showtime1.getAvailableSeats());
        Assertions.assertFalse(platform.getBookings().contains(booking));
    }

    @Test
    void testBulkBookTickets() {
        List<Showtime> showtimesToBulkBook = Arrays.asList(showtime1, showtime2);
        List<Booking> bulkBookings = platform.bulkBookTickets(showtimesToBulkBook, 5);
        Assertions.assertEquals(2, bulkBookings.size());
        for (Booking booking : bulkBookings) {
            Assertions.assertEquals(5, booking.getNumberOfTickets());
        }
    }

    @Test
    void testBulkCancelBookings() {
        List<Showtime> showtimesToBulkBook = Arrays.asList(showtime1, showtime2);
        List<Booking> bulkBookings = platform.bulkBookTickets(showtimesToBulkBook, 5);
        platform.bulkCancelBookings(bulkBookings);
        Assertions.assertEquals(100, showtime1.getAvailableSeats());
        Assertions.assertEquals(150, showtime2.getAvailableSeats());
        for (Booking booking : bulkBookings) {
            Assertions.assertFalse(platform.getBookings().contains(booking));
        }
    }

    @Test
    void testApplyDiscounts() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.JUNE, 17, 10, 0);
        showtime1.setTime(calendar.getTime());
        platform.applyDiscounts(showtime1);
        Assertions.assertEquals(5.0, showtime1.getPrice());
        calendar.set(2023, Calendar.JUNE, 17, 13, 0);
        showtime2.setTime(calendar.getTime());
        platform.applyDiscounts(showtime2);
        Assertions.assertEquals(4.8, showtime2.getPrice());
    }
}