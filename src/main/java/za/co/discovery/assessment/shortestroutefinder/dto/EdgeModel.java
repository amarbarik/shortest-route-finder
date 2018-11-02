package za.co.discovery.assessment.shortestroutefinder.dto;

public class EdgeModel extends RouteInformation {

    public EdgeModel() {
    }

    public EdgeModel(String id, String source, String destination, Float weight) {
        super(id, source, destination, weight);
    }
}
