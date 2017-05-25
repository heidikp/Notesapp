CREATE DATABASE notes;

CREATE TABLE patient (
Name varchar(30) ,
Place varchar(2),
Weeks varchar(4),
Para varchar(10),
Allergies varchar(30,
Info varchar(500),
Notes varchar(500)
PRIMARY KEY (Name)
);

-Insert a patient to test the program

INSERT INTO patient
(Name, Place, Weeks, Para, Info, Notes)
VALUES
('Maija Malli', '1', '24+3', 'G4P2', 'ASA', 'hptg', 'rrx3');  