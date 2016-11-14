package xyz.marcb.foursquare;

import android.util.Log;
import org.junit.Test;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.marcb.places.Location;

public class ExampleUnitTest {
    @Test public void test() throws Exception {
        final OkHttpClient httpClient = new OkHttpClient().newBuilder()
                .addInterceptor(new FoursquareModule("4SM44CSMI5P0PA2S3WW3DXJK0OFAKJEJUCCW1USRVTBVKNNT",
                        "4FBGXM13ZBAS3ZV44D3KMIU4H41ER5SS3K555GUZWXUCWYJM")).build();

        final Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient)
                .baseUrl("https://api.foursquare.com/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final FoursquareService foursquareService = retrofit.create(FoursquareService.class);

        final retrofit2.Response<Venues> response = foursquareService.venues(new Location(44.3, 37.2)).execute();
        Log.i("MARC", "" + response.body().response.groups.size());
    }
}