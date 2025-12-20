CREATE TABLE media_post
(
    id         CHAR(36) NOT NULL,
    user_id    CHAR(36) NULL,
    track_id   CHAR(36) NULL,
    guide_id   CHAR(36) NULL,
    media_url  TEXT NULL,
    media_type VARCHAR(255) NULL,
    created_at datetime NULL,
    CONSTRAINT pk_media_post PRIMARY KEY (id)
);

CREATE TABLE pin
(
    id            CHAR(36)     NOT NULL,
    track_id      CHAR(36)     NOT NULL,
    latitude DOUBLE NOT NULL,
    longitude DOUBLE NOT NULL,
    type          VARCHAR(255) NULL,
    name          VARCHAR(255) NOT NULL,
    `description` VARCHAR(255) NULL,
    CONSTRAINT pk_pin PRIMARY KEY (id)
);

CREATE TABLE track
(
    id            CHAR(36)     NOT NULL,
    user_id       CHAR(36)     NOT NULL,
    name          VARCHAR(255) NOT NULL,
    `description` TEXT NULL,
    difficulty    VARCHAR(255) NOT NULL,
    length DOUBLE NOT NULL,
    is_public     BIT(1)       NOT NULL,
    CONSTRAINT pk_track PRIMARY KEY (id)
);

CREATE TABLE users
(
    id         CHAR(36)     NOT NULL,
    email      VARCHAR(255) NOT NULL,
    name       VARCHAR(255) NOT NULL,
    password   VARCHAR(255) NOT NULL,
    `role`     VARCHAR(255) NULL,
    created_at datetime NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE users
    ADD CONSTRAINT uc_users_email UNIQUE (email);