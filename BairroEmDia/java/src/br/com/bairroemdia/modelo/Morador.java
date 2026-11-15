package br.com.bairroemdia.modelo;

public class Morador {
    private int id;
    private String nome;
    private String email;
    private String bairro;
    public Morador(int id, String nome, String email, String bairro) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.bairro = bairro;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getBairro() { return bairro; }
    public void setBairro(String bairro) { this.bairro = bairro; }
}
