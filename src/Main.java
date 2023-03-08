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
        int amount = 20000;
        Double[] input_array_1 = new Double[amount];
        input_array_1 = get_random_numbers_gaussian(amount);
        BasicRandomVariable basicRandomVariable = new BasicRandomVariable(input_array_1);
        PlotFileOutputForDistributionFunction plotFileOutputForDistributionFunction =
                new PlotFileOutputForDistributionFunction(basicRandomVariable.getBSV());
        plotFileOutputForDistributionFunction.create_plot_file("plot.txt", " ");
        PlotFileOutputForDistributionFunction plotFileOutputForDistributionFunction1 =
                new PlotFileOutputForDistributionFunction(input_array_1);
        plotFileOutputForDistributionFunction1.create_plot_file("plot2.txt", " ");
    }
}
