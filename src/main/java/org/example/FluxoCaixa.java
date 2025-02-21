package org.example;

import java.time.LocalDate;
import java.time.LocalDate;
import java.util.ArrayList;
import org.jfree.data.category.DefaultCategoryDataset;

public class FluxoCaixa extends OperacaoConta {

    private String email;

    public FluxoCaixa(String email) {
        this.email = email;
    }

    @Override
    public void exibirInformacoes() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        DebitoDao debitoDao = new DebitoDao();
        ArrayList<Debito> debitos = debitoDao.selectDebitos(email, LocalDate.now());
        DepositoDao depositoDao = new DepositoDao();
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
        double total = 0;

        try {
            DebitoDao debitoDao = new DebitoDao();
            ArrayList<Debito> debitos = debitoDao.selectDebitos(email, LocalDate.now());

            if (debitos != null) {
                for (Debito debito : debitos) {
                    total += debito.getValor();
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao calcular total de débitos: " + e.getMessage());
        }

        return total;
    }

    private double calcularTotalDepositos() {
        double total = 0;

        try {
            DepositoDao depositoDao = new DepositoDao();
            ArrayList<Deposito> depositos = depositoDao.selectDepositos(email, LocalDate.now());

            if (depositos != null) {
                for (Deposito deposito : depositos) {
                    total += deposito.getValor();
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao calcular total de depósitos: " + e.getMessage());
        }

        return total;
    }


    public float gerarFluxo(String email) {
        float resultadoDebito = 0;
        float resultadoDeposito = 0;

        try {
            // Obtém os débitos e depósitos
            ArrayList<Debito> debitos = new DebitoDao().selectDebitos(email, LocalDate.now());
            ArrayList<Deposito> depositos = new DepositoDao().selectDepositos(email, LocalDate.now());

            if (debitos != null) {
                for (Debito debito : debitos) {
                    resultadoDebito += debito.getValor();
                }
            }

            if (depositos != null) {
                for (Deposito deposito : depositos) {
                    resultadoDeposito += deposito.getValor();
                }
            }

        } catch (Exception e) {
            return 0;
        }
        // Retorna o saldo final (depósitos - débitos)
        return resultadoDeposito - resultadoDebito;
    }
}
