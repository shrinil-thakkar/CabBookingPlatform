package org.cabbooking.manager;

import lombok.Getter;
import org.cabbooking.model.Rider;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RiderManager {
    @Getter
    private static final List<Rider> riders = new ArrayList<>();

    public void addRider(Rider rider) {
        riders.add(rider);
    }
}
