package org.example;

import java.io.File;
import javax.swing.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.Objects;

public class Database {
    private Connection connection = null;

    private static Database INSTANCE = null;
    String dbPath = "Trabalho-Final-E-Economico/E-Economico/src/main/resources/db/sample.db";
    //String dbPath = "src/main/resources/db/sample.db";

    private Database() {
        try{
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);

            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            String sql = FileUtils.loadTextFile("Trabalho-Final-E-Economico/E-Economico/src/main/resources/db/descricao.sql");

            statement.executeUpdate(sql);
        } catch (Exception e){
            System.err.println("Houve um problema ao criar o arquivo do banco!");
            e.printStackTrace();
        }

    }

    public static  Database getInstance(){
        if(INSTANCE == null){
            INSTANCE = new Database();
        }
        return INSTANCE;
    }

    public Connection getConnection(){
        return this.connection;
    }

    public void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            System.err.println("Houve um problema ao fechar a conexâo com o banco!");
            e.printStackTrace();
        }
    }


}
