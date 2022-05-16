package utils;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
@Getter @Setter
public class CSVLogger {

    private static final String BASE_PATH = "src/test/resources/";
    private String fileName;
    private double step;
    private double lowerBorder;
    private double upperBorder;
    private static final char CSV_SEPARATOR = ',';

    public CSVLogger(String fileName, double lowerBorder, double upperBorder, double step) {
        this.fileName = fileName;
        this.lowerBorder = lowerBorder;
        this.upperBorder = upperBorder;
        this.step = step;
    }

    public void log(Calculable function) {
        try(PrintStream printStream = new PrintStream(new FileOutputStream(BASE_PATH + fileName, true))) {
            for (double i = lowerBorder; i < upperBorder; i += step) {
                printStream.printf("%f%s%f\n", i, CSV_SEPARATOR, function.calculate(i));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
