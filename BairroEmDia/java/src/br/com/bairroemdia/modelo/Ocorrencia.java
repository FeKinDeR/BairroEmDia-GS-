package br.com.bairroemdia.modelo;

public class Ocorrencia {
    private int id;
    private String titulo;
    private String descricao;
    private String localizacao;
    private String status;
    private Morador morador;
    private Categoria categoria;
    public Ocorrencia(int id, String titulo, String descricao, String localizacao, String status, Morador morador, Categoria categoria) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.localizacao = localizacao;
        this.status = status;
        this.morador = morador;
        this.categoria = categoria;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getLocalizacao() { return localizacao; }
    public void setLocalizacao(String localizacao) { this.localizacao = localizacao; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Morador getMorador() { return morador; }
    public void setMorador(Morador morador) { this.morador = morador; }
    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
}
