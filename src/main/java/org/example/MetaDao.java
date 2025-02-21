package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MetaDao {
    private Connection con;

    public MetaDao() {
        con = Database.getInstance().getConnection();
    }

    public ArrayList<Meta> selectMetas(String email) {
        String sql = "SELECT * FROM meta WHERE email = ?";
        ArrayList<Meta> metas = new ArrayList<>();

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Meta meta = new Meta(email);
                meta.setNomeMeta(rs.getString("nome"));
                meta.setValorMeta(rs.getFloat("valor"));
                meta.setMontante(rs.getFloat("montante"));
                metas.add(meta);
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println("Erro ao buscar Metas: " + e.getMessage());
        }
        return metas;
    }

    public Meta selectMeta(String email, String nome) {
        String sql = "SELECT * FROM meta WHERE email = ? AND nome = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, nome);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Meta meta = new Meta(email);
                meta.setNomeMeta(rs.getString("nome"));
                meta.setValorMeta(rs.getFloat("valor"));
                meta.setMontante(rs.getFloat("montante"));
                rs.close();
                return meta;
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println("Erro ao buscar Meta: " + e.getMessage());
        }
        return null;
    }

    public void insertMeta(String email, String nome, float valor) {
        String sql = "INSERT INTO meta (email, nome, valor, montante) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, nome);
            stmt.setFloat(3, valor);
            stmt.setFloat(4, 0.0f); // Inicializa montante com 0

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao inserir Meta: " + e.getMessage());
        }
    }

    public void updateValorMeta(String email, String nome, float valorDesejado) {
        String sql = "UPDATE meta SET valor = ? WHERE email = ? AND nome = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setFloat(1, valorDesejado);
            stmt.setString(2, email);
            stmt.setString(3, nome);

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar valor da meta: " + e.getMessage());
        }
    }

    public void updateNomeMeta(String email, String nome, String nomeDesejado) {
        String sql = "UPDATE meta SET nome = ? WHERE email = ? AND nome = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, nomeDesejado);
            stmt.setString(2, email);
            stmt.setString(3, nome);

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar nome da Meta: " + e.getMessage());
        }
    }

    public void updateDepositarValorMeta(String email, String nome, float valorDeposito) {
        String sql = "UPDATE meta SET montante = montante + ? WHERE email = ? AND nome = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setFloat(1, valorDeposito);
            stmt.setString(2, email);
            stmt.setString(3, nome);

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao depositar valor na Meta: " + e.getMessage());
        }
    }

    public void updateDebitarMontanteMeta(String email, String nome, float valorDebito) {
        String sql = "UPDATE meta SET montante = montante - ? WHERE email = ? AND nome = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setFloat(1, valorDebito);
            stmt.setString(2, email);
            stmt.setString(3, nome);

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao debitar valor na Meta: " + e.getMessage());
        }
    }

    public void deleteMeta(String email, String nome) {
        String sql = "DELETE FROM meta WHERE email = ? AND nome = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, nome);

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao deletar meta: " + e.getMessage());
        }
    }
}
