package prova;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class clienteDAO {
    
    private Connection connection;

    public clienteDAO() {
        this.connection = new ConnectionFactory().ConectaDB();
    }

    // Método para criar um novo usuário
    public void criarUsuario(Cliente usuario) throws SQLException {
    String sql = "INSERT INTO cliente(Nome, CPF, data_nascimento, Altura) VALUES(?, ?, ?, ?)";

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setString(1, usuario.getNome());
        stmt.setString(2, usuario.getCpf());
        stmt.setDate(3, new java.sql.Date(usuario.getData_nasc().getTime())); // Certifique-se que getData_nasc retorna um tipo compatível
        stmt.setFloat(4, usuario.getAltura());
        stmt.executeUpdate();
        System.out.println("Usuário criado com sucesso!");
    } catch (SQLException e) {
        System.out.println("Erro ao criar usuário: " + e.getMessage());
        throw e; // Opcional: relançar a exceção se desejar tratar mais tarde
    }
}

    // Método para listar todos os usuários
    public List<Cliente> listarUsuarios() throws SQLException {
        String sql = "SELECT * FROM cliente";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cliente> usuarios = new ArrayList<>();
        try {
            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("Nome"));
                cliente.setCpf(rs.getString("CPF"));
                cliente.setData_nasc(rs.getDate("data_nascimento"));
                cliente.setAltura(rs.getFloat("Altura"));
                usuarios.add(cliente);
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
    public Cliente buscarUsuarioPorId(int id) throws SQLException {
        String sql = "SELECT * FROM cliente WHERE id = ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cliente cliente = null;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("CPF"));
                
                java.sql.Date dataNascimento = rs.getDate("data_nascimento");
                if (dataNascimento != null) {
                    cliente.setData_nasc(new java.util.Date(dataNascimento.getTime())); // Converte para java.util.Date
                }
                
                cliente.setAltura(rs.getFloat("altura"));
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
        return cliente;
    }

    // Método para atualizar um usuário
    public void atualizarUsuario(Cliente usuario) throws SQLException {
        String sql = "UPDATE cliente SET nome = ?, CPF = ?, data_nascimento = ?, Altura = ? WHERE id = ?";
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getCpf());
            stmt.setDate(3, (Date) usuario.getData_nasc());
            stmt.setFloat(4, usuario.getAltura());
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
    public void atualizarNomeUsuario(int id, String novoNome) throws SQLException {
    String sql = "UPDATE cliente SET Nome = ? WHERE id = ?";
    PreparedStatement stmt = null;
    try {
        stmt = connection.prepareStatement(sql);
        stmt.setString(1, novoNome);
        stmt.setInt(2, id);

        // Executa a atualização
        stmt.executeUpdate();
        System.out.println("Nome do usuário atualizado com sucesso!");
    } catch (SQLException e) {
        System.out.println("Erro ao atualizar nome do usuário: " + e.getMessage());
        throw e;
    } finally {
        if (stmt != null) {
            stmt.close();
        }
    }
}

    // Método para deletar um usuário
    public void deletarUsuario(int id) throws SQLException {
        String sql = "DELETE FROM cliente WHERE id = ?";
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
