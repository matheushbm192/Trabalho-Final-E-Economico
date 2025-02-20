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
        //exibir gráficos 
    }

    Scanner entrada = new Scanner(System.in);

    @Override
    public void debitar() {
        MetaDao dao = new MetaDao();
        System.out.println("Informe o nome da meta que você deseja debitar: ");
        String nome = entrada.nextLine();
        boolean existe = verificaMeta(nome);
        if (existe == false) {
            System.out.println("Essa meta não existe.");
        } else {
            System.out.println("Informe o valor que você deseja debitar: ");
            float valorDebito = entrada.nextFloat();
            boolean validaValor = validaMontante(valorDebito, nome);
            if (validaValor == false) {
                System.out.println("Não há saldo suficiente na meta para esta retirada.");
            } else {
                dao.UpdateDebitarMontanteMeta(email, nome, valorDebito);
                //todo: acrescentar ao saldo atual 
            }
        }
    }

    @Override
    public void depositar() {
        MetaDao dao = new MetaDao();
        System.out.println("Informe o nome da meta que você deseja depositar: ");
        String nome = entrada.nextLine();
        boolean existe = verificaMeta(nome);
        if (existe == false) {
            System.out.println("Essa meta não existe.");
        } else {
            System.out.println("Informe o valor que deseja depositar: ");
            //analisar se ele tem saldo sufuciente para o deposito
            float valorDeposito = entrada.nextFloat();
            dao.UpdateDepositarValorMeta(email, nome, valorDeposito);
            // retirar do saldo atual
        }
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

        
        int resposta = entrada.nextInt();

        switch (resposta) {
            case 1:
                registrarMeta();
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
                deletar();
                break;
            case 6:
                modificarNome();
                break;
            case 7:
                modificarValor();
                break;
            case 8:
                // sair();
                break;

            default:
                System.out.println("Resposta inválida. Tente novamente.");
                menu();
                break;
        }

    }

    public boolean validaMontante(float valor, String nome) {
        MetaDao dao = new MetaDao();
        Meta meta = dao.selectMeta(email, nome);
        if (valor > meta.getValorMeta()) {
            return false;
        } else {
            return true;
        }

    }

    public boolean verificaMeta(String nome) {
        MetaDao dao = new MetaDao();
        Meta meta = dao.selectMeta(email, nome);
        if (meta == null) {
            return false;
        } else {
            return true;
        }
    }

    public void registrarMeta() {
        MetaDao dao = new MetaDao();
        boolean respMetas = true;

        System.out.println("Informe sua meta financeira: ");
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

    public void deletar() {
        MetaDao dao = new MetaDao();
        System.out.println("Informe o nome da meta que você deseja depositar: ");
        String nome = entrada.nextLine();
        boolean existe = verificaMeta(nome);
        if (existe == false) {
            System.out.println("Essa meta não existe. Portanto, não pode ser apagada.");
        } else {
            dao.deleteMeta(email, nome);
        }

    }

    public void modificarNome() {
        MetaDao dao = new MetaDao();
        System.out.println("Informe o nome atual da meta: ");
        String nomeOriginal = entrada.nextLine();
        boolean existe = verificaMeta(nomeOriginal);
        if (existe == false) {
            System.out.println("Essa meta não existe. Portanto, não pode ser modificada.");
        } else {
            System.out.println("Informe o novo nome da meta: ");
            String nomeDesejado = entrada.nextLine();
            dao.UpdateNomeMeta(email, nomeOriginal, nomeDesejado);
        }
    }

    public void modificarValor() {
        MetaDao dao = new MetaDao();
        System.out.println("Informe o nome da meta: ");
        String nome = entrada.nextLine();
        boolean existe = verificaMeta(nome);
        if (existe == false) {
            System.out.println("Essa meta não existe. Portanto, não pode ser modificada.");
        } else {
            System.out.println("Informe o novo valor da meta: ");
            Float valorDesejado = entrada.nextFloat();
            dao.UpdateValorMeta(email, nome, valorDesejado);
        }
    }

}
