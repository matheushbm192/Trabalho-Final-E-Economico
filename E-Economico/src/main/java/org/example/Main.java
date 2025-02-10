package org.example;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
// NOTE: Connection and Statement are AutoCloseable.
        Connection con = null;
        try {
            con = Database.getInstance().getConnection();
            Statement statement = con.createStatement();
//            statement.setQueryTimeout(30);  // set timeout to 30 sec.
//
//            String sql = FileUtils.loadTextFile("E-Economico/src/main/resources/db/descricao.sql");
//
//            statement.executeUpdate(sql);
//
//            statement.executeUpdate("drop table if exists person");
//            statement.executeUpdate("create table person (id integer, name string)");
//            statement.executeUpdate("insert into person values(1, 'leo')");
//            statement.executeUpdate("insert into person values(2, 'yui')");
            Usuario p = new Usuario();
            p.setId(5);
            p.setName("Matheus miranda");
//
            UsuarioDao dao = new UsuarioDao();
            // dao.insert(p);
//            dao.delete(2);
            dao.update(p,5);
            ResultSet rs = statement.executeQuery("select * from person");
            while(rs.next())
            {
                // read the result set
                System.out.println("name = " + rs.getString("name"));
                System.out.println("id = " + rs.getInt("id"));
            }
        }
        catch(SQLException e)
        {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            e.printStackTrace(System.err);
        }
        finally {
            try {
                if(con != null){
                    con.close();
                }
            }catch (SQLException e){
                System.err.println(e);
            }
        }
    }
}

