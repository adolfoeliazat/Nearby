package xyz.marcb.nearby;

import javax.inject.Singleton;
import dagger.Component;
import xyz.marcb.foursquare.FoursquareModule;

@Singleton
@Component(modules = { NearbyModule.class, FoursquareModule.class})
interface NearbyComponent {
    void inject(NearbyActivity activity);
}
