package xyz.marcb.places;

import java.util.List;
import rx.Observable;

public interface Places {
    Observable<List<Place>> near(Location location);
    Observable<List<Place>> trendingNear(Location location);
}
