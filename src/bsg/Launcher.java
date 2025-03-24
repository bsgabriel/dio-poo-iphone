package bsg;

import bsg.entidades.Iphone;
import bsg.exceptions.CelularSemBateriaException;

import java.util.Scanner;

public class Launcher {

    public static void main(String[] args) {
        var iphone = new Iphone(70);

        try {
            iphone.ligar();
        } catch (CelularSemBateriaException e) {
            System.err.println(e.getMessage());
            return;
        }

        int opc;
        Scanner input = new Scanner(System.in);

        do {
            System.out.println("Selecione um dos aplicativos");
            System.out.println("1- Agenda de Contatos");
            System.out.println("2- Navegador");
            System.out.println("3- Reprodutor Musical");
            System.out.print("Aplicação: ");
            opc = input.nextInt();

            switch (opc) {
                case 1:
                    iphone.abrirAgendaContatos();
                    break;
                case 2:
                    iphone.abrirNavegador();
                    break;
                case 3:
                    iphone.abrirReprodutorMusical();
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opc != 0);
    }

}
