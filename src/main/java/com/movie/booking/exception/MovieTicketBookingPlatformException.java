package com.movie.booking.exception;

/**
 * Custom exception to handle Movie Ticket Booking exceptions
 * @author Shubham Agarwal
 *
 */
public class MovieTicketBookingPlatformException extends Exception {

	private static final long serialVersionUID = -8064375427647152246L;

	public MovieTicketBookingPlatformException(String message) {
		super(message);
	}

}
