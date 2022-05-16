package trigonometry;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import utils.CSVLogger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Trigonometric function test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FunctionTest {

    private TrigonometricPart trigonometricPart;

    private static final double ACCURACY = 0.0001;
    private final double DELTA = 0.01;

    private final CSVLogger logger = new CSVLogger("trigonometry/sin-results.csv", -7.0, 0.0, 0.5);

    @BeforeEach
    void init(){
        trigonometricPart = spy(TrigonometricPart.class);
        trigonometricPart.setAccuracy(ACCURACY);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/trigonometry/trig.csv")
    @DisplayName("all are stubs")
    void allAreStubs(double x, double expectedResult) {
        when(trigonometricPart.calculateSin(x)).thenReturn(prepareForStub(Math.sin(x)));
        when(trigonometricPart.calculateTg(x)).thenReturn(prepareForStub(Math.tan(x)));
        when(trigonometricPart.calculateSec(x)).thenReturn(prepareForStub(1 / Math.cos(x)));
        when(trigonometricPart.calculateCsc(x)).thenReturn(prepareForStub(1 / Math.sin(x)));

        double actualResult = trigonometricPart.calculate(x);
        assertEquals(expectedResult, actualResult, DELTA);

        verify(trigonometricPart, atLeastOnce()).calculateSin(x);
        verify(trigonometricPart, atLeastOnce()).calculateTg(x);
        verify(trigonometricPart, atLeastOnce()).calculateSec(x);
        verify(trigonometricPart, atLeastOnce()).calculateCsc(x);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/trigonometry/trig.csv")
    @DisplayName("sin(x) is a stub; all other aren't a stub")
    void sinIsStub(double x, double expectedResult) {
        when(trigonometricPart.calculateSin(x)).thenReturn(prepareForStub(Math.sin(x)));

        double actualResult = trigonometricPart.calculate(x);
        assertEquals(expectedResult, actualResult, DELTA);

        verify(trigonometricPart, atLeastOnce()).calculateSin(x);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/trigonometry/trig.csv")
    @DisplayName("sec(x) is a stub; all other aren't a stub")
    void secIsStub(double x, double expectedResult) {
        when(trigonometricPart.calculateSec(x)).thenReturn(prepareForStub(1 / Math.cos(x)));

        double actualResult = trigonometricPart.calculate(x);
        assertEquals(expectedResult, actualResult, DELTA);

        verify(trigonometricPart, atLeastOnce()).calculateSec(x);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/trigonometry/trig.csv")
    @DisplayName("tan(x) is a stub; all other aren't a stub")
    void tanIsStub(double x, double expectedResult) {
        when(trigonometricPart.calculateTg(x)).thenReturn(prepareForStub(Math.tan(x)));

        double actualResult = trigonometricPart.calculate(x);
        assertEquals(expectedResult, actualResult, DELTA);

        verify(trigonometricPart, atLeastOnce()).calculateTg(x);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/trigonometry/trig.csv")
    @DisplayName("csc(x) is a stub; all other aren't a stub")
    void cscIsStub(double x, double expectedResult) {

        when(trigonometricPart.calculateCsc(x)).thenReturn(prepareForStub(1 / Math.sin(x)));

        double actualResult = trigonometricPart.calculate(x);
        assertEquals(expectedResult, actualResult, DELTA);

        verify(trigonometricPart, atLeastOnce()).calculateCsc(x);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/trigonometry/trig.csv")
    @DisplayName("All calculators aren't stubs")
    void nothingIsStub(double x, double expectedResult) {
        double actualResult = trigonometricPart.calculate(x);
        assertEquals(expectedResult, actualResult, DELTA);
    }

    @Disabled
    @Test
    void log() {
        logger.log((x) -> trigonometricPart.calculateSin(x));

        logger.setFileName("trigonometry/csc-results.csv");
        logger.log((x) -> trigonometricPart.calculateCsc(x));

        logger.setFileName("trigonometry/sec-results.csv");
        logger.log((x) -> trigonometricPart.calculateSec(x));

        logger.setFileName("trigonometry/tg-results.csv");
        logger.log((x) -> trigonometricPart.calculateTg(x));

        logger.setFileName("trigonometry/function-results.csv");
        logger.log((x) -> trigonometricPart.calculate(x));
    }

    private double prepareForStub(double number){
        if(Math.abs(number) < ACCURACY)
            return 0;
        if(Math.abs(number) > 1 / ACCURACY)
            return number > 0 ? Double.POSITIVE_INFINITY : Double.NEGATIVE_INFINITY;
        return number;
    }
}
