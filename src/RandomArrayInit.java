import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class RandomArrayInit {
    private int amount;
    private Double[] random_array;

    public RandomArrayInit(int amount) {
        this.amount = amount;
        this.random_array = new Double[amount];
        File input_file = new File("/home/matvey/Projects/Course_project/Distribution_function/random/random1.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(input_file))) {
            for (int i = 0; i < amount; i++) {
                this.random_array[i] = Double.valueOf(br.readLine());
            }
        } catch (IOException exception) {
            System.out.printf(exception.getMessage());
        }
    }

    public Double[] get_random_array() {
        return random_array;
    }
}
