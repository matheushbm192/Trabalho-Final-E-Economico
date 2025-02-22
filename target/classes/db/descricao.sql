create table if not exists usuario (email string, nome string, senha string, regime string, salario float);
create table if not exists meta (email string, nome string, valor float, montante float);
create table if not exists despesasFixas (email string, nome string, valor float, data date);
create table if not exists fluxoCaixaDebito (email string, debito float,data date);
create table if not exists fluxoCaixaDeposito (email string, deposito float,data date);
create table if not exists reservaEmergencia (email string, valor float);
create table if not exists saldoAtual (email string, valor float);