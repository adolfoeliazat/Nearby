package xyz.marcb.nearby;

import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = NearbyModule.class)
public interface NearbyComponent {
    void inject(NearbyActivity activity);
}
