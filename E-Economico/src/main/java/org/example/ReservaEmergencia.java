package org.example;

import java.util.Scanner;

public class ReservaEmergencia extends OperacaoConta implements OperacaoFinanceira {
    private float valor;
    private String email;

    public ReservaEmergencia(String email) {
        this.email = email;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    ReservaEmergenciaDao dao = new ReservaEmergenciaDao();
    SaldoAtualDao saldoDao = new SaldoAtualDao();

    @Override
    public void debitar() {
        Scanner entrada = new Scanner(System.in);
        boolean validacao = verificaReserva();
        if (validacao == false) {
            System.out.println("Você não possui reserva cadastrada para debitar.");
        } else {
            System.out.println("Informe o valor que deseja debitar: ");
            float valor = entrada.nextFloat();
            boolean validaValor = validaMontante(valor);
            if (validaValor == false) {
                System.out.println("Não há saldo suficiente na reserva para esta retirada.");
            } else {
                dao.updateDebitarReservaEmergencia(email, valor);
                saldoDao.updateDepositoSaldo(email, valor);
                // adicionar ao fluxo de caixa
            }

        }
    }

    @Override
    public void depositar() {

        Scanner entrada = new Scanner(System.in);
        boolean validaReserva = verificaReserva();
        if (validaReserva == false) {
            System.out.println("Você não possui reserva cadastrada para depositar.");
        } else {
            System.out.println("Informe o valor que deseja depositar: ");
            float valor = entrada.nextFloat();
            boolean validaSaldo = validaSaldo(valor);
            if (validaSaldo == false) {
                System.out.println("Você não possui saldo sufuciente para completar esse deposito");
            } else {
                dao.updateDepositarReservaEmergencia(email, valor);
                saldoDao.updateDebitoSaldo(email, valor);
            }
            // adicionar ao fluxo de caixa
        }
    }

    @Override

    public void exibirInformacoes() {
        boolean validacao = verificaReserva();
        if (validacao == false) {
            System.out.println("Você não possui reserva");
        } else {
            ReservaEmergencia reserva = dao.selectReservaEmergencia(email);
            System.out.println("O valor da reserva de emergência é: R$" + reserva.getValor());
        }
    }

    public void menu() {

        System.out.println("Menu reserva de emergência:");
        System.out.println("1- Crie sua primeira reserva de emergência;");
        System.out.println("2- Exibir;");
        System.out.println("3- Depositar;");
        System.out.println("4- Debitar;");
        System.out.println("5- Sair;");

        Scanner entrada = new Scanner(System.in);
        int resposta = entrada.nextInt();

        switch (resposta) {
            case 1:
                registrarReservaEmergencia();
                break;
            case 2:
                exibirInformacoes();
                break;
            case 3:
                depositar();
                break;
            case 4:
                debitar();
                break;
            case 5:
                // sair();
                break;

            default:
                System.out.println("Resposta inválida. Tente novamente.");
                break;
        }
        menu();
    }

    public boolean verificaReserva() {
        ReservaEmergencia reserva = dao.selectReservaEmergencia(email);
        if (reserva == null) {
            return false;
        } else {
            return true;
        }
    }

    public void registrarReservaEmergencia() {

        boolean validacao = verificaReserva();
        if (validacao == true) {
            System.out.println("Você já possui reserva de emergência cadastrada. ");
        } else {

            Scanner entrada = new Scanner(System.in);

            System.out.print("Digite o valor da reserva: ");
            valor = entrada.nextFloat();
            boolean validaSaldo = validaSaldo(valor);
            if (validaSaldo == false) {
                System.out.println("Você não possui saldo sufuciente para completar esta reserva");
            } else {
                dao.insertReservaEmergencia(email, valor);
                saldoDao.updateDebitoSaldo(email, valor);
                // acrescentar no fluxo de caixa

            }
        }

    }

    public boolean validaSaldo(float deposito) {
        float saldo = saldoDao.selectSaldo(email);
        if (deposito > saldo) {
            return false;
        } else {
            return true;
        }
    }

    public boolean validaMontante(float valor) {
        ReservaEmergencia reserva = dao.selectReservaEmergencia(email);
        if (valor > reserva.getValor()) {
            return false;
        } else {
            return true;
        }

    }

    public void sair() {
        // voltar para main
    }

}
