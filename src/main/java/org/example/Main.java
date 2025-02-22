package org.example;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Já possui cadastro?\n1 - Sim\n2 - Não");
        Scanner resposta = new Scanner(System.in);
        int valor = resposta.nextInt();
        switch (valor){
            case 1:
                break;
            case 2:
                Cadastro.cadastrar();
                break;
        }

        Usuario usuario = Login.login();

        while (true) {
            System.out.println("O que deseja consultar/manipular? (Tecle o numero referente a ação)");
            System.out.println("1 - Salario");
            System.out.println("2 - Despesas fixas");
            System.out.println("3 - Inserir Debito ou deposito");
            System.out.println("4 - Metas");
            System.out.println("5 - Reserva de Emergencia");
            System.out.println("6 - Exibir Fluxo de caixa");
            System.out.println("7 - Vizualizar Saldo atual");
            Scanner entrada = new Scanner(System.in);
            int escolha = entrada.nextInt();
            entrada.nextLine();
            switch (escolha) {
                case 1:
                    usuario.modificarSalario();
                    break;
                case 2:
                    usuario.modificarDespesasFixas();
                    break;
                case 3:
                    usuario.inserirDebitoDeposito();
                    break;
                case 4:
                    usuario.modificarMetas();
                    break;
                case 5:
                    usuario.modificarReservaEmergencia();
                    break;
                case 6:
                    usuario.exibirFluxoCaixa();
                    break;
                case 7:
                    usuario.exibirSaldoAtual();
                default:
                    System.out.println("O valor inserido não é valido ,insira novamente!");
            }
        }

        
    }
    
}
