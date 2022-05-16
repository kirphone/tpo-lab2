package trigonometry;

import logarithm.LogarithmicPart;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static java.lang.Math.PI;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Trigonometric calculator test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CalculatorTest {

    private TrigonometricPart trigonometricPart;

    private static final double DELTA = 0.01;

    private static final double ACCURACY = 0.0001;

    @BeforeAll
    void init(){
        this.trigonometricPart = new TrigonometricPart(ACCURACY);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "-3.1415,  0.0",
            "-1.5707, -1.0",
            "-1.2,    -0.932",
            "-0.75,   -0.682",
            "0.0,      0.0",
            "0.75,     0.682",
            "1.2,      0.932",
            "1.5707,   1.0",
            "3.1415,   0.0"
            }
    )
    void sinTest(Double x, Double expectedResult) {
        assertEquals(expectedResult, trigonometricPart.calculateSin(x), DELTA);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "-3.1415, -1.0",
            "-1.5707963267948966,  Infinity",
            "-1.2,     2.760",
            "-0.75,    1.367",
            "0.0,      1.0",
            "0.75,     1.367",
            "1.2,      2.760",
            "1.5707963267948966,   Infinity",
            "3.1415,  -1"
            }
    )
    void secTest(Double x, Double expectedResult) {
        assertEquals(expectedResult, trigonometricPart.calculateSec(x), DELTA);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "-3.1415,  0.0",
            "-1.2,    -2.572",
            "-0.75,   -0.932",
            "0.0,      0.0",
            "0.75,     0.932",
            "1.2,      2.572",
            "3.1415,   0.0"
            }
    )
    void tanTest(Double x, Double expectedResult) {
        assertEquals(expectedResult, trigonometricPart.calculateTg(x), DELTA);
    }

    @Test
    void tanNanTest(){
        TrigonometricPart trigonometricPart1 = new TrigonometricPart(0.1);
        assertEquals(Double.NaN, trigonometricPart1.calculateTg(PI/2.0));
        assertEquals(Double.NaN, trigonometricPart1.calculateTg(-PI/2.0));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "-1.5707, -1.0",
            "-1.2,    -1.073",
            "-0.75,   -1.467",
            "0.75,     1.467",
            "1.2,      1.073",
            "1.5707,   1.0"
            }
    )
    void cscTest(Double x, Double expectedResult) {
        assertEquals(expectedResult, trigonometricPart.calculateCsc(x), DELTA);
    }

    @Test
    void cscNanTest(){
        TrigonometricPart trigonometricPart1 = new TrigonometricPart(10e-300);
        assertEquals(Double.NaN, trigonometricPart1.calculateCsc(PI));
        assertEquals(Double.POSITIVE_INFINITY, trigonometricPart1.calculateCsc(0.0));
        assertEquals(Double.NaN, trigonometricPart1.calculateCsc(-PI));
    }
}
