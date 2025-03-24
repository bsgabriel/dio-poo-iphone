package bsg.entidades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ContatoTelefone {

    private final String nome;
    private final List<Long> numeros;

    public ContatoTelefone(String nome, Long... numeros) {
        this.nome = nome;
        this.numeros = new ArrayList<>();
        this.numeros.addAll(Arrays.asList(numeros));
    }

    public String getNome() {
        return nome;
    }

    public List<Long> getNumeros() {
        return numeros;
    }

}
