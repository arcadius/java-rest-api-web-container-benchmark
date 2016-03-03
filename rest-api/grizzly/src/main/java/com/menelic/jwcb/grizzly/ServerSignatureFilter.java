package com.menelic.jwcb.grizzly;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import java.io.IOException;

@Component
public class ServerSignatureFilter implements ContainerResponseFilter {

    @Value("${server.server-header:Grizzly}")
    private String serverName;

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        responseContext.getHeaders().add("Server", serverName);
    }
}
