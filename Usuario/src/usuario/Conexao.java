package usuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    public static Connection getConexao() throws SQLException {
        String url = "jdbc:mysql://localhost:3307/usuarios";
        String user = "root";
        String password = "";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conexão estabelecida com sucesso.");
            return connection;
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados:");
            e.printStackTrace(); // Imprime o stack trace da exceção
            throw e; // Re-lança a exceção para permitir que o chamador lide com ela
        }
    }
}
