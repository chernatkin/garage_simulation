package org.chernatkin.garage.simulation.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class NoFreePlacesException extends WebApplicationException {

    public NoFreePlacesException() {
        super(Response.status(Status.CONFLICT).build());
    }
}
