package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import java.sql.Date;

public class DespesasFixas extends OperacaoConta {

    private String nomeDespesa;
    private float valorDespesa;
    private String email;
    private LocalDate data;

    Scanner entrada = new Scanner(System.in);
    DespesasFixasDao dao = new DespesasFixasDao();

    public DespesasFixas(String email) {
        this.email = email;
    }

    public void setNomeDespesa(String nomeDespesa) {
        this.nomeDespesa = nomeDespesa;
    }

    public void setValorDespesa(float valorDespesa) {
        this.valorDespesa = valorDespesa;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getNomeDespesa() {
        return nomeDespesa;
    }

    public float getValorDespesa() {
        return valorDespesa;
    }

    @Override
    public void exibirInformacoes() {
        ArrayList<DespesasFixas> despesasFixas = dao.selectDespesasFixas(email, LocalDate.now());
        if (despesasFixas.size() == 0) {
            System.out.println("Não há nenhuma despesa fixa cadastrada.");
        } else {
            System.out.println("Lista de Despesas Fixas:");
            for (DespesasFixas despesa : despesasFixas) {
                System.out.println("Nome: " + despesa.getNomeDespesa());
                System.out.println("Valor: R$ " + despesa.getValorDespesa());
                System.out.println("Data de pagamento: " + despesa.getData());
                System.out.println("-------------------------------");
            }
        }
    }

    public void menu() {
        boolean continuar = true;

        while (continuar) {
            System.out.println("Menu de despesa fixa:");
            System.out.println("1- Registre uma despesa;");
            System.out.println("2- Exibir despesas;");
            System.out.println("3- Modificar valor da despesa;");
            System.out.println("4- Deletar despesa;");
            System.out.println("5- Sair;");

            int resposta = entrada.nextInt();
            entrada.nextLine();

            switch (resposta) {
                case 1:
                    registrarDesepesaFixa();
                    break;
                case 2:
                    exibirInformacoes();
                    break;
                case 3:
                    modificarValor();
                    break;
                case 4:
                    deletarDespesa();
                    break;
                case 5:
                    continuar = false;
                    break;

                default:
                    System.out.println("Resposta inválida. Tente novamente.");
                    break;
            }
        }
    }

    public void registrarDesepesaFixa() {

        Scanner entrada = new Scanner(System.in);
        boolean respDespesas = true;

        System.out.println("Informe sua despesa fixa: ");
        while (respDespesas) {
            System.out.print("Digite o nome da despesa: ");
            nomeDespesa = entrada.nextLine();

            System.out.print("Digite o valor da despesa: ");
            valorDespesa = entrada.nextFloat();
            entrada.nextLine();

            System.out.println("Informe a data de pagamento dessa despesa: (yyyy/MM/dd)");
            String inputData = entrada.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDate data = LocalDate.parse(inputData, formatter);
            dao.insertDespesaFixa(email, nomeDespesa, valorDespesa, data);

            System.out.print("Deseja adicionar outra despesa? (s/n): ");
            String resposta = entrada.nextLine();
            respDespesas = resposta.equalsIgnoreCase("s");
        }

    }

    public boolean validaDespesa(String nomeDespesa) {
        DespesasFixas despesa = dao.selectDespesaFixa(email, nomeDespesa);
        if (despesa == null) {
            return false;
        } else {
            return true;
        }
    }

    public void deletarDespesa() {
        System.out.println("Informe o nome da despesa que você deseja deletar: ");
        String nomeDespesa = entrada.nextLine();
        boolean validaDespesa = validaDespesa(nomeDespesa);
        if (validaDespesa == false) {
            System.out.println("A despesa informada não existe.");
        } else {
            dao.deleteDespesaFixa(email, nomeDespesa);
        }
    }

    public void modificarValor() {
        System.out.println("Informe o nome da despesa que você deseja modificar: ");
        String nomeDespesa = entrada.nextLine();
        boolean validaDespesa = validaDespesa(nomeDespesa);
        if (validaDespesa == false) {
            System.out.println("A despesa informada não existe.");
        } else {
            System.out.println("Informe o novo valor dessa despesa: ");
            float novoValor = entrada.nextFloat();
            entrada.nextLine();
            dao.updateValorDespesa(email, nomeDespesa, novoValor);
        }
    }

}
