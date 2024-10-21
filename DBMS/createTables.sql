-- To create tables
-- launch:
-- psql -h macppc minibuses oleg -f createTables.sql
--
create table if not exists buses.drivers (
    id bigint generated always as identity (increment 1 start 1)
                         primary key not null,
    last_name varchar(50) not null,
    first_name varchar(50) null,
    middle_name varchar(50) not null,
    date_birth date null,
    drive_license varchar not null,
    drive_license_valid date not null,
    home_address varchar not null,
    telephone varchar(50) not null,
    date_added timestamp without time zone not null,
    date_modified timestamp without time zone null,
    date_fired timestamp without time zone null,
    status integer not null
);

create table if not exists buses.buses (
    id bigint generated always as identity (increment 1 start 1)
                         primary key not null,
    model varchar(50) not null,
    description varchar not null,
    reg_number varchar(50) not null,
    capacity integer not null,
    date_made date not null,
    vin varchar(50) not null,
    date_added timestamp without time zone not null,
    date_modified timestamp without time zone null,
    date_sold timestamp without time zone null,
    status integer not null
);

create table if not exists financial.ways (
    id bigint generated always as identity (increment 1 start 1)
                         primary key not null,
    way_description varchar not null,
    way_number varchar(50) not null,
    way_length numeric(18,5) not null,
    way_cost   numeric(18,5) not null,
    date_added timestamp without time zone not null,
    date_modified timestamp without time zone null,
    date_deleted timestamp without time zone null
);

create table if not exists financial.description_ways (
    way_id bigint not null,
    part_number integer not null,
    name_street varchar not null,
    part_start  varchar not null,
    part_end    varchar not null,
    maneuver_2next_part varchar not null,
    part_length numeric(18,5) not null,
    date_added timestamp without time zone not null,
    date_modified timestamp without time zone null,
    date_deleted timestamp without time zone null,
    CONSTRAINT fk_ways
      FOREIGN KEY(way_id)
        REFERENCES financial.ways (id)
);

create table if not exists administrators.work_onway (
    way_id    bigint not null,
    driver_id bigint not null,
    bus_id    bigint not null,
    date_work_day date not null,
    time_start time without time zone null,
    time_end   time without time zone null,
    CONSTRAINT fk_ways
      FOREIGN KEY(way_id)
        REFERENCES financial.ways (id),
    CONSTRAINT fk_driver
      FOREIGN KEY(driver_id)
        REFERENCES buses.drivers (id),
    CONSTRAINT fk_bus
      FOREIGN KEY(bus_id)
        REFERENCES buses.buses (id)
);

create table if not exists administrators.medical (
    driver_id bigint not null,
    who_made_medical varchar not null,
    date_examination timestamp without time zone not null,
    status integer not null,
    description_status varchar not null,
    CONSTRAINT fk_medical
      FOREIGN KEY(driver_id)
        REFERENCES buses.drivers (id)
);


create table if not exists buses.tech_control (
    bus_id bigint not null,
    who_made_tech_control  varchar not null,
    date_tech_control      date not null,
    date_next_tech_control date not null,
    CONSTRAINT fk_tech_control
      FOREIGN KEY(bus_id)
        REFERENCES buses.buses (id)
);

create table if not exists passengers.passengers (
    id bigint generated always as identity (increment 1 start 1)
                         primary key not null,
    nic         varchar(50) not null,
    last_name   varchar(50) null,
    first_name  varchar(50) null,
    middle_name varchar(50) null,
    password    varchar(50) not null,
    email       varchar     not null,
    telephone   varchar(50) null,
    date_added    timestamp without time zone not null,
    date_modified timestamp without time zone null,
    date_deleted  timestamp without time zone null,
    status integer not null
);

create table if not exists financial.payment (
    passenger_id bigint not null,
    driver_id    bigint not null,
    bus_id       bigint not null,
    way_id       bigint not null,
    date_payment timestamp without time zone not null,
    amount       numeric(18,5) not null,
    amount_info  varchar not null,
    CONSTRAINT fk_passenger
      FOREIGN KEY(passenger_id)
        REFERENCES passengers.passengers (id),
    CONSTRAINT fk_driver
      FOREIGN KEY(driver_id)
        REFERENCES buses.drivers (id),
    CONSTRAINT fk_bus
      FOREIGN KEY(bus_id)
        REFERENCES buses.buses (id),
    CONSTRAINT fk_ways
      FOREIGN KEY(way_id)
        REFERENCES financial.ways (id)
);
