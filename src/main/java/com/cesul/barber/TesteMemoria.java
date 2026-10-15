package com.cesul.barber;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TesteMemoria {

    // Uma classe interna simples sem equals() para demonstrar o problema
    static class UtilizadorSimples {
        UUID id;
        String nome;

        public UtilizadorSimples(UUID id, String nome) {
            this.id = id;
            this.nome = nome;
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }
    }

    public static void main(String[] args) {
        UUID idPartilhado = UUID.randomUUID();

        // 1. Criamos o utilizador e adicionamos à lista (Ocupa a Memória A)
        UtilizadorSimples userNaLista = new UtilizadorSimples(idPartilhado, "João");
        List<UtilizadorSimples> lista = new ArrayList<>();
        lista.add(userNaLista);

        // 2. O aluno tenta procurar criando um NOVO objeto com os MESMOS dados (Ocupa a Memória B)
        UtilizadorSimples userParaProcurar = new UtilizadorSimples(idPartilhado, "João");

        // 3. A demonstração visual para os alunos
        System.out.println("--- ANTES DO EQUALS ---");
        System.out.println("Os dados são iguais? " + (userNaLista.nome.equals(userParaProcurar.nome))); // true

        System.out.println("As referências de memória (==) são iguais? " + (userNaLista == userParaProcurar)); // false

        System.out.println("A lista contém o utilizador? " + lista.contains(userParaProcurar)); // FALSE!
    }
}