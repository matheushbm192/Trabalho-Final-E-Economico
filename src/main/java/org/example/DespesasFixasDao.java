package org.example;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class DespesasFixasDao {
    private Connection con;

    public DespesasFixasDao() {
        con = Database.getInstance().getConnection();
    }

    public DespesasFixas selectDespesaFixa(String email, String nome) {
        String sql = "SELECT * FROM despesasFixas WHERE email = ? AND nome = ?";
        try (PreparedStatement stat = con.prepareStatement(sql)) {
            stat.setString(1, email);
            stat.setString(2, nome);
            ResultSet resultado = stat.executeQuery();

            if (resultado.next()) {
                DespesasFixas despesa = new DespesasFixas(email);
                despesa.setNomeDespesa(resultado.getString("nome"));
                despesa.setValorDespesa(resultado.getFloat("valor"));
                despesa.setData(resultado.getDate("data").toLocalDate());

                return despesa;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar Despesa: " + e.getMessage());
        }
        return null;
    }

    public void updateValorDespesa(String email, String nome, float valorDesejado) {
        String sql = "UPDATE despesasFixas SET valor = ? WHERE email = ? AND nome = ?";
        try (PreparedStatement stat = con.prepareStatement(sql)) {
            stat.setFloat(1, valorDesejado);
            stat.setString(2, email);
            stat.setString(3, nome);
            stat.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar valor da despesa: " + e.getMessage());
        }
    }

    public ArrayList<DespesasFixas> selectDespesasFixas(String email, LocalDate data) {
        int mes = data.getMonthValue();
        int ano = data.getYear();
        ArrayList<DespesasFixas> despesasFixas = new ArrayList<>();

        String sql = "SELECT * FROM despesasFixas WHERE email = ? " +
                     "AND CAST(strftime('%m', data) AS INTEGER) = ? " +
                     "AND CAST(strftime('%Y', data) AS INTEGER) = ?";
        try (PreparedStatement stat = con.prepareStatement(sql)) {
            stat.setString(1, email);
            stat.setInt(2, mes);
            stat.setInt(3, ano);
            ResultSet resultado = stat.executeQuery();

            while (resultado.next()) {
                DespesasFixas despesa = new DespesasFixas(email);
                despesa.setNomeDespesa(resultado.getString("nome"));
                despesa.setValorDespesa(resultado.getFloat("valor"));
                despesa.setData(resultado.getDate("data").toLocalDate());
                despesasFixas.add(despesa);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar Despesas Fixas: " + e.getMessage());
        }
        return despesasFixas;
    }

    public void insertDespesaFixa(String email, String nome, float valor, LocalDate data) {
        String sql = "INSERT INTO despesasFixas(email, nome, valor, data) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stat = con.prepareStatement(sql)) {
            stat.setString(1, email);
            stat.setString(2, nome);
            stat.setFloat(3, valor);
            stat.setDate(4, Date.valueOf(data));
            stat.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao inserir Despesa fixa: " + e.getMessage());
        }
    }

    public void deleteDespesaFixa(String email, String nome) {
        String sql = "DELETE FROM despesasFixas WHERE email = ? AND nome = ?";
        try (PreparedStatement stat = con.prepareStatement(sql)) {
            stat.setString(1, email);
            stat.setString(2, nome);
            stat.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao deletar Despesa Fixa: " + e.getMessage());
        }
    }
}
