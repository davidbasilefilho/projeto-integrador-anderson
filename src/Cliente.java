/**
 * Semana da Aula: Semana 04 - Estruturas de Repeticao e Depuracao
 * Professor.Mse: Anderson Henrique Rodrigues Ferreira
 * Disciplina: Programacao Orientada a Objetos em Java
 *
 * Atividade Integradora — Gabarito
 * Sistema de Conta Bancaria com Historico de Transacoes
 *
 * Arquivo: Cliente.java
 * Conceitos: Semana 01 (classe, objeto, construtor) + Semana 02 (String,
 * toUpperCase)
 *
 * Nota: esta classe nao muda em relacao a Semana 03.
 * O foco da expansao esta em ContaBancaria (ArrayList, loops) e na nova
 * Transacao.
 */
public class Cliente {

    // Semana 01: campos de instancia — existem no objeto, no heap
    private String nome;
    private int idade;

    // -----------------------------------------------------------------------
    // Semana 01: CONSTRUTOR
    //
    // Um construtor e executado automaticamente quando escrevemos `new
    // Cliente(...)`.
    // Ele tem o mesmo nome da classe e nao tem tipo de retorno.
    // Seu papel: inicializar os campos do objeto antes de qualquer uso.
    //
    // Cliente c = new Cliente("Ana", 20);
    // ^^^^^^^^^^^^^^^^^^^
    // chama este construtor; os campos recebem os valores passados
    //
    // `this.nome` refere-se ao CAMPO do objeto.
    // `nome` (sem this) refere-se ao PARAMETRO local do construtor.
    // -----------------------------------------------------------------------
    public Cliente(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    // Semana 02: String e imutavel — toUpperCase() retorna uma NOVA String
    // A String armazenada em `nome` nao e alterada
    public String saudacao() {
        return "Ola, " + nome.toUpperCase() + "!";
    }

    public String nome() {
        return nome;
    }

    public String nome(String nome) {
        return this.nome = nome;
    }

    public int idade() {
        return idade;
    }

    public int idade(int idade) {
        return this.idade = idade;
    }
}
