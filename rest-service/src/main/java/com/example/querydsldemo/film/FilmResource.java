package com.example.querydsldemo.film;

import com.example.querydsldemo.domain.film.Film;
import com.example.querydsldemo.domain.film.FilmRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@Singleton
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(tags = "Film")
public class FilmResource {

    @Autowired
    private FilmRepository filmRepository;

    @GET
    @Path("/{filmId}")
    @ApiOperation("retrieves one film")
    public Film retrieveFilm(@PathParam("filmId") final long filmId) {
        return Optional.ofNullable(filmRepository.findOne(filmId))
                .orElseThrow(() -> new NotFoundException("No film for ID " + filmId));
    }

    @POST
    @ApiOperation("creates a film")
    public Film createFilm(final Film film) {
        film.setId(null);
        return filmRepository.save(film);
    }

    @GET
    @ApiOperation("searches for films by director")
    public List<Film> searchFilmByDirectorName(@QueryParam("director-name") final String directorName) {
        return StringUtils.isBlank(directorName)
                ? Collections.emptyList()
                : filmRepository.findByDirectorName(directorName);
    }
    
}
