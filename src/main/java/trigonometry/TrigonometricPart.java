package trigonometry;

import lombok.NoArgsConstructor;

import static java.lang.Math.abs;
import static java.lang.Math.pow;

@NoArgsConstructor
public class TrigonometricPart {

    private double accuracy;

    public TrigonometricPart(double accuracy) {
        this.accuracy = accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public double calculate(double x) {
        if (x > 0.0) throw new IllegalArgumentException("X должен быть меньше или равен нулю");
        double sin = calculateSin(x);
        double tan = calculateTg(x);
        double csc = calculateCsc(x);
        double sec = calculateSec(x);
        return (sin - tan - sec + Math.pow(csc, 3)) * Math.pow(sec, 2) * sin;
    }

    public double calculateSin(double x) {
        double result = 0.0;
        double last = 100;

        for(int i = 1; abs(result - last) >= accuracy; ++i)
        {
            last = result;
            result += (Math.pow(-1 , i-1) * Math.pow(x, 2 * i - 1) / factorial(2 * i - 1));//coeff(2 * i - 1, x));//Math.pow(x, (2 * i) - 1)) / (factorial((2 * i) - 1));
        }
        return result;
    }

    private double factorial(int number){
        long result = 1;
        for(long i = 2; i <= number; ++i)
            result *= (double) i;

        return result;
    }

    private double coeff(int number, double x){
        double result = 1;
        for(int i = 1; i <= number; ++i)
            result *= x / (double)i;

        return result;
    }

    public double calculateCsc(double x) {
        return 1.0d / calculateSin(x);
    }

    public double calculateTg(double x){
        return  calculateSin(x) / Math.sqrt(1 - Math.pow(calculateSin(x), 2));
    }

    public double calculateSec(double x) {
        x = x % (2 * Math.PI);
        x += Math.PI / 2;
        if(x >= Math.PI / 2)
            x = Math.PI - x;
        return 1.0d / calculateSin(x);//(x - 3 * Math.PI / 2) % (2 * Math.PI));
    }
}