import java.util.*;

public class DistributionFunction {
    private Double[] array_of_input_numbers;
    private Map<Double, Integer> map_of_number_repetition;
    private Double[] array_of_unique_sorted_input_numbers;
    private Double[] array_of_values_of_distribution_function; // sorted
    private int length_of_array_of_values_of_distribution_function;

    public DistributionFunction(Double[] array_of_input_numbers) {
        init_array_of_input_numbers(array_of_input_numbers);
        init_array_of_unique_sorted_input_numbers();
        init_map_of_number_repetition();
        init_array_of_values_of_distribution_function();
        this.length_of_array_of_values_of_distribution_function = array_of_values_of_distribution_function.length;
    }

    private void init_array_of_input_numbers(Double[] array_of_input_numbers) {
        this.array_of_input_numbers = new Double[array_of_input_numbers.length];
        System.arraycopy(array_of_input_numbers, 0, this.array_of_input_numbers, 0, array_of_input_numbers.length);
    }

    private void init_array_of_unique_sorted_input_numbers() {
        Set<Double> temp_set_of_input_numbers = new HashSet<>(Arrays.asList(array_of_input_numbers));
        this.array_of_unique_sorted_input_numbers = new Double[temp_set_of_input_numbers.size()];
        temp_set_of_input_numbers.toArray(this.array_of_unique_sorted_input_numbers);
        Arrays.sort(this.array_of_unique_sorted_input_numbers);
    }

    private void init_map_of_number_repetition() {
        this.map_of_number_repetition = new HashMap<>();
        for (int i = 0; i < this.array_of_input_numbers.length; i++) {
            if (this.map_of_number_repetition.get(this.array_of_input_numbers[i]) == null) {
                this.map_of_number_repetition.put(this.array_of_input_numbers[i], 1);
            } else {
                this.map_of_number_repetition.put(this.array_of_input_numbers[i], this.map_of_number_repetition.get(this.array_of_input_numbers[i]) + 1);
            }
        }
    }

    private void init_array_of_values_of_distribution_function() {
        double input_length = this.array_of_input_numbers.length;
        this.array_of_values_of_distribution_function = new Double[array_of_unique_sorted_input_numbers.length];
        this.array_of_values_of_distribution_function[0] = Double.valueOf(map_of_number_repetition.get(array_of_unique_sorted_input_numbers[0]) / input_length);
        for (int i = 1; i < array_of_unique_sorted_input_numbers.length; i++) {
            this.array_of_values_of_distribution_function[i] =
                    Double.valueOf((map_of_number_repetition.get(array_of_unique_sorted_input_numbers[i]) / input_length) +
                            this.array_of_values_of_distribution_function[i - 1]);
        }
    }

    private int find_left_bound_index(Double x) {
        int left = 0;
        int right = this.array_of_unique_sorted_input_numbers.length - 1;
        int middle = (left + right) / 2;
        if (x.compareTo(this.array_of_unique_sorted_input_numbers[this.array_of_unique_sorted_input_numbers.length - 1]) == 0) {
            return right;
        }
        boolean found = false;
        while (!found && right >= left) {
            middle = (left + right) / 2;
            if (this.array_of_unique_sorted_input_numbers[middle].compareTo(x) <= 0 && this.array_of_unique_sorted_input_numbers[middle + 1].compareTo(x) > 0) {
                found = true;
            } else if (this.array_of_unique_sorted_input_numbers[middle].compareTo(x) > 0) {
                right = middle - 1;
            } else if (this.array_of_unique_sorted_input_numbers[middle + 1].compareTo(x) <= 0) {
                left = middle + 1;
            }
        }
        return middle;
    }

    public Double[] get_array_of_values_of_distribution_function() {
        return array_of_values_of_distribution_function;
    }

    public Double distribution_function(Double x) {
        if (x.compareTo(this.array_of_unique_sorted_input_numbers[0]) < 0) {
            return 0.0;
        }
        if (x.compareTo(this.array_of_unique_sorted_input_numbers[this.array_of_unique_sorted_input_numbers.length - 1]) > 0) {
            return 1.0;
        }
        return array_of_values_of_distribution_function[find_left_bound_index(x)];
    }

    public int get_length_of_array_of_values_of_distribution_function() {
        return length_of_array_of_values_of_distribution_function;
    }
}
