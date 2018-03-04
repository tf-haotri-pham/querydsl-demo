package com.example.querydsldemo.config;

import com.example.querydsldemo.RootResource;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import static com.example.querydsldemo.config.JerseyConfig.JERSEY_BASE_PATH;

@Configuration
@ApplicationPath(JERSEY_BASE_PATH)
public class JerseyConfig extends ResourceConfig {

    static final String JERSEY_BASE_PATH = "/api";

    public JerseyConfig() {
        //Jersey providers
        register(JerseyObjectMapperProvider.class);

        // Jersey features
        register(JacksonFeature.class);
        
        // Jersey REST endpoints
        register(RootResource.class);
    }

    /**
     * Instruct Jersey to use Spring's ObjectMapper, rather than its own one
     */
    @Provider
    static class JerseyObjectMapperProvider implements ContextResolver<ObjectMapper> {

        private final ObjectMapper objectMapper;

        @Autowired
        public JerseyObjectMapperProvider(final ObjectMapper springObjectMapper) {
            objectMapper = springObjectMapper;
        }

        @Override
        public ObjectMapper getContext(final Class<?> type) {
            return objectMapper;
        }
    }
}
