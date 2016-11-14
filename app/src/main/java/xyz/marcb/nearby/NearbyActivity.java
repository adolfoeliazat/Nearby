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
import rx.functions.Action1;
import xyz.marcb.nearby.stubs.StubPlaces;
import xyz.marcb.places.Location;
import xyz.marcb.places.Place;
import xyz.marcb.places.Places;

public class NearbyActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap map;
    private Places places;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby);
        places = new StubPlaces();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(50.719252, -1.842904)));
        map.moveCamera(CameraUpdateFactory.zoomTo(15));

        places.near(new Location(50.719252, -1.842904)).subscribe(new Action1<List<Place>>() {
            @Override public void call(List<Place> places) {
                map.clear();
                for (Place place: places) {
                    final LatLng location = new LatLng(place.location.latitude, place.location.longitude);
                    map.addMarker(new MarkerOptions().position(location).title(place.name));
                }
            }
        });
    }
}
