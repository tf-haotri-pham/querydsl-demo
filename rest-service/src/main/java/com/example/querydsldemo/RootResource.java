package com.example.querydsldemo;

import org.springframework.stereotype.Component;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

@Component
@Singleton
@Path("/")
public class RootResource {

    @GET
    @Path("/hello-world")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, String> helloWorld() {
        final Map<String, String> pingPong = new HashMap<>();
        pingPong.put("Hello", "World");
        return pingPong;
    }
    
}