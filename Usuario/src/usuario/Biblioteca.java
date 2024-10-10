package usuario;

import java.sql.*;
import java.time.LocalDate;

public class Biblioteca {
    // Adiciona um livro
    public void adicionarLivro(Livro livro) throws SQLException {
        String sql = "INSERT INTO Livro (titulo, autor, editora, anoPublicacao, disponibilidade) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.getConexao(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, livro.getTitulo());
            pstmt.setString(2, livro.getAutor());
            pstmt.setString(3, livro.getEditora());
            pstmt.setInt(4, livro.getAnoPublicacao());
            pstmt.setBoolean(5, livro.isDisponibilidade());
            pstmt.executeUpdate();
        }
    }

    // Remove um livro
    public void removerLivro(Livro livro) throws SQLException {
        String sql = "DELETE FROM Livro WHERE id = ?";
        try (Connection conn = Conexao.getConexao(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, livro.getId());
            pstmt.executeUpdate();
        }
    }
    
    // Adiciona um usuário
    public void adicionarUsuario(Dados novoCliente) throws SQLException {
        if (novoCliente == null) {
        throw new IllegalArgumentException("O usuário não pode ser nulo");
    }
        
        String sql = "INSERT INTO Usuario (id, nome, email, telefone, Tipo_usuario) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.getConexao(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, novoCliente.getId());
            pstmt.setString(1, novoCliente.getNome());
            pstmt.setString(1, novoCliente.getEmail());
            pstmt.setString(1, novoCliente.getTelefone());
            pstmt.setString(1, novoCliente.getTipo_usuario());
            pstmt.executeUpdate();
        } catch (SQLException e) {
        // Aqui você pode fazer log do erro ou re-lançar a exceção
        System.err.println("Erro ao adicionar usuário: " + e.getMessage());
        throw e;
    }
    }

    // Remove um usuário
    public void removerUsuario(Cliente usuario) throws SQLException {
        String sql = "DELETE FROM Usuario WHERE id = ?";
        try (Connection conn = Conexao.getConexao(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, usuario.getId());
            pstmt.executeUpdate();
        }
    }

    // Busca livro por título
    public Livro buscarLivroPorTitulo(String titulo) throws SQLException {
        String sql = "SELECT * FROM Livro WHERE titulo = ?";
        try (Connection conn = Conexao.getConexao(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, titulo);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Livro(
                    rs.getInt("id"), // Ensure this matches your database schema
                    rs.getString("titulo"),
                    rs.getString("autor"),
                    rs.getString("editora"),
                    rs.getInt("anoPublicacao"),
                    rs.getBoolean("disponibilidade")
                );
            }
        }
        return null;
    }

    // Registra um empréstimo
    public void registrarEmprestimo(Cliente cliente, Livro livro, LocalDate dataEmprestimo) throws SQLException {
        String sqlEmprestimo = "INSERT INTO Emprestimo (dataEmprestimo, livro_id, cliente_id) VALUES (?, ?, ?)";
        String sqlUpdateLivro = "UPDATE Livro SET disponibilidade = FALSE WHERE id = ?";
        String sqlUpdateCliente = "UPDATE Usuario SET numeroDeEmprestimos = numeroDeEmprestimos + 1 WHERE id = ?";
        
        try (Connection conn = Conexao.getConexao(); 
             PreparedStatement pstmtEmprestimo = conn.prepareStatement(sqlEmprestimo);
             PreparedStatement pstmtUpdateLivro = conn.prepareStatement(sqlUpdateLivro);
             PreparedStatement pstmtUpdateCliente = conn.prepareStatement(sqlUpdateCliente)) {
            
            // Registrar o empréstimo
            pstmtEmprestimo.setDate(1, Date.valueOf(dataEmprestimo));
            pstmtEmprestimo.setInt(2, livro.getId());
            pstmtEmprestimo.setInt(3, cliente.getId());
            pstmtEmprestimo.executeUpdate();
            
            // Atualizar a disponibilidade do livro
            pstmtUpdateLivro.setInt(1, livro.getId());
            pstmtUpdateLivro.executeUpdate();
            
            // Atualizar o número de empréstimos do cliente
            pstmtUpdateCliente.setInt(1, cliente.getId());
            pstmtUpdateCliente.executeUpdate();
        }
    }

}