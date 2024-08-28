package br.com.joaoalbertino;


import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Path("travelOrder")
public class TravelOrderResource {

    @Inject
    @RestClient
    FlightService flightService;

    @Inject
    @RestClient
    HotelService hotelService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TravelOrderDTO> orders(){
        return TravelOrder.<TravelOrder>listAll().stream().map(order -> {
                return TravelOrderDTO.of(order , Optional.of(flightService.findByTravelOrderId(order.id)).orElse(new Flight()) , Optional.of(hotelService.findByTravelOrderId(order.id)).orElse(new Hotel()));
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
        flight.setFromAirport(orderDTO.getFromAirport());
        flight.setToAirport(orderDTO.getToAirport());
        flight.setTravelOrderId(order.id);

        flightService.newFlight(flight);

        var hotel = new Hotel();
        hotel.setNights(orderDTO.getNights());
        hotel.setTravelOrderId(order.id);

        hotelService.newHotel(hotel);

        return orderDTO;

    }
}