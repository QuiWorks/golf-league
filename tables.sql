CREATE TABLE golfer
(
    id      int auto_increment not null,
    first_name  varchar(128)       not null,
    last_name   varchar(128)       not null,
    middle_name varchar(128),
    email       varchar(256),
    city        varchar(128),
    state       varchar(128),
    zip         varchar(128),
    home_phone  varchar(128),
    work_phone  varchar(128),
    status      int                         default 1,
    paid        tinyint                     default 0,
    new         tinyint                     default 0,
    notes       varchar(256),
    date_added  DATETIME           not null default NOW(),
    primary key (id)
);

CREATE TABLE team_member
(
    team_id int not null,
    golfer_id  int not null,
    primary key (team_id, golfer_id),
    FOREIGN KEY (team_id) REFERENCES team (id),
    FOREIGN KEY (golfer_id) REFERENCES golfer (id)
);

CREATE TABLE team
(
    id          int auto_increment not null,
    league_id int not null,
    name        varchar(128),
    description varchar(128),
    primary key (id),
    FOREIGN KEY (league_id) REFERENCES league (id)
);

CREATE TABLE team_flight
(
    team_id int not null,
    league_id int not null,
    year YEAR not null,
    PRIMARY KEY (team_id, league_id, year),
    FOREIGN KEY (team_id) references team (id),
    FOREIGN KEY (league_id, year) references flight (league_id, year)
);

CREATE TABLE flight
(
    league_id int  not null,
    year      YEAR not null,
    start     TIME not null,
    end       TIME not null,
    primary key (league_id, year, start, end),
    FOREIGN KEY (league_id, year) references season (league_id, year)
);

CREATE TABLE league
(
    id   int auto_increment not null,
    name varchar(256)       not null,
    primary key (id)
);

CREATE TABLE season
(
    year      YEAR not null,
    league_id int  not null,
    course_id int  not null,
    primary key (year, league_id),
    FOREIGN KEY (league_id) REFERENCES league (id),
    FOREIGN KEY (course_id) REFERENCES course (id)
);

CREATE TABLE event
(
    year    YEAR not null,
    day     DATE not null,
    league_id int  not null,
    event_type VARCHAR(128) not null,
    notes VARCHAR(256),
    PRIMARY KEY (year, day),
    FOREIGN KEY (league_id) REFERENCES league (id),
    FOREIGN KEY (event_type) REFERENCES event_type (name)
);

CREATE TABLE event_type
(
    name VARCHAR(128) NOT NULL,
    description VARCHAR(256),
    PRIMARY KEY (name)
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
    primary key (course_id, name)
);

CREATE TABLE hole
(
    id int not null,
    course_id   int          not null,
    nine_name   varchar(256) not null,
    hole_number int          not null,
    primary key (id),
    FOREIGN KEY (course_id, nine_name) REFERENCES nine (course_id, name),
    UNIQUE (course_id, nine_name, hole_number)
);

CREATE TABLE hole_handicap
(
    hole_id int not null,
    created DATETIME not null DEFAULT NOW(),
    handicap int not null,
    PRIMARY KEY (hole_id, created),
    FOREIGN KEY (hole_id) REFERENCES hole (id)
);

CREATE TABLE hole_par
(
    hole_id int not null,
    created DATETIME not null DEFAULT NOW(),
    par int not null,
    PRIMARY KEY (hole_id, created),
    FOREIGN KEY (hole_id) REFERENCES hole (id)
);

CREATE TABLE player_handicap
(
    id INT auto_increment not null,
    golfer_id int not null,
    created DATETIME NOT NULL default NOW(),
    handicap int not null,
    PRIMARY KEY (id),
    FOREIGN KEY (golfer_id) references golfer (id)
);

CREATE TABLE round
(
    year YEAR NOT NULL,
    day DATE NOT NULL,
    league_id INT not null,
    golfer_id int not null,
    primary key (year, day, league_id, golfer_id),
    FOREIGN KEY (league_id, year, day) references event (league_id, year, day),
    FOREIGN KEY (golfer_id) REFERENCES golfer (id)
);

CREATE TABLE score
(
    year YEAR NOT NULL,
    day DATE NOT NULL,
    league_id INT not null,
    golfer_id int not null,
    hole_id int not null,
    score int not null,
    PRIMARY KEY (year, day, league_id, golfer_id, hole_id),
    FOREIGN KEY (year, day, league_id, golfer_id) REFERENCES round (year, day, league_id, golfer_id),
    FOREIGN KEY (hole_id) REFERENCES hole ()
)

