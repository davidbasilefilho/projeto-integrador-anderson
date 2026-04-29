# Sistema de Conta Bancária

Este projeto Java implementa um sistema simples de contas bancárias com histórico de transações. Ele demonstra conceitos de Programação Orientada a Objetos, laços de repetição, listas dinâmicas e manipulação de strings.

## Funcionalidades

- Criação de clientes e contas bancárias
- Depósitos e saques com validação de valores
- Aplicação de taxas por tipo de conta
- Registro de histórico de transações usando `ArrayList<Transacao>`
- Geração de extratos e estatísticas de contas
- Demonstração de conceitos de Java como:
  - sobrecarga de construtores
  - referências a objetos
  - `for`, `do-while`, `for-each`, `break` e `continue`
  - comparação de strings com `==` e `.equals()`

## Estrutura do projeto

- `src/`
  - `Main.java` — executável principal com exemplos de uso do sistema
  - `Cliente.java` — representa o titular da conta
  - `ContaBancaria.java` — implementa a lógica da conta e histórico
  - `Transacao.java` — representa operações de depósito, saque e taxa
- `bin/` — saída de compilação Java

## Requisitos

- Java 18 ou versão compatível instalada
- Variável `JAVA_HOME` configurada opcionalmente

## Compilar e executar

No terminal, a partir da pasta do projeto:

```bash
javac -d bin src\*.java
java -cp bin Main
```

Se estiver usando o VS Code, você também pode executar o arquivo `Main.java` diretamente pelo depurador ou pelo botão de execução.

## Observações

- O projeto já usa a pasta `bin/` para armazenar classes compiladas.
- Todos os arquivos de código-fonte estão em `src/`.
- O `Main` contém exemplos de operações e impressão de relatórios no console.
