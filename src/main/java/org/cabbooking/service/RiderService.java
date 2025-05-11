package org.cabbooking.service;

import org.cabbooking.manager.RiderManager;
import org.cabbooking.manager.TripManager;
import org.cabbooking.model.Location;
import org.cabbooking.model.Rider;
import org.cabbooking.model.Trip;
import org.cabbooking.model.TripStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiderService {

    private final RiderManager riderManager;
    private final TripManager tripManager;

    @Autowired
    public RiderService(RiderManager riderManager, TripManager tripManager) {
        this.riderManager = riderManager;
        this.tripManager = tripManager;
    }

    public Rider registerRider(String name) {
        Rider rider = Rider.builder()
                .name(name)
                .build();
        riderManager.addRider(rider);
        System.out.println("Rider created with ID: " + rider.getId());
        return rider;
    }

    public Trip createTrip(Rider rider, Location startLocation, Location endLocation) {
        Trip trip = Trip.builder()
                .startLocation(startLocation)
                .endLocation(endLocation)
                .rider(rider)
                .tripStatus(TripStatus.PENDING)
                .build();

        rider.setCurrentTripId(trip.getTripId());
        tripManager.addTrip(trip);

        System.out.println("Trip created with ID: " + trip.getTripId());
        return trip;
    }

    public List<Rider> getAllRiders() {
        return RiderManager.getRiders();
    }
}
