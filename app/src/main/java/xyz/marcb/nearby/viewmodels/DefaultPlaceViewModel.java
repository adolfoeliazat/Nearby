package xyz.marcb.nearby.viewmodels;

import com.google.android.gms.maps.model.LatLng;
import xyz.marcb.places.Place;

final class DefaultPlaceViewModel implements PlaceViewModel {
    private final Place place;

    DefaultPlaceViewModel(Place place) {
        this.place = place;
    }

    @Override public String name() {
        return place.name;
    }

    @Override public LatLng location() {
        return new LatLng(place.location.latitude, place.location.longitude);
    }
}
