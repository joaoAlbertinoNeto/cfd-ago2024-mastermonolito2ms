package br.com.joaoalbertino;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient(baseUri = "http://localhost:8081/flight")
public interface FlightService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Flight> flights();

    @Path("flightsById")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Flight findById(@QueryParam("id") Long id);

    @Path("findByIdTravelOrderId")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Flight findByTravelOrderId(@QueryParam("id") Long travelOrderId);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Flight newFlight(Flight flight);
}
