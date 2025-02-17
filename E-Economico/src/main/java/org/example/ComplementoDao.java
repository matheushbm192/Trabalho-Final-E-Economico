package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class ComplementoDao {

    private Connection con;

    public ComplementoDao() {
        this.con = Database.getInstance().getConnection();
    }

    public void insertComplementos(String email, ArrayList<Meta> metas, ArrayList<DespesasFixas> despesas, ReservaEmergencia reserva) {
        try {
            // Inserindo metas
            String sqlMeta = "INSERT INTO meta (email, nome, valor) VALUES (?, ?, ?)";
            try (PreparedStatement stmtMeta = con.prepareStatement(sqlMeta)) {
                for (Meta meta : metas) {
                    System.out.println("Salvando meta: " + meta.getNomeMeta() + " - " + meta.getValorMeta());

                    stmtMeta.setString(1, email);
                    stmtMeta.setString(2, meta.getNomeMeta());
                    stmtMeta.setFloat(3, meta.getValorMeta());
                    stmtMeta.executeUpdate();
                }
            }

            // Inserindo despesas fixas
            String sqlDespesa = "INSERT INTO despesasFixas (email, nome, valor) VALUES (?, ?, ?)";
            try (PreparedStatement stmtDespesa = con.prepareStatement(sqlDespesa)) {
                for (DespesasFixas despesa : despesas) {
                    stmtDespesa.setString(1, email);
                    stmtDespesa.setString(2, despesa.getNomeDespesa());
                    stmtDespesa.setFloat(3, despesa.getValorDespesa());
                    stmtDespesa.executeUpdate();
                }
            }

            // Inserindo reserva de emergência
            String sqlReserva = "INSERT INTO ReservaEmergencia (email, valor) VALUES (?, ?)";
            try (PreparedStatement stmtReserva = con.prepareStatement(sqlReserva)) {
                stmtReserva.setString(1, email);
                stmtReserva.setFloat(2, reserva.getReservaEmergencia());
                stmtReserva.executeUpdate();
                System.out.println("Salvando meta: " + reserva.getReservaEmergencia()+"");

            }

            System.out.println("Dados inseridos com sucesso no banco!");

        } catch (SQLException e) {
            System.err.println("Erro ao inserir dados no banco: " + e.getMessage());
        }
    }

    public ReservaEmergencia selectReservaEmergencia(String email) {

    }

    public ArrayList<Meta> selectMetas() {
    }

    public ArrayList<DespesasFixas> selectDespesasFixas() {
    }
}
