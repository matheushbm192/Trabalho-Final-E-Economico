package org.example;

import java.util.Scanner;

public class ReservaEmergencia extends OperacaoConta implements OperacaoFinanceira {
    private float valor;
    private float montante;
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

    public float getMontante() {
        return montante;
    }

    public void setMontante(float montante) {
        this.montante = montante;
    }

    @Override
    public void debitar() {
    }

    @Override
    public void debositar() {
    }

    @Override
    public void exibirInformacoes() {
    }

    @Override
    public void modificarInformacoes() {
    }

    ReservaEmergenciaDao dao = new ReservaEmergenciaDao();

    public void menu() {

        System.out.println("Menu de reserva de emergência:");
        System.out.println("1- Crie sua primeira reserva de emergência");
        System.out.println("2- Exibir;");
        System.out.println("3- Depositar;");
        System.out.println("4- Debitar;");
        System.out.println("5- Deletar;");
        System.out.println("6- Sair");

        Scanner entrada = new Scanner(System.in);
        int resposta = entrada.nextInt();

        switch (resposta) {
            case 1:
                registrarReservaEmergencia();
                break;
            case 2:

                break;
            case 3:
                // chamar função dao
                break;
            case 4:
                // chamar função dao
                break;
            case 4:
                // chamar função dao
                break;
            case 4:
                // chamar função dao
                break;

            default:
                System.out.println("Resposta inválida. Tente novamente.");
                menu();
                break;
        }

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
        if(validacao == true){
            System.out.println("Você já possui reserva de emergência cadastrada. ");
        }else{
        
        Scanner entrada = new Scanner(System.in);

        System.out.print("Digite o valor da reserva: ");
        valor = entrada.nextFloat();
        dao.insertReservaEmergencia();
        //retirar o valor do saldo atual 

        }
        
    }

    public void exibeReserva() {
        ReservaEmergencia reserva = dao.selectReservaEmergencia(email);
        System.out.println("O valor da reserva de emergência é: R$" + reserva);
    }

}
