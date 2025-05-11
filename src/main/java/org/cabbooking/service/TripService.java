package org.cabbooking.service;

import org.cabbooking.manager.TripManager;
import org.cabbooking.model.Driver;
import org.cabbooking.model.Rider;
import org.cabbooking.model.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripService {
    private final TripManager tripManager;

    @Autowired
    public TripService(TripManager tripManager) {
        this.tripManager = tripManager;
    }

    public List<Trip> getTrips() {
        return tripManager.getTrips();
    }

    public List<Trip> getTrips(Rider rider) {
        return tripManager.getTrips(rider);
    }

    public List<Trip> getTrips(Driver driver) {
        return tripManager.getTrips(driver);
    }
}
