package org.cabbooking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class Rider {
    @Builder.Default
    private UUID id = UUID.randomUUID();

    @NonNull
    private String name;

    private UUID currentTripId;
}
