package org.chernatkin.garage.simulation.service;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.chernatkin.garage.simulation.exception.CarNotFoundException;
import org.chernatkin.garage.simulation.exception.NoFreePlacesException;

public class GarageService {
    
    @Inject
    private GarageDao garageDao;
    
    public GaragePlace lockFreePlace(final long carId) throws SQLException, NoFreePlacesException {
        return garageDao.lockFreePlace(carId);
    }
    
    public void releasePlace(final long carId) throws SQLException {
        garageDao.releasePlace(carId);
    }
    
    public GaragePlace getCarPlace(final long carId) throws SQLException, CarNotFoundException {
        return garageDao.getCarPlace(carId);
    }
    
    public int freePlacesCount() throws SQLException {
        return garageDao.freePlacesCount();
    }
    
    public List<GaragePlace> getAllPlaces() throws SQLException, CarNotFoundException {
        return garageDao.getAllPlaces();
    }
}
