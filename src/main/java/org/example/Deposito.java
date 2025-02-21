package org.example;

import java.time.LocalDate;
import java.util.Scanner;

public class Deposito {
    private String email;
    private float valor;
    private LocalDate data;

    public Deposito(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public void depositaSaldo(){

        Scanner entrada = new Scanner(System.in);
        System.out.println("Informe o valor que deseja depositar no saldo: ");
        float valor = entrada.nextFloat();
        entrada.nextLine();
        SaldoAtualDao saldoDao = new SaldoAtualDao();
        saldoDao.updateDepositoSaldo(email, valor);
       DepositoDao depositoDao = new DepositoDao();
       depositoDao.insertDeposito(email,valor,LocalDate.now());
    }
}
