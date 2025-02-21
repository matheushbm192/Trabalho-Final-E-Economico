package org.example;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class DebitoDao {

    private Connection con;

    public DebitoDao()  {
        con = Database.getInstance().getConnection();
    }

    public void insertDebito(String email, float debito, LocalDate data) {
        String sql = "INSERT INTO fluxoCaixaDebito (email, debito, data) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {

            // Configura os parâmetros de forma segura
            pstmt.setString(1, email);
            pstmt.setFloat(2, debito);
            pstmt.setDate(3, java.sql.Date.valueOf(data));  // Converte LocalDate para java.sql.Date

            // Executa a inserção
            pstmt.executeUpdate();
            System.out.println("Débito inserido com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao debitar fixa: " + e.getMessage());
        }
    }

    public ArrayList<Debito> selectDebitos(String email, LocalDate data) {
        int dataMes = data.getMonthValue();
        int dataAno = data.getYear();

        ArrayList<Debito> debitos = new ArrayList<>();

        String sql = "SELECT * FROM fluxoCaixaDebito WHERE email = ? " +
                "AND CAST(strftime('%m', data) AS INTEGER) = ? " +
                "AND CAST(strftime('%Y', data) AS INTEGER) = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {

            // Define os parâmetros da consulta
            pstmt.setString(1, email);
            pstmt.setInt(2, dataMes);
            pstmt.setInt(3, dataAno);

            ResultSet resultado = pstmt.executeQuery();

            // Itera sobre o resultado e adiciona os débitos à lista
            while (resultado.next()) {
                Debito debito = new Debito(email);
                debito.setValor(resultado.getFloat("debito"));
                debito.setData(resultado.getDate("data").toLocalDate());
                debitos.add(debito);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar Débitos: " + e.getMessage());
        }

        return debitos;
    }


}
