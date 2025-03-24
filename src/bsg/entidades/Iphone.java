package bsg.entidades;

import bsg.aplicativos.AparelhoTelefonico;
import bsg.exceptions.CelularSemBateriaException;

public class Iphone {

    private int bateria;
    private boolean ligado;
    private AparelhoTelefonico aparelhoTelefonico;

    public Iphone(int bateria) {
        this.bateria = Math.max(bateria, 0);
        this.ligado = false;
        this.aparelhoTelefonico = new AparelhoTelefonico();
    }

    public void ligar() throws CelularSemBateriaException {
        if (bateria <= 0) {
            throw new CelularSemBateriaException("O celular não pôde ser ligado, pois está sem bateria");
        }

        this.ligado = true;
    }

    public void desligar() {
        this.ligado = true;
    }

    public boolean isLigado() {
        return ligado;
    }

    public void abrirAgendaContatos() {
        aparelhoTelefonico.abrir();
    }

    public void abrirNavegador() {
        System.out.println("Não está implementado ainda!");
    }

    public void abrirReprodutorMusical() {
        System.out.println("Não está implementado ainda!");
    }
}
