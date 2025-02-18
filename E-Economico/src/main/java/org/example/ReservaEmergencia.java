package org.example;

public class ReservaEmergencia extends OperacaoConta implements OperacaoFinanceira{
    private String nome;
    private float valor;
    private float montante;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public float getMontante() {
        return montante;
    }

    public void setMontante(float montante) {
        this.montante = montante;
    }

    @Override
    public void debitar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'debitar'");
    }

    @Override
    public void debositar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'debositar'");
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
