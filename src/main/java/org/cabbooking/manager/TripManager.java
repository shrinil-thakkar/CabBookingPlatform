package org.cabbooking.manager;

import org.cabbooking.model.Driver;
import org.cabbooking.model.Rider;
import org.cabbooking.model.Trip;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Repository
public class TripManager {
    private static final HashMap<UUID, Trip> trips = new HashMap<>();

    public void addTrip(Trip trip) {
        if (trip == null)
            return;
        trips.put(trip.getTripId(), trip);
    }

    public List<Trip> getTrips() {
        return trips.values().stream().toList();
    }

    public List<Trip> getTrips(Rider rider) {
        return trips.values().stream()
                .filter(trip -> trip.getRider().equals(rider))
                .toList();
    }

    public List<Trip> getTrips(Driver driver) {
        if (driver == null)
            return null;
        return trips.values().stream()
                .filter(trip -> trip.getDriver() != null && trip.getDriver().equals(driver))
                .toList();
    }
}
