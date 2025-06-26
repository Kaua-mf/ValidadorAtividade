package br.edu.ifsc.sistema.validadoratividades;

public class ValidacaoLimiteSimplesStrategy implements IValidacaoStrategy {
    private final int limiteHoras;

    public ValidacaoLimiteSimplesStrategy(int limiteHoras) {
        this.limiteHoras = limiteHoras;
    }

    @Override
    public ResultadoValidacao validar(int horasDeclaradas) {
        if (horasDeclaradas > this.limiteHoras) {
            String obs = String.format("Horas declaradas (%dh) excedem o limite (%dh); ajustadas para %dh.",
                horasDeclaradas, this.limiteHoras, this.limiteHoras);
            return new ResultadoValidacao(this.limiteHoras, obs);
        } else {
            return new ResultadoValidacao(horasDeclaradas, "-- (sem ajuste)");
        }
    }

    @Override
    public String getDescricaoRegra() {
        return String.format("Limite de %d horas", limiteHoras);
    }
}