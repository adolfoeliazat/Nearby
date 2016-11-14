package xyz.marcb.foursquare;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import xyz.marcb.places.Location;

public interface FoursquareService {

    @GET("venues/explore")
    Call<Venues> venues(@Query("ll") Location location);
}
