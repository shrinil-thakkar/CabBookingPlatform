package org.cabbooking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class Driver {
    @Builder.Default
    private UUID id = UUID.randomUUID();

    @NonNull
    private String name;

    @NonNull
    private Vehicle vehicle;

    private UUID currentTripId;

    @Builder.Default
    private Boolean isAvailable = true;

    public void notify(Trip trip) {
        if (trip == null) {
            return;
        }
        System.out.println("Driver " + name + " has been notified about the trip: " + trip.getTripId());
    }
}
