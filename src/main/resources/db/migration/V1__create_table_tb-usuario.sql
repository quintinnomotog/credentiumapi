/**
 * Objetivo: 	Modelagem da tabela relacional que representa um usuário do sistema.
 * Data: 		09/07/2025
 * Demanda:		feature/CREDENTIUM20250709153615API
 */
create table if not exists tb_usuario (
	code bigint not null auto_increment comment 'Identificador privado e único da tabela',
	code_public varchar(200) not null comment 'Identificador público e único da tabela',
	nome varchar(100) not null comment 'Representa o nome da uma determinada pessoa',
	identificador varchar(100) not null comment 'Representa o identidicador de um determinado usuário',
	senha varchar(100) not null comment 'Representa a senha de um determinado usuário',
	is_active boolean default true not null comment 'Indica se um determinado registro está ativo ou inativo no sistema',
	data_criacao datetime default current_timestamp not null comment 'Representa a Data de Criação do Registro',
	data_edicao datetime default current_timestamp null comment 'Representa a Data de Deleção do Registro',
	data_delecao datetime null comment 'Representa a Data de Edição do Registro',
	constraint pk_usuario primary key (code),
	constraint un_usuario unique (nome)
) comment 'Representa uma Usuário do sistema';
