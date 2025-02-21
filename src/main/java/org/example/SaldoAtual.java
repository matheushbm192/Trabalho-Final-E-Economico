package org.example;

public class SaldoAtual extends OperacaoConta {
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

    @Override
    public void exibirInformacoes() {
        SaldoAtualDao dao = new SaldoAtualDao();
        System.out.println(dao.selectSaldo(email));
    }
}
