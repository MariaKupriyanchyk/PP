import java.util.*;
import java.io.*;
import java.math.*;
import java.util.Formatter;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите a (double): ");
        double a = scanner.nextDouble();
        System.out.print("Введите x (double): ");
        double x = scanner.nextDouble();
        System.out.print("Введите k (int): ");
        int k = scanner.nextInt();
        double eps = Math.pow(10, -k);

        double taylorResult = TaylorSeries.powerSeries(a, x, eps);
        double mathResult = Math.pow(a, x);

        System.out.println("\n--- Вариант с double");
        System.out.printf("Приближённое значение: %." + (k+1) + "f\n", taylorResult);
        System.out.printf("Значение Math.pow: %." + (k+1) + "f\n", mathResult);

        Formatter formatter = new Formatter();
        int intVal = (int) Math.round(taylorResult);
        formatter.format("Округлённое значение в 8-ричной системе: %#o\n", intVal);
        formatter.format("Округлённое значение в 16-ричной системе: %#x\n", intVal);
        System.out.println(formatter);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Введите a (BigDecimal): ");
        BigDecimal aBD = new BigDecimal(bufferedReader.readLine());
        System.out.print("Введите x (BigDecimal): ");
        BigDecimal xBD = new BigDecimal(bufferedReader.readLine());
        BigDecimal epsBD = BigDecimal.ONE.divide(BigDecimal.TEN.pow(k));

        BigDecimal taylorResultBD = TaylorSeries.powerSeriesBigDecimal(aBD, xBD, epsBD);

        System.out.println("\n--- Вариант с BigDecimal");
        System.out.printf("Приближённое значение: %." + (k+1) + "f\n", taylorResultBD);
    }
}
