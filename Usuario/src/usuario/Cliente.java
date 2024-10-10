package usuario;

// classe cliente esta herdando atributos e metodos da classe Dados
public class Cliente extends Dados{
    private int numero_emprestimos;

        // super está determinando o que vai herdar
    public Cliente(int Id, String Nome,String Email,String Telefone,String Tipo_usuario, int numero_emprestimos) {
        super(Id, Nome, Email, Telefone, Tipo_usuario);
        this.numero_emprestimos = numero_emprestimos;
    }

    public int getNumero_emprestimos() {
        return numero_emprestimos;
    }

    public void setNumero_emprestimos(int numero_emprestimos) {
        this.numero_emprestimos = numero_emprestimos;
    }
    // esse metodo vai incrementar na variavel numero de emprestimos 
    public void incrementarEmprestados(){
        this.numero_emprestimos++;
    }
}
