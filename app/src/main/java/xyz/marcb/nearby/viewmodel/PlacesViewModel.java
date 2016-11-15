package xyz.marcb.nearby.viewmodel;

import com.google.android.gms.maps.model.LatLng;
import java.util.List;
import rx.Observable;
import xyz.marcb.places.Place;

public interface PlacesViewModel {
    LatLng initialLocation();
    Observable<List<Place>> places();
}
