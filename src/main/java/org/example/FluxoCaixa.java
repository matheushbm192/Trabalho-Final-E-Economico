package org.example;

import java.util.ArrayList;
import org.jfree.data.category.DefaultCategoryDataset;

public class FluxoCaixa extends OperacaoConta {

    DebitoDao debitoDao = new DebitoDao();
    DepositoDao depositoDao = new DepositoDao();
    FluxoCaixaDao fluxoCaixaDao = new FluxoCaixaDao();
    
    private String email;

    public FluxoCaixa(String email) {
        this.email = email;
    }

    @Override
    public void exibirInformacoes() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        ArrayList<Debito> debitos = debitoDao.selectDebitos(email);
        ArrayList<Deposito> depositos = depositoDao.selectDepositos(email);
        ArrayList<FluxoCaixa> fluxos = fluxoCaixaDao.selectFluxoCaixa(email);

        double totalDebitos = calcularTotal(debitos);
        double totalDepositos = calcularTotal(depositos);
        double totalFluxo = calcularTotal(fluxos);

        dataset.addValue(totalDebitos, "Débito", "Resumo Financeiro");
        dataset.addValue(totalDepositos, "Depósito", "Resumo Financeiro");
        dataset.addValue(totalFluxo, "Fluxo de Caixa", "Resumo Financeiro");

        new BarChart("Resumo Financeiro", "Categoria", "Valor", dataset);
    }

    private double calcularTotal(ArrayList<E> lista) {
        double total = 0;
        for (OperacaoFinanceira op : lista) {
            total += op.getValor();
        }
        return total;
    }
}
