create database event_management;
create table venues (
    venue_id serial PRIMARY KEY not null ,
    venue_name varchar(100) not null ,
    location varchar(100) not null
);
drop table if exists attendees;
create  table attendees (
    attendee_id serial PRIMARY KEY not null ,
    attendee_name varchar(100) not null ,
    email varchar(100) unique not null
);
create table events (
    event_id serial PRIMARY KEY not null ,
    event_name varchar(100) not null ,
    event_date varchar(100) not null ,
    venue_id int not null , foreign key (venue_id) references
    venues(venue_id) on delete cascade
);
create table event_attendee (
    id serial PRIMARY KEY not null ,
    event_id int not null ,
    attendee_id int not null ,
    foreign key (event_id) references events (event_id) on delete cascade ,
    foreign key (attendee_id) references attendees (attendee_id) on delete cascade,
    unique (event_id, attendee_id)
);
select * from event_attendee;
select * from attendees;
select * from events;
select * from venues;

INSERT into venues (venue_name, location) VALUES
('Grand Ballroom', '123 Main St, New York'),
('Convention Center', '456 Oak Ave, Chicago'),
('Riverside Hall', '789 Pine Rd, Los Angeles'),
('Sunset Pavilion', '321 Elm Blvd, Miami'),
('City Plaza', '654 Maple Dr, Seattle'),
('Harmony Theater', '987 Cedar Ln, Boston'),
('Oceanview Center', '159 Beach St, San Diego'),
('Mountain Peak Hall', '753 Valley Way, Denver'),
('Garden Terrace', '246 Flower Path, Atlanta'),
('Starlight Arena', '864 Skyline Blvd, Dallas');

INSERT INTO attendees (attendee_name, email) VALUES
('John Smith', 'john.smith@example.com'),
('Emily Johnson', 'emily.j@example.com'),
('Michael Brown', 'michael.b@example.com'),
('Sarah Davis', 'sarah.d@example.com'),
('David Wilson', 'david.w@example.com'),
('Jessica Miller', 'jessica.m@example.com'),
('Robert Taylor', 'robert.t@example.com'),
('Jennifer Anderson', 'jennifer.a@example.com'),
('Thomas Martinez', 'thomas.m@example.com'),
('Lisa Robinson', 'lisa.r@example.com');

INSERT INTO events (event_name, event_date, venue_id) VALUES
('Tech Conference 2023', '2023-05-15', 1),
('Music Festival', '2023-06-20', 3),
('Business Summit', '2023-07-10', 2),
('Art Exhibition', '2023-08-05', 5),
('Food Fair', '2023-09-12', 4),
('Science Symposium', '2023-10-18', 7),
('Book Launch', '2023-11-22', 6),
('Startup Pitch', '2023-12-05', 8),
('Film Premiere', '2024-01-15', 9),
('Charity Gala', '2024-02-14', 10);

INSERT INTO event_attendee (event_id, attendee_id) VALUES
(1, 1), (1, 2), (1, 3), (1, 4), (1, 5),
(2, 6), (2, 7), (2, 8), (2, 9), (2, 10);