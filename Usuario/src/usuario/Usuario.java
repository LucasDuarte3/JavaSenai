package usuario;

import java.sql.SQLException;
import java.time.LocalDate;

public class Usuario {

    public static void main(String[] args) {
       try {
           
           
           
            Biblioteca biblioteca = new Biblioteca();

            // Adicionar um livro
            Livro livro = new Livro(0, "O Hobbit", "J.R.R. Tolkien", "HarperCollins", 1937, true);
            biblioteca.adicionarLivro(livro);
            System.out.println("Livro adicionado: " + livro.getTitulo());

            // Adicionar um usuário (Cliente)
            Cliente novoCliente = new Cliente(0, "Lucas", "exemplo@gmail.com", "7869597979", "colaborador", 10);
            biblioteca.adicionarUsuario(novoCliente);
            System.out.println("Cliente adicionado: " + novoCliente.getNome());

            // Buscar um livro por título
            Livro livroEncontrado = biblioteca.buscarLivroPorTitulo("O Hobbit");
            if (livroEncontrado != null) {
                System.out.println("Livro encontrado: " + livroEncontrado.getTitulo());
            } else {
                System.out.println("Livro não encontrado.");
            }

            // Registrar um empréstimo
            if (livroEncontrado != null) {
                biblioteca.registrarEmprestimo(novoCliente, livroEncontrado, LocalDate.now());
                System.out.println("Empréstimo registrado para o cliente: " + novoCliente.getNome());
            }

            // Devolver um livro
            // Aqui você deve recuperar o ID do empréstimo para atualizar
            // Exemplo de código para devolução:
            // Emprestimo emprestimo = ... // Recuperar do banco de dados ou configurar manualmente
            // biblioteca.registrarDevolucao(emprestimo, LocalDate.now());
            // System.out.println("Livro devolvido.");

            // Remover o livro
            biblioteca.removerLivro(livroEncontrado);
            System.out.println("Livro removido: " + livroEncontrado.getTitulo());

            // Remover o cliente
            biblioteca.removerUsuario(novoCliente);
            System.out.println("Cliente removido: " + novoCliente.getNome());

        } catch (SQLException e) {
            e.printStackTrace();
        }
       
       
    }

    
} 
