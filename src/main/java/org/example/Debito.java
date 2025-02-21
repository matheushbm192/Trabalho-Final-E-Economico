package org.example;

import java.time.LocalDate;
import java.util.Scanner;

public class Debito {
    private String email;
    private float valor;
    private LocalDate data;

    public Debito(String email) {
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

   ;

    public void debitaSaldo() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Informe o valor que deseja debitar do saldo: ");
        float valor = entrada.nextFloat();
        entrada.nextLine();
        boolean validaSaldo = validaSaldo(valor);
        if (validaSaldo == false) {
            System.out.println("Você não possui saldo sufuciente para completar esse deposito");
        } else {
            SaldoAtualDao saldoDao = new SaldoAtualDao();
            saldoDao.updateDebitoSaldo(email, valor);
            DebitoDao debitoDao = new DebitoDao();
            debitoDao.insertDebito(email,valor,LocalDate.now());
        }
    }

    public boolean validaSaldo(float deposito) {
        SaldoAtualDao saldoDao = new SaldoAtualDao();
        float saldo = saldoDao.selectSaldo(email);
        if (deposito > saldo) {
            return false;
        } else {
            return true;
        }
        
    }
}
