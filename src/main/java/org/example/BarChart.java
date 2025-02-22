
package org.example;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.category.CategoryDataset;

import javax.swing.*;
import java.awt.*;

public class BarChart extends JFrame {
    public BarChart(String titulo, String eixoX, String eixoY, DefaultCategoryDataset dataset) {
        super(titulo); // Define o título da janela

        // Criar o gráfico
        JFreeChart chart = createChart(titulo, eixoX, eixoY, dataset);

        // Criar o painel do gráfico
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        chartPanel.setPreferredSize(new Dimension(600, 450)); // Tamanho melhor ajustado

        // Adicionando ao JFrame
        this.setContentPane(chartPanel);
        this.pack();
        this.setLocationRelativeTo(null); // Centraliza na tela
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Permite fechar sem encerrar o programa
        this.setVisible(true);
    }

    // Método para criar o gráfico de barras
    private JFreeChart createChart(String titulo, String eixoX, String eixoY, CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
                titulo, // Título do gráfico
                eixoX, // Eixo X
                eixoY, // Eixo Y
                dataset,
                PlotOrientation.VERTICAL, // Orientação vertical
                true, // Exibir legenda
                true, // Exibir tooltips
                false // Sem URLs
        );

        // Melhorando a aparência do gráfico
        CategoryPlot plot = chart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(255, 0, 0)); // Vermelho
        renderer.setSeriesPaint(1, new Color(0, 255, 0)); // Verde
        renderer.setSeriesPaint(2, new Color(0, 0, 255)); // Azul
        renderer.setSeriesPaint(3, new Color(255, 165, 0)); // Laranja
        renderer.setMaximumBarWidth(0.05); // Reduz a largura das barras

        // Ajustar espaçamentos entre as barras
        plot.getDomainAxis().setCategoryMargin(0.2);
        plot.getDomainAxis().setLowerMargin(0.05);
        plot.getDomainAxis().setUpperMargin(0.05);

        return chart;
    }
}
