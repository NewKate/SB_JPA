
create sequence SEQ_GEN
minvalue 1
maxvalue 9999
start with 1
increment by 1;


create sequence SEQ_GEN_EVENT
minvalue 1
maxvalue 9999
start with 1
increment by 1;



create table opera (
	opera_id serial unique primary key,
    label 		varchar(200) unique not null,
	descriptoin	varchar(2000),
	age 		numeric,
	duration 	varchar(20),
	intermission numeric,
   	type 		varchar(100)
    );
    

create table event (
	event_id serial unique primary key,
    opera_id int not null,
    event_date varchar (50),
    total int, 
    available int, 
    status varchar (50),
    comment varchar(2000),
   foreign key (opera_id) references opera (opera_id)
    );
    

--drop table event;
--drop table opera;
--drop sequence SEQ_GEN;
--drop sequence SEQ_GEN_EVENT;


