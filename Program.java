import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Halloween halloweenzinho = new Halloween();

        System.out.println("**************Casos de Teste**************");

        // teste 1
        halloweenzinho.solve("13"); // saída esperada: 0 13

        // teste 2
        halloweenzinho.solve("((1 2) (3 4))"); // saída esperada: 10 10

        // teste 3
        halloweenzinho.solve("(((((1 1) 1) 1) 1) 1)"); // saída esperada: 15 6

        // teste 4
        halloweenzinho.solve("(((1 2) (3 4)) ((6 7) (8 9)))"); // saída esperada: 25 40

        // teste 5
        halloweenzinho.solve("((1 2) (((10 10) (3 4)) ((((1 1) 1) 1) 1)))"); // saída esperada: 34 35

    }

}
