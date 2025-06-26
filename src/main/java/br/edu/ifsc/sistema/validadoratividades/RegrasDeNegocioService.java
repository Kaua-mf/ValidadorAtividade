package br.edu.ifsc.sistema.validadoratividades;

import java.util.List;

public class RegrasDeNegocioService {
    private static RegrasDeNegocioService instance;
    private final List<Modalidade> modalidades;

    private RegrasDeNegocioService() {
        this.modalidades = List.of(
            new Modalidade("Ensino", List.of(
                new Atividade("Ministrar aula de reforco", new ValidacaoLimiteSimplesStrategy(10)),
                new Atividade("Monitoria de laboratorio", new ValidacaoLimiteSimplesStrategy(15))
            )),
            new Modalidade("Pesquisa", List.of(
                new Atividade("Iniciacao Cientifica (PIBIC)", new ValidacaoValorFixoStrategy(60)),
                new Atividade("Publicacao de artigo", new ValidacaoValorFixoStrategy(20))
            )),
            new Modalidade("Complementacao", List.of(
                new Atividade("Palestra tecnica", new ValidacaoLimiteSimplesStrategy(5)),
                new Atividade("Visita tecnica", new ValidacaoLimiteSimplesStrategy(8))
            )),
            new Modalidade("Extensão", List.of(
                new Atividade("Curso de extensão oferecido", new ValidacaoLimiteSimplesStrategy(30)),
                new Atividade("Organização de evento", new ValidacaoLimiteSimplesStrategy(15))
            ))
        );
    }

    public static synchronized RegrasDeNegocioService getInstance() {
        if (instance == null) {
            instance = new RegrasDeNegocioService();
        }
        return instance;
    }

    public List<Modalidade> getModalidades() {
        return modalidades;
    }
}