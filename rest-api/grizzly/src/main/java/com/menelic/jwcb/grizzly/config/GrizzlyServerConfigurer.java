package com.menelic.jwcb.grizzly.config;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.nio.transport.TCPNIOTransport;
import org.glassfish.grizzly.strategies.WorkerThreadIOStrategy;
import org.glassfish.grizzly.threadpool.ThreadPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GrizzlyServerConfigurer {
    private static Logger LOG = LoggerFactory.getLogger(GrizzlyServerConfigurer.class);

    @Value("${server.grizzly.worker-threads:0}")
    private Integer workerThreads;


    public void configure(HttpServer server) {

        if (workerThreads == null || workerThreads <= 0) {
            LOG.info("===Grizzly WorkerThreads is [{}] ... using default thread configs", workerThreads);
            return;
        }

        LOG.info("===Grizzly workerThreads is [{}] ... setting up container...", workerThreads);


        TCPNIOTransport transport = server.getListeners().iterator().next().getTransport();

        transport.setIOStrategy(WorkerThreadIOStrategy.getInstance());

        ThreadPoolConfig workerThreadPoolConfig = transport.getWorkerThreadPoolConfig();

        if (workerThreadPoolConfig == null) {
            workerThreadPoolConfig = ThreadPoolConfig.defaultConfig();
        }

        workerThreadPoolConfig.setPoolName("Grizzly-Worker-thread-0-").setCorePoolSize(workerThreads).setMaxPoolSize(workerThreads);

        transport.setWorkerThreadPoolConfig(workerThreadPoolConfig);
    }

}
