package br.edu.ifsc.sistema.validadoratividades;

import java.util.List;

public class Modalidade {
    private final String nome;
    private final List<Atividade> atividades;

    public Modalidade(String nome, List<Atividade> atividades) {
        this.nome = nome;
        this.atividades = atividades;
    }

    public String getNome() { return nome; }
    public List<Atividade> getAtividades() { return List.copyOf(atividades); }
    public Atividade getAtividadePorIndice(int index) {
        return (index >= 0 && index < atividades.size()) ? atividades.get(index) : null;
    }
}