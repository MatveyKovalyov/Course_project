import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class Main {
    public static void init_input_array(double[] input_array, int n, int min, int max) {
        for (int i = 0; i < n; i++) {
            input_array[i] = (Math.random() * ((max - min) + 1)) + min;
        }
    }

    public static double[] get_probability_array(double[] input_array, int n) {
        Map<Double, Integer> number_repeat_map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (number_repeat_map.get(input_array[i]) == null) {
                number_repeat_map.put(input_array[i], 1);
            } else {
                number_repeat_map.put(input_array[i], number_repeat_map.get(input_array[i]) + 1);
            }
        }
        double[] probability_array = new double[n];
        for (int j = 0; j < n; j++) {
            probability_array[j] = (double) number_repeat_map.get(input_array[j]) / n;
        }
        return probability_array;
    }

    public static Map<Double, Double> number_distribution_map(double[] input_array, int n) {
        double[] result_array = new double[n];
        double[] probability_array = get_probability_array(input_array, n);
        Map<Double, Double> number_probability_map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            number_probability_map.put(input_array[i], probability_array[i]);
        }
        return number_probability_map;
    }

    public static double[] sorted_unique_array(double[] input_array) {
        Set<Double> input_set = new HashSet<>();
        for (int i = 0; i < input_array.length; i++) {
            input_set.add(Double.valueOf(input_array[i]));
        }
        Double[] sorted_input_unique_array = input_set.toArray(new Double[0]);
        Arrays.sort(sorted_input_unique_array);
        double[] result = new double[sorted_input_unique_array.length];
        for (int i = 0; i < sorted_input_unique_array.length; i++) {
            result[i] = sorted_input_unique_array[i];
        }
        return result;
    }

    public static double[] distribution_func_array(double[] input_array, Map<Double, Double> number_probability_map) {
        Set<Double> input_set = new HashSet<>();
        for (int i = 0; i < input_array.length; i++) {
            input_set.add(Double.valueOf(input_array[i]));
        }
        Double[] sorted_input_unique_array = input_set.toArray(new Double[0]);
        Arrays.sort(sorted_input_unique_array);
        double[] values_of_distribution_func = new double[sorted_input_unique_array.length];
        values_of_distribution_func[0] = number_probability_map.get(sorted_input_unique_array[0]);
        for (int j = 1; j < sorted_input_unique_array.length; j++) {
            values_of_distribution_func[j] = number_probability_map.get(sorted_input_unique_array[j]) + values_of_distribution_func[j - 1];
        }
        return values_of_distribution_func;
    }

    public static int find_left_bound_index(double x, double[] sorted_input_unique_array) {
        int left = 0;
        int right = sorted_input_unique_array.length - 1;
        int middle = (left + right) / 2;
        if (x == sorted_input_unique_array[sorted_input_unique_array.length - 1]) {
            return right;
        }
        boolean found = false;
        while (!found && right >= left) {
            middle = (left + right) / 2;
            if (sorted_input_unique_array[middle] <= x && sorted_input_unique_array[middle + 1] > x) {
                found = true;
            } else if (sorted_input_unique_array[middle] > x) {
                right = middle - 1;
            } else if (sorted_input_unique_array[middle + 1] <= x) {
                left = middle + 1;
            }
        }
        return middle;
    }

    public static double distribution_function(double x, double[] sorted_input_unique_array, double[] values_of_distribution_func) {
        if (x < sorted_input_unique_array[0]) {
            return 0.0;
        }
        if (x > sorted_input_unique_array[sorted_input_unique_array.length - 1]) {
            return 1.0;
        }
        return values_of_distribution_func[find_left_bound_index(x, sorted_input_unique_array)];
    }

    public static void main(String[] args) {
       /* int n = 5;
        int min = -10;
        int max = 10;
        double[] input_array = new double[n];
        input_array = new double[]{4.0, 4.0, 1.0, 2.0, 3.0};
        double x = 4.1;
    */
        Double[] input_array = new Double[]{4.0, 4.0, 1.0, 2.0, 3.0};
        DistributionFunction distributionFunction = new DistributionFunction(input_array);
        for (double i = 0.0; i <= 5; i += 0.5) {
            System.out.println(i + " : " + distributionFunction.distribution_function(i));
        }
        System.out.println(distributionFunction.distribution_function(3.99));
    }

}
