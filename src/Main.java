import java.util.Scanner;

import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Program liczy pole pod wykresem funkcji");
        double[] cofficients = getCofficients();
        displayFunction(cofficients);
        System.out.println();
        System.out.println("Podaj początek przedziału: ");
        double startInterval = scanner.nextDouble();
        System.out.println("Podaj koniec przedziału: ");
        double endInterval = scanner.nextDouble();
        System.out.println("Podaj bład wykonywanych obliczen (np.0,001)");
        double error = scanner.nextDouble();
        scanner.nextLine();

        double valueFunction = findZeroFunction(cofficients,startInterval,endInterval,error);
        if (valueFunction < error) System.out.println("Funkcja ma miejsce zerowe w punkcie: " + valueFunction);
        else System.out.println("W podanym przedziale nie ma miejsca zerowego");

    }


    public static double findZeroFunction (double[] cofficients,double startInterval,double endInterval,double error) {
        double middle = (startInterval - endInterval)/2;
        while   (getValueFunction(cofficients,startInterval) != 0 &&
                getValueFunction(cofficients,endInterval) != 0 &&
                endInterval - startInterval > error) {
            if (getValueFunction(cofficients,startInterval)*getValueFunction(cofficients,middle) > 0) startInterval = middle;
            else endInterval = middle;
        }
        if (getValueFunction(cofficients,startInterval) == 0) return startInterval;
        if (getValueFunction(cofficients,endInterval) == 0) return endInterval;
        return middle;
    }

    public static double[] getCofficients() {
        System.out.println("Podaj stopien wielomianu: ");
        int degree = scanner.nextInt();
        double[] cofficients = new double[degree+1];
        System.out.println("Podaj wspolczynniki wielomianu w ilosci " + degree++);
        for (int i=0;i<degree;i++) cofficients[i] = scanner.nextDouble();
        return  cofficients;
    }

    public static void displayFunction(double[] cofficients) {
        for (int i=0;i<cofficients.length;i++) {
            int lenght = cofficients.length;
            System.out.print(cofficients[i] + " * x^" + (lenght-1-i)+ " + " );
        }
    }
    public static double getValueFunction(double[] cofficients,double valueX) {
        double valueY = 0;
        int size = cofficients.length;
        for (int i=0;i<size;i++) {
            valueY += cofficients[i] * pow(valueX,size - 1 - i);
        }
        return valueY;
    }
}

