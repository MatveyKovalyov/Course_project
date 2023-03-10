import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class PlotFileOutputForDistributionFunction {
    //private Double[] random_variable;
    private DistributionFunction distributionFunction;
    private Double[] array_of_values_of_distribution_function;
    private Double[] array_of_unique_sorted_input_numbers;


    public PlotFileOutputForDistributionFunction(Double[] random_variable) {
      //  this.random_variable = new Double[random_variable.length];
        //Arrays.sort(random_variable);
        //System.arraycopy(random_variable, 0, this.random_variable, 0, random_variable.length);
        this.distributionFunction = new DistributionFunction(random_variable);
        this.array_of_values_of_distribution_function = new Double[distributionFunction.get_length_of_array_of_values_of_distribution_function()];
        System.arraycopy(distributionFunction.get_array_of_values_of_distribution_function(), 0,
                this.array_of_values_of_distribution_function, 0,
                distributionFunction.get_length_of_array_of_values_of_distribution_function());
        this.array_of_unique_sorted_input_numbers = new Double[distributionFunction.get_length_of_array_of_values_of_distribution_function()];
        System.arraycopy(distributionFunction.get_array_of_unique_sorted_input_numbers(), 0,
                this.array_of_unique_sorted_input_numbers, 0, distributionFunction.get_length_of_array_of_values_of_distribution_function());
    }

    public void create_plot_file(String filename, String separator) {
        try (FileWriter writer = new FileWriter(filename, false)) {
            writer.write(array_of_unique_sorted_input_numbers[0] + separator + array_of_values_of_distribution_function[0]);
            for (int i = 0; i < distributionFunction.get_length_of_array_of_values_of_distribution_function(); i++) {
                writer.append('\n');
                writer.append(array_of_unique_sorted_input_numbers[i] + separator + array_of_values_of_distribution_function[i]);
            }
            writer.append('E');
            writer.flush();
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }
}
