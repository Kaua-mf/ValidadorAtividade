package br.edu.ifsc.sistema.validadoratividades;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaController {
    private final Scanner scanner;
    private final RegrasDeNegocioService regrasService;
    private final GeradorParecer geradorParecer;
    private final List<Requerimento> requerimentos;

    public SistemaController() {
        this.scanner = new Scanner(System.in);
        this.regrasService = RegrasDeNegocioService.getInstance();
        this.geradorParecer = new GeradorParecer();
        this.requerimentos = new ArrayList<>();
    }

    public void iniciar() {
        System.out.print("Matricula do aluno: ");
        String matricula = scanner.nextLine();

        loopPrincipal:
        while (true) {
            exibirMenuModalidades();
            int escolhaModalidade = lerInteiro();
            
            if (escolhaModalidade == -1) continue; 

            if (escolhaModalidade == 0) {
                break loopPrincipal;
            }

            if (escolhaModalidade > 0 && escolhaModalidade <= regrasService.getModalidades().size()) {
                Modalidade modalidade = regrasService.getModalidades().get(escolhaModalidade - 1);
                processarAtividades(modalidade);
            } else {
                System.out.println("Opcao invalida. Tente novamente.");
            }
        }
        
        System.out.println(geradorParecer.gerar(matricula, requerimentos));
        scanner.close();
    }

    private void exibirMenuModalidades() {
        System.out.println("\n== Menu de Modalidades ==");
        List<Modalidade> modalidades = regrasService.getModalidades();
        for (int i = 0; i < modalidades.size(); i++) {
            System.out.printf("%d) %s\n", i + 1, modalidades.get(i).getNome());
        }
        System.out.println("0) Finalizar e emitir parecer");
        System.out.print("Escolha a modalidade (0-finalizar): ");
    }

    private void processarAtividades(Modalidade modalidade) {
        while (true) {
            System.out.printf("\n-- Atividades em %s --\n", modalidade.getNome());
            List<Atividade> atividades = modalidade.getAtividades();
            for (int i = 0; i < atividades.size(); i++) {
                System.out.printf("%d) %s\n", i + 1, atividades.get(i).toString());
            }
            System.out.println("0) Voltar ao menu de modalidades");
            System.out.print("Escolha a atividade (0-voltar): ");
            int escolhaAtividade = lerInteiro();

            if (escolhaAtividade == -1) continue; 
            if (escolhaAtividade == 0) break;

            Atividade atividadeEscolhida = modalidade.getAtividadePorIndice(escolhaAtividade - 1);
            if (atividadeEscolhida != null) {
                System.out.printf("Horas declaradas para '%s': ", atividadeEscolhida.getDescricao());
                int horas = lerInteiro();
                if (horas != -1) {
                    requerimentos.add(new Requerimento(atividadeEscolhida, horas));
                    System.out.println("Atividade adicionada ao requerimento.");
                }
            } else {
                System.out.println("Opção invalida.");
            }
        }
    }

    private int lerInteiro() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Entrada invalida. Por favor, digite um numero.");
            return -1;
        }
    }
}