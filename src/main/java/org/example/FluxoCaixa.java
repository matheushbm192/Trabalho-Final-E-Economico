package org.example;

import java.time.LocalDate;
import java.util.ArrayList;

public class FluxoCaixa extends OperacaoConta{
    private String email;
    public FluxoCaixa(String email) {
        this.email = email;
    }

    @Override
    public void exibirInformacoes() {

    }

    public float gerarFluxo(String email){
        ArrayList<Debito> debitos = new DebitoDao().selectDebitos(email, LocalDate.now());
        ArrayList<Deposito> depositos = new DepositoDao().selectDepositos(email,LocalDate.now());
        float resultadoDebito = 0;
        float resultadoDeposito = 0;
        for(Debito debito : debitos ){
            resultadoDebito += debito.getValor();
        }
        for(Deposito deposito : depositos ){
            resultadoDeposito += deposito.getValor();
        }
        return resultadoDeposito - resultadoDebito;
    }

}
