package org.example;

import java.time.LocalDate;
import java.time.LocalDate;
import java.util.ArrayList;
import org.jfree.data.category.DefaultCategoryDataset;

public class FluxoCaixa extends OperacaoConta {

    DebitoDao debitoDao = new DebitoDao();
    DepositoDao depositoDao = new DepositoDao();

    private String email;

    public FluxoCaixa(String email) {
        this.email = email;
    }

    @Override
    public void exibirInformacoes() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        ArrayList<Debito> debitos = debitoDao.selectDebitos(email, LocalDate.now());
        ArrayList<Deposito> depositos = depositoDao.selectDepositos(email, LocalDate.now());


        float totalFluxo = gerarFluxo(email);
        double totalDebitos = calcularTotalDebitos();
        double totalDepositos = calcularTotalDepositos();

        dataset.addValue(totalDebitos, "Débito", "Resumo Financeiro");
        dataset.addValue(totalDepositos, "Depósito", "Resumo Financeiro");
        dataset.addValue(totalFluxo, "Fluxo de Caixa", "Resumo Financeiro");

        new BarChart("Resumo Financeiro", "Categoria", "Valor", dataset);
    }

    private double calcularTotalDebitos() {
        ArrayList<Debito> debitos = debitoDao.selectDebitos(email, LocalDate.now());
        double total = 0;
        for (Debito debito : debitos) {
            total += debito.getValor();
        }
        return total;
    }

    private double calcularTotalDepositos() {
        ArrayList<Deposito> depositos = depositoDao.selectDepositos(email, LocalDate.now());
        double total = 0;
        for (Deposito deposito : depositos) {
            total += deposito.getValor();
        }
        return total;
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
