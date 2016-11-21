package xyz.marcb.foursquare.data;

import java.util.List;

public class Trending {
    public Response response;

    public static class Response {
        public List<Venue> venues;
    }
}
