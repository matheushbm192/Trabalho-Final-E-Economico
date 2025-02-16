package org.example;

public class DespesasFixas extends OperacaoConta{

    private String nomeDespesa;
    private float valorDespesa;

    public DespesasFixas(String nomeDespesa, float valorDespesa) {
       this.nomeDespesa = nomeDespesa;
       this.valorDespesa = valorDespesa; 
    }

    public String getNomeDespesa() {
        return nomeDespesa;
    }

    public float getValorDespesa() {
        return valorDespesa;
    }

    @Override
    public void exibirInformacoes() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'exibirInformacoes'");
    }

    @Override
    public void modificarInformacoes() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modificarInformacoes'");
    }

}
