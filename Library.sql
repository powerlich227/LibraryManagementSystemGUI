-- If the database "LIBRARY" already exists, then delete it.
DROP DATABASE IF EXISTS LIBRARY;
-- Create the Database "LIBRARY"
CREATE DATABASE LIBRARY;
-- Set the currently active database to be "LIBRARY"
USE LIBRARY;

DROP TABLE IF EXISTS BOOK;
CREATE TABLE BOOK (
  Isbn        VARCHAR(25) NOT NULL,
  Title       text,
  CONSTRAINT pk_book PRIMARY KEY (Isbn)
);

drop table if exists AUTHORS;
CREATE TABLE AUTHORS (
    Author_id   VARCHAR(25) NOT NULL,
    Name        text,
    CONSTRAINT pk_authors PRIMARY KEY (Author_id)
);

drop table if exists BOOK_AUTHORS;
create table BOOK_AUTHORS (
    Author_id   VARCHAR(25) NOT NULL,
    Isbn        VARCHAR(25) NOT NULL,
    CONSTRAINT pk_book_authors PRIMARY KEY (Author_id, Isbn),
    CONSTRAINT fk_book_authors FOREIGN KEY (Isbn) references BOOK(Isbn),
    CONSTRAINT fk_book_authors_2 FOREIGN KEY (Author_id) references AUTHORS(Author_id)
);

drop table if exists borrower;
create table borrower (
    card_id int not null auto_increment,
    ssn VARCHAR(25) not null,
    bname text not null, 
    address text not null,
    phone VARCHAR(25),
    CONSTRAINT pk_borrower PRIMARY KEY(card_id),
    CONSTRAINT uk_borrower UNIQUE key(ssn)
);

drop table if EXISTS book_loans;
create table book_loans (
    loan_id int not null auto_increment,
    isbn VARCHAR(25),
    card_id int,
    date_out DATE,
    due_date DATE,
    date_in DATE,
    CONSTRAINT pk_book_loans PRIMARY KEY (loan_id),
    CONSTRAINT fk_book_loans FOREIGN key (isbn) REFERENCES book(isbn),
    CONSTRAINT fk_book_loans2 FOREIGN KEY (card_id) REFERENCES borrower(card_id)
);

drop table if EXISTS fines;
create table fines (
    loan_id int not null,
    fine_amt text,
    paid boolean,
    CONSTRAINT pk_fines PRIMARY key (loan_id),
    CONSTRAINT fk_fines FOREIGN key (loan_id) REFERENCES book_loans(loan_id)
);


-- Insert all records
Insert into BOOK values 
('0195153448', 'Classical Mythology'),
('0002005018', 'Clara Callan: A Novel'),
('0060973129', 'Decision In Normandy'),
('0374157065', 'Flu: The Story Of The Great Influenza Pandemic Of 1918 And The Search For The Virus That Caused It'),
('0393045218', 'The Mummies Of Urumchi'),
('0399135782', 'The Kitchen God''s Wife'),
('0425176428', 'What If?: The World''s Foremost Military Historians Imagine What Might Have Been'),
('0671870432', 'Pleading Guilty'),
('0679425608', 'Under The Black Flag: The Romance And The Reality Of Life Among The Pirates'),
('074322678X', 'Where You''ll Find Me: And Other Stories');

Insert into AUTHORS values 
('1', 'Mark P.O. Morford, Robert J. Lenardon'),
('2', 'Richard Bruce Wright'),
('3', 'Carlo D''Este'),
('4', 'Gina Kolata'),
('5', 'Elizabeth Wayland Barber'),
('6', 'Amy Tan'),
('7', 'Robert Cowley'),
('8', 'Scott Turow'),
('9', 'David Cordingly'),
('10', 'Ann Beattie');


Insert into BOOK_AUTHORS values 
('1', '0195153448'),
('2', '0002005018'),
('3', '0060973129'),
('4', '0374157065'),
('5', '0393045218'),
('6', '0399135782'),
('7', '0425176428'),
('8', '0671870432'),
('9', '0679425608'),
('10', '074322678X');


insert into borrower (ssn, bname, address, phone) values
('850-47-3740', 'Mark', '5677 Coolidge Street', '(469) 904-1438'),
('256-95-4382', 'Eric', '9062 Schurz Drive', '(214) 701-8127'),
('256-51-5268', 'Robert', '7786 Sachs Place', '(214) 875-5911'),
('976-95-2914', 'Donna', '3 Magdeline Terrace', '(214) 800-6127'),
('906-63-3588', 'Linda', '41260 Kedzie Terrace', '(469) 213-2549'),
('206-26-7141', 'Judy', '6583 Manitowish Hill', '(972) 898-5909'),
('898-61-3567', 'Deborah', '2 Hermina Trail', '(972) 900-2951'),
('644-25-0492', 'Adam', '05671 Norway Maple Court', '(469) 743-0176'),
('678-12-4697', 'Pamela', '1848 Fordem Avenue', '(972) 247-9852'),
('562-33-7951', 'Daniel', '400 Cody Lane', '(214) 891-7743');

insert into book_loans (isbn, card_id, date_out, due_date, date_in) values
('074322678X', '1', '2019-01-01', '2019-01-15', '2019-01-02'),
('0679425608', '2', '2019-01-01', '2019-01-15', NULL),              -- fines, not check in yet
('0671870432', '3', '2019-01-01', '2019-01-15', '2019-01-16'),      -- fines, paid
('0425176428', '4', '2019-01-02', '2019-01-16', '2019-02-03'),      -- fines, not paid yet
('0399135782', '5', '2019-01-02', '2019-01-16', '2019-01-15'),
('0393045218', '6', '2019-01-03', '2019-01-17', '2019-01-19'),      -- fines, paid
('0374157065', '7', '2019-01-04', '2019-01-18', NULL),              -- fines, not check in yet
('0060973129', '8', '2019-01-04', '2019-01-18', NULL),              -- fines, not check in yet
('0002005018', '9', '2019-01-05', '2019-01-19', '2019-01-05'),
('0002005018', '2', '2019-01-05', '2019-01-19', '2019-01-22'),      -- fines, not paid yet
('0195153448', '10', '2019-02-24', '2019-03-10', NULL),             -- no fines, not check in yet
('074322678X', '2', '2019-02-25', '2019-03-11', NULL);              -- no fines, not check in yet

insert into fines VALUES 
('3', '0.25', '1'),
('4', '4.25', '0'),
('6', '0.50', '1'),
('8', '9.25', '0'),
('10', '0.75', '0');