package com.example.querydsldemo.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    @Override
    public final int hashCode() {
        return id != null ? id.hashCode() : -1;
    }

    @Override
    public final boolean equals(final Object obj) {
        // id integer might not be universally unique across entities, hence must also consider runtime class!
        return (this == obj) || (id != null && getClass().isInstance(obj) && id.equals(((BaseEntity) obj).id));
    }
}
