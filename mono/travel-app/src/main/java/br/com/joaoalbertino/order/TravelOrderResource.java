package br.com.joaoalbertino.order;


import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import br.com.joaoalbertino.flight.Flight;
import br.com.joaoalbertino.flight.FlightResource;
import br.com.joaoalbertino.hotel.Hotel;
import br.com.joaoalbertino.hotel.HotelResource;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;


@Path("travelOrder")
public class TravelOrderResource {

    @Inject
    FlightResource flightResource;

    @Inject
    HotelResource hotelResource;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TravelOrderDTO> orders(){
        return TravelOrder.<TravelOrder>listAll().stream().map(order -> {
                return TravelOrderDTO.of(order , flightResource.findByTravelOrderId(order.id) , hotelResource.findByTravelOrderId(order.id));
        }
        ).collect(Collectors.toList());
    }

    @GET
    @Path("findById")
    @Produces(MediaType.APPLICATION_JSON)
    public TravelOrder findById( @QueryParam("id") String id){
        return TravelOrder.findById(id);
    }


    @Transactional
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public TravelOrderDTO newTravelOrder(TravelOrderDTO orderDTO){
        var order = new TravelOrder();
        order.id = null;
        order.persist();

        var flight = new Flight();
        flight.fromAirport = orderDTO.getFromAirport();
        flight.toAirport = orderDTO.getToAirport();
        flight.travelOrderId = order.id;

        flightResource.newFlight(flight);

        var hotel = new Hotel();
        hotel.nights = orderDTO.getNights();
        hotel.travelOrderId = order.id;
        hotelResource.newHotel(hotel);

        return orderDTO;

    }
}