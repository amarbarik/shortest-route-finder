package za.co.discovery.assessment.shortestroutefinder.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "edge")
public class Edge implements Serializable {

    @Id
    @SequenceGenerator(name = "edgeSeq", sequenceName = "EDGE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "edgeSeq")

    @Column
    private Long id;

    @Column
    private String routeId;

    @ManyToOne
    private Vertex source;

    @ManyToOne
    private Vertex destination;

    @Column
    private Float distance;

    @OneToOne(mappedBy = "route", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private TrafficInfo trafficInfo;

    public Edge(String routeId, Vertex source, Vertex destination, Float distance) {

        this.routeId = routeId;
        this.source = source;
        this.destination = destination;
        this.distance = distance;
    }

    public Edge(String routeId, Float distance) {

        this.routeId = routeId;
        this.distance = distance;
    }

    public Edge() {

    }

    public void addTrafficInfo(TrafficInfo trafficInfo) {
        trafficInfo.setRoute(this);
        this.trafficInfo = trafficInfo;
    }

    public void removeTrafficInfo() {
        if (trafficInfo != null) {
            trafficInfo.setRoute(null);
            this.trafficInfo = null;
        }
    }

    public TrafficInfo getTrafficInfo() {
        return trafficInfo;
    }

    public void setTrafficInfo(TrafficInfo trafficInfo) {
        this.trafficInfo = trafficInfo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public Vertex getSource() {
        return source;
    }

    public void setSource(Vertex source) {
        this.source = source;
    }

    public Vertex getDestination() {
        return destination;
    }

    public void setDestination(Vertex destination) {
        this.destination = destination;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Edge other = (Edge) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
