package org.cabbooking.startegy;

import org.cabbooking.exception.IncorrectTripStatusException;
import org.cabbooking.model.Trip;

public interface IFareService {
    void calculateFare(Trip trip) throws IncorrectTripStatusException;
}
