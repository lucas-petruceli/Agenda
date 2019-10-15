package pucminas.br.agenda;

public class Contato {

    private int ID;
    private String nome;
    private String email;
    private String nascimento;
    private String telefone;
    private String endereco;

    public Contato() {
        this.ID = 0;
        this.nome = "";
        this.email = "";
        this.nascimento = "";
        this.telefone = "";
        this.endereco = "";
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public Contato(String nome, String email, String nascimento, String telefone, String endereco) {
        this.ID = 0;
        this.nome = nome;
        this.email = email;
        this.nascimento = nascimento;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

}
