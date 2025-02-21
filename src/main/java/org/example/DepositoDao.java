package org.example;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class DepositoDao {

    private Connection con;

    public DepositoDao()  {
        con = Database.getInstance().getConnection();
    }

    public void insertDeposito(String email, float deposito, LocalDate data) {
        String sql = "INSERT INTO fluxoCaixaDeposito (email, deposito, data) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            // Define os valores nos placeholders
            pstmt.setString(1, email);
            pstmt.setFloat(2, deposito);
            pstmt.setDate(3, java.sql.Date.valueOf(data));

            // Executa a query
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao depositar: " + e.getMessage());
        }
    }


    public ArrayList<Deposito> selectDepositos(String email, LocalDate data) {
        int dataMes = data.getMonthValue();
        int dataAno = data.getYear();

        String sql = "SELECT * FROM fluxoCaixaDeposito WHERE email = ? " +
                "AND CAST(strftime('%m', data) AS INTEGER) = ? " +
                "AND CAST(strftime('%Y', data) AS INTEGER) = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            ArrayList<Deposito> depositos = new ArrayList<>();

            pstmt.setString(1, email);
            pstmt.setInt(2, dataMes);
            pstmt.setInt(3, dataAno);

            ResultSet resultado = pstmt.executeQuery();

            while (resultado.next()) {
                Deposito deposito = new Deposito(email);
                deposito.setValor(resultado.getFloat("deposito"));
                deposito.setData(resultado.getDate("data").toLocalDate());
                depositos.add(deposito);
            }

            return depositos;

        } catch (SQLException e) {
            System.err.println("Erro ao buscar Depósitos: " + e.getMessage());
        }
        return null;
    }

}
