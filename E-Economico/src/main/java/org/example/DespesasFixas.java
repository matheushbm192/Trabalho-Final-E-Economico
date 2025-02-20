package org.example;

import java.time.LocalDate;
import java.util.Scanner;

import java.sql.Date;

public class DespesasFixas extends OperacaoConta{

    private String nomeDespesa;
    private float valorDespesa;
    private String email;
    private LocalDate data;

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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'exibirInformacoes'");
    }

    public void registrarDesepesaFixa(){
        ComplementoDao dao = new ComplementoDao();
        Scanner entrada = new Scanner(System.in);
        boolean respDespesas = true;

        System.out.println("Informe sua despesa fixa: ");
        while (respDespesas) {
            System.out.print("Digite o nome da despesa: ");
            nomeDespesa = entrada.nextLine();

            System.out.print("Digite o valor da despesa: ");
            valorDespesa = entrada.nextFloat();
            entrada.nextLine();

            dao.insertDespesaFixa();


            System.out.print("Deseja adicionar outra despesa? (s/n): ");
            String resposta = entrada.nextLine();
            respDespesas = resposta.equalsIgnoreCase("s");
        }

    }

}
