package xyz.marcb.foursquare;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import xyz.marcb.foursquare.data.Venues;
import xyz.marcb.places.Location;

public interface FoursquareService {
    @GET("venues/explore") Observable<Venues> venues(@Query("ll") Location location);
}
