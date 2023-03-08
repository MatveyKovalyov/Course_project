import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;

public class Main {

    private static Double[] get_random_numbers(int amount) {
        Double[] result = new Double[amount];
        DecimalFormat dm = new DecimalFormat("#.#");
        for (int i = 0; i < amount; i++) {
            result[i] = Double.valueOf((Math.random()));
        }
        return result;
    }

    private static Double[] get_random_numbers_gaussian(int amount) {
        Double[] result = new Double[amount];
        DecimalFormat dm = new DecimalFormat("#.#");
        Random random = new Random();
        for (int i = 0; i < amount; i++) {
            result[i] = Double.valueOf(random.nextGaussian());
        }
        return result;
    }

    private static void print_distribution_function(Double[] argument, double left_bound, double right_bound, double step) {
        DistributionFunction distributionFunction = new DistributionFunction(argument);
        for (double i = left_bound; i <= right_bound; i += step) {
            System.out.println(i + "  :  " + distributionFunction.distribution_function(i));
        }
    }

    public static void main(String[] args) {
        //  Double[] input_array = new Double[]{4.0, 4.0, 1.0, 2.0, 3.0};
        int amount = 20000;
        Double[] input_array_1 = new Double[amount];
        input_array_1 = get_random_numbers_gaussian(amount);
        DistributionFunction distributionFunction = new DistributionFunction(input_array_1);
        BasicRandomVariable basicRandomVariable = new BasicRandomVariable(input_array_1);
        //print_distribution_function(basicRandomVariable.getBSV(), -0.1, 1.1, 0.01);
        DistributionFunction dfBsv = new DistributionFunction(basicRandomVariable.getBSV());
        Double[] inputBSV = basicRandomVariable.getBSV().clone();
        Arrays.sort(inputBSV);
        PlotFileOutput plotFileOutput = new PlotFileOutput(inputBSV, dfBsv.getArray_of_values_of_distribution_function());
        plotFileOutput.create_plot_file("plot.txt", " ");
        Arrays.sort(input_array_1);
        PlotFileOutput plotFileOutput2 = new PlotFileOutput(input_array_1, distributionFunction.getArray_of_values_of_distribution_function());
        plotFileOutput2.create_plot_file("plot2.txt", " ");
    }
}
