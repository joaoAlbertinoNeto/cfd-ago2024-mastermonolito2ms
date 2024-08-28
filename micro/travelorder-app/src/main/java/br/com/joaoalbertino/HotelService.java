package br.com.joaoalbertino;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;


@RegisterRestClient(baseUri = "http://localhost:8082/hotel")
public interface HotelService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Hotel> hotels();

    @Path("findById")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Hotel findById(@QueryParam("id") Long id);

    @Path("findByIdTravelOrderId")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Hotel findByTravelOrderId(@QueryParam("id") Long travelOrderId);
    @Transactional
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Hotel newHotel(Hotel hotel);
}
