# Bairro em Dia

O **Bairro em Dia** é um projeto acadêmico desenvolvido para a **Global Solution da FIAP**. O protótipo simula como moradores podem registrar problemas no bairro, como postes apagados, buracos nas vias e descarte irregular de resíduos, e acompanhar a situação de cada ocorrência.

Cada ocorrência reúne título, descrição, localização, categoria, morador responsável pelo relato e histórico de atualizações. As situações disponíveis são **Aberta**, **Em análise** e **Resolvida**.

## Como o projeto funciona

| Parte | Tecnologia | O que você pode testar |
| --- | --- | --- |
| Protótipo web | HTML, CSS e JavaScript | Consultar ocorrências de exemplo, buscar por título ou localização, filtrar por categoria e situação, ver o histórico e simular um novo registro. |
| Aplicação de terminal | Java | Cadastrar e listar moradores, registrar ocorrências, buscar por ID, consultar o histórico, atualizar a situação e remover ocorrências. |
| Banco de dados | SQLite | Criar as tabelas, carregar dados fictícios e executar consultas de listagem, contagem por situação e histórico. |

As três partes podem ser executadas de forma independente. O Java guarda os dados em memória durante a execução. No navegador, o registro simulado aparece abaixo do formulário e é descartado ao trocar de página ou recarregar. O SQLite mantém os dados no arquivo `.db` criado ao executar o script.

## Como executar no Windows

### 1. Baixe o projeto

Com o Git instalado, abra o PowerShell na pasta onde deseja baixar o projeto e execute:

```powershell
git clone https://github.com/FeKinDeR/BairroEmDia-GS-.git
cd .\BairroEmDia-GS-
```

Se preferir baixar pelo navegador, selecione **Code → Download ZIP** no GitHub, extraia o arquivo e abra o PowerShell na pasta que contém `BairroEmDia`.

Os comandos abaixo partem dessa mesma pasta.

### 2. Abra o protótipo web

```powershell
Start-Process .\BairroEmDia\web\index.html
```

Você também pode abrir `BairroEmDia/web/index.html` com um duplo clique. Basta ter um navegador. A interface usa HTML, CSS e JavaScript e pode ser aberta diretamente, sem instalar dependências.

Para testar, abra **Ocorrências**, aplique um filtro e clique em **Ver histórico** em um dos cartões. Depois, abra **Registrar**, preencha o formulário com dados fictícios e confira o novo cartão abaixo do formulário.

### 3. Execute a aplicação Java

Instale um **JDK 17** e confirme que o PowerShell reconhece o Java e o compilador:

```powershell
java -version
javac -version
```

Para compilar e iniciar pelo script incluído no projeto:

```powershell
.\BairroEmDia\java\executar.bat
```

Para compilar e executar manualmente pelo PowerShell:

```powershell
$fontes = (Get-ChildItem .\BairroEmDia\java\src -Recurse -Filter *.java -File).FullName
javac -encoding UTF-8 -d .\BairroEmDia\java\out $fontes
java -cp .\BairroEmDia\java\out br.com.bairroemdia.Main
```

Ao abrir a aplicação, pressione **Enter** para exibir o menu. Digite `4` para listar as ocorrências ou `5` e depois `1` para consultar o histórico da ocorrência de exemplo. Use `0` para sair.

Se `javac` não for reconhecido, confirme a instalação do JDK, adicione a pasta `bin` dele ao `PATH` e abra uma nova janela do PowerShell.

### 4. Execute o script SQLite

Com o comando `sqlite3` disponível no PowerShell, execute:

```powershell
sqlite3 -header -column .\bairro_em_dia.db ".read BairroEmDia/banco/bairro_em_dia.sql"
```

O script cria quatro tabelas (`morador`, `categoria`, `ocorrencia` e `atualizacao`), insere dados de exemplo e exibe três consultas no terminal. Para repetir a carga inicial, use outro nome de arquivo `.db`, pois as tabelas já estarão criadas no primeiro banco.

Para consultar o banco depois:

```powershell
sqlite3 -header -column .\bairro_em_dia.db "SELECT id, titulo, status FROM ocorrencia;"
```

## Estrutura do repositório

| Caminho | Conteúdo |
| --- | --- |
| [`BairroEmDia/web/`](BairroEmDia/web/) | Páginas e arquivos do protótipo web |
| [`BairroEmDia/java/`](BairroEmDia/java/) | Código-fonte Java e script de execução para Windows |
| [`BairroEmDia/banco/`](BairroEmDia/banco/) | Script SQL, dados de exemplo e consultas |
| [`BairroEmDia/diagramas/`](BairroEmDia/diagramas/) | Diagramas de classes UML e modelo entidade-relacionamento |
| [`BairroEmDia/docs/`](BairroEmDia/docs/) | Checklist, roteiro do pitch e verificações do projeto |

## Equipe

- Felipe Staropoli de Paiva
- Alvaro Miguel
- Thais Verdi Bona
- Taua Eduardo Marins
