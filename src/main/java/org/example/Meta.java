package org.example;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.SwingUtilities;

import org.jfree.data.category.DefaultCategoryDataset;

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

    public float getMontante() {
        return montante;
    }

    MetaDao dao = new MetaDao();
    SaldoAtualDao saldoDao = new SaldoAtualDao();

    @Override
    public void exibirInformacoes() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        ArrayList<Meta> metas = dao.selectMetas(email);
        
        if (metas != null) {
            for (Meta meta : metas) {
                dataset.addValue(meta.getValorMeta(), "Valor da Meta", meta.getNomeMeta());
                dataset.addValue(meta.getMontante(), "Montante Depositado", meta.getNomeMeta());
                System.out.println(meta.getNomeMeta());
            }
        }
    
        new BarChart("Progresso das Metas", "Metas", "Valores", dataset);
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
            entrada.nextLine();
            boolean validaValor = validaMontante(valorDebito, nome);
            if (validaValor == false) {
                System.out.println("Não há saldo suficiente na meta para esta retirada.");
            } else {
                dao.UpdateDebitarMontanteMeta(email, nome, valorDebito);
                saldoDao.updateDepositoSaldo(nome, valorDebito);
                // todo: acrescentar ao fluxo de caixa 
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
            float valor = entrada.nextFloat();
            entrada.nextLine();
            boolean validaSaldo = validaSaldo(valor);
            if (validaSaldo == false) {
                System.out.println("Você não possui saldo sufuciente para completar esse deposito");
            } else {
                dao.UpdateDepositarValorMeta(email, nome, valor);
                saldoDao.updateDebitoSaldo(email, valor);
            }
            // adicionar ao fluxo de caixa
        }
    }




    public void menu() {
        boolean continuar = true;

        while (continuar) {
            System.out.println("Menu metas:");
            System.out.println("1- Registre uma meta;");
            System.out.println("2- Exibir metas graficamente;");
            System.out.println("3- Depositar em uma meta;");
            System.out.println("4- Debitar em uma meta;");
            System.out.println("5- Deletar meta;");
            System.out.println("6- Modificar nome da meta ");
            System.out.println("7- Modificar valor da meta");
            System.out.println("8- Sair;");
    
            int resposta = entrada.nextInt();
            entrada.nextLine(); 
    
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
                    continuar = false; 
                    break;
                default:
                    System.out.println("Resposta inválida. Tente novamente.");
                    break;
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
            entrada.nextLine();
            dao.UpdateValorMeta(email, nome, valorDesejado);
        }
    }

}
