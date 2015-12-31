package com.menelic.webcontaners;

import com.menelic.webcontaners.api.AbstractApplication;
import com.menelic.webcontaners.api.JerseyResourceConfig;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import java.net.URI;

public class Application extends AbstractApplication {


    public static void main(String[] args) throws Exception {
        startServer();
        Thread.currentThread().join();
    }

    private static final URI BASE_URI = URI.create("http://localhost:8080/");

    @Autowired
    JerseyResourceConfig config;

    private static void startServer() {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.scan("com.menelic");
        // ctx.getServletContext().
        ctx.refresh();


        JerseyResourceConfig config = ctx.getBean(JerseyResourceConfig.class);
        final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, config);
    }

}