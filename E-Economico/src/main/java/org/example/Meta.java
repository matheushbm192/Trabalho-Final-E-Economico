package org.example;

import java.util.Scanner;

public class Meta extends OperacaoConta implements OperacaoFinanceira {
    private String email;
    private String nomeMeta;
    private float valorMeta;
    private float montante;

    public Meta(String email) {
        this.email = email;
    }

    public void setNomeMeta(String nomeMeta) {
        this.nomeMeta = nomeMeta;
    }

    public void setValorMeta(Float valorMeta) {
        this.valorMeta = valorMeta;
    }

    public void setMontante(float montante) {
        this.montante = montante;
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

    public void menu() {

        System.out.println("Menu metas:");
        System.out.println("1- Crie sua primeira meta;");
        System.out.println("2- Exibir metas graficamente;");
        System.out.println("3- Depositar em uma meta;");
        System.out.println("4- Debitar em uma meta;");
        System.out.println("5- Deletar meta;");
        System.out.println("6- Modificar nome da meta ");
        System.out.println("7- Modificar valor da meta");
        System.out.println("8- Sair;");

        Scanner entrada = new Scanner(System.in);
        int resposta = entrada.nextInt();

        switch (resposta) {
            case 1:
                registrarMeta();
                break;
            case 2:
                exibirInformacoes();
                break;
            case 3:
                debositar();
                break;
            case 4:
                debitar();
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                //sair();
                break;

            default:
                System.out.println("Resposta inválida. Tente novamente.");
                menu();
                break;
        }

    }

    public void registrarMeta() {
        MetaDao dao = new MetaDao();
        Scanner entrada = new Scanner(System.in);
        boolean respMetas = true;

        System.out.println("Informe suas metas financeiras: ");
        while (respMetas) {
            System.out.print("Digite o nome da meta: ");
            nomeMeta = entrada.nextLine();

            System.out.print("Digite o valor da meta: ");
            valorMeta = entrada.nextFloat();
            entrada.nextLine();

            dao.insertMeta(email, nomeMeta, valorMeta);

            System.out.print("Deseja adicionar outra meta? (s/n): ");
            String resposta = entrada.nextLine();
            respMetas = resposta.equalsIgnoreCase("s");
        }
    }

}
