package logarithm;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Logarithmic calculator test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CalculatorTest {

    private LogarithmicPart logarithmicPart;

    private static final double DELTA = 0.01;

    private static final double ACCURACY = 0.001;

    @BeforeAll
    void init() {
        logarithmicPart = new LogarithmicPart(ACCURACY);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "-3.0, NaN",
            "-2.0, NaN",
            "-1.0, NaN",
            "-0.1, NaN",
            "0.0, -Infinity",
            "0.2,  -1.609",
            "1.0,   0.0",
            "1.4,   0.336",
            "2.3,   0.833",
            "3.4,   1.224",
            "10.0,  2.303",
            "Infinity, Infinity"
    })
    void lnTest(Double x, Double expectedResult){
        assertEquals(expectedResult, logarithmicPart.calculateLn(x), DELTA);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "-3.0, NaN",
            "-2.0, NaN",
            "-1.0, NaN",
            "-0.1, NaN",
            "0.0, -Infinity",
            "0.2,  -2.322",
            "1.0,   0.0",
            "1.4,   0.485",
            "2.3,   1.202",
            "3.4,   1.766",
            "10.0,  3.322",
            "Infinity, Infinity"
    })
    void log2Test(Double x, Double expectedResult) {
        assertEquals(expectedResult, logarithmicPart.calculateLog2(x), DELTA);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "-3.0, NaN",
            "-2.0, NaN",
            "-1.0, NaN",
            "-0.1, NaN",
            "0.0, -Infinity",
            "0.2,  -1.465",
            "1.0,   0.0",
            "1.4,   0.306",
            "2.3,   0.758",
            "3.4,   1.114",
            "10.0,  2.096",
            "Infinity, Infinity"
    })
    void log3Test(Double x, Double expectedResult) {
        assertEquals(expectedResult, logarithmicPart.calculateLog3(x), DELTA);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "-3.0, NaN",
            "-2.0, NaN",
            "-1.0, NaN",
            "-0.1, NaN",
            "0.0, -Infinity",
            "0.2,  -1.0",
            "1.0,   0.0",
            "1.4,   0.209",
            "2.3,   0.518",
            "3.4,   0.766",
            "10.0,  1.431",
            "Infinity, Infinity"
    })
    void log5Test(Double x, Double expectedResult) {
        assertEquals(expectedResult, logarithmicPart.calculateLog5(x), DELTA);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "-3.0, NaN",
            "-2.0, NaN",
            "-1.0, NaN",
            "-0.1, NaN",
            "0.0, -Infinity",
            "0.2,  -0.699",
            "1.0,   0.0",
            "1.4,   0.146",
            "2.3,   0.362",
            "3.4,   0.531",
            "10.0,  1.0",
            "Infinity, Infinity"
    })
    void log10Test(Double x, Double expectedResult) {
        assertEquals(expectedResult, logarithmicPart.calculateLog10(x), DELTA);
    }

}
