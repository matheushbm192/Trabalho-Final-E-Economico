package org.example;

import java.sql.SQLException;
import java.util.Scanner;



public class Login {

    public static Usuario login()  {
        //entrada de dados
        Scanner scanner = new Scanner(System.in);

        //dados para o login
        System.out.println("Tela de Login");

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        Usuario usuarioVez = null;
        LoginDao loginDao = new LoginDao();

        //chama função que valida usuário
        usuarioVez = loginDao.validaUsuario(email, senha);

        if(usuarioVez != null){
            return usuarioVez;
        }else{
            System.out.println();
            System.out.println("Login não encontrado no sistema. Tente novamente.");
            usuarioVez = Login.login();
        }

        return usuarioVez;
    }



}
