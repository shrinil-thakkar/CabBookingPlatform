package org.cabbooking.startegy;

import org.cabbooking.exception.IncorrectTripStatusException;
import org.cabbooking.model.Trip;
import org.cabbooking.model.TripStatus;

public class DefaultFareStrategy implements IFareService {
    public void calculateFare(Trip trip) throws IncorrectTripStatusException {
        if (!trip.getTripStatus().equals(TripStatus.COMPLETED)) {
            throw new IncorrectTripStatusException("Trip is not completed yet.");
        }
        trip.setFare(100L);

        System.out.println("Fare for the trip: " + trip + " is: " + trip.getFare());
    }
}
