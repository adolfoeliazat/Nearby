package xyz.marcb.foursquare;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.marcb.places.Places;

@Module public abstract class FoursquareModule {
    private static final String CLIENT_ID = "4SM44CSMI5P0PA2S3WW3DXJK0OFAKJEJUCCW1USRVTBVKNNT";
    private static final String CLIENT_SECRET = "4FBGXM13ZBAS3ZV44D3KMIU4H41ER5SS3K555GUZWXUCWYJM";
    private static final String VERSION = "20130815";

    @Provides @Singleton static Interceptor interceptor() {
        return new FoursquareInterceptor(CLIENT_ID, CLIENT_SECRET, VERSION);
    }

    @Provides @Singleton static OkHttpClient okHttpClient(Interceptor interceptor) {
        return new OkHttpClient().newBuilder()
                .addInterceptor(interceptor)
                .build();
    }

    @Provides @Singleton static Retrofit retrofit(OkHttpClient httpClient) {
        return new Retrofit.Builder()
                .client(httpClient)
                .baseUrl("https://api.foursquare.com/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Provides @Singleton static FoursquareService foursquareService(Retrofit retrofit) {
        return retrofit.create(FoursquareService.class);
    }

    @Provides @Singleton static Places places(FoursquareService foursquareService) {
        return new FoursquarePlacesAdapter(foursquareService);
    }
}
