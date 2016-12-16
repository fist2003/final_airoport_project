package view;

import controller.PriceJPanelController;
import service.AirlineService;
import service.PricesTableService;
import view.table_models.PricesAllTableModel;
import view.table_models.PricesDepartArriveTableModel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ПК on 11.12.2016.
 */
public class PricesJPanelGUI extends EastJPanelGUI {
    public PricesJPanelGUI(){
        this.instPricesTableService = new PricesTableService();
        this.instAirlineService = new AirlineService();
    }

    protected PricesTableService instPricesTableService;
    protected AirlineService instAirlineService;

    private String departFromOdessaStr = "DEPARTURE FROM ODESSA";
    private String arrivalToOdessaStr = "ARRIVAL TO ODESSA";
    private String allPricesStr = "ALL PRICES";
    public static boolean isDepartPresed = true;
    public static boolean isArrivalPresed = false;
    public static boolean isAllPresed = false;

    @Override
    public void drawJPanel() {
        super.drawJPanel();
        /*
        eastJPanel.setVisible(false);
        eastJPanel.removeAll();
        eastJPanel.setBackground(darkBackGround);
        eastJPanel.setPreferredSize(eastJPanelDimension);
        eastJPanel.setLayout(new BorderLayout());
        eastJPanel.add(topEastJPanel,BorderLayout.NORTH);
        eastJPanel.add(middleEastJPanel,BorderLayout.CENTER);
        eastJPanel.add(bottomEastJPanel,BorderLayout.SOUTH);
        */
        drawTopEastJPanel();
        drawBottomEastJPanel();
        eastJPanel.setVisible(true);
    }
    public void drawTopEastJPanel(){
        PriceJPanelController instPriceJPanelController = new PriceJPanelController();
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
        JButton departFromOdButton = new JButton(departFromOdessaStr);
        JButton arrivalToOdButton = new JButton(arrivalToOdessaStr);
        JButton allPricesButton = new JButton(allPricesStr);
        JButton[] arr = {departFromOdButton,arrivalToOdButton,allPricesButton};
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
        instPriceJPanelController.topButtonsController(departFromOdButton,arrivalToOdButton,allPricesButton);
        instEastJPanelController.topButtonsMouseController(departFromOdButton);
        instEastJPanelController.topButtonsMouseController(arrivalToOdButton);
        instEastJPanelController.topButtonsMouseController(allPricesButton);
        departFromOdButton.setBackground(red);
        topEastJPanel.setVisible(true);
    }

    protected void drawSouthBoardJPanel() {
        southBoardJPanel.setVisible(false);
        southBoardJPanel.removeAll();
        southBoardJPanel.setPreferredSize(southBoardJPanelDimension);
        southBoardJPanel.setBackground(darkBackGround);
        if (isDepartPresed) {
            drawSouthBoardDepartPricesJPanel();
            PricesDepartArriveTableModel instPricesDepartArriveTableModel = new PricesDepartArriveTableModel(instPricesTableService.makePriceTableDeparture());
            drawTable(instPricesDepartArriveTableModel);
        } else if (isArrivalPresed) {
            drawSouthBoardArrivePricesJPanel();
            PricesDepartArriveTableModel instPricesDepartArriveTableModel = new PricesDepartArriveTableModel(instPricesTableService.makePriceTableArrival());
            drawTable(instPricesDepartArriveTableModel);
        } else if (isAllPresed) {
            drawSouthBoardAllPricesJPanel();
            PricesAllTableModel instPricesAllTableModel = new PricesAllTableModel(instPricesTableService.makePriceTableAll());
            drawTable(instPricesAllTableModel);
        }
        southBoardJPanel.setVisible(true);
    }

