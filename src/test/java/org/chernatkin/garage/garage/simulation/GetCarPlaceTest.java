package org.chernatkin.garage.garage.simulation;

import javax.ws.rs.NotFoundException;

import org.chernatkin.garage.simulation.service.GaragePlace;
import org.junit.Assert;
import org.junit.Test;

public class GetCarPlaceTest extends AbstractGarageTest {

    private final Long carId = 432L;
    
    @Test
    public void getCarPlaceTest(){
        final GaragePlace lockedPlace = lockPlace(carId);
        
        final GaragePlace lockedPlace1 = getCarPlace(carId);
        Assert.assertEquals(lockedPlace, lockedPlace1);
    }
    
    @Test(expected = NotFoundException.class)
    public void getNotExistsCarPlaceTest(){
        getCarPlace(carId);
    }
}
