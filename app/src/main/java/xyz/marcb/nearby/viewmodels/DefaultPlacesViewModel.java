package xyz.marcb.nearby.viewmodels;

import com.google.android.gms.maps.model.LatLng;
import java.util.List;
import rx.Observable;
import xyz.marcb.places.Location;
import xyz.marcb.places.Place;
import xyz.marcb.places.Places;

public class DefaultPlacesViewModel implements PlacesViewModel {
    private final Places places;

    public DefaultPlacesViewModel(Places places) {
        this.places = places;
    }

    @Override public LatLng initialLocation() {
        return new LatLng(50.719252, -1.842904);
    }

    @Override public Observable<List<Place>> places() {
        return places.near(new Location(50.719252, -1.842904));
    }
}
