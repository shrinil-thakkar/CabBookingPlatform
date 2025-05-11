package org.cabbooking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
@AllArgsConstructor
public class Location {
    @NonNull
    private Integer x;

    @NonNull
    private Integer y;
}
