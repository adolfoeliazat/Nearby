package xyz.marcb.foursquare;

import java.io.IOException;
import okhttp3.*;
import okhttp3.Response;

public class FoursquareModule implements Interceptor {
    private final String clientId;
    private final String clientSecret;

    public FoursquareModule(String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    @Override public Response intercept(Chain chain) throws IOException {
        final Request original = chain.request();
        final HttpUrl url = original.url().newBuilder()
                .addQueryParameter("client_id", clientId)
                .addQueryParameter("client_secret", clientSecret)
                .addQueryParameter("v", "20130815")
                .build();
        return chain.proceed(original.newBuilder().url(url).build());
    }
}
