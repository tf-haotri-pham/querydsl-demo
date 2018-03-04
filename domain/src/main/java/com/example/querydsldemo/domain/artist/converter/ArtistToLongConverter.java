package com.example.querydsldemo.domain.artist.converter;

import com.example.querydsldemo.domain.artist.Artist;
import com.fasterxml.jackson.databind.util.StdConverter;

public class ArtistToLongConverter extends StdConverter<Artist, Long> {
    
    @Override
    public Long convert(final Artist artist) {
        return artist == null ? null : artist.getId();
    }
}
