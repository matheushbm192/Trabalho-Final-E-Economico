package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class ComplementoDados {
    private String email;
    private ReservaEmergencia reservaEmergencia;
    private ArrayList<Meta> arrMetas;
    private ArrayList<DespesasFixas> arrDespesasFixas;

    // eu preciso de um construtor? simmmmm ;)
    public ComplementoDados(String email) {
        this.email = email;
        reservaEmergencia = getReservaEmergencia(email);
        arrMetas = getMetas(email);
        arrDespesasFixas = getDespesasFixas(email);
    }

    private ArrayList<DespesasFixas> getDespesasFixas(String email) {
        ComplementoDao dao = new ComplementoDao();
        return dao.selectDespesasFixas();
    }

    private ArrayList<Meta> getMetas(String email) {
        ComplementoDao dao = new ComplementoDao();
        return dao.selectMetas();
    }




    public void registrarMeta(){
        ComplementoDao dao = new ComplementoDao();
        Scanner entrada = new Scanner(System.in);
        boolean respMetas = true;

        System.out.println("Informe suas metas financeiras: ");
        while (respMetas) {
            System.out.print("Digite o nome da meta: ");
            String nomeMeta = entrada.nextLine();

            System.out.print("Digite o valor da meta: ");
            float valorMeta = entrada.nextFloat();
            entrada.nextLine();

            dao.insertMeta(nomeMeta , valorMeta);


            System.out.print("Deseja adicionar outra meta? (s/n): ");
            String resposta = entrada.nextLine();
            respMetas = resposta.equalsIgnoreCase("s");
        }
    }
    public void registrarReservaEmergencia(){
        ComplementoDao dao = new ComplementoDao();
        Scanner entrada = new Scanner(System.in);
        boolean respMetas = true;

        System.out.println("Informe suas metas financeiras: ");
        while (respMetas) {
            System.out.print("Digite o nome da meta: ");
            String nomeMeta = entrada.nextLine();

            System.out.print("Digite o valor da meta: ");
            float valorMeta = entrada.nextFloat();
            entrada.nextLine();

            dao.insertReservaEmergencia();


            System.out.print("Deseja adicionar outra meta? (s/n): ");
            String resposta = entrada.nextLine();
            respMetas = resposta.equalsIgnoreCase("s");
        }

    }
    public void registrarDesepesaFixa(){
        ComplementoDao dao = new ComplementoDao();
        Scanner entrada = new Scanner(System.in);
        boolean respMetas = true;

        System.out.println("Informe suas metas financeiras: ");
        while (respMetas) {
            System.out.print("Digite o nome da meta: ");
            String nomeMeta = entrada.nextLine();

            System.out.print("Digite o valor da meta: ");
            float valorMeta = entrada.nextFloat();
            entrada.nextLine();

            dao.insertDespesaFixa();


            System.out.print("Deseja adicionar outra meta? (s/n): ");
            String resposta = entrada.nextLine();
            respMetas = resposta.equalsIgnoreCase("s");
        }

    }
    public void registraDados(Usuario usuarioVez) {
        ComplementoDao dao = new ComplementoDao();
        Scanner entrada = new Scanner(System.in);

        System.out.println("Cadastro inicial de complemento financeiro: ");



        boolean respDespesas = true;
        System.out.println("Agora informe suas despesas fixas: ");
        while (respDespesas) {
            System.out.print("Digite o nome da despesa: ");
            String nomeDespesa = entrada.nextLine();

            System.out.print("Digite o valor da despesa: ");
            float valorDespesa = entrada.nextFloat();
            entrada.nextLine(); 

            arrDespesasFixas.add(new DespesasFixas(nomeDespesa, valorDespesa));

            System.out.print("Deseja adicionar outra despesa? (s/n): ");
            String resposta = entrada.nextLine();
            respDespesas = resposta.equalsIgnoreCase("s");
        }
        System.out.print("Informe o valor da sua reserva de emergência: ");
        float valorReserva = entrada.nextFloat();


        dao.insertComplementos(usuarioVez.getEmail(), arrMetas, arrDespesasFixas, reservaEmergencia);
    }
}
