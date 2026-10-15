package com.cesul.barber;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class TesteMemoriaResolvido {

    static class UtilizadorInteligente {
        UUID id;
        String nome;

        public UtilizadorInteligente(UUID id, String nome) {
            this.id = id;
            this.nome = nome;
        }

        // Ensinamos o Java a comparar pelo ID
        @Override
        public boolean equals(Object o) {
            // Se for exatamente a mesma morada de memória, é igual (otimização)
            if (this == o) return true;

            // Se o outro objeto for nulo ou de uma classe diferente, não é igual
            if (o == null || getClass() != o.getClass()) return false;

            // Comparamos a "mobília" (neste caso, o ID)
            UtilizadorInteligente queVeioDeFora = (UtilizadorInteligente) o;
            return Objects.equals(id, queVeioDeFora.id);
        }

        // Sempre que redefinimos o equals, temos de redefinir o hashCode
        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }

    public static void main(String[] args) {
        UUID idPartilhado = UUID.randomUUID();

        UtilizadorInteligente userNaLista = new UtilizadorInteligente(idPartilhado, "João");
        List<UtilizadorInteligente> lista = new ArrayList<>();
        lista.add(userNaLista);

        UtilizadorInteligente userParaProcurar = new UtilizadorInteligente(idPartilhado, "João");

        System.out.println("--- DEPOIS DO EQUALS ---");
        System.out.println("A lista contém o utilizador? " + lista.contains(userParaProcurar)); // TRUE!
    }
}