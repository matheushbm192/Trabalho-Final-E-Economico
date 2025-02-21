package org.example;

import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {

    private Connection con;

    public UsuarioDao()  {
        con = Database.getInstance().getConnection();
    }


    public Usuario selectUsuario(String email) throws SQLException {
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery("select from usuario where email = '" + email+"'");

        if(rs.next()){
           // Usuario usuario = new Usuario();
          //  usuario.setEmail(rs.getString("email"));
           // usuario.setName(rs.getString("name"));
            stat.close();
         //   return usuario;
        }
        stat.close();
        return null;
    }

    public void update(String email, float valorDesejado) {
        String sql = "UPDATE usuario SET salario = ? WHERE email = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            // Passa os parâmetros de forma segura
            pstmt.setFloat(1, valorDesejado);  // Passa o valor desejado
            pstmt.setString(2, email);         // Passa o email

            // Executa a atualização
            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Linhas atualizadas: " + rowsAffected);

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar valor do salario: " + e.getMessage());
        }
    }
}
