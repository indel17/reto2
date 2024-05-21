create database AjedrezOpen;
use AjedrezOpen;

create table jugador(
    rankingI int,
    rankingF int,
    titulo varchar(5),
    nombre varchar(50),
    federacion varchar(20),
    fide int,
    fideID varchar(30),
    hotel boolean default false,
    CV boolean default false,
    importeP int,
    categoria enum('A', 'B'),
    descalificado boolean default false,
    primary key(rankingI, categoria)
);

create table torneo(
    categoria enum('A', 'B') primary key
);

alter table jugador add constraint fk_jugador_categoria foreign key (categoria) references torneo(categoria) on delete cascade on update cascade;

create table premio(
    tipo varchar(30),
    categoria enum('A', 'B'),
    puesto int, 
    importe int,
    primary key (tipo, categoria, puesto),
    rankingI int,
    constraint fk_premio_rankingI foreign key (rankingI) references jugador(rankingI) on delete cascade on update cascade
);


create table optarPremio(
    rankingI int,
    categoria enum('A', 'B'),
    tipo varchar(30),
    puesto int,
    constraint fk_optarPremio_jugador foreign key (rankingI, categoria) references jugador(rankingI, categoria) on delete cascade on update cascade,
     constraint fk_optarPremio_premio foreign key (tipo, categoria, puesto) references premio(tipo, categoria, puesto) on delete cascade on update cascade,
    primary key (rankingI, categoria, tipo, puesto)
);


INSERT INTO torneo (categoria) VALUES ('A'), ('B');


/*Estos insert es para probar*/
insert into  premio (categoria, tipo, puesto, importe) values 
	('B', "general", 1, 1200),
    ('B', "general", 2, 1000),
    ('B', "sub1800", 1, 1100),
    ('B', "sub1800", 2, 800),
    ('B', "sub1600", 1, 900),
    ('B', "sub1600", 2, 600),
    ('B', "sub1400", 1, 150),
    ('B', "sub1400", 2, 100), 
    ('A', "general", 1, 1200),
    ('A', "general", 2, 1000),
    ('A', "sub2400", 1, 225),
    ('A', "sub2200", 1, 150),
	('A', "sub2200", 2, 100);

select * from jugador order by categoria ;
