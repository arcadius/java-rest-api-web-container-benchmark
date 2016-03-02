package com.menelic.jwcb.undertow;

import io.undertow.Undertow;
import io.undertow.UndertowOptions;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.undertow.UndertowBuilderCustomizer;
import org.springframework.boot.context.embedded.undertow.UndertowEmbeddedServletContainerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "server.undertow", ignoreUnknownFields = false)
public class Customizer implements EmbeddedServletContainerCustomizer {


    private boolean alwaysSetKeepAlive;

    public boolean isAlwaysSetKeepAlive() {
        return alwaysSetKeepAlive;
    }

    public void setAlwaysSetKeepAlive(boolean alwaysSetKeepAlive) {
        this.alwaysSetKeepAlive = alwaysSetKeepAlive;
    }


    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        if (container instanceof UndertowEmbeddedServletContainerFactory) {
            customizeUndertow((UndertowEmbeddedServletContainerFactory) container);
        }
    }

    private void customizeUndertow(UndertowEmbeddedServletContainerFactory factory) {
        factory.addBuilderCustomizers(new UndertowBuilderCustomizer() {

            @Override
            public void customize(Undertow.Builder builder) {
                //default to false i.e do not display the hearder
                builder.setServerOption(UndertowOptions.ALWAYS_SET_KEEP_ALIVE, isAlwaysSetKeepAlive());
            }

        });

    }

}
