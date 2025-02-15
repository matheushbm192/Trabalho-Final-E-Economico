package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CadastroDao {
    private  Connection con;

    public CadastroDao()  {
        con = Database.getInstance().getConnection();
    }

    public void insert(String nome,String email, String senha,String regime, float salario)  {
        Statement stat = null;
        try{
            stat = con.createStatement();
            stat.executeUpdate("insert into usuario(email,nome,senha,regime,salario) values('"
                    + email + "','"
                    + nome + "','"
                    + senha + "','"
                    + regime + "',"
                    + salario + ")");
        }catch (SQLException e){
            System.err.println("Erro ao cadastrar usuario "+ e);
        }finally {
            try{
                if (stat != null) stat.close();
                if (con != null) con.close();
            }catch (SQLException u){
                System.err.println("Error ao fechar a conexao "+ u);
            }

        }
       // email string, nome string, senha string, regime string, salario float);
    }
}
