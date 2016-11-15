package xyz.marcb.nearby.viewmodels;

import com.google.android.gms.maps.model.LatLng;
import java.util.List;
import rx.Observable;

public interface PlacesViewModel {
    LatLng initialLocation();
    Observable<List<PlaceViewModel>> places();
}
