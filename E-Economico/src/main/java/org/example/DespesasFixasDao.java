package org.example;

import java.sql.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

public class DespesasFixasDao {
    private Connection con;

    public DespesasFixasDao()  {
        con = Database.getInstance().getConnection();
    }

    public ArrayList<DespesasFixas> selectDespesaFixasMes(String email, LocalDate data = LocalDate. ) {


        try {
            Statement stat = con.createStatement();
            ResultSet resultado = stat.executeQuery("select * from reservaEmergencia where email = '" + email + "'");

            ReservaEmergencia reserva = new ReservaEmergencia();
            reserva.setNome(resultado.getString("nome"));
            reserva.setValor(resultado.getFloat("valor"));
            reserva.setMontante(resultado.getDate("montante").);
            stat.close();

            return reserva;

        } catch (SQLException e){
            System.err.println("Erro ao buscar Reserva de Emergencia" + e);
        }
        return null;
    }
    public void insertReservaEmergencia(String email, float valor) {
        try{
            Statement stat = con.createStatement();
            stat.executeUpdate("insert into reservaEmergencia(email,valor) values('"+ email + "'," + valor + ")");
            stat.close();
        }catch (SQLException e){
            System.err.println("Erro ao inserir Reserva de Emergencia");
        }
    }

    public void updateDepositarReservaEmergencia(String email, float valor) {
        try{
            Statement stat = con.createStatement();
            stat.executeUpdate("update reservaEmergencia set valor = valor + " + valor + " where email = '" + email +"'");
            stat.close();
            //todo: criar tratamentos para não deixar o usuario usar sem ter cadastrado um reserva de emergencia
        }catch (SQLException e){
            System.err.println("Erro ao inserir Reserva de Emergencia");
        }
    }

    public void updateDebitarReservaEmergencia(String email, float valor) {
        try{
            Statement stat = con.createStatement();
            stat.executeUpdate("update reservaEmergencia set valor = valor - " + valor + " where email = '" + email +"'");
            stat.close();
            //todo: criar tratamentos para não deixar o usuario usar sem ter cadastrado um reserva de emergencia
            //todo: não deixar usuario retirar mais dinheiro do que a reserva possui
        }catch (SQLException e){
            System.err.println("Erro ao inserir Reserva de Emergencia");
        }
    }
}
