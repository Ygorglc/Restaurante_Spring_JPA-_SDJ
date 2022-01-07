create sequence if not exists cozinha_seq
	increment 1
	minvalue 1
	maxvalue 999999999999
	start 1
	cache 1;

create table cozinha(
	id BIGINT not null default NEXTVAL('cozinha_seq'),
	nome VARCHAR(60) not null,

	primary key (id)
);