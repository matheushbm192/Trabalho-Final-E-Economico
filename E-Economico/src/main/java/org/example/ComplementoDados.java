package org.example;

import java.util.Scanner;

public class ComplementoDados {
    private double meta; 
    private double despesasFixas; 
    private double reservaEmerg; 

    public ComplementoDados(double meta, double despesasFixas, double reservaEmerg){
        this.meta = meta; 
        this.despesasFixas = despesasFixas;
        this.reservaEmerg = reservaEmerg; 
    }
    public void registraDados(Usuario usuarioVez){
        Scanner entrada = new Scanner(System.in);
        System.out.println("Cadastro inicial de complemento financeiro: ");
        System.out.println("Informe sua meta financeira: ");
        meta = entrada.nextDouble();
        System.out.println("Informe o valor da sua reserva de emergência: ");
        reservaEmerg = entrada.nextDouble();
        System.out.println("Informe o total das suas despesas fixas mensais: ");
        despesasFixas = entrada.nextDouble();

        ComplementoDados dados = new ComplementoDados(meta, despesasFixas, reservaEmerg);
        ComplementoDao.insertComplementos(usuarioVez, dados); 
    }
}
