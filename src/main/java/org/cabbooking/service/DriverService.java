package org.cabbooking.service;

import org.cabbooking.exception.DriverNotAssignedException;
import org.cabbooking.exception.DriverNotAvailableException;
import org.cabbooking.exception.IncorrectDriverException;
import org.cabbooking.exception.IncorrectTripStatusException;
import org.cabbooking.manager.DriverManager;
import org.cabbooking.model.Driver;
import org.cabbooking.model.Location;
import org.cabbooking.model.Trip;
import org.cabbooking.model.TripStatus;
import org.cabbooking.model.Vehicle;
import org.cabbooking.model.VehicleType;
import org.cabbooking.startegy.DefaultFareStrategy;
import org.cabbooking.startegy.TimeBasedDefaultFareStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DriverService {
    private final DriverManager driverManager;
    private final DefaultFareStrategy defaultFareStrategy;

    @Autowired
    public DriverService(DriverManager driverManager, TimeBasedDefaultFareStrategy fareService) {
        this.driverManager = driverManager;
        this.defaultFareStrategy = fareService;
    }

    public Driver registerDriver(String name, String licenseNumber, VehicleType vehicleType, Location location) {
        Vehicle vehicle = Vehicle.builder()
                .licensePlate(licenseNumber)
                .vehicleType(vehicleType)
                .location(location)
                .build();

        Driver driver = Driver.builder()
                .name(name)
                .vehicle(vehicle)
                .build();

        driverManager.addDriver(driver);

        System.out.println("Driver created with ID: " + driver.getId());
        return driver;
    }

    public void notifyDrivers(Trip trip) {
        List<Driver> driverList = DriverManager.getDrivers();

        for (Driver driver: driverList) {
            if (driver.getIsAvailable())
                driver.notify(trip);
        }
    }

    public void acceptTrip(Trip trip, Driver driver) throws DriverNotAvailableException {
        if (!driver.getIsAvailable()) {
            throw new DriverNotAvailableException();
        }

        trip.setTripStatus(TripStatus.ACCEPTED);
        trip.setDriver(driver);
        driver.setCurrentTripId(trip.getTripId());
    }

    public void startTrip(Trip trip, Driver driver) throws Exception {
        if (trip.getTripStatus() != TripStatus.ACCEPTED) {
            throw new IncorrectTripStatusException("Trip is not accepted yet.");
        }
        if (trip.getDriver() == null) {
            throw new DriverNotAssignedException();
        }
        if (!trip.getDriver().equals(driver)) {
            throw new IncorrectDriverException("This is not the driver assigned to the trip");
        }

        trip.setTripStatus(TripStatus.IN_PROGRESS);
        trip.setStartTime(LocalDateTime.now());
    }

    public void endTrip(Trip trip, Driver driver) throws Exception{
        if (trip.getTripStatus() != TripStatus.IN_PROGRESS) {
            throw new IncorrectTripStatusException("Trip is not in progress yet.");
        }
        if (!trip.getDriver().equals(driver)) {
            throw new IncorrectDriverException("This is not the driver assigned to the trip");
        }

        trip.setTripStatus(TripStatus.COMPLETED);
        trip.setEndTime(LocalDateTime.now().plusMinutes(30));

        defaultFareStrategy.calculateFare(trip);
    }

    public void makeAvailable(Driver driver) {
        driver.setIsAvailable(true);
    }

    public void makeUnavailable(Driver driver) {
        driver.setIsAvailable(false);
    }

    public List<Driver> getAllAvailableDrivers() {
        return DriverManager.getDrivers().stream()
                .filter(Driver::getIsAvailable)
                .toList();
    }
}
