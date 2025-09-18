import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaylorSeriesTest {

    private final TaylorSeries ts = new TaylorSeries();

    @Test
    void testPowerSeriesApproximation() {
        double a = 2.0;
        double x = 3.0;
        int k = 5;
        double eps = Math.pow(10, -k);

        double approx = ts.powerSeries(a, x, eps);
        double exact = Math.pow(a, x);

        assertEquals(exact, approx, eps,
                "Приближённое значение должно совпадать с Math.pow в пределах eps");
    }

    @Test
    void testAMustBePositive() {
        double a = -2.0;
        double x = 2.0;
        double eps = 1e-5;

        assertThrows(IllegalArgumentException.class, () -> {
            ts.powerSeries(a, x, eps);
        }, "Основание a должно быть больше 0");
    }

    @Test
    void testEpsMustBePositive() {
        assertThrows(IllegalArgumentException.class, () -> {
            ts.powerSeries(2.0, 3.0, 0);
        });
    }
}
