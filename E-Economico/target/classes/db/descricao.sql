create table if not exists usuario (email string, name string, senha string, regime string, salario float);
create table if not exists meta (email string, meta float);
create table if not exists despesasFixas (email string, despesa float, data date);
create table if not exists fluxoCaixaDebito (email string, debito float,data date);
create table if not exists fluxoCaixaDeposito (email string, deposito float,data date);
