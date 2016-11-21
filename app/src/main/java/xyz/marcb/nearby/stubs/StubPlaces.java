package xyz.marcb.nearby.stubs;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import rx.Observable;
import xyz.marcb.places.Location;
import xyz.marcb.places.Place;
import xyz.marcb.places.Places;

public class StubPlaces implements Places {
    @Override public Observable<List<Place>> near(Location location) {
        return Observable.just(Arrays.asList(
                new Place("Marc's Bistro", new Location(50.719252, -1.842904)),
                new Place("Luigi's Pizza", new Location(50.720056, -1.840894))
        ));
    }

    @Override public Observable<List<Place>> trendingNear(Location location) {
        return Observable.just(Collections.singletonList(
                new Place("Luigi's Pizza", new Location(50.720056, -1.840894))
        ));
    }
}
