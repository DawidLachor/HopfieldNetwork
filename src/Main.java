import java.util.Arrays;
import java.util.Scanner;

public class Main {
        private final static int[] x1 = new int[]{
            1, 1, 0, 0, 0, 1, 0, 1,
            1, 0, 1, 1, 0, 1, 0, 1,
            1, 0, 1, 0, 0, 0, 0, 0,
            1, 0, 0, 1, 1, 1, 1, 1,
            0, 0, 1, 0, 1, 0, 1, 1,
            0, 0, 0, 0, 0, 0, 0, 1,
            1, 1, 0, 1, 1, 1, 1, 0,
            0, 1, 0, 0, 0, 0, 0, 0
    };

//    private final static int[] x1 = new int[]{
//            1, 1, 0, 1, 1
//    };


    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("Twój obraz to:");
        show(x1);


        int menu;
        do {
            menu();
            menu = input.nextInt();
            switch (menu) {
                case 1 -> show(x1);
                case 2 -> inputPaint(input);
            }
        } while (menu!=3);


    }

    public static void inputPaint(Scanner input) {
        int[] picture = new int[x1.length];
        for (int i = 0; i < picture.length; i++) {
            picture[i] = input.nextByte();
        }

        int[][] w = new int[x1.length][x1.length];

        for (int i = 0; i < x1.length; i++) {
            for (int j = 0; j < x1.length; j++) {
                w[i][j] = matrix(i, j, x1);
            }
        }

        System.out.println("Tabele z obliczeniami ");
        for (int[] v: w) {
            System.out.println(Arrays.toString(v));
        }

        int[] s = calculations(w, picture);

        System.out.println("Wpisany przez ciebie obraz to:");
        show(picture);
        System.out.println("Obliczenia");
        show(s);
        System.out.println("Po algorytmie syfru Hopfielda");
        show(condition(s, picture));
    }

    public static void menu() {
        System.out.println("Co chcez zrobić:");
        System.out.println("1. Pokaz obraz");
        System.out.println("2. Wpisz obraz");
        System.out.println("3. Exit");
    }

    public static void show(int[] x1) {
        for (int i = 1; i < x1.length + 1; i++) {
            if (i % 8 != 0)
                System.out.print(x1[i - 1] + ", ");
            else
                System.out.print(x1[i - 1] + ", \n");
        }
        System.out.print("\n");
    }

    //Wzór W(ij)
    public static int matrix(int i, int j, int[] x1) {
        if (i == j)
            return 0;
        else
            return (2 * x1[i] - 1) * (2 * x1[j] - 1);
    }

    //Obliczenie W
    public static int[] calculations(int[][] w, int[] test) {
        int[] result = new int[test.length];
        for (int i = 0; i < test.length; i++) {
            result[i] = ox(w[i], test);

        }
        return result;
    }

    //obliczenie pojedynczej wartości w "matrix"
    public static int ox(int[] w, int[] test) {
        int oxResult = 0;
        for (int i = 0; i < w.length; i++) {
            oxResult += w[i] * test[i];
        }
        return oxResult;
    }

    //Ostatecznie obliczenia
    public static int[] condition(int[] calculations, int[] test) {
        int[] end = new int[test.length];
        for (int i = 0; i < test.length; i++) {
            end[i] = check(calculations[i], test[i]);
        }
        return end;
    }

    //Sprawdzenie jaką wartośc dać 0, 1 czy zostawić
    public static int check(int calculation, int test) {
        if (calculation > 0)
            return 1;
        else if (calculation < 0)
            return 0;
        else
            return test;
    }
}
