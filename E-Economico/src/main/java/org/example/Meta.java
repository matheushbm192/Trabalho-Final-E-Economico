package org.example;

public class Meta extends OperacaoConta implements OperacaoFinanceira{
    private String email;
    private String nomeMeta;
    private float valorMeta;
    private float montante;
    
    public Meta(String email) {
       this.email = email;
    }

    public void setNomeMeta(String nomeMeta) {
        this.nomeMeta = nomeMeta;
    }

    public void setValorMeta(Float valorMeta) {
        this.valorMeta = valorMeta;
    }

    public void setMontante(float montante) {
        this.montante = montante;
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
