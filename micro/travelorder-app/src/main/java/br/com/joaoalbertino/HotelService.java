package br.com.joaoalbertino;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.faulttolerance.*;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.time.temporal.ChronoUnit;
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
    @Timeout(unit = ChronoUnit.SECONDS , value = 2)
    @Fallback(fallbackMethod = "fallbackHotel")
    @CircuitBreaker(
            requestVolumeThreshold = 4,
            failureRatio = 0.5 ,
            delay = 5000 ,
            successThreshold = 2
    )
    public Hotel findByTravelOrderId(@QueryParam("id") Long travelOrderId);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Hotel newHotel(Hotel hotel);

    default Hotel fallbackHotel(Long travelOrderId){
        return new Hotel(1L,travelOrderId,0);
    }
}
