drop table if exists account;
drop table if exists internal_transaction;
drop table if exists transaction_status;
drop table if exists external_transaction;
drop table if exists role;
drop table if exists staff;
drop table if exists transaction_unique_id;
drop table if exists faq;
drop table if exists help_center;
drop table if exists help_center_thread;

create table faq(
id int(11) auto_increment not null primary key 
,question varchar(255) not null
,answer text  not null
)engine=innodb;




create table transaction_unique_id(
transaction_id varchar(255) not null primary key 
)engine=innodb;

create table role(
id int(11) not null primary key 
,title varchar(40) not null
)engine=innodb;


insert into role (id,title) values (1,'Amministratore');
insert into role (id,title) values (2,'Verificatore');
insert into role (id,title) values (3,'Help Desk');

create table staff(
id int(11) not null auto_increment primary key 
,created_at datetime not null
,updated_at datetime not null
,email varchar(70) not null unique
,password varchar(255) not null
,name varchar(30) not null
,surname varchar(40) not null
,role_id int(11) not null 
,next_otp_code_after_date datetime not null
,otp_code varchar(10) unique
,otp_code_expires_at datetime
,foreign key (role_id) references role(id)
)engine=innodb;





create table transaction_status(
id int(11) not null primary key 
,title varchar(40) not null
)engine=innodb;

insert into transaction_status (id,title) values (1,'in verifica');
insert into transaction_status (id,title) values (2,'verifica terminata con errore');
insert into transaction_status (id,title) values (3,'verifica terminata con successo');

create table account(
id int(11) not null auto_increment primary key 
,created_at datetime not null
,updated_at datetime not null
,iban varchar(30) not null
,balance decimal(11,2) not null default 0
,email varchar(70) not null unique
,password varchar(255) not null
,name varchar(30) not null
,surname varchar(40) not null
,next_otp_code_after_date datetime  not null
,otp_code varchar(10) unique
,otp_code_expires_at datetime
,credit_card_no varchar(40) not null
,credit_card_cin varchar(5) not null
,credit_card_expires_at varchar(5) not null
)engine=innodb;

create table help_center(
id int(11) auto_increment not null primary key 
,created_at datetime not null
,updated_at datetime not null
,closed_at datetime 
,from_account_id int(11) not null 
,question varchar(255) not null
,assigned_to_id int(11) not null
,foreign key (from_account_id) references account(id)
,foreign key (assigned_to_id) references staff(id)
)engine=innodb;

create table help_center_thread(
id int(11) auto_increment not null primary key 
,created_at datetime not null
,help_center_id int(11) not null 
,question varchar(255) not null
,answer text 
,foreign key (help_center_id) references help_center(id)
)engine=innodb;

create table internal_transaction(
id int(11) not null auto_increment primary key
,created_at datetime not null
,custom_code varchar(255) not null
,transaction_id varchar(255) not null unique
,amount decimal(11,2) not null default 0
,from_account_id int(11) not null 
,to_account_id int(11) not null 
,foreign key (from_account_id) references account(id)
,foreign key (to_account_id) references account(id)
,foreign key (transaction_id) references transaction_unique_id(transaction_id)
,unique (from_account_id,custom_code)
)engine=innodb;


create table external_transaction(
id int(11) not null auto_increment primary key
,created_at datetime not null
,custom_code varchar(255) not null
,transaction_id varchar(255) not null unique
,amount decimal(11,2) not null default 0
,to_account_id int(11) not null 
,transaction_status_id int(11) not null 
,transaction_error_reason varchar(255)
,verify_assigned_to int(11) not null
,customer_name varchar(30) not null
,customer_surname varchar(40) not null
,customer_credit_card_no varchar(40) not null
,customer_credit_card_cin varchar(5) not null
,customer_credit_card_expires_at varchar(5) not null
,foreign key (to_account_id) references account(id)
,foreign key (transaction_status_id) references transaction_status(id)
,foreign key (verify_assigned_to) references staff(id)
,foreign key (transaction_id) references transaction_unique_id(transaction_id)
,unique (from_account_id,custom_code)
)engine=innodb;


