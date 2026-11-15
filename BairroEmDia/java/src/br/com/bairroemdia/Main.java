package br.com.bairroemdia;
import br.com.bairroemdia.modelo.*;
import br.com.bairroemdia.servico.BairroService;
import java.util.Scanner;

public class Main {
    private final Scanner entrada=new Scanner(System.in);
    private final BairroService servico=new BairroService();
    private String ler(String mensagem){System.out.print(mensagem);return entrada.nextLine();}
    private int numero(String mensagem){
        try{return Integer.parseInt(ler(mensagem).trim());}
        catch(NumberFormatException e){throw new IllegalArgumentException("Digite um número inteiro válido.");}
    }
    private void mostrar(Ocorrencia o){
        System.out.println(o.getId()+" | "+o.getTitulo()+" | "+o.getStatus()+" | "+o.getCategoria().getNome());
        System.out.println(o.getDescricao()+" | Local: "+o.getLocalizacao()+" | Morador: "+o.getMorador().getNome());
    }
    public void executar(){
        boolean aberto=true;
        while(aberto && entrada.hasNextLine()){
            System.out.println("\nBAIRRO EM DIA - SIMULAÇÃO ACADÊMICA\n1 Cadastrar morador\n2 Listar moradores\n3 Cadastrar ocorrência\n4 Listar ocorrências\n5 Buscar e ver histórico\n6 Atualizar situação\n7 Remover ocorrência\n0 Sair");
            try{
                switch(numero("Opção: ")){
                    case 1:
                        Morador m=servico.cadastrarMorador(ler("Nome: "),ler("E-mail: "),ler("Bairro: "));
                        System.out.println("Morador cadastrado. ID: "+m.getId());break;
                    case 2:
                        for(Morador pessoa:servico.listarMoradores()) System.out.println(pessoa.getId()+" | "+pessoa.getNome()+" | "+pessoa.getBairro());break;
                    case 3:
                        for(Categoria c:servico.listarCategorias()) System.out.println(c.getId()+" | "+c.getNome());
                        Ocorrencia o=servico.cadastrarOcorrencia(ler("Título: "),ler("Descrição: "),ler("Local: "),numero("ID do morador: "),numero("ID da categoria: "));
                        System.out.println("Ocorrência criada. ID: "+o.getId());break;
                    case 4:
                        if(servico.listarOcorrencias().isEmpty()) System.out.println("Nenhuma ocorrência cadastrada.");
                        for(Ocorrencia item:servico.listarOcorrencias()) mostrar(item);break;
                    case 5:
                        int id=numero("ID: ");mostrar(servico.buscarOcorrencia(id));
                        for(Atualizacao a:servico.listarAtualizacoes(id)) System.out.println(a.getData()+" | "+a.getMensagem());break;
                    case 6: servico.atualizarOcorrencia(numero("ID: "),ler("Situação (Aberta / Em análise / Resolvida): "),ler("Mensagem: "));System.out.println("Atualizada.");break;
                    case 7:
                        int remover=numero("ID: ");servico.buscarOcorrencia(remover);
                        if(ler("Excluir ocorrência e histórico? Digite SIM: ").equals("SIM")){servico.removerOcorrencia(remover);System.out.println("Removida.");}break;
                    case 0:aberto=false;break;
                    default:System.out.println("Opção inexistente.");
                }
            }catch(IllegalArgumentException e){System.out.println("Atenção: "+e.getMessage());}
        }
    }
    public static void main(String[] args){new Main().executar();}
}
