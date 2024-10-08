package br.com.joaoalbertino.hotel;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("hotel")
public class HotelResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Hotel> hotels(){
        return Hotel.listAll();
    }

    @Path("findById")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Hotel findById(@QueryParam("id") Long id){
        return Hotel.findById(id);
    }

    @Path("findByIdTravelOrderId")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Hotel findByTravelOrderId(@QueryParam("id") Long travelOrderId){
        return Hotel.findByTravelOrderId(travelOrderId);
    }

    @Transactional
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Hotel newHotel(Hotel hotel){
        hotel.id = null;
        hotel.persist();

        return hotel;

    }

}
