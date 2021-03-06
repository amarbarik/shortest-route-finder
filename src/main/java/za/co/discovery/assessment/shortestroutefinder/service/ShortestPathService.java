package za.co.discovery.assessment.shortestroutefinder.service;

import org.springframework.stereotype.Service;
import za.co.discovery.assessment.shortestroutefinder.helper.Graph;
import za.co.discovery.assessment.shortestroutefinder.model.Edge;
import za.co.discovery.assessment.shortestroutefinder.model.Vertex;

import java.util.*;

@Service
public class ShortestPathService extends PathService {

    public Map<Vertex, Vertex> run(Graph graph, Vertex source) {
        //Initialize
        graph = overlayGraph(graph);
        List<Edge> edges = graph.getEdges();
        Map<Vertex, Float> distance = new HashMap<>();
        Map<Vertex, Vertex> previousPaths = new HashMap<>();
        Set<Vertex> visitedVertices = new HashSet<>();
        Set<Vertex> unvisitedVertices = new HashSet<>();

        //Set distances and unvisitedVertices
        distance.put(source, 0f);
        unvisitedVertices.add(source);

        //Find
        while (!unvisitedVertices.isEmpty()) {
            Vertex currentVertex = getVertexWithLowestDistance(distance, unvisitedVertices);
            visitedVertices.add(currentVertex);
            unvisitedVertices.remove(currentVertex);
            //Evaluate Neighbors With Minimal Distances
            List<Vertex> adjacentVertices = getNeighbors(edges, visitedVertices, currentVertex);
            for (Vertex target : adjacentVertices) {
                float alternateDistance = getShortestDistance(distance, currentVertex) + getDistance(edges, currentVertex, target);
                if (alternateDistance < getShortestDistance(distance, target)) {
                    distance.put(target, alternateDistance);
                    previousPaths.put(target, currentVertex);
                    unvisitedVertices.add(target);
                }
            }
        }
        //Collect
        return previousPaths;
    }

    public LinkedList<Vertex> getPath(Map<Vertex, Vertex> previousPaths, Vertex target) {
        LinkedList<Vertex> path = new LinkedList<>();
        Vertex step = target;

        if (previousPaths.get(step) == null) {
            return null;
        }
        path.add(step);
        while (previousPaths.get(step) != null) {
            step = previousPaths.get(step);
            path.add(step);
        }

        Collections.reverse(path);
        return path;
    }
}
