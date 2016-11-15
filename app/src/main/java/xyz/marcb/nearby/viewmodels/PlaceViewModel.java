package xyz.marcb.nearby.viewmodels;

import com.google.android.gms.maps.model.LatLng;

public interface PlaceViewModel {
    String name();
    LatLng location();
}
