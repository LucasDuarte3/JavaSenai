package contato;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Agenda agenda = new Agenda();
        

        // CREATE
//        Contato contato1 = agenda.adicionarContato("Joao", "123456789");
//        Contato contato2 = agenda.adicionarContato("Maria", "987654321");
        
        // READ
        System.out.println("Lista de contatos:");
        for (Contato contato : agenda.listarContatos()) {
            System.out.println(contato);
        }
        
        // UPDATE
         System.out.print("\nDigite o ID do contato para atualizar: ");
        int idParaAtualizar = scanner.nextInt();
        scanner.nextLine(); // Consome a nova linha deixada por nextInt
        System.out.print("Digite o novo nome: ");
        String novoNome = scanner.nextLine();
        System.out.print("Digite o novo telefone: ");
        String novoTelefone = scanner.nextLine();
        agenda.atualizarContato(idParaAtualizar, novoNome, novoTelefone);
        
        // READ após atualização
        System.out.println("\nLista de contatos após atualização:");
        for (Contato contato : agenda.listarContatos()) {
            System.out.println(contato);
        }
        
        // DELETE
        System.out.print("\nDigite o ID do contato para remover: ");
        int idParaRemover = scanner.nextInt();
        agenda.removerContato(idParaRemover);
        
        // READ após remoção
        System.out.println("\nLista de contatos após remoção:");
        for (Contato contato : agenda.listarContatos()) {
            System.out.println(contato);
        }
        scanner.close();
    }
}


