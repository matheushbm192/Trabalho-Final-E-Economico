package org.example;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        /*boolean validacao = Cadastro.cadastrar();
        if (!validacao) {
            Usuario teste = Login.login();
        } else {*/
            Usuario teste = Login.login();
            System.out.println(teste.getNome());
            ComplementoDados compl = new ComplementoDados(new ArrayList<>(), new ArrayList<>(), null);
            compl.registraDados(teste);
        //}

    }
}
