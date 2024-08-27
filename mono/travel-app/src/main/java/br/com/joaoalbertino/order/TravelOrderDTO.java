package br.com.joaoalbertino.order;

import br.com.joaoalbertino.flight.Flight;
import br.com.joaoalbertino.hotel.Hotel;

public class TravelOrderDTO {
    
    public String fromAirport;
    public String toAirport;
    public Integer nights;


    public TravelOrderDTO(){
        
    }

    public TravelOrderDTO(String fromAirport, String toAirport, Integer nights) {
        this.fromAirport = fromAirport;
        this.toAirport = toAirport;
        this.nights = nights;
    }

    public static TravelOrderDTO of(TravelOrder order , Flight flight , Hotel hotel){
        return new TravelOrderDTO(flight.fromAirport, flight.toAirport, hotel.nights);
    }

    public static TravelOrderDTO of(String fromString , String toAirport , Integer nights){
        return new TravelOrderDTO(toAirport, toAirport, nights);
    }

    public String getFromAirport() {
        return fromAirport;
    }

    public String getToAirport() {
        return toAirport;
    }

    public Integer getNights() {
        return nights;
    }

    public void setFromAirport(String fromAirport) {
        this.fromAirport = fromAirport;
    }

    public void setToAirport(String toAirport) {
        this.toAirport = toAirport;
    }

    public void setNights(Integer nights) {
        this.nights = nights;
    }

    @Override
    public String toString() {
        return "TravelOrderDTO {fromAirport:" + fromAirport + ", toAirport:" + toAirport + ", nights:" + nights + "}";
    }

    
}
