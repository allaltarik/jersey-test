package com.necoutezpas;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("home")
public class Resource {
    private final MyDependency myDependency;

    @Inject
    public Resource(MyDependency myDependency) {
        this.myDependency = myDependency;
    }

    @GET
    @Path("hello/{idType}={idValue}")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloWorld(@PathParam("idType") String idType, @PathParam("idValue") String idValue) {
        return String.format("Hello, %s=%s! (%s)", idType, idValue, myDependency.getFoo());
    }
}