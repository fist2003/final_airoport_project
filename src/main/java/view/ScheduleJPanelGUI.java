package view;

import controller.ScheduleJPanelController;
import javafx.geometry.HorizontalDirection;
import service.ScheduleTableSearchService;
import service.ScheduleTableService;
import view.table_models.ArrivalTableModel;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;
import java.util.Date;
import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Created by ПК on 11.12.2016.
 */
public class ScheduleJPanelGUI extends EastJPanelGUI {
    public ScheduleJPanelGUI(){
        this.instScheduleTableSearchService = new ScheduleTableSearchService();
    }

    protected ScheduleTableSearchService instScheduleTableSearchService;

    protected final String arrivalsStr = "ARRIVALS";
    protected final String departuresStr = "DEPARTURES";
    private String searchStr = "SEARCH";
    private JSlider jSliderTime;
    private static boolean isArrivalsScheduleTable = true;
    public static boolean isArrivalsChoosedComboBox = true;

    protected static void setIsArrivalsScheduleTable(boolean isArrivalsScheduleTable) {ScheduleJPanelGUI.isArrivalsScheduleTable = isArrivalsScheduleTable;}
    protected static boolean isArrivalsScheduleTable() {return isArrivalsScheduleTable;}

    @Override
    public void drawJPanel() {
        super.drawJPanel();
        /*eastJPanel.setVisible(false);
        eastJPanel.removeAll();
        eastJPanel.setBackground(darkBackGround);
        eastJPanel.setPreferredSize(eastJPanelDimension);
        eastJPanel.setLayout(new BorderLayout());
        eastJPanel.add(topEastJPanel,BorderLayout.NORTH);
        eastJPanel.add(middleEastJPanel,BorderLayout.CENTER);
        eastJPanel.add(bottomEastJPanel,BorderLayout.SOUTH);*/
        drawTopEastJPanel();
        Date curDate = new Date();
        ArrivalTableModel instArrivalTableModel =
                new ArrivalTableModel(instScheduleTableSearchService.makeArrivalScheduleTable(jSliderTime.getValue(),
                        instScheduleTableSearchService.getListArrivalFlightsByDate(curDate)));
        drawTable(instArrivalTableModel);
        drawBottomEastJPanel();
        eastJPanel.setVisible(true);
    }

    public void drawTopEastJPanel(){
        ScheduleJPanelController instScheduleJPanelController = new ScheduleJPanelController();
        topEastJPanel.setVisible(false);
        topEastJPanel.removeAll();
        topEastJPanel.setPreferredSize(topEastJPanelDimension);
        topEastJPanel.setBackground(darkBackGround);
        topEastJPanel.setLayout(new BorderLayout());
        northBoardJPanel.setVisible(false);
        northBoardJPanel.removeAll();
        northBoardJPanel.setLayout(new GridLayout(1,3,10,0));
        northBoardJPanel.setBackground(backGround);
        northBoardJPanel.setPreferredSize(northBoardJPanelDimension);
        JButton arrivalsButton = new JButton(arrivalsStr);
        JButton departuresButton = new JButton(departuresStr);
        JButton searchButton = new JButton(searchStr);
        JButton[] arr = {arrivalsButton,departuresButton,searchButton};
        addPropertiesToTopButtons(arr,buttontTopDimension200x50,northBoardJPanel);
        northBoardJPanel.setVisible(true);
        topEastJPanel.add(northBoardJPanel,BorderLayout.NORTH);
        centerBoardJPanel.setVisible(false);
        centerBoardJPanel.removeAll();
        centerBoardJPanel.setPreferredSize(centerBoardJPanelDimension);
        centerBoardJPanel.setBackground(red);
        centerBoardJPanel.setVisible(true);
        topEastJPanel.add(centerBoardJPanel,BorderLayout.CENTER);
        drawSouthBoardJPanel();
        topEastJPanel.add(southBoardJPanel,BorderLayout.SOUTH);
        instScheduleJPanelController.topButtonsController(arrivalsButton,departuresButton,searchButton,jSliderTime);
        instEastJPanelController.topButtonsMouseController(arrivalsButton);
        instEastJPanelController.topButtonsMouseController(departuresButton);
        instEastJPanelController.topButtonsMouseController(searchButton);
        arrivalsButton.setBackground(red);
        topEastJPanel.setVisible(true);
    }

