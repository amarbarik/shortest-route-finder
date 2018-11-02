package za.co.discovery.assessment.shortestroutefinder.dto;

public class TrafficInfoModel extends RouteInformation{

    public TrafficInfoModel() {
    }

    public TrafficInfoModel(String id, String source, String destination, Float weight) {
        super(id, source, destination, weight);
    }
}
