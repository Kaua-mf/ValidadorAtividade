package br.edu.ifsc.sistema.validadoratividades;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class GeradorParecer {
    public String gerar(String matricula, List<Requerimento> requerimentos) {
        StringBuilder parecer = new StringBuilder();
        int totalHorasDeclaradas = 0;
        int totalHorasValidadas = 0;

        parecer.append("\n=== PARECER DE VALIDACAO ===\n");
        parecer.append("Matricula: ").append(matricula).append("\n");
        parecer.append("Data emissao: ").append(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE)).append("\n\n");

        for (int i = 0; i < requerimentos.size(); i++) {
            Requerimento req = requerimentos.get(i);
            totalHorasDeclaradas += req.getValorDeclarado();
            totalHorasValidadas += req.getHorasValidadas();
            ///feeee

            IValidacaoStrategy estrategia = req.getAtividade().getEstrategiaValidacao();

            parecer.append(String.format("Atividade %d:\n", i + 1));
            parecer.append(String.format("  Descricao:        %s\n", req.getAtividade().getDescricao()));
            parecer.append(String.format("  Horas declaradas: %dh\n", req.getValorDeclarado()));

            if (estrategia instanceof ValidacaoLimiteSimplesStrategy) {
                String[] partes = estrategia.getDescricaoRegra().split(" ");
                String limite = partes[partes.length - 2]; 
                parecer.append(String.format("  Limite Maximo:    %sh\n", limite));
            }

            parecer.append(String.format("  Horas validadas:  %dh\n", req.getHorasValidadas()));
            parecer.append(String.format("  Observacao:       %s\n\n", req.getObservacao()));
        }

        parecer.append("Resumo geral:\n");
        parecer.append(String.format("  Total de horas declaradas: %dh\n", totalHorasDeclaradas));
        parecer.append(String.format("  Total de horas validadas:  %dh\n", totalHorasValidadas));

        return parecer.toString();
    }
}