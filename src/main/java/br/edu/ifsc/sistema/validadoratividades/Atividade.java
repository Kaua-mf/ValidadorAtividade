package br.edu.ifsc.sistema.validadoratividades;

public class Atividade {
    private final String descricao;
    private final IValidacaoStrategy estrategiaValidacao;

    public Atividade(String descricao, IValidacaoStrategy estrategiaValidacao) {
        this.descricao = descricao;
        this.estrategiaValidacao = estrategiaValidacao;
    }

    public String getDescricao() { return descricao; }
    public IValidacaoStrategy getEstrategiaValidacao() { return estrategiaValidacao; }

    @Override
    public String toString() {
        return String.format("%s (%s)", descricao, estrategiaValidacao.getDescricaoRegra());
    }
}