package com.example.querydsldemo.config;

import com.example.querydsldemo.domain.BaseEntity;
import com.example.querydsldemo.domain.BaseEntityRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan(basePackageClasses = { BaseEntity.class, Jsr310JpaConverters.class })
@EnableJpaRepositories(basePackageClasses = BaseEntityRepository.class)
@EnableTransactionManagement
public class DatabaseConfig {
}