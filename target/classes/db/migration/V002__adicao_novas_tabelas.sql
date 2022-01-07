create sequence if not exists cidade_seq
	increment 1
	minvalue 1
	maxvalue 999999999999
	start 1
	cache 1;

create table cidade (
    id  bigint not null default NEXTVAL('cidade_seq'),
    nome varchar(255) not null,
    estado_id int8,
    primary key (id)
);

create sequence if not exists estado_seq
    increment 1
    minvalue 1
    maxvalue 999999999999
    start 1
    cache 1;

create table estado (
   id  bigint not null default NEXTVAL('estado_seq'),
    nome varchar(255) not null,
    primary key (id)
);

create sequence if not exists forma_pagamento_seq
    increment 1
    minvalue 1
    maxvalue 999999999999
    start 1
    cache 1;

create table forma_pagamento (
   id  bigint not null default NEXTVAL('forma_pagamento_seq'),
    descricao varchar(255),
    primary key (id)
);

create sequence if not exists grupo_seq
    increment 1
    minvalue 1
    maxvalue 999999999999
    start 1
    cache 1;

create table grupo (
   id  bigint not null default NEXTVAL('grupo_seq'),
    nome varchar(255) not null,
    primary key (id)
);

create sequence if not exists grupo_permissao_seq
    increment 1
    minvalue 1
    maxvalue 999999999999
    start 1
    cache 1;

create table grupo_permissao (
   grupo_id bigint not null default NEXTVAL('grupo_permissao_seq'),
    permissao_id bigint not null,

    primary key (grupo_id, permissao_id)
);

create sequence if not exists permissao_seq
    increment 1
    minvalue 1
    maxvalue 999999999999
    start 1
    cache 1;

create table permissao (
    id  bigint not null default NEXTVAL('permissao_seq'),
    descricao varchar(255),
    nome varchar(255) not null,
    primary key (id)
);

create sequence if not exists produto_seq
    increment 1
    minvalue 1
    maxvalue 999999999999
    start 1
    cache 1;

create table produto (
   id  bigint not null default NEXTVAL('produto_seq'),
    ativo boolean not null,
    nome varchar(255) not null,
    preco numeric(19, 2) not null,
    restaurante_id bigint not null,
    primary key (id)
);

create sequence if not exists restaurante_seq
    increment 1
    minvalue 1
    maxvalue 999999999999
    start 1
    cache 1;

create table restaurante (
   id  bigint not null default NEXTVAL('restaurante_seq'),
    data_atualizacao timestamp not null,
    data_cadastro timestamp not null,
    endereco_bairro varchar(255),
    endereco_cep varchar(255),
    endereco_complemento varchar(255),
    endereco_logradouro varchar(255),
    endereco_numero varchar(255),
    nome varchar(255) not null,
    taxa_frete numeric(19, 2) not null,
    cozinha_id bigint not null,
    endereco_cidade_id bigint,
    primary key (id)
);

create sequence if not exists restaurante_forma_pagamento_seq
    increment 1
    minvalue 1
    maxvalue 999999999999
    start 1
    cache 1;

create table restaurante_forma_pagamento (
   restaurante_id bigint not null default NEXTVAL('restaurante_forma_pagamento_seq'),
    forma_pagamento_id bigint not null,

    primary key (restaurante_id, forma_pagamento_id)
);

create sequence if not exists usuario_seq
    increment 1
    minvalue 1
    maxvalue 999999999999
    start 1
    cache 1;

create table usuario (
   id  bigint not null default NEXTVAL('usuario_seq'),
    data_cadastro timestamp not null,
    email varchar(255) not null,
    nome varchar(255) not null,
    senha varchar(255) not null,
    primary key (id)
);

create sequence if not exists usuario_grupo_seq
    increment 1
    minvalue 1
    maxvalue 999999999999
    start 1
    cache 1;

create table usuario_grupo (
   usuario_id bigint not null default NEXTVAL('usuario_grupo_seq'),
    grupo_id bigint not null,

    primary key (usuario_id, grupo_id)
);




alter table grupo_permissao add constraint fk_grupo_permissao_permissao
foreign key (permissao_id) references permissao (id);

alter table grupo_permissao add constraint fk_grupo_permissao_grupo
foreign key (grupo_id) references grupo (id);

alter table produto add constraint fk_produto_restaurante
foreign key (restaurante_id) references restaurante (id);

alter table restaurante add constraint fk_restaurante_cozinha
foreign key (cozinha_id) references cozinha (id);

alter table restaurante add constraint fk_restaurante_cidade
foreign key (endereco_cidade_id) references cidade (id);

alter table restaurante_forma_pagamento add constraint fk_rest_forma_pagto_forma_pagto
foreign key (forma_pagamento_id) references forma_pagamento (id);

alter table restaurante_forma_pagamento add constraint fk_rest_forma_pagto_restaurante
foreign key (restaurante_id) references restaurante (id);

alter table usuario_grupo add constraint fk_usuario_grupo_grupo
foreign key (grupo_id) references grupo (id);

alter table usuario_grupo add constraint fk_usuario_grupo_usuario
foreign key (usuario_id) references usuario (id);