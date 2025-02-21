package org.example;

import java.sql.SQLException;
import java.util.Scanner;

public class Cadastro {

    public static boolean cadastrar() {
      
        Scanner scanner = new Scanner(System.in);

        //dados para o cadastro
        System.out.println("Tela de Cadastro!");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        System.out.print("Regime de Trabalho (ex: CLT, PJ): ");
        String regimeTrab = scanner.nextLine();

        System.out.print("Media Salarial: ");
        float salario = scanner.nextFloat();


        CadastroDao cadastroDao = new CadastroDao();
        cadastroDao.insert(nome,email,senha,regimeTrab,salario);
        SaldoAtualDao saldoAtualDao = new SaldoAtualDao();
        saldoAtualDao.insertSaldo(email);
        return true;
        


        

    }
    
}
