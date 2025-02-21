package org.example;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
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

        // Converte o primeiro e o último dia do mês para timestamp
        LocalDate primeiroDiaMes = data.withDayOfMonth(1);
        LocalDateTime primeiroDiaMesInicio = primeiroDiaMes.atStartOfDay();
        long primeiroTimestamp = primeiroDiaMesInicio.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        // Último dia do mês (23:59:59)
        LocalDate ultimoDiaMes = primeiroDiaMes.withDayOfMonth(primeiroDiaMes.lengthOfMonth());
        LocalDateTime ultimoDiaMesFim = ultimoDiaMes.atTime(LocalTime.MAX);
        long ultimoTimestamp = ultimoDiaMesFim.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        ArrayList<Deposito> depositos = new ArrayList<>();

        // Query para buscar depósitos dentro do intervalo de timestamp
        String sql = "SELECT * FROM fluxoCaixaDeposito WHERE email = ? " +
                "AND data >= ? AND data <= ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.setLong(2, primeiroTimestamp);  // Timestamp do primeiro dia do mês
            pstmt.setLong(3, ultimoTimestamp);    // Timestamp do último dia do mês

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
