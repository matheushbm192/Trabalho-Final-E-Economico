package org.example;

import java.sql.SQLException;
import java.util.Scanner;

public class Cadastro {

    public void cadastrar() {
        //entrada de dados
        Scanner scanner = new Scanner(System.in);

        //dados para o cadastro
        System.out.println("Tela de Cadastro");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        System.out.print("Regime de Trabalho (ex: CLT, PJ): ");
        String regimeTrab = scanner.nextLine();

        System.out.print("Media Salarial");
        float salario = scanner.nextFloat();

        Usuario novoUsuario = new Usuario(nome, email, senha, regimeTrab, salario);

        try{
            CadastroDao.insert(novoUsuario);
        }catch (SQLException e ){
            System.err.println("Error ao cadastrar usuario");
        }
        //Cria um novo usuário usando os dados fornecidos

    }
    
}
