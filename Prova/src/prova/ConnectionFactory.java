/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prova;


import java.sql.*;

public class ConnectionFactory {
    public Connection ConectaDB() {
        Connection conn = null;
        try {
            // Configura a conexão com o banco de dados
            String url = "jdbc:mysql://localhost:3307/cliente"; // URL do banco de dados
            String user = "root"; // Nome de usuário
            String password = ""; // Senha
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
