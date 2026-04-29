/**
 * Semana da Aula: Semana 04 - Estruturas de Repeticao e Depuracao
 * Professor.Mse: Anderson Henrique Rodrigues Ferreira
 * Disciplina: Programacao Orientada a Objetos em Java
 *
 * Atividade Integradora — Gabarito
 * Sistema de Conta Bancaria com Historico de Transacoes
 *
 * Arquivo: Transacao.java
 * Conceitos:
 * Semana 01: classe, construtor, campos de instancia
 * Semana 02: String.format() para formatacao monetaria com duas casas decimais
 * Semana 03: operador ternario — deposito soma, saque e taxa subtraem
 */
public class Transacao {

    public enum Tipo {
        DEPOSITO,
        SAQUE,
        TAXA
    }

    // Semana 02: tipos primitivos + enum como campos de instancia
    private Tipo tipo; // DEPOSITO | SAQUE | TAXA
    private double valor; // valor absoluto da operacao (sempre positivo)
    private double saldoApos; // saldo da conta imediatamente apos esta transacao

    // -----------------------------------------------------------------------
    // Semana 01: CONSTRUTOR
    // Inicializa os tres campos de uma so vez ao criar o objeto.
    // -----------------------------------------------------------------------
    public Transacao(Tipo tipo, double valor, double saldoApos) {
        this.tipo = tipo;
        this.valor = valor;
        this.saldoApos = saldoApos;
    }

    public Tipo tipo() {
        return tipo;
    }

    public Tipo tipo(Tipo tipo) {
        return this.tipo = tipo;
    }

    public double valor() {
        return valor;
    }

    public double valor(double valor) {
        return this.valor = valor;
    }

    public double saldoApos() {
        return saldoApos;
    }

    public double saldoApos(double saldoApos) {
        return this.saldoApos = saldoApos;
    }

    // -----------------------------------------------------------------------
    // Semana 02: String.format() formata numeros com especificadores de largura
    // e casas decimais — evita concatenacao manual para valores monetarios
    //
    // %-8s : String alinhada a esquerda em campo de 8 caracteres
    // %+9.2f: numero com sinal, 9 caracteres, 2 casas decimais
    //
    // Semana 03: OPERADOR TERNARIO
    // condicao ? valor_se_true : valor_se_false
    //
    // Deposito: aumenta o saldo → exibe valor positivo
    // Saque e Taxa: diminuem o saldo → exibe valor negativo
    //
    // Leitura: "se o tipo for DEPOSITO, use +valor; senao, use -valor"
    //
    // String resumo() {
    // /*
    // * EQUIVALENTE EM IF/ELSE:
    // *
    // * double sinal;
    // * if (tipo.equals("DEPOSITO")) {
    // * sinal = valor;
    // * } else {
    // * sinal = -valor;
    // * }
    // */
    // -----------------------------------------------------------------------
    public String resumo() {
        double sinal = tipo == Tipo.DEPOSITO ? valor : -valor;
        return String.format("%-8s  %+9.2f   saldo apos: R$ %9.2f", tipo.name(), sinal, saldoApos);
    }
}
