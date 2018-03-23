CREATE TABLE photo (
  id   BIGSERIAL NOT NULL
    CONSTRAINT photo_pkey
    PRIMARY KEY,
  data BYTEA
);

CREATE TABLE member (
  id                BIGSERIAL    NOT NULL
    CONSTRAINT member_pkey
    PRIMARY KEY,
  email             VARCHAR(255) NOT NULL
    CONSTRAINT member_email_key
    UNIQUE,
  first_name        VARCHAR(255) NOT NULL,
  last_name         VARCHAR(255) NOT NULL,
  password_hash     VARCHAR(255) NOT NULL,
  username          VARCHAR(255) NOT NULL
    CONSTRAINT member_username_key
    UNIQUE,
  profilepicture_id BIGINT
    CONSTRAINT member_profilepicture_id_fkey
    REFERENCES photo,
  last_online       TIMESTAMP    NOT NULL DEFAULT NOW()
);

CREATE TABLE member_photo (
  member_id BIGINT NOT NULL
    CONSTRAINT member_photo_mamber_fkey
    REFERENCES member,
  photo_id  BIGINT NOT NULL
    CONSTRAINT mamber_photo_photo_feky
    REFERENCES photo,
  CONSTRAINT member_photo_pkey
  PRIMARY KEY (member_id, photo_id)
);

CREATE TABLE tagtype (
  id   BIGSERIAL    NOT NULL
    CONSTRAINT tagtype_pkey
    PRIMARY KEY,
  name VARCHAR(255) NOT NULL
    CONSTRAINT tagtype_name_key
    UNIQUE,
  showAsAlbum BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE tag (
  id        BIGSERIAL NOT NULL
    CONSTRAINT tag_pkey
    PRIMARY KEY,
  parent_id BIGINT
    CONSTRAINT tag_parent_id_fkey
    REFERENCES tag,
  type_id   BIGINT
    CONSTRAINT tag_type_id_fkey
    REFERENCES tagtype
);

CREATE TABLE member_tag (
  member_id BIGINT NOT NULL
    CONSTRAINT member_tag_member_fkey
    REFERENCES member,
  tag_id    BIGINT NOT NULL
    CONSTRAINT mamber_tag_tag_fkey
    REFERENCES tag,
  CONSTRAINT member_tag_pkey
  PRIMARY KEY (member_id, tag_id)
);

CREATE TABLE photo_tag (
  tag_id   BIGINT NOT NULL
    CONSTRAINT photo_tag_tag_id_fkey
    REFERENCES tag,
  photo_id BIGINT NOT NULL
    CONSTRAINT photo_tag_photo_id_fkey
    REFERENCES photo,
  CONSTRAINT photo_tag_pkey
  PRIMARY KEY (photo_id, tag_id)
);

