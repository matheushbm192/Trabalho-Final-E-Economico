package org.example;

public class Meta extends OperacaoConta implements OperacaoFinanceira{
    private String nomeMeta;
    private Float valorMeta;
    private double capitalAcumulado;
    
    public Meta(String nomeMeta, float valorMeta) {
        this.nomeMeta = nomeMeta;
        this.valorMeta = valorMeta;
    }

    public double getCapitalAcumulado() {
        return capitalAcumulado;
    }
     
    public String getNomeMeta() {
        return nomeMeta;
    }

    public float getValorMeta() {
        return valorMeta;
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
}
