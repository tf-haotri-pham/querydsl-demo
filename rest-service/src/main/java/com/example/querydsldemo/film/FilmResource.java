package com.example.querydsldemo.film;

import com.example.querydsldemo.domain.film.Film;
import com.example.querydsldemo.domain.film.FilmRepository;
import com.example.querydsldemo.domain.film.QFilm;
import com.querydsl.core.types.dsl.BooleanExpression;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
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
    @ApiOperation(value = "searches for films by director", response = Films.class)
    public Iterable<Film> searchFilmByDirectorName(@QueryParam("director-name") final String directorName) {
        return StringUtils.isBlank(directorName)
                ? Collections.emptyList()
                : filmRepository.findByDirectorName(directorName); // TODO: use QueryDSL
    }

    @GET
    @Path("/search")
    @ApiOperation("searches for films by director")
    public Page<Film> searchFilm(@QueryParam("title") final String title,
                                 @QueryParam("release-year") final Integer releaseYear,
                                 @QueryParam("release-year-matching") final DateMatching releaseYearMatching,
                                 @QueryParam("director-name") final String directorName,
                                 @QueryParam("director-dob") final LocalDate directorDob,
                                 @QueryParam("director-dob-matching") final DateMatching directorDobMatching,
                                 @QueryParam("page-index") @DefaultValue("0") final int pageIndex,
                                 @QueryParam("page-size") @DefaultValue("10") final int pageSize,
                                 @QueryParam("sort-direction") @DefaultValue("ASC") final Sort.Direction sortDirection,
                                 @QueryParam("sort-propety") @DefaultValue("title") final String sortProperty) {
        BooleanExpression searchCriteria = QFilm.film.title.isNotEmpty();
        if (StringUtils.isNotBlank(title)) {
            searchCriteria = QFilm.film.title.containsIgnoreCase(title);
        }
        if (releaseYear != null && releaseYearMatching != null) {
            switch (releaseYearMatching) {
                case BEFORE:
                    searchCriteria = searchCriteria.and(QFilm.film.releaseYear.lt(releaseYear)); break;
                case AFTER:
                    searchCriteria = searchCriteria.and(QFilm.film.releaseYear.gt(releaseYear)); break;
                default:
                    searchCriteria = searchCriteria.and(QFilm.film.releaseYear.eq(releaseYear)); break;
            }
        }

        if (StringUtils.isNotBlank(directorName)) {
            searchCriteria = searchCriteria.and(QFilm.film.director.firstName.containsIgnoreCase(directorName)
                    .or(QFilm.film.director.lastName.containsIgnoreCase(directorName)));
        }

        if (directorDob != null && directorDobMatching != null) {
            switch (directorDobMatching) {
                case BEFORE:
                    searchCriteria = searchCriteria.and(QFilm.film.director.dateOfBirth.lt(directorDob)); break;
                case AFTER:
                    searchCriteria = searchCriteria.and(QFilm.film.director.dateOfBirth.gt(directorDob)); break;
                default:
                    searchCriteria = searchCriteria.and(QFilm.film.director.dateOfBirth.eq(directorDob)); break;
            }
        }

        return filmRepository.findAll(searchCriteria, new PageRequest(0, 10, sortDirection, sortProperty));
    }

    interface Films extends Collection<Film> {
    }
}
