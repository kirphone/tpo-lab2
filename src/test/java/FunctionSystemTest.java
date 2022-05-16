import system.FunctionSystem;

import logarithm.*;
import trigonometry.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("System solver tests")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FunctionSystemTest {
    private FunctionSystem functionSystem;
    private static final Double DELTA = 0.005;

    @BeforeAll
    void init(){
        double accuracy = 0.001;
        this.functionSystem = new FunctionSystem(
                new TrigonometricPart(accuracy), new LogarithmicPart(accuracy));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/system.csv")
    void systemSolverTest(double x, double expectedResult) {
        assertEquals(expectedResult, functionSystem.calculate(x), DELTA);
    }
}