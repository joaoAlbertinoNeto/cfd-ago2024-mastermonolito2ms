package br.com.joaoalbertino;

import java.util.Objects;

public class Hotel {

    private Long id;
    private Long travelOrderId;
    private Integer nights;

    public Hotel() {
    }

    public Hotel(Long id, Long travelOrderId, Integer nights) {
        this.id = id;
        this.travelOrderId = travelOrderId;
        this.nights = nights;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTravelOrderId() {
        return travelOrderId;
    }

    public void setTravelOrderId(Long travelOrderId) {
        this.travelOrderId = travelOrderId;
    }

    public Integer getNights() {
        return nights;
    }

    public void setNights(Integer nights) {
        this.nights = nights;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        return Objects.equals(id, hotel.id) && Objects.equals(travelOrderId, hotel.travelOrderId) && Objects.equals(nights, hotel.nights);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, travelOrderId, nights);
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", travelOrderId=" + travelOrderId +
                ", nights=" + nights +
                '}';
    }
}
