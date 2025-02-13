package org.example;

import java.util.Scanner;

public class Login {

    public void login() {
        //entrada de dados
        Scanner scanner = new Scanner(System.in);

        //dados para o login
        System.out.println("Tela de Login");

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        //chama função que valida usuário
        Usuario usuarioVez = LoginDao.validaUsuario(email, senha);
        
        if(usuarioVez != null){
            //chamo alguma classe
        }else{
            System.out.println();
            System.out.println("Login não encontrado no sistema. Tente novamente.");
            login();
        }
    }

}
