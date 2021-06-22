create database Beneficiados
go
use Beneficiados

create table beneficiado(
id bigint identity,
nome varchar(100),
cpf varchar(14),
nis varchar(11)
primary key(id))

select * from beneficiado