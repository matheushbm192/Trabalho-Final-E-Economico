package org.example;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
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

        // Converte o primeiro e o último dia do mês para timestamp
        LocalDate primeiroDiaMes = data.withDayOfMonth(1);
        LocalDateTime primeiroDiaMesInicio = primeiroDiaMes.atStartOfDay();
        long primeiroTimestamp = primeiroDiaMesInicio.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        // Último dia do mês (23:59:59)
        LocalDate ultimoDiaMes = primeiroDiaMes.withDayOfMonth(primeiroDiaMes.lengthOfMonth());
        LocalDateTime ultimoDiaMesFim = ultimoDiaMes.atTime(LocalTime.MAX);
        long ultimoTimestamp = ultimoDiaMesFim.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        ArrayList<Debito> debitos = new ArrayList<>();

        // Query para buscar débitos dentro do intervalo de timestamp
        String sql = "SELECT * FROM fluxoCaixaDebito WHERE email = ? " +
                "AND data >= ? AND data <= ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.setLong(2, primeiroTimestamp);  // Timestamp do primeiro dia do mês
            pstmt.setLong(3, ultimoTimestamp);    // Timestamp do último dia do mês

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
