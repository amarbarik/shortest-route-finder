package za.co.discovery.assessment.shortestroutefinder.helper;

import za.co.discovery.assessment.shortestroutefinder.model.Edge;
import za.co.discovery.assessment.shortestroutefinder.model.TrafficInfo;
import za.co.discovery.assessment.shortestroutefinder.model.Vertex;

import java.util.ArrayList;
import java.util.List;

public class Graph {

    private List<Vertex> vertexes;
    private List<Edge> edges;
    private List<TrafficInfo> trafficInfos;
    private boolean undirectedGraph;
    private boolean trafficAllowed;

    public Graph(List<Vertex> vertexes, List<Edge> edges, List<TrafficInfo> trafficInfos) {
        this.vertexes = vertexes;
        this.edges = edges;
        this.trafficInfos = new ArrayList<>(trafficInfos);
    }

    public List<TrafficInfo> getTrafficInfos() {
        return trafficInfos;
    }

    public List<Vertex> getVertexes() {
        return vertexes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public boolean isUndirectedGraph() {
        return undirectedGraph;
    }

    public void setUndirectedGraph(boolean undirectedGraph) {
        this.undirectedGraph = undirectedGraph;
    }

    public boolean isTrafficAllowed() {
        return trafficAllowed;
    }

    public void setTrafficAllowed(boolean trafficAllowed) {
        this.trafficAllowed = trafficAllowed;
    }
}
