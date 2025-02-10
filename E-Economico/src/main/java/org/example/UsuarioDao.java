package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {

    private Connection con;
    public UsuarioDao(){
        con = Database.getInstance().getConnection();
    }
    public void insert(Usuario usuario) throws SQLException {
        Statement stat = con.createStatement();
        stat.executeUpdate("insert into person values("+ usuario.getId()+ ",'"+ usuario.getName() + "')");
        stat.close();
    }

    public void delete(int id) throws SQLException {
        Statement stat = con.createStatement();
        stat.executeUpdate("delete from person where person.id = " + id);
        stat.close();
    }

    public List<Usuario> getAll() throws SQLException {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery("select * from person");
        while(rs.next())
        {
            Usuario usuario = new Usuario();
            usuario.setId(rs.getInt("id"));
            usuario.setName(rs.getString("name"));
            usuarios.add(usuario);
        }
        return usuarios;
    }

    public Usuario getById(int id) throws SQLException {
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery("select from person where person.id = " + id);

        if(rs.next()){
            Usuario usuario = new Usuario();
            usuario.setId(rs.getInt("id"));
            usuario.setName(rs.getString("name"));
            stat.close();
            return usuario;
        }
        stat.close();
        return null;
    }

    public void update(Usuario usuario, int id) throws SQLException {
        Statement stat = con.createStatement();
        stat.executeUpdate("update person set name = '" + usuario.getName() +
                        "',id = " + usuario.getId() +" where person.id = " + id);
        stat.close();
    }
}
