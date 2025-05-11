package org.cabbooking.manager;

import lombok.Getter;
import org.cabbooking.model.Driver;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DriverManager {

    @Getter
    private static final List<Driver> drivers = new ArrayList<>();

    public void addDriver(Driver driver) {
        if (driver == null)
            return;
        drivers.add(driver);
    }
}