    private void drawSouthBoardDepartPricesJPanel(){
        PriceJPanelController instPriceJPanelController = new PriceJPanelController();
        southBoardJPanel.setLayout(new GridLayout(2,4,10,0));
        JLabel airlineJLabel = new JLabel("AIRLINE");
        JLabel directionJLabel = new JLabel("DIRECTION");
        JLabel timeRangeJLabel = new JLabel("TIME DEPART");
        JLabel dateRangeJLabel = new JLabel("DATE DEPART");
        JLabel[] jLabels = {airlineJLabel,directionJLabel,timeRangeJLabel,dateRangeJLabel};
        JComboBox airlinesNamesComboBox = new JComboBox(instAirlineService.makeArrAirlinesNamesForComboBox());
        JComboBox directionComboBox = new JComboBox(instPricesTableService.makeArrOfDestinPortsFromOdessaForComboBox());
        JComboBox timeRangeComboBox = new JComboBox(instPricesTableService.makeArrTimerangeForComboBox());
        JComboBox dateRangeComboBox = new JComboBox(instPricesTableService.makeArrDateRangeFromOdessaForComboBox());
        JComboBox[] jComboBoxes = {airlinesNamesComboBox,directionComboBox,timeRangeComboBox,dateRangeComboBox};
        tuneJLabelsAndAddToJPanel(jLabels);
        tuneComboBoxesAndAddToJPanel(jComboBoxes);
        instPriceJPanelController.airlinesNamesComboBoxController(airlinesNamesComboBox);
        instPriceJPanelController.destinPortsComboBoxController(directionComboBox);
        instPriceJPanelController.timeDepartComboBoxController(timeRangeComboBox);
        instPriceJPanelController.dateDepartComboBoxController(dateRangeComboBox);
    }

    private void drawSouthBoardArrivePricesJPanel(){
        PriceJPanelController instPriceJPanelController = new PriceJPanelController();
        southBoardJPanel.setLayout(new GridLayout(2,4,10,0));
        JLabel airlineJLabel = new JLabel("AIRLINE");
        JLabel originJLabel = new JLabel("ORIGIN");
        JLabel timeRangeJLabel = new JLabel("TIME ARRIVE");
        JLabel dateRangeJLabel = new JLabel("DATE ARRIVE");
        JLabel[] jLabels = {airlineJLabel,originJLabel,timeRangeJLabel,dateRangeJLabel};
        JComboBox airlinesNamesComboBox = new JComboBox(instAirlineService.makeArrAirlinesNamesForComboBox());
        JComboBox originComboBox = new JComboBox(instPricesTableService.makeArrOfOriginPortsToOdessaForComboBox());
        JComboBox timeRangeComboBox = new JComboBox(instPricesTableService.makeArrTimerangeForComboBox());
        JComboBox dateRangeComboBox = new JComboBox(instPricesTableService.makeArrDateRangeToOdessaForComboBox());
        JComboBox[] jComboBoxes = {airlinesNamesComboBox,originComboBox,timeRangeComboBox,dateRangeComboBox};
        tuneJLabelsAndAddToJPanel(jLabels);
        tuneComboBoxesAndAddToJPanel(jComboBoxes);
        instPriceJPanelController.airlinesNamesComboBoxController(airlinesNamesComboBox);
        instPriceJPanelController.destinPortsComboBoxController(originComboBox);
        instPriceJPanelController.timeDepartComboBoxController(timeRangeComboBox);
        instPriceJPanelController.dateDepartComboBoxController(dateRangeComboBox);
    }

    private void drawSouthBoardAllPricesJPanel(){
        PriceJPanelController instPriceJPanelController = new PriceJPanelController();
        southBoardJPanel.setLayout(new GridLayout(2,3,10,0));
        JLabel airlineJLabel = new JLabel("AIRLINE");
        JLabel timeRangeJLabel = new JLabel("TIME DEPART");
        JLabel dateRangeJLabel = new JLabel("DATE DEPART");
        JLabel[] jLabels = {airlineJLabel,timeRangeJLabel,dateRangeJLabel};
        JComboBox airlinesNamesComboBox = new JComboBox(instAirlineService.makeArrAirlinesNamesForComboBox());
        JComboBox timeRangeComboBox = new JComboBox(instPricesTableService.makeArrTimerangeForComboBox());
        JComboBox dateRangeComboBox = new JComboBox(instPricesTableService.makeArrDateRangeFromOdessaForComboBox());
        JComboBox[] jComboBoxes = {airlinesNamesComboBox,timeRangeComboBox,dateRangeComboBox};
        tuneJLabelsAndAddToJPanel(jLabels);
        tuneComboBoxesAndAddToJPanel(jComboBoxes);
        instPriceJPanelController.airlinesNamesComboBoxController(airlinesNamesComboBox);
        instPriceJPanelController.timeDepartComboBoxController(timeRangeComboBox);
        instPriceJPanelController.dateDepartComboBoxController(dateRangeComboBox);
    }

}
