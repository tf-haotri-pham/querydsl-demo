package com.example.querydsldemo.domain.film;

import com.example.querydsldemo.domain.BaseEntity;
import com.example.querydsldemo.domain.artist.Artist;
import com.example.querydsldemo.domain.artist.converter.ArtistToLongConverter;
import com.example.querydsldemo.domain.artist.converter.LongToArtistConverter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Film extends BaseEntity {
    
    private String title;
    private int releaseYear;

    @ManyToOne
    @JoinColumn(name = "director_id")
    @JsonProperty("directorId")
    @JsonDeserialize(converter = LongToArtistConverter.class)
    @JsonSerialize(converter = ArtistToLongConverter.class)
    @ApiModelProperty(name = "directorId", dataType = "int")
    private Artist director;
    
    private String originalLanguage;

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(final int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Artist getDirector() {
        return director;
    }

    public void setDirector(final Artist director) {
        this.director = director;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(final String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }
}
