/*Create the Computer ID table*/
CREATE TABLE computers (
    id varchar(30) NOT NULL,
    compname varchar(30) NOT NULL,
    os varchar(30) NOT NULL,
    ip varchar(20) NOT NULL,
    sernum varchar(25) NOT NULL,
    datepurch DATE NOT NULL,
    status varchar(30) NOT NULL,
    notes varchar(300) NOT NULL,
);
