package xyz.marcb.foursquare.data;

import java.util.List;

public class Group {
    private List<Item> items;

    public List<Item> items() {
        return items;
    }

    public static class Item {
        public Venue venue;
    }
}
