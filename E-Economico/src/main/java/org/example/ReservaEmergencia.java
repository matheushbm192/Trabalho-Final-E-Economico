package org.example;

public class ReservaEmergencia extends OperacaoConta implements OperacaoFinanceira{
    private float reservaEmergencia;

    public ReservaEmergencia(float reservaEmergencia) {
      this.reservaEmergencia = reservaEmergencia;
    }

    public float getReservaEmergencia() {
        return reservaEmergencia;
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
