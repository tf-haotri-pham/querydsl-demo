------------------------- SHARED OBJECTS -------------------------

-- If Hibernate finds a sequence 'hibernate_sequence', it will be used 
-- for @GeneratedValue(strategy = GenerationType.SEQUENCE)
CREATE SEQUENCE ${schema}.hibernate_sequence;
ALTER TABLE ${schema}.hibernate_sequence OWNER TO ${dbowner};

REVOKE ALL ON SEQUENCE ${schema}.hibernate_sequence FROM PUBLIC;
REVOKE ALL ON SEQUENCE ${schema}.hibernate_sequence FROM ${dbowner};
GRANT ALL ON SEQUENCE ${schema}.hibernate_sequence TO ${dbowner};


------------------------- BUSINESS ENTITIES -------------------------

CREATE TABLE ${schema}.artist (
  id            BIGINT PRIMARY KEY NOT NULL,
  first_name    VARCHAR(64) NOT NULL,
  last_name     VARCHAR(64) NOT NULL,
  date_of_birth DATE
);
ALTER TABLE ${schema}.artist OWNER TO ${dbowner};

CREATE TABLE ${schema}.film (
  id            BIGINT PRIMARY KEY NOT NULL,
  title         VARCHAR(256) NOT NULL,
  release_year  INTEGER NOT NULL,
  director_id   BIGINT REFERENCES ${schema}.artist
);
ALTER TABLE ${schema}.film OWNER TO ${dbowner};