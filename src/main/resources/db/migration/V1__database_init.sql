CREATE TABLE availability_slot
(
    id         CHAR(36) NOT NULL,
    guide_id   CHAR(36) NULL,
    date       date NULL,
    start_time time NULL,
    end_time   time NULL,
    capacity   INT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE booking
(
    id         CHAR(36) NOT NULL,
    guide_id   CHAR(36) NULL,
    user_id    CHAR(36) NULL,
    slot_id    CHAR(36) NULL,
    status     ENUM('Pending','Confirmed','Rejected','Cancelled') NULL,
    created_at datetime NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE business_profile
(
    id            CHAR(36) NOT NULL,
    user_id       CHAR(36) NULL,
    title         VARCHAR(255) NULL,
    `description` LONGTEXT NULL,
    phone         VARCHAR(50) NULL,
    rating DOUBLE NULL,
    verified      TINYINT(1)   NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE category
(
    id   CHAR(36) NOT NULL,
    name VARCHAR(255) NULL,
    type ENUM('Track','Guide') NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE favorite
(
    user_id     CHAR(36) NOT NULL,
    target_id   CHAR(36) NOT NULL,
    target_type ENUM('Track','Guide') NULL,
    created_at  datetime NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (user_id, target_id)
);

CREATE TABLE follow
(
    user_id     CHAR(36) NOT NULL,
    follower_id CHAR(36) NOT NULL,
    created_at  datetime NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (user_id, follower_id)
);

CREATE TABLE guide
(
    id               CHAR(36) NOT NULL,
    user_id          CHAR(36) NULL,
    business_id      CHAR(36) NULL,
    title            VARCHAR(255) NULL,
    `description`    LONGTEXT NULL,
    difficulty       ENUM('Easy','Medium','Hard') NULL,
    price DOUBLE NULL,
    max_participants INT NULL,
    is_active        TINYINT(1)   NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE guide_category
(
    guide_id    CHAR(36) NOT NULL,
    category_id CHAR(36) NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (guide_id, category_id)
);

CREATE TABLE media_post
(
    id         CHAR(36) NOT NULL,
    user_id    CHAR(36) NULL,
    track_id   CHAR(36) NULL,
    guide_id   CHAR(36) NULL,
    media_url  LONGTEXT NOT NULL,
    media_type ENUM('Photo','Video') NULL,
    created_at datetime NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE notification
(
    id         CHAR(36) NOT NULL,
    user_id    CHAR(36) NULL,
    message    LONGTEXT NULL,
    created_at datetime NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE pin
(
    id            CHAR(36) NOT NULL,
    track_id      CHAR(36) NULL,
    latitude DOUBLE NULL,
    longitude DOUBLE NULL,
    type          ENUM('View','Camp','Water','Danger') NULL,
    name          VARCHAR(255) NULL,
    `description` LONGTEXT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE rating
(
    id          CHAR(36) NOT NULL,
    user_id     CHAR(36) NULL,
    target_type ENUM('Track','Guide','Business','Post') NULL,
    target_id   CHAR(36) NULL,
    score       INT NULL,
    comment     LONGTEXT NULL,
    created_at  datetime NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE report
(
    id          CHAR(36) NOT NULL,
    reporter_id CHAR(36) NULL,
    target_id   CHAR(36) NULL,
    target_type ENUM('User','Track','Guide','Business','Comment') NULL,
    status      ENUM('Open','Closed') NULL,
    handled_by  CHAR(36) NULL,
    created_at  datetime NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE sponsor_package
(
    id            CHAR(36) NOT NULL,
    name          VARCHAR(255) NULL,
    price DOUBLE NULL,
    duration      INT NULL,
    `description` LONGTEXT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE sponsorship
(
    id          CHAR(36) NOT NULL,
    business_id CHAR(36) NULL,
    package_id  CHAR(36) NULL,
    status      ENUM('Active','Expired','Cancelled') NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE track
(
    id            CHAR(36) NOT NULL,
    user_id       CHAR(36) NULL,
    name          VARCHAR(255) NULL,
    `description` LONGTEXT NULL,
    difficulty    ENUM('Easy','Medium','Hard') NULL,
    length DOUBLE NULL,
    is_public     TINYINT(1)   NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE track_category
(
    track_id    CHAR(36) NOT NULL,
    category_id CHAR(36) NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (track_id, category_id)
);

CREATE TABLE track_point
(
    id          CHAR(36) NOT NULL,
    track_id    CHAR(36) NULL,
    latitude DOUBLE NULL,
    longitude DOUBLE NULL,
    elevation DOUBLE NULL,
    recorded_at datetime NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE users
(
    id         CHAR(36)     NOT NULL,
    email      VARCHAR(255) NOT NULL,
    name       VARCHAR(255) NOT NULL,
    password   VARCHAR(255) NOT NULL,
    `role`     ENUM('User','Business','Admin') NULL,
    created_at datetime     NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

ALTER TABLE users
    ADD CONSTRAINT email UNIQUE (email);

ALTER TABLE availability_slot
    ADD CONSTRAINT availability_slot_ibfk_1 FOREIGN KEY (guide_id) REFERENCES guide (id) ON DELETE CASCADE;

CREATE INDEX guide_id ON media_post (guide_id);

ALTER TABLE booking
    ADD CONSTRAINT booking_ibfk_1 FOREIGN KEY (guide_id) REFERENCES guide (id) ON DELETE CASCADE;

CREATE INDEX guide_id ON media_post (guide_id);

ALTER TABLE booking
    ADD CONSTRAINT booking_ibfk_2 FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE;

CREATE INDEX user_id ON track (user_id);

ALTER TABLE booking
    ADD CONSTRAINT booking_ibfk_3 FOREIGN KEY (slot_id) REFERENCES availability_slot (id) ON DELETE CASCADE;

CREATE INDEX slot_id ON booking (slot_id);

ALTER TABLE business_profile
    ADD CONSTRAINT business_profile_ibfk_1 FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE;

CREATE INDEX user_id ON track (user_id);

ALTER TABLE favorite
    ADD CONSTRAINT favorite_ibfk_1 FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE;

ALTER TABLE follow
    ADD CONSTRAINT follow_ibfk_1 FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE;

ALTER TABLE follow
    ADD CONSTRAINT follow_ibfk_2 FOREIGN KEY (follower_id) REFERENCES users (id) ON DELETE CASCADE;

CREATE INDEX follower_id ON follow (follower_id);

ALTER TABLE guide_category
    ADD CONSTRAINT guide_category_ibfk_1 FOREIGN KEY (guide_id) REFERENCES guide (id) ON DELETE CASCADE;

ALTER TABLE guide_category
    ADD CONSTRAINT guide_category_ibfk_2 FOREIGN KEY (category_id) REFERENCES category (id) ON DELETE NO ACTION;

CREATE INDEX category_id ON track_category (category_id);

ALTER TABLE guide
    ADD CONSTRAINT guide_ibfk_1 FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE;

CREATE INDEX user_id ON track (user_id);

ALTER TABLE guide
    ADD CONSTRAINT guide_ibfk_2 FOREIGN KEY (business_id) REFERENCES business_profile (id) ON DELETE NO ACTION;

CREATE INDEX business_id ON sponsorship (business_id);

ALTER TABLE media_post
    ADD CONSTRAINT media_post_ibfk_1 FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE NO ACTION;

CREATE INDEX user_id ON track (user_id);

ALTER TABLE media_post
    ADD CONSTRAINT media_post_ibfk_2 FOREIGN KEY (track_id) REFERENCES track (id) ON DELETE NO ACTION;

CREATE INDEX track_id ON track_point (track_id);

ALTER TABLE media_post
    ADD CONSTRAINT media_post_ibfk_3 FOREIGN KEY (guide_id) REFERENCES guide (id) ON DELETE NO ACTION;

CREATE INDEX guide_id ON media_post (guide_id);

ALTER TABLE notification
    ADD CONSTRAINT notification_ibfk_1 FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE;

CREATE INDEX user_id ON track (user_id);

ALTER TABLE pin
    ADD CONSTRAINT pin_ibfk_1 FOREIGN KEY (track_id) REFERENCES track (id) ON DELETE CASCADE;

CREATE INDEX track_id ON track_point (track_id);

ALTER TABLE rating
    ADD CONSTRAINT rating_ibfk_1 FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE;

CREATE INDEX user_id ON track (user_id);

ALTER TABLE report
    ADD CONSTRAINT report_ibfk_1 FOREIGN KEY (reporter_id) REFERENCES users (id) ON DELETE CASCADE;

CREATE INDEX reporter_id ON report (reporter_id);

ALTER TABLE report
    ADD CONSTRAINT report_ibfk_2 FOREIGN KEY (handled_by) REFERENCES users (id) ON DELETE NO ACTION;

CREATE INDEX handled_by ON report (handled_by);

ALTER TABLE sponsorship
    ADD CONSTRAINT sponsorship_ibfk_1 FOREIGN KEY (business_id) REFERENCES business_profile (id) ON DELETE CASCADE;

CREATE INDEX business_id ON sponsorship (business_id);

ALTER TABLE sponsorship
    ADD CONSTRAINT sponsorship_ibfk_2 FOREIGN KEY (package_id) REFERENCES sponsor_package (id) ON DELETE CASCADE;

CREATE INDEX package_id ON sponsorship (package_id);

ALTER TABLE track_category
    ADD CONSTRAINT track_category_ibfk_1 FOREIGN KEY (track_id) REFERENCES track (id) ON DELETE CASCADE;

ALTER TABLE track_category
    ADD CONSTRAINT track_category_ibfk_2 FOREIGN KEY (category_id) REFERENCES category (id) ON DELETE NO ACTION;

CREATE INDEX category_id ON track_category (category_id);

ALTER TABLE track
    ADD CONSTRAINT track_ibfk_1 FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE;

CREATE INDEX user_id ON track (user_id);

ALTER TABLE track_point
    ADD CONSTRAINT track_point_ibfk_1 FOREIGN KEY (track_id) REFERENCES track (id) ON DELETE CASCADE;

CREATE INDEX track_id ON track_point (track_id);