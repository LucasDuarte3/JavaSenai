package crud_biblioteca;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private Connection connection;

    public UsuarioDAO() {
        this.connection = new ConnectionFactory().ConectaDB();
    }

    // Método para criar um novo usuário
    public void criarUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuario(Nome, Email, Telefone, Tipo_Usuario) VALUES(?, ?, ?, ?)";

        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getTelefone());
            stmt.setString(4, usuario.getTipo_usuario());
            stmt.executeUpdate();
            System.out.println("Usuário criado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao criar usuário: " + e.getMessage());
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    // Método para listar todos os usuários
    public List<Usuario> listarUsuarios() throws SQLException {
        String sql = "SELECT * FROM usuario";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Usuario> usuarios = new ArrayList<>();
        try {
            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setTelefone(rs.getString("telefone"));
                usuario.setTipo_usuario(rs.getString("tipo_usuario"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar usuários: " + e.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return usuarios;
    }

    // Busca de Usuario por ID
    public Usuario buscarUsuarioPorId(int id) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE id = ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario usuario = null;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setTelefone(rs.getString("telefone"));
                usuario.setTipo_usuario(rs.getString("tipo_usuario"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar usuário: " + e.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return usuario;
    }

    // Método para atualizar um usuário
    public void atualizarUsuario(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuario SET nome = ?, email = ?, telefone = ?, tipo_usuario = ? WHERE id = ?";
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getTelefone());
            stmt.setString(4, usuario.getTipo_usuario());
            stmt.setInt(5, usuario.getId());

            // Executa a atualização
            stmt.executeUpdate();
            System.out.println("Usuário atualizado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar usuário: " + e.getMessage());
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    // Método para deletar um usuário
    public void deletarUsuario(int id) throws SQLException {
        String sql = "DELETE FROM usuario WHERE id = ?";
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Usuário deletado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao deletar usuário: " + e.getMessage());
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    // Método para fechar a conexão (caso precise ser manualmente fechado)
    public void fecharConexao() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
