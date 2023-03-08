import java.io.FileWriter;
import java.io.IOException;

public class PlotFileOutput {
    private Double[] x;
    private Double[] y;

    public PlotFileOutput(Double[] x, Double[] y) {
        this.x = new Double[x.length];
        System.arraycopy(x, 0, this.x, 0, x.length);
        this.y = new Double[y.length];
        System.arraycopy(y, 0, this.y, 0, y.length);    }

    public void create_plot_file(String filename, String separator) {
        if (x.length != y.length) {
            System.out.println("error: x.length != y.length");
            return;
        }
        try (FileWriter writer = new FileWriter(filename, false)) {
            writer.write(x[0] + separator + y[0]);
            for (int i = 0; i < x.length; i++) {
                writer.append('\n');
                writer.append(x[i] + separator + y[i]);
            }
            writer.append('E');
            writer.flush();
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }
}
