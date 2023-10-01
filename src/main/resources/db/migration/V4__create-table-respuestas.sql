create table respuestas(

    id bigint not null auto_increment,
    mensaje text not null,
    topico_id bigint not null,
    fecha_creacion datetime not null,
    autor_id bigint not null,
    solucion tinyint default 0 not null,
    motivo_cancelamiento varchar(100) ,

    primary key(id),

    constraint fk_respuestas_topico_id foreign key(topico_id) references topicos(id),
    constraint fk_respuestas_autor_id foreign key(autor_id) references usuarios(id)

);