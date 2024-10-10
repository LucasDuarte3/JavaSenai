package contato;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Agenda {
    private Connection connection;

    public Agenda() {
        try {
            // Configura a conexão com o banco de dados
            String url = "jdbc:mysql://localhost:3307/agenda_db"; // URL do banco de dados
            String user = "root"; // Nome de usuário
            String password = ""; // Senha
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // CREATE
    public Contato adicionarContato(String nome, String telefone) {
        String sql = "INSERT INTO contatos (nome, telefone) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, nome);
            stmt.setString(2, telefone);
            stmt.executeUpdate();
            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    return new Contato(id, nome, telefone);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // READ
    public List<Contato> listarContatos() {
        List<Contato> contatos = new ArrayList<>();
        String sql = "SELECT * FROM contatos";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String telefone = rs.getString("telefone");
                contatos.add(new Contato(id, nome, telefone));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contatos;
    }

    public Contato buscarContatoPorId(int id) {
        String sql = "SELECT * FROM contatos WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String nome = rs.getString("nome");
                    String telefone = rs.getString("telefone");
                    return new Contato(id, nome, telefone);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // UPDATE
    public boolean atualizarContato(int id, String nome, String telefone) {
        String sql = "UPDATE contatos SET nome = ?, telefone = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setString(2, telefone);
            stmt.setInt(3, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // DELETE
    public boolean removerContato(int id) {
        String sql = "DELETE FROM contatos WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}


