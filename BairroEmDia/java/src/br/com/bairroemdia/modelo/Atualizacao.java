package br.com.bairroemdia.modelo;

public class Atualizacao {
    private int id;
    private Ocorrencia ocorrencia;
    private String data;
    private String mensagem;
    public Atualizacao(int id, Ocorrencia ocorrencia, String data, String mensagem) {
        this.id = id;
        this.ocorrencia = ocorrencia;
        this.data = data;
        this.mensagem = mensagem;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Ocorrencia getOcorrencia() { return ocorrencia; }
    public void setOcorrencia(Ocorrencia ocorrencia) { this.ocorrencia = ocorrencia; }
    public String getData() { return data; }
    public void setData(String data) { this.data = data; }
    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }
}
