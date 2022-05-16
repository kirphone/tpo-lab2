package system;

import logarithm.LogarithmicPart;
import trigonometry.TrigonometricPart;

public class FunctionSystem {
    private final TrigonometricPart trigonometricPart;
    private final LogarithmicPart logarithmicPart;

    public FunctionSystem(TrigonometricPart trigonometricPart, LogarithmicPart logarithmicPart) {
        this.trigonometricPart = trigonometricPart;
        this.logarithmicPart = logarithmicPart;
    }

    public double calculate(double x) {
        return x <= 0 ? trigonometricPart.calculate(x) : logarithmicPart.calculate(x);
    }
}