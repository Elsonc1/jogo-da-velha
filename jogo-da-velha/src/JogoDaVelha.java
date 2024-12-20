import java.util.Scanner;

public class JogoDaVelha {

    public static void main(String[] args) {
        Tabuleiro tabuleiro = new Tabuleiro();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bem-vindo ao Jogo da Velha!");
        char jogadorAtual = 'X';

        while (true) {
            tabuleiro.imprimirTabuleiro();
            System.out.println("Vez do jogador " + jogadorAtual);

            int linha, coluna;

            while (true) {
                linha = obterEntradaValida(scanner, "--Digite a linha (0, 1, 2): ");
                coluna = obterEntradaValida(scanner, "--Digite a coluna (0, 1, 2): ");

                if (tabuleiro.jogar(linha, coluna, jogadorAtual)) {
                    break;
                } else {
                    System.out.println("Essa posição já está ocupada. Tente novamente.");
                }
            }

            if (tabuleiro.verificarVencedor(jogadorAtual)) {
                tabuleiro.imprimirTabuleiro();
                System.out.println("Parabéns! O jogador " + jogadorAtual + " venceu!");
                break;
            }

            if (tabuleiro.tabuleiroCompleto()) {
                tabuleiro.imprimirTabuleiro();
                System.out.println("O jogo terminou em empate!");
                break;
            }

            jogadorAtual = (jogadorAtual == 'X') ? 'O' : 'X';
        }

        scanner.close();
    }

    private static int obterEntradaValida(Scanner scanner, String mensagem) {
        int entrada;
        while (true) {
            System.out.print(mensagem);
            String linha = scanner.nextLine().trim(); // Lê a entrada e remove espaços em branco

            if (linha.isEmpty()) {
                System.out.println("Entrada inválida! Não pode ser nula ou em branco. Tente novamente.");
                continue;
            }

            try {
                entrada = Integer.parseInt(linha);
                if (entrada >= 0 && entrada <= 2) {
                    return entrada;
                } else {
                    System.out.println("Entrada inválida! Digite um número entre 0 e 2.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Digite um número inteiro.");
            }
        }
    }
}