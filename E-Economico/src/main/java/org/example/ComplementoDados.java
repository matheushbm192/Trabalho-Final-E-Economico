package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class ComplementoDados {
    private ReservaEmergencia reservaEmergencia;
    private ArrayList<Meta> arrMetas;
    private ArrayList<DespesasFixas> arrDespesasFixas;

    // eu preciso de um construtor?
    public ComplementoDados(ArrayList<Meta> arrMetas, ArrayList<DespesasFixas> arrDespesasFixas,
            ReservaEmergencia reservaEmergencia) {
        this.arrMetas = arrMetas;
        this.arrDespesasFixas = arrDespesasFixas;
        this.reservaEmergencia = reservaEmergencia;
    }
    

    public void registraDados(Usuario usuarioVez) {
        ComplementoDao dao = new ComplementoDao();
        Scanner entrada = new Scanner(System.in);

        System.out.println("Cadastro inicial de complemento financeiro: ");

        boolean respMetas = true;
        System.out.println("Informe suas metas financeiras: ");
        while (respMetas) {
            System.out.print("Digite o nome da meta: ");
            String nomeMeta = entrada.nextLine();

            System.out.print("Digite o valor da meta: ");
            float valorMeta = entrada.nextFloat();
            entrada.nextLine(); 

            arrMetas.add(new Meta(nomeMeta, valorMeta));

            System.out.print("Deseja adicionar outra meta? (s/n): ");
            String resposta = entrada.nextLine();
            respMetas = resposta.equalsIgnoreCase("s");
        }

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
        this.reservaEmergencia = new ReservaEmergencia(valorReserva);

        dao.insertComplementos(usuarioVez.getEmail(), arrMetas, arrDespesasFixas, reservaEmergencia);
    }
}
