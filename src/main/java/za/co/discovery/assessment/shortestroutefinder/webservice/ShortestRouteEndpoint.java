package za.co.discovery.assessment.shortestroutefinder.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import za.co.discovery.assessment.shortestroutefinder.dto.ShortestRouteModel;
import za.co.discovery.assessment.shortestroutefinder.webservice.schema.FindShortestPathToRequest;
import za.co.discovery.assessment.shortestroutefinder.webservice.schema.FindShortestPathToResponse;


@Endpoint
public class ShortestRouteEndpoint {

    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";
    private ShortestRouteRepository pathRepository;

    @Autowired
    public ShortestRouteEndpoint(ShortestRouteRepository pathRepository) {
        this.pathRepository = pathRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "findShortestPathToRequest")

    @ResponsePayload
    public FindShortestPathToResponse getShortestPath(@RequestPayload FindShortestPathToRequest request) {
        FindShortestPathToResponse response = new FindShortestPathToResponse();
        ShortestRouteModel shortestPath = pathRepository.getShortestPath(request.getName());
        response.setPath(shortestPath.getThePath());
        response.setDistanceTravelled(shortestPath.getDistanceTravelled());
        return response;
    }
}
