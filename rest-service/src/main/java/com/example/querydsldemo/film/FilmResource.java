package com.example.querydsldemo.film;

import com.example.querydsldemo.domain.film.Film;
import com.example.querydsldemo.domain.film.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.Optional;

@Component
@Singleton
public class FilmResource {

    @Autowired
    private FilmRepository filmRepository;

    @GET
    @Path("/{filmId}")
    public Film retrieveFilm(@PathParam("filmId") final long filmId) {
        return Optional.ofNullable(filmRepository.findOne(filmId))
                .orElseThrow(() -> new NotFoundException("No film for ID " + filmId));
    }

    @POST
    public Film createFilm(final Film film) {
        film.setId(null);
        return filmRepository.save(film);
    }
    
}
