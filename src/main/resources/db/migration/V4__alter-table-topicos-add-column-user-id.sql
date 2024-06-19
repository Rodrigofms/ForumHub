alter table topicos add column autor_id BIGINT;
alter table topicos add foreign key (autor_id) references usuarios(id);