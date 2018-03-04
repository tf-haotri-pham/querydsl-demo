package com.example.querydsldemo.domain.artist;

import com.example.querydsldemo.domain.BaseEntityRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends BaseEntityRepository<Artist>, QueryDslPredicateExecutor<Artist> {
}
