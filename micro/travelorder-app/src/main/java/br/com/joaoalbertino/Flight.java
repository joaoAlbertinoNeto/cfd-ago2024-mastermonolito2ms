package br.com.joaoalbertino;

import java.util.Objects;

public class Flight{
    private Long id;
    private Long travelOrderId;
    private String fromAirport;
    private String toAirport;

    public Flight() {
    }

    public Flight(Long travelOrderId, String fromAirport, String toAirport , Long id) {
        this.travelOrderId = travelOrderId;
        this.fromAirport = fromAirport;
        this.toAirport = toAirport;
        this.id = id;
    }

    public Long getTravelOrderId() {
        return travelOrderId;
    }

    public void setTravelOrderId(Long travelOrderId) {
        this.travelOrderId = travelOrderId;
    }

    public String getFromAirport() {
        return fromAirport;
    }

    public void setFromAirport(String fromAirport) {
        this.fromAirport = fromAirport;
    }

    public String getToAirport() {
        return toAirport;
    }

    public void setToAirport(String toAirport) {
        this.toAirport = toAirport;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return Objects.equals(id, flight.id) && Objects.equals(travelOrderId, flight.travelOrderId) && Objects.equals(fromAirport, flight.fromAirport) && Objects.equals(toAirport, flight.toAirport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, travelOrderId, fromAirport, toAirport);
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", travelOrderId=" + travelOrderId +
                ", fromAirport='" + fromAirport + '\'' +
                ", toAirport='" + toAirport + '\'' +
                '}';
    }
}