package usuario;

public class funcionario extends Dados{
    private String cargo;

    public funcionario(int Id, String Nome, String Email, String Telefone, String Tipo_usuario) {
        super(Id, Nome, Email, Telefone, Tipo_usuario);
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    
}
