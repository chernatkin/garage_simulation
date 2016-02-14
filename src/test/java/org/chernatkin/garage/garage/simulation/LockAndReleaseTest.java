package org.chernatkin.garage.garage.simulation;

import java.util.List;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;

import org.chernatkin.garage.simulation.service.GaragePlace;
import org.junit.Assert;
import org.junit.Test;


public class LockAndReleaseTest extends AbstractGarageTest {
    
    private final Long carId = 234L;
    
    @Test
    public void lockTest() {
        final GaragePlace lockedPlace = lockPlace(carId);
        checkSinglePlaceLocked(getAllPlaces(), lockedPlace);
    }
    
    @Test
    public void repeatedLockTest() {
        final GaragePlace lockedPlace = lockPlace(carId);
        checkSinglePlaceLocked(getAllPlaces(), lockedPlace);
        
        final GaragePlace lockedPlace1 = lockPlace(carId);
        Assert.assertEquals(lockedPlace, lockedPlace1);
        
        checkSinglePlaceLocked(getAllPlaces(), lockedPlace1);
    }
    
    @Test
    public void releaseTest() {
        releasePlaceSuccess(carId);
        checkNoPlacesLocked(getAllPlaces());
    }
    
    @Test
    public void lockAndReleaseTest() {
        final GaragePlace lockedPlace = lockPlace(carId);
        checkSinglePlaceLocked(getAllPlaces(), lockedPlace);
        
        releasePlaceSuccess(carId);
        checkNoPlacesLocked(getAllPlaces());
    }
    
    @Test
    public void lockAndRepeatedReleaseTest() {
        final GaragePlace lockedPlace = lockPlace(carId);
        checkSinglePlaceLocked(getAllPlaces(), lockedPlace);
        
        releasePlaceSuccess(carId);
        checkNoPlacesLocked(getAllPlaces());
        
        releasePlaceSuccess(carId);
        checkNoPlacesLocked(getAllPlaces());
    }
    
    @Test
    public void noFreePlacesLockTest() {
        
        for(int i = 1; i <= NUMBER_OF_PLACES; i++){
            lockPlace(i);
        }

        try{
            lockPlace(NUMBER_OF_PLACES + 1);
            Assert.fail();
        }
        catch(ClientErrorException ce){
            Assert.assertEquals(Response.Status.CONFLICT.getStatusCode(), ce.getResponse().getStatus());
        }
    }
    
    @Test(expected = BadRequestException.class)
    public void invalidCarIdLockTest() {
        lockPlace(0);
    }

    protected void checkSinglePlaceLocked(final List<GaragePlace> allPlaces, final GaragePlace lockedPlace){
        Assert.assertNotNull(allPlaces);
        Assert.assertEquals(NUMBER_OF_PLACES, allPlaces.size());
        
        Assert.assertEquals(NUMBER_OF_PLACES - 1, allPlaces.stream().filter(place -> place.getCarId() == null).count());
        Assert.assertEquals(1, allPlaces.stream().filter(place -> lockedPlace.getCarId().equals(place.getCarId())).count());
    }
    
    protected void checkNoPlacesLocked(final List<GaragePlace> allPlaces){
        Assert.assertNotNull(allPlaces);
        Assert.assertEquals(NUMBER_OF_PLACES, allPlaces.size());
        
        Assert.assertEquals(NUMBER_OF_PLACES, allPlaces.stream().filter(place -> place.getCarId() == null).count());
    }
}
