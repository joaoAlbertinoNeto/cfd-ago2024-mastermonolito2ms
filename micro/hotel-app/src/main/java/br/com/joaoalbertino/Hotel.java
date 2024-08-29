package br.com.joaoalbertino;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

import java.util.Optional;

@Entity
public class Hotel extends PanacheEntity {
    public Long travelOrderId;
    public Integer nights;

    public static Hotel findByTravelOrderId(Long travelOrderId) throws InterruptedException {

        Thread.sleep(3000);
        return find("travelOrderId", travelOrderId).firstResult();
    }

}
