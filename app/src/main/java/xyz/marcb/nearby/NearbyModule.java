package xyz.marcb.nearby;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import xyz.marcb.nearby.stubs.StubPlaces;
import xyz.marcb.nearby.viewmodel.DefaultPlacesViewModel;
import xyz.marcb.nearby.viewmodel.PlacesViewModel;
import xyz.marcb.places.Places;

@Module abstract class NearbyModule {

    @Provides static PlacesViewModel placesViewModel(Places places) {
        return new DefaultPlacesViewModel(places);
    }

    @Provides @Singleton static Places places() {
        return new StubPlaces();
    }
}
