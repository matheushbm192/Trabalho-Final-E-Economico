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
