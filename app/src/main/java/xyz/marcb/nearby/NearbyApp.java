package xyz.marcb.nearby;

import android.app.Application;

public class NearbyApp extends Application {
    private NearbyComponent component;

    @Override public void onCreate() {
        super.onCreate();
        component = DaggerNearbyComponent.builder().build();
    }

    public NearbyComponent getComponent() {
        return component;
    }
}
