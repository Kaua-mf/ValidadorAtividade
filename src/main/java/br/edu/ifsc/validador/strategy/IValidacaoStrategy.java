package br.edu.ifsc.validador.strategy;

public interface IValidacaoStrategy {
    ResultadoValidacao validar(int valorDeclarado);
    String getDescricaoRegra();
}