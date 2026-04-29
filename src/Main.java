/**
 * Semana da Aula: Semana 04 - Estruturas de Repeticao e Depuracao
 * Professor.Mse: Anderson Henrique Rodrigues Ferreira
 * Disciplina: Programacao Orientada a Objetos em Java
 *
 * Atividade Integradora — Gabarito
 * Sistema de Conta Bancaria com Historico de Transacoes
 *
 * Arquivo: Main.java
 * Conceitos cobertos:
 * Semana 01: new, referencias, sobrecarga de construtores, duas refs para o
 * mesmo objeto
 * Semana 02: String Pool, == vs .equals(), StringBuilder, String.format()
 * Semana 03: operadores compostos, &&, switch, if-else, operador ternario
 * Semana 04: for indexado (array), do-while, for-each + break, for-each +
 * continue (em gerarHistorico)
 */
public class Main {

    public static void main(String[] args) {

        // -------------------------------------------------------------------
        // 1. Criar clientes e contas
        // Semana 01: new cria o objeto no heap; a variavel guarda a referencia
        // -------------------------------------------------------------------
        Cliente maria = new Cliente("Maria Silva", 34);
        Cliente joao = new Cliente("Joao Costa", 28);
        Cliente ana = new Cliente("Ana Pereira", 22);

        // Sobrecarga 2: com saldo inicial
        ContaBancaria contaCorrente = new ContaBancaria(1001, maria, ContaBancaria.Tipo.CORRENTE, 500.0);
        // Sobrecarga 1: sem saldo inicial — saldo comeca em 0.0
        ContaBancaria contaPoupanca = new ContaBancaria(2002, joao, ContaBancaria.Tipo.POUPANCA);
        ContaBancaria contaInvestimento = new ContaBancaria(3003, ana, ContaBancaria.Tipo.INVESTIMENTO, 1000.0);

        // Semana 02: toUpperCase() retorna nova String — nome original nao muda
        System.out.println(maria.saudacao());
        System.out.println(joao.saudacao());
        System.out.println(ana.saudacao());

        // -------------------------------------------------------------------
        // 2. FOR indexado: depositos em lote via array (Semana 04)
        //
        // O for e ideal quando o numero de iteracoes e conhecido antes de comecar.
        // Aqui: percorremos um array fixo de aportes e depositamos cada valor.
        //
        // Anatomia do for:
        // inicializacao : int i = 0 → executada uma unica vez
        // condicao : i < aportes.length → testada antes de cada iteracao
        // atualizacao : i++ → executada apos cada iteracao
        // -------------------------------------------------------------------
        System.out.println("\n--- Depositos em Lote na Conta Poupanca (Joao) ---");
        double[] aportes = { 500.0, 750.0, 1000.0, 250.0 };
        for (int i = 0; i < aportes.length; i++) {
            contaPoupanca.depositar(aportes[i]);
        }

        // -------------------------------------------------------------------
        // 3. Operacoes avulsas na conta corrente (Maria)
        // Exercita: depositar, sacar (valido e negado), deposito invalido
        // Semana 03: && e if-else dentro dos metodos chamados
        // -------------------------------------------------------------------
        System.out.println("\n--- Operacoes na Conta Corrente (Maria) ---");
        contaCorrente.depositar(400.0); // saldo: 500 + 400 = 900
        contaCorrente.sacar(200.0); // saldo: 900 - 200 = 700 [APROVADO]
        contaCorrente.sacar(900.0); // 700 >= 900 = false [NEGADO: saldo insuficiente]
        contaCorrente.depositar(-50.0); // -50 > 0 = false [NEGADO: valor invalido]

        // -------------------------------------------------------------------
        // 4. DO-WHILE: aplicar taxa em todas as contas (Semana 04)
        //
        // O do-while garante que o bloco execute AO MENOS UMA VEZ,
        // porque a condicao e avaliada APOS o corpo — nao antes.
        //
        // Aqui: queremos que cada conta receba a taxa antes de verificar
        // se ainda ha contas a processar.
        //
        // Semana 03: OPERADOR TERNARIO para compor a mensagem de saida
        // condicao ? valor_se_true : valor_se_false
        // Leitura: "se taxa > 0, mensagem de cobranca; senao, mensagem de isencao"
        //
        // Semana 02: String.format() para duas casas decimais
        // -------------------------------------------------------------------
        System.out.println("\n--- Aplicacao de Taxas (do-while) ---");
        ContaBancaria[] contas = { contaCorrente, contaPoupanca, contaInvestimento };
        int idx = 0;
        do {
            ContaBancaria c = contas[idx];
            double taxa = c.aplicarTaxa();

            String msg = taxa > 0
                    ? String.format("Conta %d (%s): taxa de R$ %.2f cobrada.", c.numero(), c.resolverTipo(), taxa)
                    : String.format("Conta %d (%s): isenta de taxa.", c.numero(), c.resolverTipo());
            System.out.println(msg);

            idx++;
        } while (idx < contas.length);

        // -------------------------------------------------------------------
        // 5. FOR-EACH + BREAK: auditoria de contas (Semana 04)
        //
        // O for-each itera sobre todos os elementos sem expor o indice.
        // O break encerra o laco imediatamente ao encontrar conta inativa,
        // pois nao faz sentido continuar a auditoria apos detectar irregularidade.
        //
        // Semana 02: String.format() para alinhar colunas do relatorio
        // -------------------------------------------------------------------
        System.out.println("\n--- Auditoria de Contas (for-each + break) ---");
        contaInvestimento.ativa(false); // simula conta desativada para demonstrar o break

        for (ContaBancaria c : contas) {
            String linha = String.format("Conta %d | %-12s | %-7s | R$ %9.2f",
                    c.numero(), c.resolverTipo(), c.status(), c.saldo());
            System.out.println(linha);
            if (!c.ativa()) {
                System.out.println("  [AUDITORIA] Conta inativa detectada — varredura interrompida.");
                break; // Semana 04: sai do for-each ao encontrar conta inativa
            }
        }

        // -------------------------------------------------------------------
        // 6. Estatisticas via for-each em ContaBancaria (Semana 04)
        //
        // contarOperacoes() e maiorDeposito() usam for-each internamente
        // sobre o ArrayList<Transacao> — consulte ContaBancaria.java
        // -------------------------------------------------------------------
        System.out.println("\n--- Estatisticas da Conta Corrente (Maria) ---");
        int qtdDepositos = contaCorrente.contarOperacoes(Transacao.Tipo.DEPOSITO);
        int qtdSaques = contaCorrente.contarOperacoes(Transacao.Tipo.SAQUE);
        System.out.println("Depositos realizados : " + qtdDepositos);
        System.out.println("Saques realizados    : " + qtdSaques);
        System.out.printf("Maior deposito       : R$ %.2f%n", contaCorrente.maiorDeposito());

        System.out.println("\n--- Estatisticas da Conta Poupanca (Joao) ---");
        System.out.println("Depositos realizados : " + contaPoupanca.contarOperacoes(Transacao.Tipo.DEPOSITO));
        System.out.printf("Maior deposito       : R$ %.2f%n", contaPoupanca.maiorDeposito());

        // -------------------------------------------------------------------
        // 7. == vs .equals() com Strings (Semana 02)
        // String Pool: literais identicos compartilham referencia no pool
        // new String(...) cria um objeto FORA do pool, em outra area do heap
        // -------------------------------------------------------------------
        System.out.println("\n--- Comparacao de Strings ---");
        String nomeA = "Maria Silva"; // armazenado no String Pool
        String nomeB = new String("Maria Silva"); // objeto novo, FORA do pool
        String nomeC = "Maria Silva"; // reutiliza a referencia do pool

        System.out.println("nomeA == nomeB : " + (nomeA == nomeB)); // false
        System.out.println("nomeA == nomeC : " + (nomeA == nomeC)); // true
        System.out.println("nomeA.equals(nomeB): " + nomeA.equals(nomeB)); // true

        // CONCLUSAO: para comparar CONTEUDO de Strings, SEMPRE use .equals()

        // -------------------------------------------------------------------
        // 8. Duas referencias para o mesmo objeto (Semana 01)
        // ref2 nao e uma copia — aponta para o mesmo objeto que contaCorrente
        // Modificar via ref2 afeta contaCorrente (e vice-versa)
        // -------------------------------------------------------------------
        System.out.println("\n--- Duas Referencias, Mesmo Objeto ---");
        ContaBancaria ref2 = contaCorrente;

        System.out.printf("Saldo antes (via contaCorrente): R$ %.2f%n", contaCorrente.saldo());
        ref2.depositar(1000.0);
        System.out.printf("Saldo depois (via contaCorrente): R$ %.2f%n", contaCorrente.saldo());

        // -------------------------------------------------------------------
        // 9. Historico completo — gerarHistorico() usa FOR-EACH + CONTINUE (Semana 04)
        // O continue em gerarHistorico() pula transacoes com valor zero
        // Consulte o metodo gerarHistorico() em ContaBancaria.java
        // -------------------------------------------------------------------
        System.out.println("\n" + contaCorrente.gerarHistorico());
        System.out.println("\n" + contaPoupanca.gerarHistorico());

        // -------------------------------------------------------------------
        // 10. Extratos finais — StringBuilder + String.format() (Semana 02)
        // -------------------------------------------------------------------
        System.out.println("\n" + contaCorrente.gerarExtrato());
        System.out.println("\n" + contaPoupanca.gerarExtrato());
        System.out.println("\n" + contaInvestimento.gerarExtrato());
    }
}
