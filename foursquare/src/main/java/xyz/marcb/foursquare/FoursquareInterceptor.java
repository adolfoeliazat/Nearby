package xyz.marcb.foursquare;

import java.io.IOException;
import okhttp3.*;
import okhttp3.Response;

public class FoursquareInterceptor implements Interceptor {
    private final String clientId;
    private final String clientSecret;
    private final String version;

    public FoursquareInterceptor(String clientId, String clientSecret, String version) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.version = version;
    }

    @Override public Response intercept(Chain chain) throws IOException {
        final Request original = chain.request();
        final HttpUrl url = original.url().newBuilder()
                .addQueryParameter("client_id", clientId)
                .addQueryParameter("client_secret", clientSecret)
                .addQueryParameter("v", version)
                .build();
        return chain.proceed(original.newBuilder().url(url).build());
    }
}
