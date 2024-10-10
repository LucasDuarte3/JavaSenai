package usuario;

public class Dados {
    private int Id;
    private String Nome;
    private String Email;
    private String Telefone;
    private String Tipo_usuario;

    public Dados(int Id, String Nome, String Email, String Telefone, String Tipo_usuario) {
        this.Id = Id;
        this.Nome = Nome;
        this.Email = Email;
        this.Telefone = Telefone;
        this.Tipo_usuario = Tipo_usuario;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String Telefone) {
        this.Telefone = Telefone;
    }

    public String getTipo_usuario() {
        return Tipo_usuario;
    }

    public void setTipo_usuario(String Tipo_usuario) {
        this.Tipo_usuario = Tipo_usuario;
    }
    
    
}

