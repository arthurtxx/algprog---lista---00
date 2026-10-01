import java.util.Scanner;

public class Elevador {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.println("Em que andar o elevador esta? (1 a 10)");
        int atual = entrada.nextInt();
        while (atual < 1 || atual > 10) {
            System.out.println("Andar invalido. Digite de 1 a 10.");
            atual = entrada.nextInt();
        }

        System.out.println("Quantas chamadas? (1 a 5)");
        int total = entrada.nextInt();
        while (total < 1 || total > 5) {
            System.out.println("Valor invalido. Digite de 1 a 5.");
            total = entrada.nextInt();
        }

        int[] andares = new int[total];
        int[] direcoes = new int[total];
        boolean[] atendida = new boolean[total];

        for (int i = 0; i < total; i++) {
            System.out.println("Chamada " + (i + 1) + " - andar (1 a 10):");
            andares[i] = entrada.nextInt();
            while (andares[i] < 1 || andares[i] > 10) {
                System.out.println("Andar invalido. Digite de 1 a 10.");
                andares[i] = entrada.nextInt();
            }

            System.out.println("Chamada " + (i + 1) + " - direcao (1 = subir, 2 = descer):");
            direcoes[i] = entrada.nextInt();
            while (direcoes[i] != 1 && direcoes[i] != 2) {
                System.out.println("Opcao invalida. Digite 1 ou 2.");
                direcoes[i] = entrada.nextInt();
            }

            if (andares[i] == 1 && direcoes[i] == 2) {
                System.out.println("Do andar 1 so da para subir. Chamada ajustada para subir.");
                direcoes[i] = 1;
            }
            if (andares[i] == 10 && direcoes[i] == 1) {
                System.out.println("Do andar 10 so da para descer. Chamada ajustada para descer.");
                direcoes[i] = 2;
            }
        }

        int direcao = 0;
        int pendentes = total;

        while (pendentes > 0) {
            int escolhida = -1;
            int menorDistancia = 100;

            for (int i = 0; i < total; i++) {
                if (atendida[i] == false) {
                    boolean noCaminho = false;
                    if (direcao == 1 && direcoes[i] == 1 && andares[i] >= atual) {
                        noCaminho = true;
                    }
                    if (direcao == 2 && direcoes[i] == 2 && andares[i] <= atual) {
                        noCaminho = true;
                    }
                    if (noCaminho) {
                        int distancia = andares[i] - atual;
                        if (distancia < 0) {
                            distancia = -distancia;
                        }
                        if (distancia < menorDistancia) {
                            menorDistancia = distancia;
                            escolhida = i;
                        }
                    }
                }
            }

            if (escolhida == -1) {
                for (int i = 0; i < total; i++) {
                    if (atendida[i] == false) {
                        int distancia = andares[i] - atual;
                        if (distancia < 0) {
                            distancia = -distancia;
                        }
                        if (distancia < menorDistancia) {
                            menorDistancia = distancia;
                            escolhida = i;
                        }
                    }
                }
                direcao = direcoes[escolhida];
                if (direcao == 1) {
                    System.out.println("Elevador vai SUBIR.");
                } else {
                    System.out.println("Elevador vai DESCER.");
                }
            }

            System.out.println("Indo do andar " + atual + " ao andar " + andares[escolhida]);
            atual = andares[escolhida];
            atendida[escolhida] = true;
            pendentes = pendentes - 1;

            if (direcoes[escolhida] == 1) {
                System.out.println("Parou no andar " + atual + ". Passageiro que vai subir entrou.");
            } else {
                System.out.println("Parou no andar " + atual + ". Passageiro que vai descer entrou.");
            }
        }

        System.out.println("Todas as chamadas foram atendidas.");
        entrada.close();
    }
}