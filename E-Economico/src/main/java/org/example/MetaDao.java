package org.example;

import java.sql.Connection;
import java.util.ArrayList;

public class MetaDao {
    private Connection con;

    public MetaDao()  {
        con = Database.getInstance().getConnection();
    }
    public ArrayList<Meta> selectMetas(String email) {
    }

    public Meta selectMeta(String email,String nome) {
    }

    
    public void insertMeta(String email, String nome, float valor) {
    }
    public void UpdateValorMeta(String email, String nome, float valor) {
    }
    public void UpdateDepositarValorMeta(String email, String nome, float valor) {
    }
    public void UpdateDebitarValorMeta(String email, String nome, float valor) {
    }
    public void deleteMetas(String email, String nome) {
    }
}
