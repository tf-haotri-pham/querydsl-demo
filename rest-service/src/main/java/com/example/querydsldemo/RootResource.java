package com.example.querydsldemo;

import com.example.querydsldemo.artist.ArtistResource;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Component;

import javax.inject.Singleton;
import javax.ws.rs.Path;

@Component
@Singleton
@Path("/")
@Api
public class RootResource {
    
    @Path("/artist")
    public Class<ArtistResource> artist() {
        return ArtistResource.class;
    }
    
}