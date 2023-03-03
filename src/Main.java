import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

    public static double[] distribution_func(double[] array, int n) {
        double[] temp_array = new double[n];
        for (int i = 0; i < n; i++) {
            temp_array[i] = array[i];
        }
        Arrays.sort(temp_array);
        //Arrays.stream(temp_array).forEach(System.out::print);
        String output = temp_array[0] + ";";
        for (int j = 1; j < n; j++) {
            output += temp_array[j] + ";";
        }
        // System.out.println(output);
        return temp_array;
    }

    public static void main(String[] args) {
        int n = 5;
        int min = -10;
        int max = 10;
        double[] input_array = new double[n];
        input_array = new double[]{4.0, 4.0, 1.0, 2.0, 3.0};
        //init_input_array(input_array, n, min, max);
        distribution_func(input_array, n);
        Arrays.stream(get_probability_array(input_array, n)).forEach(System.out::println);
    }
}
