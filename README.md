# Validador de Atividades Complementares
![Java](https://img.shields.io/badge/Java-17%2B-blue?style=for-the-badge&logo=java)
![License](https://img.shields.io/badge/License-MIT-green?style=for-the-badge)

IFSC 3 fase, INTEGRANTES DO PROJETO: Kauã machado, Murilo possamai, Gabriel lucas da conceição lima
## 1. Apresentação do Projeto

Este projeto consiste em um sistema de console em Java desenvolvido para realizar a avaliação automatizada de requerimentos de validação de **Atividades Complementares**. O software foi baseado nas regras e critérios do Instituto Federal de Santa Catarina – Câmpus Tubarão, simulando um ambiente real de análise de horas.

O programa permite que um aluno submeta suas atividades, declare as horas realizadas, e ao final, receba um parecer completo e detalhado, com as horas validadas e observações, tudo calculado automaticamente com base nas regras de negócio pré-definidas no sistema.

### Principais Funcionalidades

-   **Interface de Console Interativa**: Menus de navegação simples para que o aluno possa selecionar modalidades e atividades.
-   **Avaliação Automatizada**: Cálculo automático das horas validadas com base em diferentes regras (limite de horas, valor fixo, etc.).
-   **Regras de Negócio Centralizadas**: Toda a lógica de validação e as atividades disponíveis são gerenciadas em um único local, facilitando futuras manutenções.
-   **Geração de Parecer Detalhado**: Ao final do processo, um relatório completo é exibido, com o resumo das atividades, horas declaradas, horas validadas e observações pertinentes.

## 2. Design e Arquitetura

O sistema foi projetado com foco na separação de responsabilidades, flexibilidade e manutenibilidade, utilizando padrões de projeto para garantir um código limpo e robusto.

### Diagrama UML de Classes

O diagrama a seguir ilustra as principais classes do sistema e como elas se relacionam.

![mermaid-diagram-2025-06-30-142628](https://github.com/user-attachments/assets/2d8b6540-2010-47ab-b588-9fd276964d2f)


**Descrição das Relações:**
-   `Main`: Apenas inicia a aplicação, instanciando o `SistemaController`.
-   `SistemaController`: É o "cérebro" da aplicação. Ele gerencia o fluxo de interação com o usuário, utiliza o `RegrasDeNegocioService` para obter os dados das atividades e o `GeradorParecer` para formatar o relatório final.
-   `RegrasDeNegocioService`: Implementa o padrão **Singleton** e atua como a única fonte de verdade para as regras de negócio (modalidades e atividades), que são carregadas em memória.
-   `GeradorParecer`: É um serviço com a responsabilidade única de formatar a `String` do parecer.
-   `Requerimento`: Representa uma atividade submetida pelo aluno, conectando uma `Atividade` às horas declaradas.
-   `Atividade`: Contém a descrição de uma atividade e delega a lógica de cálculo para sua **Estratégia** de validação.
-   `IValidacaoStrategy`: É a **Interface** do padrão Strategy, que define o contrato para diferentes algoritmos de validação.

### Padrões de Projeto Utilizados

1.  **Strategy Pattern**
    -   **Onde foi usado**: No núcleo do sistema de validação, com a interface `IValidacaoStrategy` e suas implementações (`ValidacaoLimiteSimplesStrategy`, `ValidacaoValorFixoStrategy`).
    -   **Justificativa**: Este padrão foi escolhido para desacoplar a lógica de validação da classe `Atividade`. Em vez de a atividade ter vários `if/else` para saber como calcular as horas, ela simplesmente delega essa responsabilidade a um objeto de estratégia. Isso torna o sistema extremamente flexível: para criar uma nova regra de validação (ex: "horas em dobro aos domingos"), basta criar uma nova classe que implemente `IValidacaoStrategy`, sem alterar nenhuma linha de código existente.

2.  **Singleton Pattern**
    -   **Onde foi usado**: Na classe `RegrasDeNegocioService`.
    -   **Justificativa**: As regras de negócio (modalidades, atividades e seus limites) são fixas durante a execução do programa. O padrão Singleton garante que essas regras sejam carregadas **uma única vez** em memória e que exista apenas **uma instância** dessa classe servindo como fonte de verdade para toda a aplicação. Isso economiza memória e garante a consistência dos dados.

3.  **Separação de Responsabilidades (Princípio SRP)**
    -   Embora não seja um "padrão" no mesmo sentido, o projeto segue fortemente este princípio. Cada classe tem um propósito claro e único:
        -   `SistemaController`: Controla o fluxo da aplicação.
        -   `GeradorParecer`: Apenas formata o texto do parecer.
        -   `RegrasDeNegocioService`: Apenas fornece os dados das regras.
        -   Classes do `model`: Apenas representam os dados do domínio.
    -   **Justificativa**: Essa separação torna o código mais fácil de entender, testar e modificar no futuro.

## 3. Estrutura do Projeto

O projeto foi organizado em um único pacote para simplificar, conforme solicitado. A estrutura de arquivos dentro da pasta `src` é a seguinte:

```
└── br/
    └── edu/
        └── ifsc/
            └── sistema/
                ├── Atividade.java
                ├── GeradorParecer.java
                ├── IValidacaoStrategy.java
                ├── Main.java
                ├── Modalidade.java
                ├── RegrasDeNegocioService.java
                ├── Requerimento.java
                ├── ResultadoValidacao.java
                ├── SistemaController.java
                ├── ValidacaoLimiteSimplesStrategy.java
                └── ValidacaoValorFixoStrategy.java
```

## 4. Instruções de Uso

Siga os passos abaixo para compilar e executar o projeto através do terminal.

### Pré-requisitos

-   **Java Development Kit (JDK)** - Versão 17 ou superior instalada.
    -   Para verificar se você tem o Java instalado, abra o terminal e digite: `java --version`

### Como Compilar e Executar

1.  **Navegue até a pasta do projeto**: Abra seu terminal (Prompt de Comando, PowerShell, etc.) e use o comando `cd` para navegar até a pasta raiz do projeto (a pasta que contém o diretório `src`).

2.  **Compile o código**: Execute o comando abaixo. Ele irá compilar todos os arquivos `.java` e colocar os arquivos `.class` resultantes em um novo diretório chamado `out`.

    ```bash
    javac -d out src/br/edu/ifsc/sistema/*.java
    ```

3.  **Execute o programa**: Após a compilação bem-sucedida, execute o seguinte comando para iniciar a aplicação.

    ```bash
    java -cp out br.edu.ifsc.sistema.Main
    ```

4.  **Interaja com o programa**: O prompt de comando ficará ativo, solicitando a matrícula do aluno e, em seguida, exibindo os menus para a seleção das atividades. Siga as instruções na tela.

---
