package org.chernatkin.garage.simulation.service;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.chernatkin.garage.simulation.exception.CarNotFoundException;
import org.chernatkin.garage.simulation.exception.NoFreePlacesException;

@Path("/garage")
public class GarageResource {
    
    @Inject
    private GarageService garageService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/status")
    public String status() {
        return "OK";
    }
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/place/lock")
    public GaragePlace lockFreePlace(@FormParam("carId") final long carId) throws SQLException, NoFreePlacesException {
        checkCarId(carId);
        return garageService.lockFreePlace(carId);
    }
    
    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/place/release")
    public Response releasePlace(@FormParam("carId") final long carId) throws SQLException {
        checkCarId(carId);
        garageService.releasePlace(carId);
        return Response.noContent().build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/car/place")
    public GaragePlace getCarPlace(@QueryParam("carId") final long carId) throws SQLException, CarNotFoundException {
        checkCarId(carId);
        return garageService.getCarPlace(carId);
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/place/free/count")
    public String freePlacesCount() throws SQLException {
        return String.valueOf(garageService.freePlacesCount());
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/place/all")
    public List<GaragePlace> getCarPlace() throws SQLException, CarNotFoundException {
        return garageService.getAllPlaces();
    }
    
    private void checkCarId(final long carId){
        if(carId <= 0){
            throw new WebApplicationException("CarId=" + carId + " is not valid", Response.Status.BAD_REQUEST);
        }
    }
}
