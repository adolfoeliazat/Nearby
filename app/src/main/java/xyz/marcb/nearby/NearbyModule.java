package xyz.marcb.nearby;

import dagger.Module;
import dagger.Provides;
import xyz.marcb.nearby.viewmodels.DefaultPlacesViewModel;
import xyz.marcb.nearby.viewmodels.PlacesViewModel;
import xyz.marcb.places.Places;

@Module abstract class NearbyModule {

    @Provides static PlacesViewModel placesViewModel(Places places) {
        return new DefaultPlacesViewModel(places);
    }
}
