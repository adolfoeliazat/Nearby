package xyz.marcb.foursquare.data;

import java.util.List;

public class Explore {
    private Response response;

    public List<Group> groups() {
        return response.groups;
    }

    private static class Response {
        List<Group> groups;
    }
}
