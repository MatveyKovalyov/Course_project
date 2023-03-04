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

    public static double[] sorted_unique_array(double[] input_array){
        Set<Double> input_set = new HashSet<>();
        for (int i = 0; i < input_array.length; i++) {
            input_set.add(Double.valueOf(input_array[i]));
        }
        Double[] sorted_input_unique_array = input_set.toArray(new Double[0]);
        Arrays.sort(sorted_input_unique_array);
        double[] result = new double[sorted_input_unique_array.length];
        for(int i=0;i<sorted_input_unique_array.length;i++){
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

    public static double distribution_function(double x, double[] sorted_input_unique_array, double[] values_of_distribution_func) {
        if(x < sorted_input_unique_array[0]){
            return 0.0;
        }
        if(x > sorted_input_unique_array[sorted_input_unique_array.length - 1]){
            return 1.0;
        }
        int left_bound = 0;
        int right_bound = sorted_input_unique_array.length - 1;
        int position = sorted_input_unique_array.length / 2;
        do {
            if (x == sorted_input_unique_array[position]) {
                return values_of_distribution_func[position];
            }
            if (x < sorted_input_unique_array[position]) {
                right_bound = position;
                position = (int) Math.floor((right_bound + left_bound) / 2.0);
            }
            if (x > sorted_input_unique_array[position]) {
                left_bound = position;
                position = (int) Math.ceil((right_bound + left_bound) / 2.0);
            }
        } while (!(x > sorted_input_unique_array[position] && x < sorted_input_unique_array[position + 1]));
        return values_of_distribution_func[position];
    }

    public static void main(String[] args) {
        int n = 5;
        int min = -10;
        int max = 10;
        double[] input_array = new double[n];
        input_array = new double[]{4.0, 4.0, 1.0, 2.0, 3.0};
        //init_input_array(input_array, n, min, max);
        // distribution_func(input_array, n);
        //Arrays.stream(get_probability_array(input_array, n)).forEach(System.out::println);
        // Arrays.stream(distribution_func(input_array, n)).forEach(System.out::println);
        //Arrays.stream(distribution_func_array(input_array, number_distribution_map(input_array, n))).forEach(System.out::println);
       // Arrays.stream(distribution_func_array(input_array, number_distribution_map(input_array, n))).forEach(System.out::println);
        double x = 3.99;
        System.out.print(distribution_function(x, sorted_unique_array(input_array),distribution_func_array(input_array, number_distribution_map(input_array, n))));
    }
}
