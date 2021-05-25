CREATE DATABASE golf_league;
use golf_league;

CREATE TABLE golfer
(
    id          int auto_increment not null,
    first_name  varchar(128) not null,
    last_name   varchar(128) not null,
    middle_name varchar(128),
    email       varchar(256),
    city        varchar(128),
    state       varchar(128),
    zip         varchar(128),
    home_phone  varchar(128),
    work_phone  varchar(128),
    notes       varchar(256),
    active      boolean               DEFAULT true,
    date_added  DATETIME     not null default NOW(),
    primary key (id)
);

CREATE TABLE admin
(
    username VARCHAR(128) not null,
    password VARCHAR(1024) not null,
    golfer_id int not null,
    league_id int not null,
    date_added  DATETIME     not null default NOW(),
    FOREIGN KEY (golfer_id) REFERENCES golfer (id),
    FOREIGN KEY (league_id) REFERENCES league (id),
    primary key (username)
);

CREATE TABLE league
(
    id   int auto_increment not null,
    name varchar(256) not null,
    primary key (id)
);

CREATE TABLE team
(
    id          int auto_increment not null,
    league_id   int not null,
    name        varchar(128),
    description varchar(128),
    primary key (id),
    FOREIGN KEY (league_id) REFERENCES league (id)
);

CREATE TABLE team_member
(
    team_id   int not null,
    golfer_id int not null,
    primary key (team_id, golfer_id),
    FOREIGN KEY (team_id) REFERENCES team (id),
    FOREIGN KEY (golfer_id) REFERENCES golfer (id)
);

CREATE TABLE flight
(
    id        int  not null auto_increment,
    league_id int  not null,
    start     timestamp not null,
    end       timestamp not null,
    primary key (id),
    FOREIGN KEY (league_id) references league (id),
);

CREATE TABLE course
(
    id   int auto_increment not null,
    name varchar(256) not null,
    primary key (id)
);

CREATE TABLE nine
(
    course_id int          not null,
    name      varchar(256) not null,
    primary key (course_id, name),
    FOREIGN KEY (course_id) REFERENCES course (id)
);

CREATE TABLE hole
(
    id          int auto_increment not null,
    course_id   int          not null,
    nine_name   varchar(256) not null,
    hole_number int          not null,
    handicap int      not null,
    par     int      not null,
    yardage int not null,
    created     DATETIME     not null DEFAULT NOW(),
    primary key (id),
    FOREIGN KEY (course_id, nine_name) REFERENCES nine (course_id, name)
);

CREATE TABLE season
(
    id        int auto_increment not null,
    year int not null,
    league_id int not null,
    course_id int not null,
    primary key (id),
    FOREIGN KEY (league_id) REFERENCES league (id),
    FOREIGN KEY (course_id) REFERENCES course (id),
    UNIQUE (year, league_id, course_id)
);

CREATE TABLE event_type
(
    name        VARCHAR(128) NOT NULL,
    description VARCHAR(256),
    PRIMARY KEY (name)
);

CREATE TABLE event
(
    id         int          not null auto_increment,
    season_id  int          not null,
    week       int          not null,
    day        DATE         not null,
    event_type VARCHAR(128) not null,
    notes      VARCHAR(256),
    PRIMARY KEY (id),
    FOREIGN KEY (season_id) REFERENCES season (id),
    FOREIGN KEY (event_type) REFERENCES event_type (name)
);

CREATE TABLE tee_time
(
    flight_id  int          not null,
    slot        int         not null,
    time timestamp not null,
    PRIMARY KEY (flight_id, slot)
);

CREATE TABLE event_match
(
    id int not null auto_increment,
    event_id int not null,
    course_id int not null,
    nine varchar(256) not null,
    flight_id int not null,
    slot int not null,
    PRIMARY KEY (id),
    FOREIGN KEY (event_id) REFERENCES event (id),
    FOREIGN KEY (course_id, nine) REFERENCES nine (course_id, name),
    FOREIGN KEY (flight_id, slot) REFERENCES tee_time (flight_id, slot)
);

CREATE TABLE team_match
(
    match_id int not null,
    team_id  int not null,
    home BOOLEAN not null DEFAULT 0,
    date_played DATETIME not null DEFAULT NOW(),
    PRIMARY KEY (match_id, team_id),
    FOREIGN KEY (match_id) REFERENCES event_match (id),
    FOREIGN KEY (team_id) REFERENCES team (id)
);

CREATE TABLE player_handicap
(
    id        INT auto_increment not null,
    golfer_id int      not null,
    created   DATETIME NOT NULL default NOW(),
    handicap  int      not null,
    PRIMARY KEY (id),
    FOREIGN KEY (golfer_id) references golfer (id)
);

CREATE TABLE round
(
    id        int not null auto_increment,
    match_id  int not null,
    golfer_id int not null,
    primary key (id),
    FOREIGN KEY (match_id) references event_match (id),
    FOREIGN KEY (golfer_id) REFERENCES golfer (id),
    UNIQUE (match_id, golfer_id)
);

CREATE TABLE score
(
    id        int not null auto_increment,
    round_id int not null,
    hole_id  int not null,
    score    int not null,
    PRIMARY KEY (id),
    UNIQUE (round_id, hole_id),
    FOREIGN KEY (round_id) REFERENCES round (id),
    FOREIGN KEY (hole_id) REFERENCES hole (id)
);

