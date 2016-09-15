-- Table: flow_user

-- DROP TABLE flow_user;

CREATE TABLE flow_user
(
  id bigint NOT NULL,
  activated boolean NOT NULL,
  activationkey character varying(100),
  avatar character varying(255),
  bio character varying(255),
  email character varying(50),
  address character varying(255),
  latitude double precision,
  longitude double precision,
  name character varying(255) NOT NULL,
  password character varying(500),
  phone character varying(255),
  resetpasswordkey character varying(100),
  username character varying(50) NOT NULL,
  created_date timestamp without time zone NOT NULL,
  role integer,
  CONSTRAINT flow_user_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE flow_user
  OWNER TO postgres;


CREATE TABLE user_authority (
    user_id bigint NOT NULL,
    authority VARCHAR(50) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES flow_user (id),
    FOREIGN KEY (authority) REFERENCES authority (name)
    --UNIQUE INDEX user_authority_idx_1 (username, authority)
);


CREATE TABLE oauth_access_token (
  token_id VARCHAR(256) DEFAULT NULL,
  token bytea,
  authentication_id VARCHAR(256) DEFAULT NULL,
  user_name VARCHAR(256) DEFAULT NULL,
  client_id VARCHAR(256) DEFAULT NULL,
  authentication bytea,
  refresh_token VARCHAR(256) DEFAULT NULL
);

CREATE TABLE oauth_refresh_token (
  token_id VARCHAR(256) DEFAULT NULL,
  token bytea,
  authentication bytea
);

-- Table: feed

-- DROP TABLE feed;

CREATE TABLE feed
(
  id bigint NOT NULL,
  userid bigint,
  CONSTRAINT feed_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE feed
  OWNER TO postgres;

  
-- Table: feed_workout

-- DROP TABLE feed_workout;

CREATE TABLE feed_workout
(
  feed_id bigint NOT NULL,
  workout_id bigint NOT NULL,
  CONSTRAINT fk_hlbxudt7uxxkjilg1kexmycgp FOREIGN KEY (feed_id)
      REFERENCES feed (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT uk_i48ddw5viomph5l8mpi7lekij UNIQUE (workout_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE feed_workout
  OWNER TO postgres;

-- Table: follow

-- DROP TABLE follow;

CREATE TABLE follow
(
  id bigint NOT NULL,
  created_date timestamp without time zone NOT NULL,
  relationship character varying(255) NOT NULL,
  target_id bigint NOT NULL,
  user_id bigint NOT NULL
)
WITH (
  OIDS=FALSE
);
ALTER TABLE follow
  OWNER TO postgres;

  -- Table: journal

-- DROP TABLE journal;

CREATE TABLE journal
(
  id bigint NOT NULL,
  created_date timestamp without time zone NOT NULL,
  entry character varying(255) NOT NULL,
  owner_id bigint,
  title character varying(255) NOT NULL,
  owner_username character varying(50),
  CONSTRAINT fk_1g8juodpt8oirghl259ch3mho FOREIGN KEY (owner_id)
      REFERENCES flow_user (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE journal
  OWNER TO postgres;
  
-- Table: media

-- DROP TABLE media;

CREATE TABLE media
(
  id bigint NOT NULL,
  created_date timestamp without time zone NOT NULL,
  caption character varying(255) NOT NULL,
  filename character varying(255) NOT NULL,
  owner_id bigint NOT NULL,
  workout_id bigint,
  thumbnail_file character varying(255) NOT NULL
)
WITH (
  OIDS=FALSE
);
ALTER TABLE media
  OWNER TO postgres;
  
-- Table: notification

-- DROP TABLE notification;

CREATE TABLE notification
(
  id bigint NOT NULL,
  created_date timestamp without time zone NOT NULL,
  notify_type character varying(255) NOT NULL,
  user_id bigint NOT NULL,
  notifytype character varying(255) NOT NULL
)
WITH (
  OIDS=FALSE
);
ALTER TABLE notification
  OWNER TO postgres;
  
-- Table: workout

-- DROP TABLE workout;

CREATE TABLE workout
(
  id bigint NOT NULL,
  created_date timestamp without time zone NOT NULL,
  description character varying(1024),
  address character varying(255),
  latitude double precision,
  longitude double precision,
  media_id bigint,
  owner_id bigint,
  owner_username character varying(50),
  CONSTRAINT fk_hua5pddsoep3q1a5q0v4f3cas FOREIGN KEY (owner_id)
      REFERENCES flow_user (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE workout
  OWNER TO postgres;

-- Table: workout_activities

-- DROP TABLE workout_activities;

CREATE TABLE workout_activities
(
  rworkout_id bigint NOT NULL,
  weight_amount character varying(255),
  workout_type character varying(255)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE workout_activities
  OWNER TO postgres;
  
-- Table: workout_favourite

-- DROP TABLE workout_favourite;

CREATE TABLE workout_favourite
(
  id bigint NOT NULL,
  created_date timestamp without time zone NOT NULL,
  user_id bigint NOT NULL,
  workout_id bigint NOT NULL,
  CONSTRAINT workout_favourite_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE workout_favourite
  OWNER TO postgres;
