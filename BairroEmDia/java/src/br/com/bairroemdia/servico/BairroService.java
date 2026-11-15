package br.com.bairroemdia.servico;
import br.com.bairroemdia.modelo.*;
import java.util.*;
import java.time.LocalDate;

public class BairroService {
    private final List<Morador> moradores = new ArrayList<>();
    private final List<Categoria> categorias = new ArrayList<>();
    private final List<Ocorrencia> ocorrencias = new ArrayList<>();
    private final List<Atualizacao> atualizacoes = new ArrayList<>();
    private int proximoMorador=1, proximaOcorrencia=1, proximaAtualizacao=1;
    public BairroService() {
        categorias.add(new Categoria(1,"Iluminação"));
        categorias.add(new Categoria(2,"Vias públicas"));
        categorias.add(new Categoria(3,"Limpeza"));
        cadastrarMorador("Ana Exemplo","ana@example.com","Centro");
        cadastrarOcorrencia("Lâmpada apagada","Poste sem iluminação há três dias.","Rua das Flores, 120",1,1);
    }
    private String texto(String valor, int minimo) {
        if(valor==null || valor.trim().length()<minimo) throw new IllegalArgumentException("Preencha o campo com pelo menos "+minimo+" caracteres.");
        return valor.trim();
    }
    public Morador cadastrarMorador(String nome,String email,String bairro) {
        nome=texto(nome,3); email=texto(email,5); bairro=texto(bairro,2);
        if(!email.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) throw new IllegalArgumentException("E-mail inválido.");
        for(Morador m:moradores) if(m.getEmail().equalsIgnoreCase(email)) throw new IllegalArgumentException("E-mail já cadastrado.");
        Morador m=new Morador(proximoMorador++,nome,email,bairro); moradores.add(m); return m;
    }
    public List<Morador> listarMoradores(){ return new ArrayList<>(moradores); }
    public List<Categoria> listarCategorias(){ return new ArrayList<>(categorias); }
    public List<Ocorrencia> listarOcorrencias(){ return new ArrayList<>(ocorrencias); }
    public Ocorrencia buscarOcorrencia(int id){
        for(Ocorrencia o:ocorrencias) if(o.getId()==id) return o;
        throw new IllegalArgumentException("Ocorrência não encontrada.");
    }
    public Ocorrencia cadastrarOcorrencia(String titulo,String descricao,String local,int moradorId,int categoriaId){
        titulo=texto(titulo,5);descricao=texto(descricao,10);local=texto(local,5);
        Morador morador=null; Categoria categoria=null;
        for(Morador m:moradores) if(m.getId()==moradorId) morador=m;
        for(Categoria c:categorias) if(c.getId()==categoriaId) categoria=c;
        if(morador==null || categoria==null) throw new IllegalArgumentException("Morador ou categoria inexistente.");
        Ocorrencia o=new Ocorrencia(proximaOcorrencia++,titulo,descricao,local,"Aberta",morador,categoria);
        ocorrencias.add(o); adicionarAtualizacao(o.getId(),"Registro criado pelo morador."); return o;
    }
    public void atualizarOcorrencia(int id,String status,String mensagem){
        Ocorrencia o=buscarOcorrencia(id);
        if(!Arrays.asList("Aberta","Em análise","Resolvida").contains(status)) throw new IllegalArgumentException("Use Aberta, Em análise ou Resolvida.");
        mensagem=texto(mensagem,5); o.setStatus(status);adicionarAtualizacao(id,mensagem);
    }
    public void adicionarAtualizacao(int id,String mensagem){
        Ocorrencia o=buscarOcorrencia(id);mensagem=texto(mensagem,5);
        atualizacoes.add(new Atualizacao(proximaAtualizacao++,o,LocalDate.now().toString(),mensagem));
    }
    public List<Atualizacao> listarAtualizacoes(int id){
        buscarOcorrencia(id); List<Atualizacao> resultado=new ArrayList<>();
        for(Atualizacao a:atualizacoes) if(a.getOcorrencia().getId()==id) resultado.add(a);
        return resultado;
    }
    public void removerOcorrencia(int id){
        Ocorrencia o=buscarOcorrencia(id);
        atualizacoes.removeIf(a -> a.getOcorrencia().getId()==id);ocorrencias.remove(o);
    }
}
