package com.movie.booking.repo;

import com.movie.booking.model.Movie;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository is used to load the data from DB
 */
@Repository
public class MovieTicketBookingPlatformRepo {

    public static List<Movie> loadMovies(){
        List<Movie> movies = new ArrayList<>();
        movies.add(Movie.builder()
                        .title("Movie 1")
                        .language("English")
                        .genre("Action")
                        .duration(120)
                .build());

        movies.add(Movie.builder()
                .title("Movie 2")
                .language("Hindi")
                .genre("Drama")
                .duration(150)
                .build());

        return movies;
    }
}
