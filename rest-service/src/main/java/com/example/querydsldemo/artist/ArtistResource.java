package com.example.querydsldemo.artist;

import com.example.querydsldemo.domain.artist.Artist;
import com.example.querydsldemo.domain.artist.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;
import java.util.Optional;

@Component
@Singleton
public class ArtistResource {
    
    @Autowired
    private ArtistRepository artistRepository;

    @GET
    public List<Artist> retrieveAllArtists() {
        return artistRepository.findAll();
    }
    
    @GET
    @Path("/{artistId}")
    public Artist retrieveArtist(@PathParam("artistId") final long artistId) {
        return Optional.ofNullable(artistRepository.findOne(artistId))
                .orElseThrow(() -> new NotFoundException("No artist for ID " + artistId));
    }

    @POST
    public Artist createArtist(final Artist artist) {
        artist.setId(null);
        return artistRepository.save(artist);
    }
    
}