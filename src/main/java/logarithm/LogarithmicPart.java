package logarithm;

import lombok.NoArgsConstructor;
@NoArgsConstructor
public class LogarithmicPart {

    private double accuracy;

    public LogarithmicPart(double accuracy) {
        this.accuracy = accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public double calculate(double x) {
        if (x <= 0.0)
            throw new IllegalArgumentException("x должен быть больше нуля");

        double ln = calculateLn(x);
        double log2 = calculateLog2(x);
        double log3 = calculateLog3(x);
        double log5 = calculateLog5(x);
        double log10 = calculateLog10(x);

        return Math.pow(Math.pow(log3, 3) / ln, 2) +
                log10 / (log2 + log5) + log5 + log10;
    }

    public double calculateLn(double x) {
        if (Double.isNaN(x) || x < 0.0) {
            return Double.NaN;
        }

        if (x == Double.POSITIVE_INFINITY) {
            return Double.POSITIVE_INFINITY;
        }

        if (x == 0.0) {
            return Double.NEGATIVE_INFINITY;
        }

        double value = 0;
        double prevValue;
        int n = 1;
        int k = 1;
        if (Math.abs(x - 1) <= 1) {
            do {
                prevValue = value;
                value -= ((Math.pow(-1, n) * Math.pow(x - 1, n)) / n);
                n++;
            } while (accuracy <= Math.abs(value - prevValue));
        } else {
            do {
                prevValue = value;
                value -= ((Math.pow(-1, k) * Math.pow(x - 1, -k)) / k);
                k++;
            } while (accuracy <= Math.abs(value - prevValue));
            value += calculateLn(x-1);
        }
        return value;
    }

    public double calculateLog2(double x) {
        return calculateLn(x) / calculateLn(2.0);
    }

    public double calculateLog3(double x) {
        return calculateLn(x) / calculateLn(3.0);
    }

    public double calculateLog5(double x) {
        return calculateLn(x) / calculateLn(5.0);
    }

    public double calculateLog10(double x) {
        return calculateLn(x) / calculateLn(10.0);
    }
}