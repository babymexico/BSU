import org.example.FactorialCalculator;
import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class FactorialCalculatorTest {

    @Test
    void testCalculateFactorials() {
        FactorialCalculator calculator = new FactorialCalculator();
        List<BigInteger> factorials = calculator.calculateFactorials(5);

        assertNotNull(factorials);
        assertEquals(5, factorials.size());
        assertEquals(BigInteger.ONE, factorials.get(0));
        assertEquals(BigInteger.valueOf(2), factorials.get(1));
        assertEquals(BigInteger.valueOf(6), factorials.get(2));
        assertEquals(BigInteger.valueOf(24), factorials.get(3));
        assertEquals(BigInteger.valueOf(120), factorials.get(4));
    }

    @Test
    void testCalculateFactorialsNegativeInput() {
        FactorialCalculator calculator = new FactorialCalculator();

        assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculateFactorials(-5);
        });
    }
}
