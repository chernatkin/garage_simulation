package org.chernatkin.garage.garage.simulation;

import org.junit.Assert;
import org.junit.Test;

public class GetFreePlacesCountTest extends AbstractGarageTest {

    private final Long carId = 654L;
    
    @Test
    public void getFreePlacesCountTest(){
        Assert.assertEquals(NUMBER_OF_PLACES, freePlacesCount().intValue());
        
        lockPlace(carId);
        Assert.assertEquals(NUMBER_OF_PLACES - 1, freePlacesCount().intValue());
    }
    
    @Test
    public void getZeroFreePlacesCountTest(){
        for(int i = 1; i <= NUMBER_OF_PLACES; i++){
            lockPlace(i);
        }
        
        Assert.assertEquals(0, freePlacesCount().intValue());
    }
}
