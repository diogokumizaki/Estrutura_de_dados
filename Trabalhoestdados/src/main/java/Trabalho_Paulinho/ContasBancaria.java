/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Trabalho_Paulinho;

/**
 *
 * @author diogo massayuki Kumizaki, Leonardo Duarte
 */
public class ContasBancaria {
    
    private int numeracaoConta;
    private String nomeTitular;
    private double saldo;

    public ContasBancaria(int numeroConta, String nomeTitular, double saldoInicial) {
        this.numeracaoConta = numeroConta;
        this.nomeTitular = nomeTitular;
        this.saldo = saldoInicial;
    }

    public int getNumeroConta() {
        return numeracaoConta;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void realizarSaque(double valor) {
        saldo -= valor;
    }

    @Override
    public String toString() {
        return "Número da conta: " + numeracaoConta + "\n"
                + "Nome do titular: " + nomeTitular + "\n"
                + "Saldo: R$" + saldo;
    }
}


