package br.edu.ifsc.sistema.validadoratividades;

public interface IValidacaoStrategy {
    ResultadoValidacao validar(int valorDeclarado);
    String getDescricaoRegra();
}