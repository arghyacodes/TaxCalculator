create database wbsedcl;
use wbsedcl;

create table Customer(
CustomerId int primary key,
Name varchar(50) not null,
Address varchar(100) not null,
PhoneNumber varchar(10) not null,
Email varchar(50) unique
);

insert into Customer
values
(164080,"Swapan Kumar Ghoshal","Ramnagar,Tarakeswar,Hooghly","8995938993","skg888@gmail.com"),
(165080,"Sandip Chatterjee","Tagra,Tarakeswar,Hooghly","8995943993","sandip9732@gmail.com"),
(164082,"Basudev Roy","Ramnagar,Tarakeswar,Hooghly","7095938993","broy2007@yahoo.com"),
(165084,"Swapan Ghoshal","Tagra,Tarakeswar,Hooghly","8995888993","swapan2011@hotmail.com"),
(160080,"Naba Kumar Bhattacharya","Khardaha,North 24 Parganas","9474938993","nil.1960@gmail.com"),
(165088,"Swapan Chatterjee","Tagra,Tarakeswar,Hooghly","6279000656","sctagra05@gmail.com");

create table Meter(
MeterId int primary key,
CustomerId int,
InstallationDate date not null,
LastReadingDate date not null,
foreign key (CustomerId) references Customer(CustomerId) on delete cascade
);

insert into Meter
values
(1210,164080,"2002-02-02","2025-10-18"),
(1011,164082,"2000-03-02","2025-11-18"),
(2023,165084,"2007-12-02","2025-08-28"),
(1820,165080,"2005-07-22","2025-10-18"),
(9874,165080,"2024-06-12","2025-10-18"),
(6016,160080,"2005-05-11","2025-10-24"),
(4096,165088,"2010-02-02","2025-08-28");

create table ElectricityUsage(
UsageId int primary key,
MeterId int,
ReadingDate date not null,
UsageUnits numeric check (UsageUnits>=0),
foreign key (MeterId) references Meter(MeterId) on delete cascade
);

insert into ElectricityUsage
values
(1070,1210,"2025-10-18",356),
(1071,1011,"2025-11-18",196),
(1072,2023,"2025-08-28",155),
(1073,1820,"2025-10-18",432),
(1074,9874,"2025-10-18",56),
(1075,6016,"2025-10-24",256),
(1076,4096,"2025-08-28",200);

create table Bill(
BillId int primary key,
MeterId int,
BillDate date not null,
AmountDue numeric check (AmountDue>=0),
DueDate date not null,
Paid boolean not null default 0,
foreign key (MeterId) references Meter(MeterId) on delete cascade
);

insert into Bill
values
(9070,1210,"2025-10-18",1780,"2025-11-18",1),
(9071,1011,"2025-11-18",980,"2025-12-18",0),
(9072,2023,"2025-08-28",775,"2025-09-28",1),
(9073,1820,"2025-10-18",2160,"2025-11-18",1),
(9074,9874,"2025-10-18",280,"2025-11-18",1),
(9075,6016,"2025-10-24",1280,"2025-11-24",0),
(9076,4096,"2025-08-28",3400,"2025-09-28",0);

create table Payment(
PaymentId int primary key,
BillId int,
PaymentDate date not null,
AmountPaid numeric check(AmountPaid>=0),
foreign key (BillId) references Bill(BillId) on delete cascade
);

insert into Payment
values
(1001,9070,"2025-10-28",1780),
(1003,9072,"2025-09-09",775),
(1004,9073,"2025-10-29",2160),
(1005,9074,"2025-11-03",280);

-- Fetch all the records --
select * from Customer;
select * from Meter;
select * from ElectricityUsage;
select * from Bill;
select * from Payment;

select MeterId, sum(UsageUnits) as TotalUsage
from ElectricityUsage
group by MeterId
having TotalUsage>200;

select c.CustomerId, c.Name, sum(b.AmountDue) as TotalUnpaid
from Customer c
join Meter m on c.CustomerId=m.CustomerId
join Bill b on m.MeterId=b.MeterId
where b.Paid=0
group by c.CustomerId, c.Name
order by TotalUnpaid desc;


select BillId,MeterId,BillDate,AmountDue,DueDate,
case when Paid=1 then 'Paid' else 'Unpaid'
end as PaymentStatus from Bill
order by BillDate;

select distinct c.CustomerId,c.Name,c.Address,c.PhoneNumber, c.Email
from Customer c
join Meter m on c.CustomerId=m.CustomerId
where m.InstallationDate>'2023-12-31';

select m.MeterId, m.LastReadingDate, sum(eu.UsageUnits) as TotalUsage
from Meter m
join ElectricityUsage eu on m.MeterId = eu.MeterId
group by m.MeterId, m.LastReadingDate
order by TotalUsage desc;