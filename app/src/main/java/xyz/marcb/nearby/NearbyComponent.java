package xyz.marcb.nearby;

import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = NearbyModule.class)
interface NearbyComponent {
    void inject(NearbyActivity activity);
}
