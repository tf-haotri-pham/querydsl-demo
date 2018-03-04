package com.example.querydsldemo.domain.artist.converter;

import com.example.querydsldemo.domain.artist.Artist;
import com.example.querydsldemo.domain.artist.ArtistRepository;
import com.fasterxml.jackson.databind.util.StdConverter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.NotFoundException;
import java.util.Optional;

public class LongToArtistConverter extends StdConverter<Long, Artist> {
    
    @Autowired
    private ArtistRepository artistRepository;
    
    @Override
    public Artist convert(final Long artistId) {
        return artistId == null 
            ? null
            : Optional.ofNullable(artistRepository.findOne(artistId))
                    .orElseThrow(() -> new IllegalArgumentException("No artist for ID " + artistId));
    }
}
