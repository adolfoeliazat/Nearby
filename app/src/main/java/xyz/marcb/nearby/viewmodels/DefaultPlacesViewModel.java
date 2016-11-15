package xyz.marcb.nearby.viewmodels;

import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;
import xyz.marcb.places.Location;
import xyz.marcb.places.Place;
import xyz.marcb.places.Places;

public final class DefaultPlacesViewModel implements PlacesViewModel {
    private final Places places;
    private final Location currentLocation;

    public DefaultPlacesViewModel(Places places) {
        this.places = places;
        this.currentLocation = new Location(50.719252, -1.842904);
    }

    @Override public LatLng initialLocation() {
        return new LatLng(currentLocation.latitude, currentLocation.longitude);
    }

    @Override public Observable<List<PlaceViewModel>> places() {
        return places.near(currentLocation).map(this::convert);
    }

    private List<PlaceViewModel> convert(List<Place> places) {
        final List<PlaceViewModel> viewModels = new ArrayList<>();
        for (Place place : places) {
            viewModels.add(new DefaultPlaceViewModel(place));
        }
        return viewModels;
    }
}
