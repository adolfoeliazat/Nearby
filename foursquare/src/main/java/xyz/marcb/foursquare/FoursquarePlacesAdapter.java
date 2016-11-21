package xyz.marcb.foursquare;

import java.util.ArrayList;
import java.util.List;
import rx.Observable;
import xyz.marcb.foursquare.data.Group;
import xyz.marcb.foursquare.data.Venue;
import xyz.marcb.foursquare.data.Explore;
import xyz.marcb.places.Location;
import xyz.marcb.places.Place;
import xyz.marcb.places.Places;

final class FoursquarePlacesAdapter implements Places {
    private final FoursquareService foursquareService;

    FoursquarePlacesAdapter(FoursquareService foursquareService) {
        this.foursquareService = foursquareService;
    }

    @Override public Observable<List<Place>> near(Location location) {
        return foursquareService.explore(location).map(this::convert);
    }

    @Override public Observable<List<Place>> trendingNear(Location location) {
        return foursquareService.trending(location).map( it -> convert(it.response.venues));
    }

    private List<Place> convert(Explore explore) {
        final List<Place> places = new ArrayList<>();
        for (Group group : explore.groups()) {
            for (Group.Item item : group.items()) {
                places.add(convert(item.venue));
            }
        }
        return places;
    }

    private List<Place> convert(List<Venue> venues) {
        final List<Place> places = new ArrayList<>();
        for (Venue venue : venues) {
            places.add(convert(venue));
        }
        return places;
    }

    private Place convert(Venue venue) {
        return new Place(venue.name, new Location(venue.location.lat, venue.location.lng));
    }
}
