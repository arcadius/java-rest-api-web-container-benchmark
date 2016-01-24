package com.menelic;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.jetty.JettyServerCustomizer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * original of this config file came from https://gist.github.com/jdavisonc/570819f4c7c6fe178df8
 */
@Component
@ConfigurationProperties(prefix = "server.jetty", ignoreUnknownFields = false)
public class JettyProperties implements EmbeddedServletContainerCustomizer {

    private static final Logger LOGGER = LoggerFactory.getLogger(JettyProperties.class);


    private Integer maxThreads;

    private Integer minThreads;

    public Integer getMaxThreads() {
        return maxThreads;
    }

    public void setMaxThreads(Integer maxThreads) {
        this.maxThreads = maxThreads;
    }

    public Integer getMinThreads() {
        return minThreads;
    }

    public void setMinThreads(Integer minThreads) {
        this.minThreads = minThreads;
    }

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        if (container instanceof JettyEmbeddedServletContainerFactory) {
            customizeJetty((JettyEmbeddedServletContainerFactory) container);
        }
    }

    private void customizeJetty(JettyEmbeddedServletContainerFactory factory) {
        factory.addServerCustomizers(new JettyServerCustomizer() {
            @Override
            public void customize(Server server) {

                final QueuedThreadPool threadPool = server.getBean(QueuedThreadPool.class);
                if (maxThreads != null) {
                    threadPool.setMaxThreads(maxThreads);
                    LOGGER.info("===Jetty server configured with maxThreads {}", maxThreads);
                }

                if (minThreads != null) {
                    threadPool.setMinThreads(minThreads);
                    LOGGER.info("===Jetty server configured with minThreads {}", minThreads);
                }

            }
        });
    }
}