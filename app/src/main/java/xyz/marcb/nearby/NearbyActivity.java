package xyz.marcb.nearby;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.List;
import javax.inject.Inject;
import rx.functions.Action1;
import xyz.marcb.nearby.viewmodels.PlacesViewModel;
import xyz.marcb.places.Place;

public class NearbyActivity extends FragmentActivity implements OnMapReadyCallback {

    @Inject PlacesViewModel viewModel;
    private GoogleMap map;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby);
        ((NearbyApp) getApplication()).getComponent().inject(this);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.moveCamera(CameraUpdateFactory.newLatLng(viewModel.initialLocation()));
        map.moveCamera(CameraUpdateFactory.zoomTo(15));

        viewModel.places().subscribe(new Action1<List<Place>>() {
            @Override public void call(List<Place> places) {
                setPlaces(places);
            }
        });
    }

    private void setPlaces(List<Place> places) {
        map.clear();
        for (Place place: places) {
            final LatLng location = new LatLng(place.location.latitude, place.location.longitude);
            map.addMarker(new MarkerOptions().position(location).title(place.name));
        }
    }
}
