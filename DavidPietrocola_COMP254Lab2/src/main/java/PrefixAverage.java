/*
 * Copyright 2014, Michael T. Goodrich, Roberto Tamassia, Michael H. Goldwasser
 *
 * Developed for use with the book:
 *
 *    Data Structures and Algorithms in Java, Sixth Edition
 *    Michael T. Goodrich, Roberto Tamassia, and Michael H. Goldwasser
 *    John Wiley & Sons, 2014
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * Demonstration of algorithms for computing the prefix averages of an array.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 */

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

//Perform an experimental analysis of the two algorithms prefixAverage1 and prefixAverage2,
// from lesson examples. Optionally, visualize their running times as a function of the input size with a log-log chart.
// Use either Java or Python graphical capabilities for visualization.
//Hint: Choose representative values of the input size n, similar to StringExperiment.java from class examples.

class PrefixAverage {

    /**
     * Returns an array a such that, for all j, a[j] equals
     * the average of x[0], ..., x[j].
     * A[j] = (X[0] + X[1] + � + X[j])/(j+1)
     ******************************************************/
    // inner loop size will be 1, 2, 3, ..., n  (based on j=0,1,2,...,n-1)
    // we know that 1+2+3+...+ n-1+n = n(n+1)/2
    // so, the running time os O(n^2)
    public static double[] prefixAverage1(double[] x) {
        int n = x.length;
        double[] a = new double[n];    // filled with zeros by default
        for (int j = 0; j < n; j++) {
            double total = 0;            // begin computing x[0] + ... + x[j]
            for (int i = 0; i <= j; i++)
                total += x[i];
            a[j] = total / (j + 1);        // record the average
        }
        return a;
    }

    /**
     * Returns an array a such that, for all j, a[j] equals the average of x[0], ..., x[j].
     */
    // the running time is O(n)
    public static double[] prefixAverage2(double[] x) {
        int n = x.length;
        double[] a = new double[n];    // filled with zeros by default
        double total = 0;              // compute prefix sum as x[0] + x[1] + ...
        for (int j = 0; j < n; j++) {
            total += x[j];               // update prefix sum to include x[j]
            a[j] = total / (j + 1);        // compute average based on current sum
        }
        return a;
    }

    public static void main(String[] args) {
        double[] x = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        // We analyze the running time of the first algorithm
        long startTime = System.nanoTime();
        double[] a = prefixAverage1(x);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("prefixAverage1 took " + duration + " nanoseconds");
        // We analyze the running time of the second algorithm
        startTime = System.nanoTime();
        double[] b = prefixAverage2(x);
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.println("prefixAverage2 took " + duration + " nanoseconds");

        for (int i = 0; i < x.length; i++)
            System.out.println(a[i] + " " + b[i]);

        //========OPTIONAL=========
        //!README: JakeyWakey#1569 on Discord helped me with this part and the installation of JFreeChart and Maven
        // Define the input sizes (n) to test
        int[] inputSizes = {100, 200, 300, 400, 500, 600, 700, 800, 900, 1000};
        // Define the number of times to run each algorithm for each input size
        int numberOfRuns = 10;
        // Define the XYSeries for each algorithm
        XYSeries series1 = new XYSeries("prefixAverage1");
        XYSeries series2 = new XYSeries("prefixAverage2");
        // Run the algorithms for each input size
        for (int n : inputSizes) {
            // Create the input array
            double[] input = new double[n];
            // Fill the input array with random numbers
            for (int i = 0; i < n; i++) {
                input[i] = Math.random();
            }
            // Run the algorithms and compute the average running time
            long totalTime1 = 0;
            long totalTime2 = 0;
            for (int i = 0; i < numberOfRuns; i++) {
                startTime = System.nanoTime();
                prefixAverage1(input);
                endTime = System.nanoTime();
                totalTime1 += (endTime - startTime);
                startTime = System.nanoTime();
                prefixAverage2(input);
                endTime = System.nanoTime();
                totalTime2 += (endTime - startTime);
            }
            long averageTime1 = totalTime1 / numberOfRuns;
            long averageTime2 = totalTime2 / numberOfRuns;
            // Add the data to the series
            series1.add(Math.log10(n), Math.log10(averageTime1));
            series2.add(Math.log10(n), Math.log10(averageTime2));
        }
        // Create the chart
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Prefix Average Running Times",
                "Input Size",
                "Running Time (nanoseconds)",
                dataset);
        // Display the chart
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 500));
        javax.swing.JFrame frame = new javax.swing.JFrame("Prefix Average Running Times");
        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }

}
