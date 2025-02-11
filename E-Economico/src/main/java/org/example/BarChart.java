
package org.example;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.category.CategoryDataset;

import javax.swing.*;
import java.awt.*;

public class BarChart extends JFrame {
    public BarChart() {
        //Cria o dataset
        CategoryDataset dataset = createDataset();

        //Cria o gráfico com o dataset
        JFreeChart chart = createChart(dataset);

        //Cria o painel para exibir o gráfico
        ChartPanel chartPanel = new ChartPanel(chart);

        //Definir a borda para o painel do gráfico
        chartPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        //Alterar o tamanho do painel do gráfico
        chartPanel.setPreferredSize(new Dimension(400, 300)); //Tamanho menor

        //Reduzir a largura das barras
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.getDomainAxis().setCategoryMargin(0); //Reduz a distância entre as barras
        plot.getDomainAxis().setLowerMargin(0.1); //Ajusta as margens

        //Adiciona o painel no JFrame
        this.add(chartPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    //Método para criar o dataset
    private CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        //Adiciona dados de exemplo ao dataset
        dataset.addValue(1.0, "Débitos", "Jan");
        dataset.addValue(4.0, "Depositos", "Fev");
        dataset.addValue(3.0, "Variação", "Mar");
        dataset.addValue(9.0, "Categoria 2", "Set");
        dataset.addValue(15.0, "Categoria 3", "Out");

        return dataset;
    }

    //Método para criar o gráfico
    private JFreeChart createChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
                "Gráfico de Barras", //Título
                "Mês", //Eixo X
                "Valor", //Eixo Y
                dataset //Dados
        );

        return chart;
    }

    public static void main(String[] args) {
        //Cria a janela com o gráfico
        new BarChart();
    }
}
