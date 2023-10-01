create table cursos(

    id bigint not null auto_increment,
    nombre varchar(100) not null,
    categoria varchar(100) not null,
    activo tinyint default 1,

    primary key(id)

);