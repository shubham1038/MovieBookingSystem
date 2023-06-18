package com.movie.booking;

import com.movie.booking.exception.MovieTicketBookingPlatformException;
import com.movie.booking.handler.MovieTicketBookingPlatformHandler;
import com.movie.booking.service.MovieTicketBookingPlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Main class. Create spring container to start program which will
 * internally run the logic for Movie ticket booking system
 * @author Shubham Agarwal
 *
 */
@Configuration
@ComponentScan(basePackages = "com.movie.booking.*")
public class ClientApp {

    @Autowired
    MovieTicketBookingPlatformHandler movieTicketBookingPlatformHandler;
    @Autowired
    MovieTicketBookingPlatformService movieTicketBookingPlatformService;
    public static void main(String[] args) {

        try(AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ClientApp.class)) {
            ClientApp client = context.getBean(ClientApp.class);
            client.start();
        } catch (MovieTicketBookingPlatformException | IllegalAccessException e) {
            System.out.println("There was an error while booking movie ticket :" + e.getLocalizedMessage());
        }
    }
    public void start() throws MovieTicketBookingPlatformException, IllegalAccessException {

        movieTicketBookingPlatformHandler.handle();

    }
}