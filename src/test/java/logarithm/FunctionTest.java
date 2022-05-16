package logarithm;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import utils.CSVLogger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Logarithmic function test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FunctionTest {

    private LogarithmicPart logarithmicPart;

    private final double DELTA = 0.01;
    private static final double ACCURACY = 0.0001;
    private final CSVLogger logger = new CSVLogger("logarithm/ln-results.csv", 0.25, 5.0, 0.5);

    @BeforeEach
    void init() {
        logarithmicPart = spy(LogarithmicPart.class);
        logarithmicPart.setAccuracy(ACCURACY);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/logarithm/ln.csv")
    void allAreStubs(double x, double expectedResult) {

        when(logarithmicPart.calculateLn(x)).thenReturn(Math.log(x));
        when(logarithmicPart.calculateLog2(x)).thenReturn(Math.log(x) / Math.log(2));
        when(logarithmicPart.calculateLog3(x)).thenReturn(Math.log(x) / Math.log(3));
        when(logarithmicPart.calculateLog5(x)).thenReturn(Math.log(x) / Math.log(5));
        when(logarithmicPart.calculateLog10(x)).thenReturn(Math.log10(x));

        double actualResult = logarithmicPart.calculate(x);
        assertEquals(expectedResult, actualResult, DELTA);

        verify(logarithmicPart, atLeastOnce()).calculateLn(x);
        verify(logarithmicPart, atLeastOnce()).calculateLog2(x);
        verify(logarithmicPart, atLeastOnce()).calculateLog3(x);
        verify(logarithmicPart, atLeastOnce()).calculateLog5(x);
        verify(logarithmicPart, atLeastOnce()).calculateLog10(x);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/logarithm/ln.csv")
    void lnIsStub(double x, double expectedResult){
        when(logarithmicPart.calculateLn(x)).thenReturn(Math.log(x));

        double actualResult = logarithmicPart.calculate(x);
        assertEquals(expectedResult, actualResult, DELTA);
        verify(logarithmicPart, atLeastOnce()).calculateLn(x);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/logarithm/ln.csv")
    void log2IsStub(double x, double expectedResult){
        when(logarithmicPart.calculateLog2(x)).thenReturn(Math.log(x) / Math.log(2));

        double actualResult = logarithmicPart.calculate(x);
        assertEquals(expectedResult, actualResult, DELTA);
        verify(logarithmicPart, atLeastOnce()).calculateLog2(x);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/logarithm/ln.csv")
    void log3IsStub(double x, double expectedResult){
        when(logarithmicPart.calculateLog3(x)).thenReturn(Math.log(x) / Math.log(3));

        double actualResult = logarithmicPart.calculate(x);
        assertEquals(expectedResult, actualResult, DELTA);
        verify(logarithmicPart, atLeastOnce()).calculateLog3(x);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/logarithm/ln.csv")
    void log5IsStub(double x, double expectedResult){

        when(logarithmicPart.calculateLog5(x)).thenReturn(Math.log(x) / Math.log(5));

        double actualResult = logarithmicPart.calculate(x);
        assertEquals(expectedResult, actualResult, DELTA);
        verify(logarithmicPart, atLeastOnce()).calculateLog5(x);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/logarithm/ln.csv")
    void log10isStub(double x, double expectedResult){
        when(logarithmicPart.calculateLog10(x)).thenReturn(Math.log10(x));

        double actualResult = logarithmicPart.calculate(x);
        assertEquals(expectedResult, actualResult, DELTA);
        verify(logarithmicPart, atLeastOnce()).calculateLog10(x);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/logarithm/ln.csv")
    void nothingIsStubs(double x, double expectedResult){
        double actualResult = logarithmicPart.calculate(x);
        assertEquals(actualResult, expectedResult, DELTA);
    }

    @Disabled
    @Test
    void logResults() {
        logger.log((x) -> logarithmicPart.calculateLn(x));

        logger.setFileName("logarithm/log2-results.csv");
        logger.log((x) -> logarithmicPart.calculateLog2(x));

        logger.setFileName("logarithm/log3-results.csv");
        logger.setUpperBorder(10.0);
        logger.log((x) -> logarithmicPart.calculateLog3(x));

        logger.setFileName("logarithm/log5-results.csv");
        logger.setUpperBorder(15.0);
        logger.log((x) -> logarithmicPart.calculateLog5(x));

        logger.setFileName("logarithm/log10-results.csv");
        logger.setUpperBorder(20.0);
        logger.log((x) -> logarithmicPart.calculateLog10(x));

        logger.setFileName("logarithm/function-results.csv");
        logger.log((x) -> logarithmicPart.calculate(x));
    }
}
