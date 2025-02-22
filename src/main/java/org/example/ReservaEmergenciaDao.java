package org.example;

import java.sql.*;
import java.util.ArrayList;

public class ReservaEmergenciaDao {
    private Connection con;

    public ReservaEmergenciaDao() {
        con = Database.getInstance().getConnection();
    }

    public ReservaEmergencia selectReservaEmergencia(String email) {
        String sql = "SELECT * FROM reservaEmergencia WHERE email = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, email);  //
            ResultSet resultado = pstmt.executeQuery();

            // Verifica se encontrou algum registro
            if (resultado.next()) {
                ReservaEmergencia reserva = new ReservaEmergencia(email);
                reserva.setValor(resultado.getFloat("valor"));
                return reserva;
            } else {
                System.out.println("Nenhuma reserva encontrada para o email: " + email);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar Reserva de Emergência: " + e.getMessage());
        }

        return null;
    }



    public void insertReservaEmergencia(String email, float valor) {
        String sql = "INSERT INTO reservaEmergencia (email, valor) VALUES (?, ?)";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            // Define os valores nos placeholders
            pstmt.setString(1, email);
            pstmt.setFloat(2, valor);

            // Executa a query
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao inserir Reserva de Emergência: " + e.getMessage());
        }
    }


    public void updateDepositarReservaEmergencia(String email, float valor) {
        String sql = "UPDATE reservaEmergencia SET valor = valor + ? WHERE email = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            // Define os valores nos placeholders
            pstmt.setFloat(1, valor);
            pstmt.setString(2, email);

            pstmt.executeUpdate();



        } catch (SQLException e) {
            System.err.println("Erro ao atualizar Reserva de Emergência: " + e.getMessage());
        }
    }


    public void updateDebitarReservaEmergencia(String email, float valor) {
        String sql = "UPDATE reservaEmergencia SET valor = valor - ? WHERE email = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            // Define os valores nos placeholders
            pstmt.setFloat(1, valor);
            pstmt.setString(2, email);

            // Executa a query
            pstmt.executeUpdate();



        } catch (SQLException e) {
            System.err.println("Erro ao debitar Reserva de Emergência: " + e.getMessage());
        }
    }

}
