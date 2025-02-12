package org.example;

import java.util.Scanner;

public class Cadastro {

    public void cadastrar(){
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

        //Cria um novo usuário usando os dados fornecidos
        Usuario novoUsuario = new Usuario(nome, email, senha, regimeTrab);
    }
    
}
