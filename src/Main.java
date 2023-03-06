public class Main {
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
