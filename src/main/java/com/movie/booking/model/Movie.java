package com.movie.booking.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
@Getter
public class Movie {
    private String title;
    private String language;
    private String genre;
    private int duration;

    public Movie(String title, String language, String genre, int duration) {
        this.title = title;
        this.language = language;
        this.genre = genre;
        this.duration = duration;
    }

}


