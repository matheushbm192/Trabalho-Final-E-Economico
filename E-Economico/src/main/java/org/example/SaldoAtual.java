package org.example;

public class SaldoAtual {
    private String email; 
    private float saldoConta; 

    public SaldoAtual(String email){
        this.email = email;

    }

    public float getSaldoConta() {
        return saldoConta;
    }

    public void setSaldoConta(float saldoConta) {
        this.saldoConta = saldoConta;
    }
}
