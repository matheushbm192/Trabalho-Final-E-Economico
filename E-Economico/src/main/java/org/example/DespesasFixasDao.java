package org.example;

import java.sql.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;

public class DespesasFixasDao {
    private Connection con;

    public DespesasFixasDao()  {
        con = Database.getInstance().getConnection();
    }

    public ArrayList<DespesasFixas> selectDespesaFixas(String email) {



        try {
            ArrayList<DespesasFixas> despesasFixas = new ArrayList<>();
            Statement stat = con.createStatement();
            ResultSet resultado = stat.executeQuery("select * from despesasFixas where email = '" + email + "'");
            //create table if not exists despesasFixas (email string, nome string, valor float, data date);
            while(resultado.next()){
                DespesasFixas despesa = new DespesasFixas(email);
                despesa.setNomeDespesa(resultado.getString("nome"));
                despesa.setValorDespesa(resultado.getFloat("valor"));
                despesa.setData(resultado.getDate("data"));
                despesasFixas.add(despesa);
            }

            stat.close();
            return despesasFixas ;

        } catch (SQLException e){
            System.err.println("Erro ao buscar Reserva de Emergencia" + e);
        }
        return null;
    }

    public void insertDespesaFixa(String email, String nome, float valor, LocalDate data) {
        try{

            Statement stat = con.createStatement();
            // Converte LocalDate para java.sql.Date
            java.sql.Date sqlDate = java.sql.Date.valueOf(data);

            // Monta a query concatenando os valores
            stat.executeUpdate( "INSERT INTO despesasFixas(email, nome, valor, data) VALUES ('"
                    + email + "', '" + nome + "', " + valor + ", '" + sqlDate + "')");
            stat.close();
        }catch (SQLException e){
            System.err.println("Erro ao inserir Reserva de Emergencia");
        }
    }
    public void deleteDespesaFixa(String email, String nome) {
        try {
            Statement stat = con.createStatement();
            stat.executeUpdate("delete from despesasFixas where email = '" + email + "' AND '" + nome + "'");
            stat.close();

        } catch (SQLException e) {
            System.err.println("Error ao deletar Despesa Fixa");
        }
    }



}
