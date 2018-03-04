package com.example.querydsldemo.domain.film;

import com.example.querydsldemo.domain.BaseEntityRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends BaseEntityRepository<Film> {

    @Query("select f from Film f where f.director.firstName like %:name% OR f.director.lastName like %:name%")
    List<Film> findByDirectorName(@Param("name") String name);
    
}
