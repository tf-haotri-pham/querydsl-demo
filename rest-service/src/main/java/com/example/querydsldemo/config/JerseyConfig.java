package com.example.querydsldemo.config;

import com.example.querydsldemo.RootResource;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import static com.example.querydsldemo.config.JerseyConfig.JERSEY_BASE_PATH;

@Configuration
@ApplicationPath(JERSEY_BASE_PATH)
public class JerseyConfig extends ResourceConfig {

    static final String JERSEY_BASE_PATH = "/api";

    @Value("${spring.jersey.application-path:" + JERSEY_BASE_PATH + "}")
    private String apiPath;

    public JerseyConfig() {
        //Jersey providers
        register(JerseyObjectMapperProvider.class);

        // Jersey features
        register(JacksonFeature.class);
        
        // Jersey REST endpoints
        register(RootResource.class);
    }

    @PostConstruct
    public void init() {
        configureSwaggerForJersey();
    }

    private void configureSwaggerForJersey() {
        // Available at localhost:port/swagger.json
        register(ApiListingResource.class);
        register(SwaggerSerializers.class);

        final BeanConfig config = new BeanConfig();
        config.setConfigId("springboot-jersey-swagger-querydsl-demo");
        config.setTitle("QueryDSL Demo Swagger Documentation");
        config.setVersion("v1");
        config.setContact("HaoTri.Pham@twinformatics.at");
        config.setSchemes(new String[]{"http", "https"});
        config.setBasePath(apiPath);
        config.setResourcePackage(RootResource.class.getPackage().getName());
        config.setPrettyPrint(true);
        config.setScan(true);
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
