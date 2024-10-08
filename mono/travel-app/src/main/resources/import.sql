-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

create sequence travelorder_sequence start with 1 increment by 1;
INSERT INTO TravelOrder(id) VALUES (nextval('travelorder_sequence'));


create sequence flight_sequence start with 1 increment by 1;
INSERT INTO Flight(id,travelorderid,fromairport,toairport) VALUES (nextval('flight_sequence'),1,'GRU','OUTRO');

create sequence hotel_sequence start with 1 increment by 1;
INSERT INTO Hotel(id,travelorderid,nights) VALUES (nextval('hotel_sequence'),1,5);
INSERT INTO Hotel(id,travelorderid,nights) VALUES (nextval('hotel_sequence'),1,10);