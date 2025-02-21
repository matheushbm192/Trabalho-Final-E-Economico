package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SaldoAtualDao {

    private Connection con;

    public SaldoAtualDao()  {
        con = Database.getInstance().getConnection();
    }

    public float selectSaldo(String email){
        float saldo = 0;
        try (Statement stat = con.createStatement()){

            ResultSet resultado = stat.executeQuery("select valor from saldoAtual where email = '" + email + "'");


            saldo = resultado.getFloat("valor");
            stat.close();

            return saldo;

        } catch (SQLException e) {
            System.err.println("Erro ao buscar Saldo atual" + e);
        }

        return saldo;
    }

    public void updateDebitoSaldo(String email, float valor) {
        try (Statement stat = con.createStatement()){

            stat.executeUpdate(
                    "update saldoAtual set valor = valor - " + valor + " where email = '" + email + "'");

        } catch (SQLException e) {
            System.err.println("Erro ao debitar em saldoAtual");
        }
    }
    public void updateDepositoSaldo(String email, float valor) {
        try (Statement stat = con.createStatement()){

            stat.executeUpdate(
                    "update saldoAtual set valor = valor + " + valor + " where email = '" + email + "'");

        } catch (SQLException e) {
            System.err.println("Erro ao depositar em saldoAtual");
        }
    }

}
