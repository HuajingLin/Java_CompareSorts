/*
 * For CSCI 112 - week 6 Assignment
 * Unit 6 Programming Assignment - Benchmarking Sorting Algorithms.
 * Student: Huajing Lin
 * Date: 2/25/2017
 */
package comparesortsproject;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class CompareSortsProject extends JFrame implements ActionListener, Runnable {

    private final JButton btnStartSort;     //start button
    private final JButton btnStopSort;     //stop button
    private final JLabel labelInfo;
    private final XYSeriesCollection dataset;
    private final XYSeries[] serieSorts;
    
    CompareSorts compareSorts;
    private boolean bStopSort;

    int xPoint=0;
    int yPoint=0;
    
    public CompareSortsProject() {
        // set the title, size, location and exit behavior for the main frame
        this.setTitle("Week 6 - Compare Sorts - Exercises 2");
        this.setSize(800, 600);
        this.setLocation(100, 50);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //GridBagLayout
        Container pane = getContentPane();
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        //creat the button to start sort
        btnStartSort = new JButton("Start Sort");
        btnStartSort.addActionListener(this);  //button add action listerner
        btnStartSort.setMnemonic(1);           //set action identification as 1
        c.insets = new Insets(5, 5, 0, 0);     //top and left padding
        c.weightx = 0.3;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 0;
        pane.add(btnStartSort, c);
        
        //creat the button to stop sort
        btnStopSort = new JButton("Stop Sort");
        btnStopSort.addActionListener(this);  //button add action listerner
        btnStopSort.setMnemonic(2);           //set action identification as 2
        c.weightx = 0.3;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 0;
        pane.add(btnStopSort, c);
        
        //creat the label for showing information
        labelInfo = new JLabel("Click start button to start.");
        labelInfo.setForeground(Color.red);
        c.weightx = 0.3;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 1;
        pane.add(labelInfo, c);

        JFreeChart lineChart = ChartFactory.createXYLineChart(
                "Compare Sorts", "Data Size (Million)", "Sort time(Second)", null);
        XYPlot plot = lineChart.getXYPlot();
        ChartPanel chartPanel = new ChartPanel(lineChart);
               
        c.weightx = 0.0;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 2;
        c.ipady = 500;
        pane.add(chartPanel, c);
        
        compareSorts = new CompareSorts();
        
        dataset = new XYSeriesCollection();
        serieSorts = new XYSeries[5];
        serieSorts[0] = new XYSeries("Bubble");
        serieSorts[1] = new XYSeries("Selection");
        serieSorts[2] = new XYSeries("Insertion");
        serieSorts[3] = new XYSeries("Merge");
        serieSorts[4] = new XYSeries("Quick");
        plot.setDataset(dataset);
        
        bStopSort = true;
    }
        
    //respond buttons' events
    @Override
    public void actionPerformed(ActionEvent e) {
        //get event source and change to button
        JButton source = (JButton) e.getSource();

        //get action identification of button
        int btnIndex = source.getMnemonic();
        switch (btnIndex) {
            case 1: startSort();
            break;
            case 2: stopSort();
            break;
            default:
                break;
        }// end switch
    }// end actionPerformed

    //Thread run method
    public void run() {
        dataset.removeAllSeries();
        for(int i = 0; i < 5; i++)
            dataset.addSeries(serieSorts[i]);
        
        int times = 100;
        double timeSort;   //second
        
        for (int i = 5; i > 0; i--) {
        
            if(bStopSort)
                break;
            switch(i){
                case 1: labelInfo.setText("doing Bubble sort...");
                    break;
                case 2: labelInfo.setText("doing Selection sort...");
                    break;
                case 3: labelInfo.setText("doing Insertion sort...");
                    break;
                case 4: labelInfo.setText("doing Merge sort...");
                    break;
                case 5: labelInfo.setText("doing Quick sort...");
                    break;
                default: break;
            }
            //1
            timeSort = compareSorts.RunSortTimes(i, 10000, times);
            if(timeSort > 0){
                serieSorts[i-1].add((double)1.0, timeSort);
                //System.out.printf("%d Point:1.0, %12.8f\n",i,timeSort);
            }
            else
                continue;
            //2
            timeSort = compareSorts.RunSortTimes(i, 20000, times);
            if(timeSort > 0){
                serieSorts[i-1].add((double)2.0, timeSort);
                //System.out.printf("%d Point:2.0, %12.8f\n",i,timeSort);
            }
            else
                continue;
            //3
            timeSort = compareSorts.RunSortTimes(i, 100000, times);
            if(timeSort > 0){
                serieSorts[i-1].add(10.0, timeSort);
                //System.out.printf("%d Point:10.0, %12.8f\n",i,timeSort);
            }
            else
                continue;
            //4.
            timeSort = compareSorts.RunSortTimes(i, 200000, times);
            if(timeSort > 0){
                serieSorts[i-1].add(20.0, timeSort);
                //System.out.printf("%d Point:20.0, %12.8f\n",i,timeSort);
            }
            else
                continue;
            //5
            timeSort = compareSorts.RunSortTimes(i, 1000000, times);
            if(timeSort > 0){
                serieSorts[i-1].add(100.0, timeSort);
                //System.out.printf("%d Point:100.0, %12.8f\n",i,timeSort);
            }
            else
                continue;
            //6
            timeSort = compareSorts.RunSortTimes(i, 2000000, times);
            if(timeSort > 0){
                serieSorts[i-1].add(200.0, timeSort);
                //System.out.printf("%d Point:200.0, %12.8f\n",i,timeSort);
            }
            else
                continue;
        }
        labelInfo.setText("sort done.");
        System.out.println("=== sort done.===");
    }// end run()
    
    private void startSort(){
        
        //final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        //renderer.setSeriesLinesVisible(0, false);
        //renderer.setSeriesShapesVisible(1, false);
        //plot.setRenderer(renderer);
        //create a thread to count number
        bStopSort = false;/*
        xPoint += 2;
        if(yPoint==2)
            yPoint = 6;
        yPoint += 1;
        
        serieSorts[1].add(xPoint, yPoint);*/
        Thread drawThread = new Thread(this);
        drawThread.setPriority(9);
        drawThread.start();
    }
    private void stopSort(){
        bStopSort = true;
    }
    public static void main(String[] args) {
        CompareSortsProject mainJFrame = new CompareSortsProject();
        mainJFrame.setVisible(true);        
    }

}
