create table subjects
(
    subjectName varchar(100) not null,
    subjectID   int          not null,
    constraint subjectID
        unique (subjectID)
);

create table users
(
    ID        int auto_increment
        primary key,
    name      varchar(255) not null,
    email     varchar(255) not null,
    type      varchar(255) not null,
    password  varchar(255) not null,
    subjectID int          null,
    constraint users_email_uindex
        unique (email),
    constraint users_ibfk_1
        foreign key (subjectID) references subjects (subjectID)
);

create table notifications
(
    ID       int auto_increment
        primary key,
    sender   int          not null,
    receiver int          not null,
    subject  varchar(255) not null,
    content  varchar(255) not null,
    sentDate datetime     not null,
    constraint notifications_users_ID_fk
        foreign key (sender) references users (ID),
    constraint notifications_users_ID_fk_2
        foreign key (receiver) references users (ID)
);

create table officehours
(
    ID            int auto_increment
        primary key,
    staffMemberID int          not null,
    status        tinyint(1)   not null,
    day_of_week   varchar(50)  null,
    start_time    time         not null,
    end_time      time         not null,
    location      varchar(255) not null,
    type          varchar(255) not null,
    constraint officehours_staffmembers_ID_fk
        foreign key (staffMemberID) references users (ID)
);

create table reservations
(
    ID         int auto_increment
        primary key,
    reserveeID int        not null,
    staffID    int        not null,
    fromDate   datetime   not null,
    toDate     datetime   not null,
    status     tinyint(1) not null,
    constraint reservations_ibfk_1
        foreign key (reserveeID) references users (ID),
    constraint reservations_ibfk_2
        foreign key (staffID) references users (ID)
);

create index reserveeID
    on reservations (reserveeID);

create index staffID
    on reservations (staffID);

create index subjectID
    on users (subjectID);


