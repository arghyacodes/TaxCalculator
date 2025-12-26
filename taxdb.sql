create database taxcalculator;
drop database taxcalculator;
use taxcalculator;

create table taxpayerlogin(
username varchar(24) primary key,
password varchar(24) not null
);

insert into taxpayerlogin
values
("admin", "admin123");

select * from taxpayerlogin;

create table property(
id int primary key auto_increment,
basevalue numeric not null check(basevalue>0),
area int not null check(area>0),
age int not null check(age>0),
incity varchar(3) not null,
propertytax double(10,2),
constraint checkcity check(incity in('Y','N'))
);
drop table property;

select * from property;

create table vehicle(
regno varchar(10) primary key,
brand varchar(16) not null,
topspeed int not null check (topspeed between 120 and 300),
seatingcapacity int not null check(seatingcapacity between 2 and 50),
fueltype varchar(10) not null,
purchasecost double not null check(purchasecost between 50000 and 1000000),
vehicletax double(10,2),
constraint fuel check(fueltype in('PETROL','DIESEL','CNG/LPG'))
);
select * from vehicle;

drop table vehicle;