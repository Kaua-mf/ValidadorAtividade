package br.edu.ifsc.sistema.validadoratividades;

public class ValidacaoValorFixoStrategy implements IValidacaoStrategy {
    private final int horasFixas;

    public ValidacaoValorFixoStrategy(int horasFixas) {
        this.horasFixas = horasFixas;
    }

    @Override
    public ResultadoValidacao validar(int valorDeclarado) {
        String obs = String.format("Atividade concede um valor fixo de %dh.", this.horasFixas);
        return new ResultadoValidacao(this.horasFixas, obs);
    }

    @Override
    public String getDescricaoRegra() {
        return String.format("Valor fixo de %d horas", horasFixas);
    }
}