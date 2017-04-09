/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package holecym.gui;

import com.github.lgooddatepicker.components.DateTimePicker;
import holecym.api.AppliancesApi;
import holecym.api.EnergyMonitorApi;
import holecym.api.impl.ConsuptionApplianceApiImpl;
import holecym.api.impl.EnergyMonitorApiImpl;
import holecym.model.Appliance;
import holecym.model.Consumption;
import holecym.model.ConsumptionModel;
import holecym.model.Production;
import holecym.model.ProductionModel;
import java.awt.FlowLayout;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

/**
 *
 * @author Michal
 */
public class GuiForm extends javax.swing.JFrame {

    private static final String[] STATS_COLUMNS = new String[]{"Spotrebič", " Celková spotreba", "Vyrobených kusov"};
    private final DateTimePicker dateTimeTo;
    private final DateTimePicker dateTimeFrom;
    private List<Appliance> spotrebice;
    private Production production;
    private Consumption consumption;

    /**
     * Creates new form GuiForm
     */
    public GuiForm() {
        initComponents();
        dateTimeFrom = new DateTimePicker();
        dateTimeTo = new DateTimePicker();

        jPanelDateFromTimePicker.add(dateTimeFrom);
        jPanelDateFromTimePicker.repaint();
        jPanelDateFromTimePicker.validate();

        jPanelDateToTimePicker.add(dateTimeTo);
        jPanelDateToTimePicker.repaint();
        jPanelDateToTimePicker.validate();
    }

    private void fetchAppliances() {
        AppliancesApi appliances = new ConsuptionApplianceApiImpl();
        if (spotrebice == null) {
            spotrebice = appliances.getAppliances();
        }
    }

    private ChartPanel getChartPanel(JFreeChart lineChart) {
        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setVisible(true);
        return chartPanel;
    }

    private XYDataset createConsumptionDataset() {
        TimeSeriesCollection dataset = new TimeSeriesCollection();

        Appliance[] appliances = getAppliances();

        consumption = getConsumption(appliances);

        final Map<Appliance, TreeSet<ConsumptionModel>> consumptionData = consumption.getData();
        consumptionData.forEach((Appliance key, TreeSet<ConsumptionModel> consumptionModels) -> {
            TimeSeries timeSeries = new TimeSeries(key.toString());
            consumptionModels.forEach((ConsumptionModel model) -> timeSeries.add(new Millisecond(model.getDatetime()),
                    model.getUsage()));
            dataset.addSeries(timeSeries);
        });
        return dataset;
    }

    private Appliance[] getAppliances() {
        final int[] selectedIndices = jListSpotrebice.getSelectedIndices();
        Appliance[] appliances = new Appliance[selectedIndices.length];

        for (int i = 0; i < selectedIndices.length; i++) {
            appliances[i] = spotrebice.get(selectedIndices[i]);
        }
        return appliances;
    }

    private Consumption getConsumption(Appliance[] appliances) {
        EnergyMonitorApi operations = new EnergyMonitorApiImpl();
        LocalDateTime dateTimeFromVar = dateTimeFrom.getDateTimeStrict();
        LocalDateTime dateTimeToVar = dateTimeTo.getDateTimeStrict();
        return operations.getConsumptionData(
                dateTimeFromVar,
                dateTimeToVar,
                appliances);
    }

    private XYDataset createProductionDataset() {
        TimeSeriesCollection dataset = new TimeSeriesCollection();

        LocalDateTime dateTimeFromVar = dateTimeFrom.getDateTimeStrict();
        LocalDateTime dateTimeToVar = dateTimeTo.getDateTimeStrict();

        Appliance[] appliances = getAppliances();

        production = getProduction(appliances);

        final Map<Appliance, TreeSet<ProductionModel>> productionData = production.getData();
        productionData.forEach((Appliance key, TreeSet<ProductionModel> productionModels) -> {
            TimeSeries timeSeries = new TimeSeries(key.toString());
            productionModels.forEach((ProductionModel model) -> timeSeries.add(new Millisecond(model.getDate()),
                    model.getUnitsAssembled()));
            dataset.addSeries(timeSeries);
        });
        return dataset;
    }