    protected void drawSouthBoardJPanel(){
        ScheduleJPanelController instScheduleJPanelController = new ScheduleJPanelController();
        Font font = new Font("Verdana", Font.PLAIN, 10);
        southBoardJPanel.setVisible(false);
        southBoardJPanel.removeAll();
        southBoardJPanel.setPreferredSize(southBoardJPanelDimension);
        southBoardJPanel.setBackground(darkBackGround);
        southBoardJPanel.setLayout(new BorderLayout());
        JPanel topJPanel = new JPanel();
        JPanel bottomJPanel = new JPanel();
        topJPanel.setPreferredSize(panelTopSouthScheduleJpanelDimension850x25);
        bottomJPanel.setPreferredSize(panelBottomSouthScheduleJpanelDimension850x50);
        topJPanel.setBackground(darkBackGround);
        bottomJPanel.setBackground(red);
        southBoardJPanel.add(topJPanel,BorderLayout.NORTH);
        southBoardJPanel.add(bottomJPanel,BorderLayout.SOUTH);
        topJPanel.setLayout(new BorderLayout());
        JLabel timeFrameLabel = new JLabel("TIME FRAME");
        timeFrameLabel.setPreferredSize(timeFrameLabelScheduleJpanelDimension500x15);
        timeFrameLabel.setFont(timeFrameVerdana10Bold);
        JLabel dateJLabel = new JLabel("DATE:");
        dateJLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        dateJLabel.setFont(font);
        JComboBox dateComboBox;
        if (isArrivalsScheduleTable) {
            dateComboBox = new JComboBox(instScheduleTableSearchService.datesForArrivalComboBox());
        }
        else {
            dateComboBox = new JComboBox(instScheduleTableSearchService.datesForDepartComboBox());
        }
        dateComboBox.setUI(new BasicComboBoxUI() {
                @Override
                protected JButton createArrowButton() {
                    return new JButton() {
                        @Override
                        public int getWidth() {
                            return 0;
                        }
                    };
                }
            });
        ((JLabel)dateComboBox.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
        dateComboBox.setBackground(darkBackGround);
        dateComboBox.setFont(font);
        dateComboBox.setMaximumRowCount(5);
        dateComboBox.setAutoscrolls(true);
        dateComboBox.setAlignmentX(SwingConstants.CENTER);
        jSliderTime = new JSlider(0,24,3);
        jSliderTime.setPreferredSize(jsliderScheduleJpanelDimension850x45);
        jSliderTime.setMajorTickSpacing(12);
        jSliderTime.setMinorTickSpacing(3);
        jSliderTime.setPaintTicks(true);
        Dictionary<Integer, Component> labelTable = new Hashtable<Integer, Component>();
        JLabel label24 = new JLabel("24h");
        JLabel label12 = new JLabel("12h");
        JLabel label0 = new JLabel("0h");
        label24.setFont(font);
        label12.setFont(font);
        label0.setFont(font);
        labelTable.put(0, label24);
        labelTable.put(12, label12);
        labelTable.put(24, label0);
        jSliderTime.setLabelTable(labelTable);
        jSliderTime.setPaintLabels(true);
        jSliderTime.setBackground(darkBackGround);
        jSliderTime.setInverted(true);
        jSliderTime.setValue(24);
        topJPanel.add(timeFrameLabel,BorderLayout.WEST);
        topJPanel.add(dateJLabel,BorderLayout.CENTER);
        topJPanel.add(dateComboBox,BorderLayout.EAST);
        bottomJPanel.add(jSliderTime);
        instScheduleJPanelController.jSliderTimeController(jSliderTime,dateComboBox);
        instScheduleJPanelController.datesComboBoxController(dateComboBox,jSliderTime);
        southBoardJPanel.setVisible(true);

    }

    protected void drawSouthBoardJPanelSearchSchedule(){
        ScheduleJPanelController instScheduleJPanelController = new ScheduleJPanelController();
        southBoardJPanel.setVisible(false);
        southBoardJPanel.removeAll();
        southBoardJPanel.setPreferredSize(southBoardJPanelDimension);
        southBoardJPanel.setBackground(darkBackGround);
        southBoardJPanel.setLayout(new BorderLayout());
        JPanel topJPanel = new JPanel();
        topJPanel.setBackground(darkBackGround);
        topJPanel.setPreferredSize(panelBottomSouthScheduleJpanelDimension850x50);
        topJPanel.setLayout(new GridLayout(2,2,50,10));
        String[] scheduleStr;
        if(isArrivalsChoosedComboBox) {scheduleStr = new String[]{arrivalsStr, departuresStr};}
        else {scheduleStr = new String[]{departuresStr,arrivalsStr};}
        JComboBox scheduleComboBox = new JComboBox(scheduleStr);
        JComboBox dateComboBox = new JComboBox(instScheduleTableSearchService.makeArrOfDatesForSearchComboBox());
        JComboBox destinationComboBox = new JComboBox(instScheduleTableSearchService.makeArrOfDestinPortsForSearchComboBox());
        JComboBox flightsComboBox = new JComboBox(instScheduleTableSearchService.makeArrOfFlightsForSearchComboBox());
        JComboBox[] arrComboBox = {dateComboBox,destinationComboBox,scheduleComboBox,flightsComboBox};
        for (JComboBox combo:arrComboBox) {
            combo.setUI(new BasicComboBoxUI() {
                @Override
                protected JButton createArrowButton() {
                    return new JButton() {
                        @Override
                        public int getWidth() {
                            return 0;
                        }
                    };
                }
            });
            ((JLabel)combo.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
            combo.setBackground(darkBackGround);
            combo.setFont(timeFrameVerdana10Bold);
            combo.setMaximumRowCount(5);
            combo.setAutoscrolls(true);
            combo.setAlignmentX(SwingConstants.CENTER);
            topJPanel.add(combo);
        }
        southBoardJPanel.add(topJPanel,BorderLayout.NORTH);
        JPanel bottomJPanel = new JPanel();
        bottomJPanel.setBackground(darkBackGround);
        bottomJPanel.setPreferredSize(panelBottomJpanelDimension850x20);
        bottomJPanel.setLayout(new CardLayout());
        JButton searchJButton = new JButton(searchStr);
        searchJButton.setFont(timeFrameVerdana10Bold);
        searchJButton.setBackground(red);
        searchJButton.setPreferredSize(buttontSearchDimension200x15);
        bottomJPanel.add(searchJButton,BorderLayout.CENTER);
        southBoardJPanel.add(bottomJPanel,BorderLayout.SOUTH);
        southBoardJPanel.setVisible(true);
        instScheduleJPanelController.scheduleSearcComboBoxController(scheduleComboBox);
        instScheduleJPanelController.dateComboBoxSearchController(destinationComboBox,flightsComboBox,dateComboBox);
        instScheduleJPanelController.searchButtonSearchMenu(searchJButton,dateComboBox,destinationComboBox,scheduleComboBox,flightsComboBox);
    }

}
