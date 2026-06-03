package com.fatec.zl.ads.view;

import java.util.List;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.fatec.zl.ads.entity.Livro.Livro;
import com.fatec.zl.ads.service.LivroService;

@Component
public class MenuPrincipal implements CommandLineRunner {
    private static Scanner sc = new Scanner(System.in);
    private final LivroService livroService;

    public MenuPrincipal(LivroService livroService) {
        this.livroService = livroService;
    }

    @Override
    public void run(String... args) throws Exception {
        menuDeOpcoes();
    }

    private void menuDeOpcoes() {
        while (true) {
            System.out.println(
                    "\nSelecione uma opção\n1. Pesquisar livros\n2. Visualizar pedido\n3. Encerrar\n");
            int opcao = sc.nextInt();
            switch (opcao) {
                case 1:
                    menuPesquisarLivro();
                    break;
                case 2:
                    System.out.println("Teste 2");
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Opção desconhecida!");
                    break;
            }
        }
    }

    private void menuPesquisarLivro() {
        while (true) {
            System.out.println("\nVocê deseja buscar por:\n1. Nome\n2. Autor\n3. Editora\n4. Categoria\n5. Voltar\n");
            int opcao = sc.nextInt();
            switch (opcao) {
                case 1 -> {
                    System.out.print("\nDigite o titulo do livro: ");
                    String tituloLivro = sc.next();
                    List<Livro> listaDeLivros = livroService.buscarLivroPorTitulo(tituloLivro);
                    if (listaDeLivros.isEmpty()) {
                        System.err.println("Nenhum livro encontrado!");
                    }
                    for (Livro livro : listaDeLivros) {
                        String titulo = livro.getTitulo();
                        Integer id = livro.getId();
                        System.out.println(id + ". : " + titulo);
                        selecionarLivro();
                    }
                }
                case 5 -> {
                    return;
                }
                default -> {
                    System.out.println("Opção desconhecida!");
                    break;
                }
            }
        }
    }

    private void selecionarLivro() {
        System.out.println("\nDeseja selecionar algum Livro?\nS ou N\n");
        String opcao = sc.next();
        
        if (opcao.equals("N")) {
            return;
        }

        while (true) {
            if (opcao.equals("S")) {
                System.out.println("\nDigite código e quantidade\nEx.: 1 10");
                try {
                    sc.nextLine();
                    String valores = sc.nextLine();
                    String[] splitados = valores.split(" ");
                    Integer codigo = Integer.parseInt(splitados[0]);
                    Integer quantidade = Integer.parseInt(splitados[1]);
                    System.out.println(codigo + "" + quantidade);
                    return;
                } catch (Exception e) {
                    System.err.println("valores não reconhecidos!");
                    continue;
                }
            } else {
                return;
            }
        }
    }
}
