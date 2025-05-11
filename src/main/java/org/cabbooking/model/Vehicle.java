package org.cabbooking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
@AllArgsConstructor
public class Vehicle {
    private VehicleType vehicleType;

    @NonNull
    private String licensePlate;

    @NonNull
    private Location location;
}
