package xyz.marcb.foursquare;

import java.util.ArrayList;
import java.util.List;
import rx.Observable;
import xyz.marcb.foursquare.data.Group;
import xyz.marcb.foursquare.data.Venue;
import xyz.marcb.foursquare.data.Venues;
import xyz.marcb.places.Location;
import xyz.marcb.places.Place;
import xyz.marcb.places.Places;

final class FoursquarePlacesAdapter implements Places {
    private final FoursquareService foursquareService;

    FoursquarePlacesAdapter(FoursquareService foursquareService) {
        this.foursquareService = foursquareService;
    }

    @Override public Observable<List<Place>> near(Location location) {
        return foursquareService.venues(location).map(this::convert);
    }

    private List<Place> convert(Venues venues) {
        final List<Place> places = new ArrayList<>();
        for (Group group : venues.groups()) {
            for (Group.Item item : group.items()) {
                final Venue venue = item.venue;
                places.add(new Place(venue.name, new Location(venue.location.lat, venue.location.lng)));
            }
        }
        return places;
    }
}
