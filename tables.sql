CREATE TABLE golfer
(
    id          int auto_increment not null,
    first_name  varchar(128)       not null,
    last_name   varchar(128)       not null,
    middle_name varchar(128),
    email       varchar(256),
    city        varchar(128),
    state       varchar(128),
    zip         varchar(128),
    home_phone  varchar(128),
    work_phone  varchar(128),
    notes       varchar(256),
    date_added  DATETIME           not null default NOW(),
    primary key (id)
);

CREATE TABLE golfer_status
(
    golfer_id int          not null,
    status_id varchar(128) not null,
    updated   datetime     not null default NOW(),
    primary key (golfer_id, status_id),
    FOREIGN KEY (golfer_id) REFERENCES golfer (id),
    FOREIGN KEY (status_id) REFERENCES status (name)
);

CREATE TABLE status
(
    name        varchar(128) not null,
    description varchar(256),
    primary key (name)
);

CREATE TABLE team_member
(
    team_id   int not null,
    golfer_id int not null,
    primary key (team_id, golfer_id),
    FOREIGN KEY (team_id) REFERENCES team (id),
    FOREIGN KEY (golfer_id) REFERENCES golfer (id)
);

CREATE TABLE team
(
    id          int auto_increment not null,
    league_id   int                not null,
    name        varchar(128),
    description varchar(128),
    primary key (id),
    FOREIGN KEY (league_id) REFERENCES league (id)
);

CREATE TABLE team_flight
(
    team_id   int not null,
    flight_id int not null,
    PRIMARY KEY (team_id, flight_id),
    FOREIGN KEY (team_id) references team (id),
    FOREIGN KEY (flight_id) references flight (id)
);

CREATE TABLE flight
(
    id        int  not null auto_increment,
    league_id int  not null,
    start     TIME not null,
    end       TIME not null,
    primary key (id),
    FOREIGN KEY (league_id) references league (id),
    UNIQUE (league_id, start, end)
);

CREATE TABLE league
(
    id   int auto_increment not null,
    name varchar(256)       not null,
    primary key (id)
);

CREATE TABLE season
(
    id        int auto_increment not null,
    year      YEAR               not null,
    league_id int                not null,
    course_id int                not null,
    primary key (id),
    FOREIGN KEY (league_id) REFERENCES league (id),
    FOREIGN KEY (course_id) REFERENCES course (id),
    UNIQUE (year, league_id, course_id)
);

CREATE TABLE event
(
    id         int          not null auto_increment,
    season_id  int          not null,
    day        DATE         not null,
    event_type VARCHAR(128) not null,
    notes      VARCHAR(256),
    PRIMARY KEY (id),
    FOREIGN KEY (season_id) REFERENCES season (id),
    FOREIGN KEY (event_type) REFERENCES event_type (name)
);

CREATE TABLE event_type
(
    name        VARCHAR(128) NOT NULL,
    description VARCHAR(256),
    PRIMARY KEY (name)
);


CREATE TABLE team_event
(
    event_id         int          not null auto_increment,
    team_id  int          not null,
    PRIMARY KEY (event_id, team_id),
    FOREIGN KEY (event_id) REFERENCES event (id),
    FOREIGN KEY (team_id) REFERENCES team (id)
);

CREATE TABLE course
(
    id   int auto_increment not null,
    name varchar(256)       not null,
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
    id          int          not null,
    course_id   int          not null,
    nine_name   varchar(256) not null,
    hole_number int          not null,
    primary key (id),
    FOREIGN KEY (course_id, nine_name) REFERENCES nine (course_id, name),
    UNIQUE (course_id, nine_name, hole_number)
);

CREATE TABLE hole_handicap
(
    hole_id  int      not null,
    created  DATETIME not null DEFAULT NOW(),
    handicap int      not null,
    PRIMARY KEY (hole_id, created),
    FOREIGN KEY (hole_id) REFERENCES hole (id)
);

CREATE TABLE hole_par
(
    hole_id int      not null,
    created DATETIME not null DEFAULT NOW(),
    par     int      not null,
    PRIMARY KEY (hole_id, created),
    FOREIGN KEY (hole_id) REFERENCES hole (id)
);

CREATE TABLE player_handicap
(
    id        INT auto_increment not null,
    golfer_id int                not null,
    created   DATETIME           NOT NULL default NOW(),
    handicap  int                not null,
    PRIMARY KEY (id),
    FOREIGN KEY (golfer_id) references golfer (id)
);

CREATE TABLE round
(
    id        int not null auto_increment,
    event_id  int not null,
    golfer_id int not null,
    primary key (id),
    FOREIGN KEY (event_id) references event (id),
    FOREIGN KEY (golfer_id) REFERENCES golfer (id),
    UNIQUE (event_id, golfer_id)
);

CREATE TABLE score
(
    round_id int not null,
    hole_id  int not null,
    score    int not null,
    PRIMARY KEY (round_id, hole_id),
    FOREIGN KEY (round_id) REFERENCES round (id),
    FOREIGN KEY (hole_id) REFERENCES hole (id)
)

