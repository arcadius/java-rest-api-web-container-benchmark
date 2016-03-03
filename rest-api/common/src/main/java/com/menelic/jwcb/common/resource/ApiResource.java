package com.menelic.jwcb.common.resource;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.ok;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;


@Produces(APPLICATION_JSON_UTF8_VALUE)
@Path("/api")
@Component
public class ApiResource {
    public static final String RESPONSE = "{\"greeting\":\"Hello World!\"}";

    @GET
    public Response test() {
        return ok(RESPONSE).build();
    }
}
