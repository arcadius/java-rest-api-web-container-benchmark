package com.menelic.jwcb.common.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyResourceConfig extends ResourceConfig {
    public JerseyResourceConfig() {
        super();
        packages(true, "com.menelic");
    }
}