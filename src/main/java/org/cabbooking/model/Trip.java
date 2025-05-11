package org.cabbooking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class Trip {
    @Builder.Default
    private UUID tripId = UUID.randomUUID();

    private Driver driver;

    @NonNull
    private Rider rider;

    @Builder.Default
    private TripStatus tripStatus = TripStatus.PENDING;

    @NonNull
    private Location startLocation;

    @NonNull
    private Location endLocation;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @Builder.Default
    private Long fare = 0L;

    @Override
    public String toString() {
        return "Trip {" +
                "driver=" + (driver != null ? driver.getName() : null) +
                ", rider=" + rider.getName() +
                ", tripStatus=" + tripStatus +
                ", startLocation=" + startLocation +
                ", endLocation=" + endLocation +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", fare=" + fare +
                '}';
    }
}
