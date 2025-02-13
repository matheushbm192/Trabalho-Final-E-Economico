package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CadastroDao {
    private static Connection con;

    public CadastroDao()  {
        con = Database.getInstance().getConnection();
    }
    public static void insert(Usuario usuario) throws SQLException {
       // email string, name string, senha string, regime string, salario float);
        Statement stat = con.createStatement();
        stat.executeUpdate("insert into person values("
                + usuario.getEmail()+ ",'"
                + usuario.getNome() + "','"
                + usuario.getSenha() + "','"
                + usuario.getRegime() + "','"
                + usuario.getSalario() + "')");
        stat.close();
    }
}
