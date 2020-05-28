/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;

/**
 *
 * @author admin
 */
class ClassFeedback extends SideMenuBaseForm{

    private Container containerDialog = new Container(new BorderLayout());
    Container containerGraph = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    private Dialog dialogExplain = new Dialog(new BorderLayout());

    public void dialogFinal () throws IOException {

        dialogExplain.add(BorderLayout.NORTH, containerDialog);
        containerDialog.add(BorderLayout.CENTER, containerGraph);

        // doesn't influence the space around the graph
        containerGraph.getAllStyles().setPadding(0,0,0,0);

        createPieChartForm();

        dialogExplain.show();

    }


    public void createPieChartForm() {

            int porcCor = 7* 100/ 5 ;
            int porcFal = 4 *100 / 2;

            double[] values = new double[]{porcFal, porcCor};
            int[] colors = new int[]{ColorUtil.BLUE, 0x009933};

            DefaultRenderer renderer = buildCategoryRenderer(colors);
            renderer.setDisplayValues(true);
            renderer.setShowLabels(true);

            // doesn't influence the space around the graph
            renderer.setMargins(new int [] {0,0,0,0});

            SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
            r.setHighlighted(true);

            PieChart chart = new PieChart(buildCategoryDataset("Project budget", values), renderer);
            ChartComponent c = new ChartComponent(chart);

            // adding the chart to container with the BoxLayout
            containerGraph.add(c);

     }

    protected CategorySeries buildCategoryDataset(String title, double[] values) {
            CategorySeries series = new CategorySeries(title);

            String[] strings = new String[]{"Errado %", "Certo %"};

            for (int i = 0 ; i != values.length ; i++) {
                series.add(strings[i], values[i]);
            }

            return series;

    }

    private DefaultRenderer buildCategoryRenderer(int[] colors) {
            DefaultRenderer renderer = new DefaultRenderer();
            renderer.setLabelsTextSize(40);
            renderer.setShowLegend(false);

            // doesn't influence the space around the graph
            renderer.setMargins(new int[]{20, 20, 120, 20});
            for (int color : colors) {
                SimpleSeriesRenderer r = new SimpleSeriesRenderer();
                r.setColor(color);
                renderer.addSeriesRenderer(r);

            }
            return renderer;
     }

}