    private Production getProduction(Appliance[] appliances) {
        EnergyMonitorApi operations = new EnergyMonitorApiImpl();
        LocalDateTime dateTimeFromVar = dateTimeFrom.getDateTimeStrict();
        LocalDateTime dateTimeToVar = dateTimeTo.getDateTimeStrict();
        return operations.getProducedItemsCountData(
                dateTimeFromVar,
                dateTimeToVar,
                appliances);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonSpocitaj = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListSpotrebice = new javax.swing.JList();
        tabbedPane = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanelDateFromTimePicker = new javax.swing.JPanel();
        jPanelDateToTimePicker = new javax.swing.JPanel();
        jLabelSpotrebic = new javax.swing.JLabel();
        jLabelObdobieOd = new javax.swing.JLabel();
        jLabelObdobieDo = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPanelDetail = new javax.swing.JTextPane();
        jLabelDetail = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButtonSpocitaj.setText("Vykresli graf");
        jButtonSpocitaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSpocitajActionPerformed(evt);
            }
        });

        jListSpotrebice.setModel(new javax.swing.AbstractListModel() {

            @Override
            public int getSize() {
                if (spotrebice == null){
                    fetchAppliances();
                }
                return spotrebice.size();
            }

            @Override
            public Object getElementAt(int i) {
                return spotrebice.get(i);
            }
        });
        jListSpotrebice.setMinimumSize(new java.awt.Dimension(200, 0));
        jListSpotrebice.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListSpotrebiceValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jListSpotrebice);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setMinimumSize(new java.awt.Dimension(650, 480));
        jPanel1.setName(""); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(650, 480));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 716, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        tabbedPane.addTab("Spotreba", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 720, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 482, Short.MAX_VALUE)
        );

        tabbedPane.addTab("Výroba", jPanel2);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Spotrebič", "Celková spotreba", "Vyrobených kusov"
            }
        ));
        jScrollPane3.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 248, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 55, Short.MAX_VALUE))
        );

        tabbedPane.addTab("Štatistika", jPanel3);

        jPanelDateFromTimePicker.setLayout(new java.awt.BorderLayout());

        jPanelDateToTimePicker.setLayout(new java.awt.BorderLayout());

        jLabelSpotrebic.setText("Spotrebič(e):");

        jLabelObdobieOd.setText("Obdobie od:");

        jLabelObdobieDo.setText("Obdobie do:");

        jTextPanelDetail.setEditable(false);
        jTextPanelDetail.setContentType(" text/html"); // NOI18N
        jScrollPane2.setViewportView(jTextPanelDetail);

        jLabelDetail.setText("Detail spotrebiča:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelSpotrebic, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelDetail))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabbedPane)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabelObdobieOd, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addComponent(jPanelDateFromTimePicker, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanelDateToTimePicker, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelObdobieDo, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE))
                        .addGap(26, 26, 26)
                        .addComponent(jButtonSpocitaj, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSpotrebic)
                    .addComponent(jLabelObdobieOd)
                    .addComponent(jLabelObdobieDo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanelDateToTimePicker, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanelDateFromTimePicker, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonSpocitaj, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelDetail)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSpocitajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSpocitajActionPerformed
        createConsumptionGraph();
        createProductionGraph();
        createStatistics();
    }//GEN-LAST:event_jButtonSpocitajActionPerformed

    private void createStatistics() {
        Appliance[] appliances = getAppliances();

        if (consumption == null) {
            consumption = getConsumption(appliances);
        }

        if (production == null) {
            production = getProduction(appliances);
        }

        final String[][] table = new String[appliances.length][3];

        for (int i = 0; i < appliances.length; i++) {
            Appliance appliance = appliances[i];

            table[i][0] = appliance.toString();
            table[i][1] = getTotalConsumtion(appliance, consumption);
            table[i][2] = getTotalProduction(appliance, production);
        }

        TableModel model = new DefaultTableModel(table, STATS_COLUMNS);
        jTable1.setModel(model);
    }

    private String getTotalProduction(Appliance appliance, Production production) {
        Map<String, Integer> totalProducedItems = production.getTotalProducedItems();
        if (totalProducedItems != null && !totalProducedItems.isEmpty()) {
            int result = totalProducedItems.get(appliance.toString());
            return String.valueOf(result);
        } else {
            return "Výsledok neexistuje";
        }
    }

    private String getTotalConsumtion(Appliance appliance, Consumption consumption) {
        Map<String, Double> usage = consumption.getTotalUsage();
        if (usage != null && !usage.isEmpty()) {
            double result = usage.get(appliance.toString());
            return String.valueOf(result);
        } else {
            return "Výsledok neexistuje";
        }
    }

    private void createConsumptionGraph() {
        JFreeChart lineChart = ChartFactory.createTimeSeriesChart(
                "Graf spotreby",
                "Čas",
                "Spotreba",
                createConsumptionDataset()
        );

        ChartPanel chartPanel = getChartPanel(lineChart);
        jPanel1.removeAll();
        jPanel1.setLayout(new FlowLayout(FlowLayout.CENTER));
        jPanel1.add(chartPanel);
        jPanel1.repaint();
        jPanel1.validate();
    }

    private void createProductionGraph() {
        JFreeChart lineChart = ChartFactory.createTimeSeriesChart(
                "Graf výroby",
                "Čas",
                "Počet kusov",
                createProductionDataset()
        );

        ChartPanel chartPanel = getChartPanel(lineChart);
        jPanel2.removeAll();
        jPanel2.setLayout(new FlowLayout(FlowLayout.CENTER));
        jPanel2.add(chartPanel);
        jPanel2.repaint();
        jPanel2.validate();
    }

    private void jListSpotrebiceValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListSpotrebiceValueChanged
        final int selectedIndice = jListSpotrebice.getAnchorSelectionIndex();
        Appliance appliance = spotrebice.get(selectedIndice);

        jTextPanelDetail.setText("CC: " + appliance.getCc() + "\n\n"
                + "Path: " + appliance.getPath() + "\n\n"
        );
    }//GEN-LAST:event_jListSpotrebiceValueChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSpocitaj;
    private javax.swing.JLabel jLabelDetail;
    private javax.swing.JLabel jLabelObdobieDo;
    private javax.swing.JLabel jLabelObdobieOd;
    private javax.swing.JLabel jLabelSpotrebic;
    private javax.swing.JList jListSpotrebice;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanelDateFromTimePicker;
    private javax.swing.JPanel jPanelDateToTimePicker;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextPane jTextPanelDetail;
    private javax.swing.JTabbedPane tabbedPane;
    // End of variables declaration//GEN-END:variables
}
