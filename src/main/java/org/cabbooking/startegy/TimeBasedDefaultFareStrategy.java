package org.cabbooking.startegy;

import org.cabbooking.exception.IncorrectTripStatusException;
import org.cabbooking.model.Trip;
import org.cabbooking.model.TripStatus;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class TimeBasedDefaultFareStrategy extends DefaultFareStrategy {
    public void calculateFare(Trip trip) throws IncorrectTripStatusException {
        if (!trip.getTripStatus().equals(TripStatus.COMPLETED)) {
            throw new IncorrectTripStatusException("Trip is not completed yet.");
        }
        Duration duration = Duration.between(trip.getStartTime(), trip.getEndTime());
        trip.setFare(duration.toMinutes() * 3);

        System.out.println("Fare for the trip: " + trip.getTripId() + " is: Rs." + trip.getFare());
    }
}
