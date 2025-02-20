package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class DebitoDao {

    private Connection con;

    public DebitoDao()  {
        con = Database.getInstance().getConnection();
    }

    public void insertDebito(String email, float debito, LocalDate data) {
        try{

            Statement stat = con.createStatement();
            // Converte LocalDate para java.sql.Date
            java.sql.Date sqlDate = java.sql.Date.valueOf(data);

            // Monta a query concatenando os valores
            stat.executeUpdate( "INSERT INTO fluxoCaixaDebito (email,debito, data) VALUES ('"
                    + email + "', '," + debito + ", '" + sqlDate + "')");
            stat.close();
        }catch (SQLException e){
            System.err.println("Erro ao debitar fixa");
        }
    }

    public ArrayList<Debito> selectDebitos(String email, LocalDate data) {
        int dataMes = data.getMonthValue();
        int dataAno = data.getYear();

        try {
            ArrayList<Debito> debitos = new ArrayList<>();
            Statement stat = con.createStatement();
            ResultSet resultado = stat.executeQuery("select * from fluxoCaixaDebito where email = '" + email + "'" +
                    " and CAST(strftime('%m', data) AS INTEGER) = " + dataMes + "and CAST(strftime('%Y', data)AS INTEGER) = " + dataAno);

            while(resultado.next()){
                Debito debito = new Debito(email);
                debito.setValor(resultado.getFloat("debito"));
                debito.setData(resultado.getDate("data").toLocalDate());
                debitos.add(debito);
            }
            stat.close();

            return debitos;

        } catch (SQLException e){
            System.err.println("Erro ao buscar Debitos" + e);
        }
        return null;
    }
}
