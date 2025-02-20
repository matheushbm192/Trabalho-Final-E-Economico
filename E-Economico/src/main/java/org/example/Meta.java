package org.example;

import java.util.Scanner;

public class Meta extends OperacaoConta implements OperacaoFinanceira{
    private String nomeMeta;
    private Float valorMeta;
    private double capitalAcumulado;
    private String email; 
    
    public Meta(String email) {
        this.email = email; 
    }

    public double getCapitalAcumulado() {
        return capitalAcumulado;
    }
     
    public String getNomeMeta() {
        return nomeMeta;
    }

    public float getValorMeta() {
        return valorMeta;
    }

    @Override
    public void exibirInformacoes() {
    }

    @Override
    public void modificarInformacoes() {
    }

    @Override
    public void debitar() {
    }

    @Override
    public void debositar() {
    }
    /*1- exibir metas 
     *2-
     */

    public void registrarMeta(){
        ComplementoDao dao = new ComplementoDao();
        Scanner entrada = new Scanner(System.in);
        boolean respMetas = true;

        System.out.println("Informe suas metas financeiras: ");
        while (respMetas) {
            System.out.print("Digite o nome da meta: ");
            nomeMeta = entrada.nextLine();

            System.out.print("Digite o valor da meta: ");
            valorMeta = entrada.nextFloat();
            entrada.nextLine();

            dao.insertMeta(nomeMeta , valorMeta);


            System.out.print("Deseja adicionar outra meta? (s/n): ");
            String resposta = entrada.nextLine();
            respMetas = resposta.equalsIgnoreCase("s");
        }
    }




}
