import java.math.BigDecimal;
import java.math.RoundingMode;

class TaylorSeries {
    public static double powerSeries(double a, double x, double eps) {
        if (a <= 0) {
            throw new IllegalArgumentException("Основание a должно быть > 0");
        }
        if (eps <= 0) {
            throw new IllegalArgumentException("Точность eps должна быть > 0");
        }

        double slag = 1.0;
        double sum = 1.0;
        int n = 1;
        double arg = x * Math.log(a);

        while (Math.abs(slag) > eps) {
            slag *= arg / n;
            sum += slag;
            n++;
        }
        return sum;
    }

    public static BigDecimal powerSeriesBigDecimal(BigDecimal a, BigDecimal x, BigDecimal eps) {
        BigDecimal sum = BigDecimal.ONE;
        BigDecimal slag = BigDecimal.ONE;
        BigDecimal arg = x.multiply(BigDecimal.valueOf(Math.log(a.doubleValue())));

        int n = 1;
        while (slag.abs().compareTo(eps) > 0) {
            slag = slag.multiply(arg).divide(BigDecimal.valueOf(n), eps.scale() + 5, RoundingMode.HALF_UP);
            sum = sum.add(slag);
            n++;
        }
        return sum;
    }
}
