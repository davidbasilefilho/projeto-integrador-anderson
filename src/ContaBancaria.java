
/**
 * Semana da Aula: Semana 04 - Estruturas de Repeticao e Depuracao
 * Professor.Mse: Anderson Henrique Rodrigues Ferreira
 * Disciplina: Programacao Orientada a Objetos em Java
 *
 * Atividade Integradora — Gabarito
 * Sistema de Conta Bancaria com Historico de Transacoes
 *
 * Arquivo: ContaBancaria.java
 * Conceitos cobertos:
 *   Semana 01: classe, referencia a outro objeto (Cliente), sobrecarga de construtores
 *   Semana 02: tipos primitivos (int, double, boolean, char), StringBuilder, String.format()
 *   Semana 03: operadores aritmeticos, compostos, &&, if-else, switch
 *   Semana 04: ArrayList (Bloco 4), for-each, continue
 */

import java.util.ArrayList;

public class ContaBancaria {
    public enum Tipo {
        CORRENTE,
        POUPANCA,
        INVESTIMENTO
    }

    // Semana 02: tipos primitivos como campos de instancia
    private int numero;
    private Cliente titular; // Semana 01: referencia — aponta para um objeto Cliente no heap
    private double saldo;
    private boolean ativa;
    private Tipo tipo; // Tipo da conta: CORRENTE, POUPANCA, INVESTIMENTO

    // -----------------------------------------------------------------------
    // Semana 04: ARRAYLIST (Bloco 4)
    //
    // ArrayList e uma lista dinamica — cresce automaticamente conforme elementos
    // sao adicionados. Ao contrario de um array fixo (double[]), nao e preciso
    // saber o tamanho antecipadamente.
    //
    // ArrayList<Transacao> historico — cada elemento e um objeto Transacao
    // add(t) — adiciona ao final da lista
    // size() — retorna o numero de elementos
    // -----------------------------------------------------------------------
    private ArrayList<Transacao> historico;

    // -----------------------------------------------------------------------
    // Semana 01: CONSTRUTORES com sobrecarga
    //
    // new ContaBancaria(1001, maria, Tipo.CORRENTE) → Sobrecarga 1 (saldo zero)
    // new ContaBancaria(1001, maria, Tipo.CORRENTE, 500.0) → Sobrecarga 2 (saldo
    // inicial)
    // -----------------------------------------------------------------------

    // Sobrecarga 1: sem saldo inicial (saldo comeca em 0.0)
    public ContaBancaria(int numero, Cliente titular, Tipo tipo) {
        this.numero = numero;
        this.titular = titular;
        this.tipo = tipo;
        this.saldo = 0.0;
        this.ativa = true;
        this.historico = new ArrayList<>(); // lista vazia; crescera a cada operacao
    }

    // Sobrecarga 2: com saldo inicial
    public ContaBancaria(int numero, Cliente titular, Tipo tipo, double saldoInicial) {
        this.numero = numero;
        this.titular = titular;
        this.tipo = tipo;
        this.saldo = saldoInicial;
        this.ativa = true;
        this.historico = new ArrayList<>();
    }

    // -----------------------------------------------------------------------
    // Semana 01: SOBRECARREGAS de metodo para acessadores e modificadores
    // similar ao padrao usado em Cliente: nome()/nome(String), idade()/idade(int)
    // -----------------------------------------------------------------------
    public int numero() {
        return numero;
    }

    public int numero(int numero) {
        return this.numero = numero;
    }

    public Cliente titular() {
        return titular;
    }

    public Cliente titular(Cliente titular) {
        return this.titular = titular;
    }

    public double saldo() {
        return saldo;
    }

    public double saldo(double saldo) {
        return this.saldo = saldo;
    }

    public boolean ativa() {
        return ativa;
    }

    public boolean ativa(boolean ativa) {
        return this.ativa = ativa;
    }

    public Tipo tipo() {
        return tipo;
    }

    public Tipo tipo(Tipo tipo) {
        return this.tipo = tipo;
    }

    public ArrayList<Transacao> historico() {
        return historico;
    }

    public ArrayList<Transacao> historico(ArrayList<Transacao> historico) {
        return this.historico = historico;
    }

