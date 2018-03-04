package com.example.querydsldemo;

import com.example.querydsldemo.artist.ArtistResource;
import com.example.querydsldemo.film.FilmResource;
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
    
    @Path("/film")
    public Class<FilmResource> film() {
        return FilmResource.class;
    }
}