package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MetaDao {
    private Connection con;

    public MetaDao() {
        con = Database.getInstance().getConnection();
    }

    public ArrayList<Meta> selectMetas(String email) {
        try {
            ArrayList<Meta> metas = new ArrayList<>();
            Statement stat = con.createStatement();
            ResultSet resultadoss = stat.executeQuery("select * from meta where email = '" + email + "'");
            while (resultadoss.next()) {
                Meta meta = new Meta(email);
                meta.setNomeMeta(resultadoss.getString("nome"));
                meta.setValorMeta(resultadoss.getFloat("valor"));
                meta.setMontante(resultadoss.getFloat("montante"));
                metas.add(meta);
            }
            return metas;
        } catch (SQLException e) {
            System.err.println("Erro ao buscar Metas " + e);
        }
        return null;
    }

    public Meta selectMeta(String email, String nome) {
        String sql = "SELECT * FROM meta WHERE email = ? AND nome = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, nome);
            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {
                Meta meta = new Meta(email);
                meta.setNomeMeta(resultado.getString("nome"));
                meta.setValorMeta(resultado.getFloat("valor"));
                meta.setMontante(resultado.getFloat("montante"));
                return meta;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar Meta: " + e.getMessage());
        }
        return null;
    }

    public void insertMeta(String email, String nome, float valor) {
        try {
            Statement stat = con.createStatement();
            stat.executeUpdate("insert into meta(email,nome,valor) values(" + email + ",'" + nome + "'," + valor + ")");
            stat.close();
        } catch (SQLException e) {
            System.err.println("Erro ao inserir Meta");
        }
    }

    public void UpdateValorMeta(String email, String nome, float valorDesejado) {

        try {
            Statement stat = con.createStatement();
            stat.executeUpdate("update meta set valor = " + valorDesejado + " where email = '" + email
                    + "' AND nome = '" + nome + "'");
            stat.close();
            // todo: criar tratamentos para não deixar o usuario usar sem ter cadastrado um
            // reserva de emergencia
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar valor da meta");
        }

    }

    public void UpdateNomeMeta(String email, String nome, String nomeDesejado) {

        try {
            Statement stat = con.createStatement();
            stat.executeUpdate("update meta set nome = '" + nomeDesejado + "' where email = '" + email
                    + "' AND nome = '" + nome + "'");
            stat.close();

        } catch (SQLException e) {
            System.err.println("Erro ao Atualizar nome da Meta" + e);
        }

    }

    public void UpdateDepositarValorMeta(String email, String nome, float valorDeposito) {
        try {
            Statement stat = con.createStatement();
            stat.executeUpdate("update meta set montante = montante + " + valorDeposito + " where email = '" + email
                    + "'AND nome = '" + nome + "'");
            stat.close();
            // todo: debitar do saldo atual
        } catch (SQLException e) {
            System.err.println("Erro ao depositar valor na Meta" + e);
        }
    }

    public void UpdateDebitarMontanteMeta(String email, String nome, float valorDebito) {
        try {
            Statement stat = con.createStatement();
            stat.executeUpdate("update meta set montate = montante - " + valorDebito + " where email = '" + email
                    + "'AND nome = '" + nome + "'");
            stat.close();
            // todo: acrescentar ao saldo atual

        } catch (SQLException e) {
            System.err.println("Erro ao debitar valor na Meta" + e);
        }
    }

    public void deleteMeta(String email, String nome) {
        try {
            Statement stat = con.createStatement();
            stat.executeUpdate("delete from meta where email = '" + email + "' AND '" + nome + "'");
            stat.close();

        } catch (SQLException e) {
            System.err.println("Error ao deletar meta");
        }
    }
}
