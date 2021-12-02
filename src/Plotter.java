package unisa.group1.test_scalc;
import ChartDirector.Chart;
import ChartDirector.ChartViewer;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.WindowConstants;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.util.ShapeUtilities;
import unisa.group1.test_scalc.Calculator.OPT_Complex;
import ChartDirector.ThreeDScatterChart;
import ChartDirector.ThreeDScatterGroup;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/**
 * Basic plotter for 2-D/3-D complex numbers.
 * 
 * @author Group 1
 */
public class Plotter {
    
    
    /** A collection of numbers. */
    private XYSeriesCollection result;
    /** A collection of numbers. */
    private ChartViewer viewer;

    
    /**
     * Constructor method.
     * 
     * @param l the list of numbers.
     */
    public Plotter(ArrayList<OPT_Complex> l) {
        result = new XYSeriesCollection();
        XYSeries series = new XYSeries("Complex values");
        for (OPT_Complex i : l)
            series.add(i.getReal(), i.getImaginary());
        result.addSeries(series);
    }
    
    /**
     * Constructs 3D graph.
     * 
     * @param l the array of elements.
     */
    public void setup3D(ArrayList<OPT_Complex> l) {
        viewer = new ChartViewer();
        double[] lr = new double[l.size()];
        double[] li = new double[l.size()];
        int j = 0;
        for (OPT_Complex i : l) {
            lr[j] = i.getReal();
            li[j] = i.getImaginary();
            ++j;
        }
        double[] lz = new double[l.size()];
        for (int i = 0; i < l.size(); i++)
            lz[i] = 0.0;
        ThreeDScatterChart c = new ThreeDScatterChart(720, 600);
        c.addTitle("Calculator complex", "Arial Bold", 20);
        c.xAxis().setTitle("Real", "Arial Bold", 10);
        c.yAxis().setTitle("Imaginary", "Arial Bold", 10);
        c.setViewAngle(15, 30);
        ThreeDScatterGroup g = c.addScatterGroup(lr, li, lz, "", Chart.GlassSphere2Shape, 13, Chart.SameAsMainColor);
        g.setDropLine(0x888888);
        viewer.setChart(c);
        //viewer.setImageMap(c.getHTMLImageMap("clickable", "", "title='({x|p}, y={y|p}, z={z|p})'"));
        JFrame frame = new JFrame();
        frame.setResizable(false);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {frame.dispose();}
        });
        frame.getContentPane().add(viewer);
        frame.pack();
        frame.setVisible(true);
    }
    
    /**
     * Plot the scatter graph.
     * 
     */
    public void plot() {
        JFreeChart chart = ChartFactory.createScatterPlot(
            "Scatter Plot", // chart title
            "Real", // x axis label
            "Imaginary", // y axis label
            result, // data  ***-----PROBLEM------***
            PlotOrientation.VERTICAL,
            true, // include legend
            true, // tooltips
            false // urls
            );
        chart.getXYPlot().setDomainCrosshairVisible(true);
        chart.getXYPlot().setRangeCrosshairVisible(true);
        chart.getXYPlot().getDomainAxis().setTickMarkInsideLength(2);
        chart.getXYPlot().getRangeAxis().setTickMarkInsideLength(2);
        chart.getXYPlot().getDomainAxis().setTickMarkOutsideLength(2);
        chart.getXYPlot().getRangeAxis().setTickMarkOutsideLength(2);
        chart.getXYPlot().getDomainAxis().setTickMarkPaint(new Color(0, 255, 60, 255));
        chart.getXYPlot().getRangeAxis().setTickMarkPaint(new Color(0, 255, 60, 255));
        chart.getXYPlot().getDomainAxis().setAxisLinePaint(new Color(0, 255, 0, 255));
        chart.getXYPlot().getRangeAxis().setAxisLinePaint(new Color(0, 255, 0, 255));
        chart.getXYPlot().getRenderer().setSeriesShape(0, ShapeUtilities.createDiagonalCross(3, 1));
        ChartFrame frame = new ChartFrame("Calculator complex", chart);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    
}
