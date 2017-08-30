create database lib

use Library

create table Login
(
	libType char(1),
	libId int,
	libPass int,
	libName varchar(10),
	libAdd varchar(30),
	libMob varchar(10)
)

ALTER TABLE Login
ALTER COLUMN libPass varchar(10)

select * from Login

insert into Login values('A',1,'admin1.P','Shweta','Andheri',7787628370)
insert into Login values('A',2,'admin2.P','Rohit','Khar',9182736451)
insert into Login values('U',101,'abc123','Shriya','14/101, Panvel',7986354204)
insert into Login values('U',102,'def456','Pooja','Kalyan',1029384756)

create table Available
(
	bkId int,
	bkType char(1),
	bkCat char(20),
	bkName varchar(10),
	bkAuthor varchar(10),
	bkBb char(1),
	bkPeriod int,
	bkAvail int
)
select * from Available

EXEC sp_RENAME 'Issued.ReturnDate', 'Return Date', 'COLUMN'

create table Issued
(
	StudentID int,
	BookID int,
	BookType char(1),
	Category char(20),
	Name varchar(10),
	Author varchar(10),
	BookBank char(1),
	IssueDate varchar(10),
	ReturnDate varchar(10),
	Penalty int
)

select * from Issued

ALTER TABLE "Issued" DROP COLUMN "Penalty";