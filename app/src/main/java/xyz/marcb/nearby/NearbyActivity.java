package xyz.marcb.nearby;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.List;
import javax.inject.Inject;
import rx.Subscription;
import xyz.marcb.nearby.viewmodels.PlaceViewModel;
import xyz.marcb.nearby.viewmodels.PlacesViewModel;

public class NearbyActivity extends FragmentActivity implements OnMapReadyCallback {
    @Inject PlacesViewModel viewModel;
    private GoogleMap map;
    private Subscription subscription;

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
        subscription = viewModel.places().subscribe(this::setPlaces);
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }

    private void setPlaces(List<PlaceViewModel> places) {
        map.clear();
        for (PlaceViewModel place: places) {
            map.addMarker(new MarkerOptions().position(place.location()).title(place.name()));
        }
    }
}
