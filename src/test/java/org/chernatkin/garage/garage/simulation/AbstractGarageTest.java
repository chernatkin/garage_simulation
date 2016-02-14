package org.chernatkin.garage.garage.simulation;

import java.util.List;
import java.util.Properties;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.chernatkin.garage.simulation.ApplicationConfig;
import org.chernatkin.garage.simulation.service.GaragePlace;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;

public abstract class AbstractGarageTest extends JerseyTest {

    protected static final int NUMBER_OF_PLACES = 9;
    
    private Properties props;
    
    protected Properties getProperties(){
        if(props == null){
            props = new Properties();
            props.put("db.url", "jdbc:hsqldb:mem:garagedb");
            props.put("db.login", "SA");
            props.put("db.pwd", "");
            props.put("db.pool.size", "2");
        }
        
        return props;
    }
    
    @Override
    protected Application configure() {
        return new ApplicationConfig(getProperties());
    }
    
    protected WebTarget garageTarget(){
        return target("garage");
    }
    
    protected GaragePlace lockPlace(final long carId){
        final GaragePlace place = garageTarget().path("/place/lock")
                                                .queryParam("carId", carId)
                                                .request()
                                                .put(Entity.form(new Form("carId", Long.toString(carId))), GaragePlace.class);
        Assert.assertNotNull(place);
        Assert.assertNotNull(place.getCarId());
        Assert.assertEquals(carId, place.getCarId().longValue());
        
        return place;
    }
    
    protected Response releasePlace(final long carId){
        return garageTarget().path("/place/release")
                             .queryParam("carId", carId)
                             .request()
                             .put(Entity.form(new Form("carId", Long.toString(carId))));
    }
    
    protected Response releasePlaceSuccess(final long carId){
        final Response response = releasePlace(carId);
        Assert.assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
        
        return response;
    }

    protected GaragePlace getCarPlace(final long carId){
        return garageTarget().path("/car/place")
                             .queryParam("carId", carId)
                             .request()
                             .get(GaragePlace.class);
    }
    
    protected List<GaragePlace> getAllPlaces(){
        return garageTarget().path("/place/all")
                             .request()
                             .get(new GenericType<List<GaragePlace>>(){});
    }
    
    protected Integer freePlacesCount(){
        return garageTarget().path("/place/free/count")
                             .request()
                             .get(Integer.class);
    }
}
