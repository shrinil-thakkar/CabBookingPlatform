package org.cabbooking;

import org.cabbooking.model.Driver;
import org.cabbooking.model.Location;
import org.cabbooking.model.Rider;
import org.cabbooking.model.Trip;
import org.cabbooking.model.VehicleType;
import org.cabbooking.service.DriverService;
import org.cabbooking.service.RiderService;
import org.cabbooking.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Main implements CommandLineRunner {

    @Autowired
    private RiderService riderService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private TripService tripService;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    public void run(String... args) throws Exception {
        Rider rider1 = riderService.registerRider("John Doe");

        Location location = Location.builder().x(1).y(1).build();
        Driver driver1 = driverService.registerDriver("Jane Smith", "ABC123", VehicleType.SUV, location);

        Trip trip1 = riderService.createTrip(rider1, location, location);

        List<Rider> riderList = riderService.getAllRiders();
        System.out.println("\nRiders: " + riderList);

        List<Driver> driverList = driverService.getAllAvailableDrivers();
        System.out.println("\nDrivers: " + driverList);

        List<Trip> tripList = tripService.getTrips();
        System.out.println("\nTrips: " + tripList);

        driverService.notifyDrivers(trip1);

        System.out.println("\nAccepting trip...\n");
        driverService.acceptTrip(trip1, driver1);
        System.out.println(trip1);

        System.out.println("\nStarting trip...\n");
        driverService.startTrip(trip1, driver1);
        System.out.println(trip1);

        System.out.println("\nEnding trip...\n");
        driverService.endTrip(trip1, driver1);
        System.out.println(trip1);

        List<Trip> tripsForRider1 = tripService.getTrips(rider1);
        System.out.println("\nTrips for Rider 1: " + tripsForRider1);

        List<Trip> tripsForDriver1 = tripService.getTrips(driver1);
        System.out.println("\nTrips for Driver 1: " + tripsForDriver1);
    }
}