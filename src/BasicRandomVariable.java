public class BasicRandomVariable {
    private Double[] bsv;

    BasicRandomVariable(Double[] input_data) {
        DistributionFunction distributionFunction = new DistributionFunction(input_data);
        bsv = new Double[input_data.length];
        for (int i = 0; i < input_data.length; i++) {
            bsv[i] = Double.valueOf(distributionFunction.distribution_function(input_data[i]));
        }
    }

    public Double[] getBSV() {
        return bsv;
    }
}