    // -----------------------------------------------------------------------
    // Semana 03: if-else + operador de comparacao (>) + operador composto (+=)
    // Semana 04: historico.add() registra cada deposito valido
    // -----------------------------------------------------------------------
    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            historico.add(new Transacao(Transacao.Tipo.DEPOSITO, valor, saldo));
            System.out.println("Deposito de R$ " + valor + " realizado. Novo saldo: R$ " + saldo);
        } else {
            System.out.println("Valor de deposito invalido: " + valor);
        }
    }

    // -----------------------------------------------------------------------
    // Semana 03: && (AND com short-circuit) + operador composto (-=)
    // Se `ativa` for false, `saldo >= valor` NAO e avaliado (short-circuit)
    // Semana 04: historico.add() registra cada saque aprovado
    // -----------------------------------------------------------------------
    public boolean sacar(double valor) {
        if (ativa && saldo >= valor) {
            saldo -= valor;
            historico.add(new Transacao(Transacao.Tipo.SAQUE, valor, saldo));
            System.out.println("Saque de R$ " + valor + " aprovado. Novo saldo: R$ " + saldo);
            return true;
        } else if (!ativa) {
            System.out.println("Saque de R$ " + valor + " NEGADO - conta inativa");
        } else {
            System.out.println("Saque de R$ " + valor + " NEGADO - saldo insuficiente");
        }
        return false;
    }

    // -----------------------------------------------------------------------
    // Semana 03: switch com char + operadores aritmeticos (* e -=)
    // Semana 04: historico.add() registra a taxa cobrada (apenas se valor > 0)
    // -----------------------------------------------------------------------
    public double aplicarTaxa() {
        double percentual;
        switch (tipo) {
            case CORRENTE:
                percentual = 0.02; // 2% para Corrente
                break;
            case POUPANCA:
                percentual = 0.005; // 0,5% para Poupanca
                break;
            case INVESTIMENTO:
                percentual = 0.0; // 0% para Investimento
                break;
            default:
                percentual = 0.0;
        }
        double taxa = saldo * percentual;
        saldo -= taxa;
        if (taxa > 0) {
            historico.add(new Transacao(Transacao.Tipo.TAXA, taxa, saldo));
        }
        return taxa;
    }

    // -----------------------------------------------------------------------
    // Semana 04: FOR-EACH sobre ArrayList
    //
    // Percorre cada Transacao e incrementa o contador quando o tipo corresponde
    // ao que foi pedido.
    //
    // Sintaxe: for (Tipo variavel : colecao)
    // - variavel recebe cada elemento em ordem, sem expor o indice
    // - mais legivel e menos propenso a erros de off-by-one que um for indexado
    // -----------------------------------------------------------------------
    public int contarOperacoes(Transacao.Tipo tipoBuscado) {
        int contador = 0;
        for (Transacao t : historico) {
            if (t.tipo() == tipoBuscado) {
                contador++;
            }
        }
        return contador;
    }

    // -----------------------------------------------------------------------
    // Semana 04: FOR-EACH + variavel de controle
    //
    // Percorre o historico procurando o maior valor entre os depositos.
    // A variavel `maior` e atualizada sempre que um deposito maior e encontrado.
    // -----------------------------------------------------------------------
    public double maiorDeposito() {
        double maior = 0.0;
        for (Transacao t : historico) {
            if (t.tipo() == Transacao.Tipo.DEPOSITO && t.valor() > maior) {
                maior = t.valor();
            }
        }
        return maior;
    }

    // -----------------------------------------------------------------------
    // Semana 04: FOR-EACH + CONTINUE
    //
    // Itera sobre o historico para montar o relatorio linha a linha.
    // O `continue` pula registros com valor zero.
    //
    // Regra do continue: ao encontrar `continue`, o Java ignora o restante do
    // corpo da iteracao atual e passa imediatamente para o proximo elemento.
    // -----------------------------------------------------------------------
    public String gerarHistorico() {
        StringBuilder sb = new StringBuilder("=== HISTORICO: Conta " + numero
                + " (" + titular.nome() + ") ===\n");

        for (Transacao t : historico) {
            if (t.valor() == 0.0)
                continue; // pula entradas sem movimentacao real
            sb.append("  ").append(t.resumo()).append("\n");
        }

        sb.append("  Total registrado: " + historico.size() + " operacoes");
        return sb.toString();
    }

    // Semana 03: switch com char retornando descricao textual
    public String resolverTipo() {
        switch (tipo) {
            case CORRENTE:
                return "Corrente";
            case POUPANCA:
                return "Poupanca";
            case INVESTIMENTO:
                return "Investimento";
            default:
                return "Desconhecido";
        }
    }

    // Semana 03: if-else retornando String conforme o estado da conta
    public String status() {
        return ativa ? "Ativa" : "Inativa";
    }

    // -----------------------------------------------------------------------
    // Semana 02: StringBuilder — mutavel, eficiente para concatenacoes multiplas
    // Semana 02: String.format() para exibir saldo com duas casas decimais
    // -----------------------------------------------------------------------
    public String gerarExtrato() {
        return """
                === EXTRATO ===
                Conta:    %d
                Titular:  %s
                Tipo:     %s
                Status:   %s
                Saldo:    R$ %.2f
                ===============
                """.formatted(numero, titular.nome(), resolverTipo(), status(), saldo);
    }
}
