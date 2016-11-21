package xyz.marcb.foursquare;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import xyz.marcb.foursquare.data.Trending;
import xyz.marcb.foursquare.data.Explore;
import xyz.marcb.places.Location;

public interface FoursquareService {
    @GET("venues/explore") Observable<Explore> explore(@Query("ll") Location location);
    @GET("venues/trending") Observable<Trending> trending(@Query("ll") Location location);
}
