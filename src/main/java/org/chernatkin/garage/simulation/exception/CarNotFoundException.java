package org.chernatkin.garage.simulation.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class CarNotFoundException extends WebApplicationException {

    public CarNotFoundException() {
        super(Response.status(Status.NOT_FOUND).build());
    }

}
