package org.example;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginDao {

    private Connection con;

    public LoginDao()  {
        con = Database.getInstance().getConnection();
    }
     public Usuario validaUsuario(String email, String senha) {

         ResultSet resultados = null;
        try(Statement stat = con.createStatement();){

            //reate table if not exists usuario (email string, name string, senha string, regime string, salario float)
            resultados = stat.executeQuery("select * from usuario where email = '" + email +"' AND senha = '"+ senha+"'");
            if(resultados.next()){

                return new Usuario(resultados.getString("nome"),
                        resultados.getString("email"),
                        resultados.getString("senha"),
                        resultados.getString("regime"),
                        resultados.getFloat("salario"));
            }
        }catch (SQLException e){
            System.err.println("Error ao validar usuario" + e);
        }

           return null; 
        }
    
}
