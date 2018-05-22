package com.example.querydsldemo.domain.film;

import com.example.querydsldemo.domain.BaseEntityRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends BaseEntityRepository<Film>, QueryDslPredicateExecutor<Film> {
}
