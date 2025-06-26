package br.edu.ifsc.sistema.validadoratividades;

public class Requerimento {
    private final Atividade atividade;
    private final int valorDeclarado;
    private final int horasValidadas;
    private final String observacao;

    public Requerimento(Atividade atividade, int valorDeclarado) {
        this.atividade = atividade;
        this.valorDeclarado = valorDeclarado;
        
        ResultadoValidacao resultado = atividade.getEstrategiaValidacao().validar(valorDeclarado);
        this.horasValidadas = resultado.horasValidadas();
        this.observacao = resultado.observacao();
    }

    public Atividade getAtividade() { return atividade; }
    public int getValorDeclarado() { return valorDeclarado; }
    public int getHorasValidadas() { return horasValidadas; }
    public String getObservacao() { return observacao; }
}