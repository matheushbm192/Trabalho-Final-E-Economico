package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class DepositoDao {

    private Connection con;

    public DepositoDao()  {
        con = Database.getInstance().getConnection();
    }

    public void insertDeposito(String email, float deposito, LocalDate data) {
        try(Statement stat = con.createStatement()){

            // Converte LocalDate para java.sql.Date
            java.sql.Date sqlDate = java.sql.Date.valueOf(data);

            // Monta a query concatenando os valores
            stat.executeUpdate( "INSERT INTO fluxoCaixaDeposito (email,deposito,data) VALUES ('"
                    + email + "'," + deposito + ", '" + sqlDate + "')");

        }catch (SQLException e){
            System.err.println("Erro ao depositar");
        }
    }

    public ArrayList<Deposito> selectDepositos(String email, LocalDate data) {
        int dataMes = data.getMonthValue();
        int dataAno = data.getYear();

        try (Statement stat = con.createStatement()){
            ArrayList<Deposito> depositos = new ArrayList<>();

            ResultSet resultado = stat.executeQuery("select * from fluxoCaixaDeposito where email = '" + email +  "'" +
                    " and CAST(strftime('%m', data) AS INTEGER) = " + dataMes + "and CAST(strftime('%Y', data)AS INTEGER) = " + dataAno);

            while(resultado.next()){
                Deposito deposito = new Deposito(email);
                deposito.setValor(resultado.getFloat("deposito"));
                deposito.setData(resultado.getDate("data").toLocalDate());
                depositos.add(deposito);
            }

            return depositos;

        } catch (SQLException e){
            System.err.println("Erro ao buscar Depositos" + e);
        }
        return null;
    }
}
