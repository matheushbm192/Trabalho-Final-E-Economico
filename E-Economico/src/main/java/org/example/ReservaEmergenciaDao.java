package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ReservaEmergenciaDao {
    private Connection con;

    public ReservaEmergenciaDao() {
        con = Database.getInstance().getConnection();
    }

    public ReservaEmergencia selectReservaEmergencia(String email) {
        try {
            Statement stat = con.createStatement();
            ResultSet resultado = stat.executeQuery("select * from reservaEmergencia where email = '" + email + "'");

            ReservaEmergencia reserva = new ReservaEmergencia(email);
            reserva.setValor(resultado.getFloat("valor"));
            stat.close();

            return reserva;

        } catch (SQLException e) {
            System.err.println("Erro ao buscar Reserva de Emergencia" + e);
        }
        return null;
    }

    public void insertReservaEmergencia(String email, float valor) {
        try {
            Statement stat = con.createStatement();
            stat.executeUpdate("insert into reservaEmergencia(email,valor) values('" + email + "'," + valor + ")");
            stat.close();
            // todo: retirar do saldo atual
        } catch (SQLException e) {
            System.err.println("Erro ao inserir Reserva de Emergencia");
        }
    }

    public void updateDepositarReservaEmergencia(String email, float valor) {
        try {
            Statement stat = con.createStatement();
            stat.executeUpdate(
                    "update reservaEmergencia set valor = valor + " + valor + " where email = '" + email + "'");
            stat.close();
            // todo: tirar do saldo atual
        } catch (SQLException e) {
            System.err.println("Erro ao inserir Reserva de Emergencia");
        }
    }

    public void updateDebitarReservaEmergencia(String email, float valor) {
        try {
            Statement stat = con.createStatement();
            stat.executeUpdate(
                    "update reservaEmergencia set valor = valor - " + valor + " where email = '" + email + "'");
            stat.close();
        } catch (SQLException e) {
            System.err.println("Erro ao inserir Reserva de Emergencia");
        }
    }
}
