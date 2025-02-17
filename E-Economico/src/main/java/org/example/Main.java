package org.example;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        /*boolean validacao = Cadastro.cadastrar();
        if (!validacao) {
            Usuario teste = Login.login();
        } else {*/
            Usuario usuario = Login.login();
            System.out.println(usuario.getNome());
            usuario.complemento.registrarDesepesaFixa();

        //}
        while(true){
            System.out.println("O que deseja consultar/manipular? (Tecle o numero referente a ação)");
            System.out.println("1 - Salario");
            System.out.println("2 - Despesas fixas");
            System.out.println("3 - Debito ou deposito");
            System.out.println("4 - Metas");
            System.out.println("5 - Reserva de Emergencia");
            System.out.println("6 - Fluxo de caixa");
            Scanner entrada = new Scanner(System.in);
            int escolha = entrada.nextInt();
            switch (escolha){
                case 1:
                    usuario.modificarSalario();
                    break;
                case 2:
                    usuario.modificarDespesasFixas();
                    break;
                case 3:
                    usuario.modificarDebitoDeposito();
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
                default:
                    System.out.println("O valor inserido não é valido ,insira novamente!");
            }
        }

        /*deseja realizar cadastro ou fazer login
        salario
        despesas fixas
        debito ou deposito
        metas
        reservas de emergencia
         */
    }
}
