package br.com.joaoalbertino;

import jakarta.inject.Inject;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

@Readiness
public class ReadinessCheck implements HealthCheck {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Inject
    @RestClient
    FlightService flightService;

    @Inject
    @RestClient
    HotelService hotelService;


    @Override
    public HealthCheckResponse call() {
        try {
            var flightResp = flightService.findById(1L);
            var hotelResp = hotelService.findById(750L);

            if (Objects.nonNull(flightResp) && Objects.nonNull(hotelResp)) {
                return HealthCheckResponse.up("All services up and running");
            } else {
                var systemStatus = "";
                if (Objects.isNull(flightResp)) {
                    systemStatus += " flight service down";
                }

                if (Objects.isNull(hotelResp)) {
                    systemStatus += " hotel service down";
                }
                return HealthCheckResponse.down(String.format("systems dowm : [ %s ]", systemStatus));
            }
        }catch (Exception e){

            log.error(e.getLocalizedMessage());
            return HealthCheckResponse.down("all systems down");
        }
    }
}
