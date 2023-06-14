/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
   package Trabalho_Paulinho;
   import javax.swing.JOptionPane;
/**
 *
 * @author diogo massayuki Kumizaki, Leonardo Duarte
 */
public class SistemaContaBancaria {
    
 



    private static final int LIMITE_CONTAS = 100; 

    private ContasBancaria[] contas; 
    private int numContas; 

    public SistemaContaBancaria() {
        contas = new ContasBancaria[LIMITE_CONTAS];
        numContas = 0;
    }

    public void cadastrarConta(int numeroConta, String nomeTitular, double saldoInicial) {
        if (numContas >= LIMITE_CONTAS) {
            JOptionPane.showMessageDialog(null, "Número máximo de contas cadastradas atingido.");
            return;
        }

        ContasBancaria novaConta = new ContasBancaria(numeroConta, nomeTitular, saldoInicial);
        contas[numContas] = novaConta;
        numContas++;

        ordenarContasPorNumeroConta();
        JOptionPane.showMessageDialog(null, "Conta cadastrada com sucesso.");
    }

    public ContasBancaria pesquisarContaPorNumero(int numeroConta) {
        int inicio = 0;
        int fim = numContas - 1;

        while (inicio <= fim) {
            int meio = (inicio + fim) / 2;
            ContasBancaria contaMeio = contas[meio];

            if (contaMeio.getNumeroConta() == numeroConta) {
                return contaMeio;
            } else if (contaMeio.getNumeroConta() < numeroConta) {
                inicio = meio + 1;
            } else {
                fim = meio - 1;
            }
        }

        return null; 
    }

    public void realizarSaque(int numeroConta, double valorSaque) {
        ContasBancaria conta = pesquisarContaPorNumero(numeroConta);

        if (conta != null) {
            if (valorSaque <= conta.getSaldo()) {
                conta.realizarSaque(valorSaque);
                JOptionPane.showMessageDialog(null, "Saque de R$" + valorSaque + " realizado com sucesso na conta " + numeroConta + ".");
            } else {
                JOptionPane.showMessageDialog(null, "Saldo insuficiente para realizar o saque.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Conta não encontrada.");
        }
    }

    public void exibirContas() {
        StringBuilder mensagem = new StringBuilder("---- Contas Bancárias ----\n");

        for (int i = 0; i < numContas; i++) {
            mensagem.append("Conta ").append(i + 1).append("\n");
            mensagem.append(contas[i].toString()).append("\n");
            mensagem.append("-------------------------\n");
        }

        JOptionPane.showMessageDialog(null, mensagem.toString());
    }

    public void ordenarContasPorNumeroConta() {
        for (int i = 0; i < numContas - 1; i++) {
            for (int j = 0; j < numContas - i - 1; j++) {
                if (contas[j].getNumeroConta() > contas[j + 1].getNumeroConta()) {
                    ContasBancaria temp = contas[j];
                    contas[j] = contas[j + 1];
                    contas[j + 1] = temp;
                }
            }
        }
    }

    public double calcularSaldoTotal() {
        return calcularSaldoTotalRecursivo(0);
    }

    private double calcularSaldoTotalRecursivo(int index) {
        if (index >= numContas) {
            return 0.0;
        }

        ContasBancaria conta = contas[index];
        return conta.getSaldo() + calcularSaldoTotalRecursivo(index + 1);
    }

    public boolean verificarSaldoNegativo() {
        return verificarSaldoNegativoRecursivo(0);
    }

    private boolean verificarSaldoNegativoRecursivo(int index) {
        if (index >= numContas) {
            return false;
        }

        ContasBancaria conta = contas[index];
        if (conta.getSaldo() < 0) {
            return true;
        }

        return verificarSaldoNegativoRecursivo(index + 1);
    }

    public static void main(String[] args) {
        SistemaContaBancaria sistema = new SistemaContaBancaria();

        int opcao = 0;

        while (opcao != 7) {
            String menu = "---- Menu Interativo ----\n"
                    + "1. Cadastrar nova conta\n"
                    + "2. Exibir contas cadastradas\n"
                    + "3. Pesquisar conta por número\n"
                    + "4. Realizar saque\n"
                    + "5. Calcular saldo total\n"
                    + "6. Verificar saldo negativo\n"
                    + "7. Sair\n"
                    + "Escolha uma opção:";
            opcao = Integer.parseInt(JOptionPane.showInputDialog(menu));

            switch (opcao) {
                case 1:
                    int numeroConta = Integer.parseInt(JOptionPane.showInputDialog("Digite o número da conta:"));
                    String nomeTitular = JOptionPane.showInputDialog("Digite o nome do titular:");
                    double saldoInicial = Double.parseDouble(JOptionPane.showInputDialog("Digite o saldo inicial:"));

                    sistema.cadastrarConta(numeroConta, nomeTitular, saldoInicial);
                    break;
                case 2:
                    sistema.exibirContas();
                    break;
                case 3:
                    int numeroPesquisa = Integer.parseInt(JOptionPane.showInputDialog("Digite o número da conta:"));

                    ContasBancaria contaNumero = sistema.pesquisarContaPorNumero(numeroPesquisa);
                    if (contaNumero != null) {
                        JOptionPane.showMessageDialog(null, "Conta encontrada:\n" + contaNumero.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "Conta não encontrada.");
                    }
                    break;
                case 4:
                    int numeroSaque = Integer.parseInt(JOptionPane.showInputDialog("Digite o número da conta:"));
                    double valorSaque = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor do saque:"));

                    sistema.realizarSaque(numeroSaque, valorSaque);
                    break;
                case 5:
                    double saldoTotal = sistema.calcularSaldoTotal();
                    JOptionPane.showMessageDialog(null, "Saldo Total: R$" + saldoTotal);
                    break;
                case 6:
                    boolean saldoNegativo = sistema.verificarSaldoNegativo();
                    if (saldoNegativo) {
                        JOptionPane.showMessageDialog(null, "Existem contas com saldo negativo.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Todas as contas possuem saldo positivo.");
                    }
                    break;
                case 7:
                    JOptionPane.showMessageDialog(null, "Saindo...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida.");
            }
        }
    }
}


