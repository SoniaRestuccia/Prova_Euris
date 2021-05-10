create database monete;
use monete;

create table articles (
    code int primary key auto_increment,
    name varchar(50),
    cost varchar(50)
);

INSERT INTO articles VALUES 
(0,'Maglietta','5p 17s 10d'),
(0,'Giacca','15p 18s 1d'),
(0,'Felpa','9p 11s 10d'),
(0,'Pantaloni','7p 18s 4d'),
(0,'Cintura','56p 15s 3d'),
(0,'Anello','4p 19s 1d'),
(0,'Bracciale','115p 10s 9d'),
(0,'Scarpe','50p 19s 3d'),
(0,'Sciarpa','54p 11s 5d'),
(0,'Polsino','25p 13s 8d');

select *from articles;