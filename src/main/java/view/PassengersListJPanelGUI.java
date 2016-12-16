package view;

import controller.PassengersListController;
import service.AirlineService;
import service.PassengersListService;
import view.table_models.PassengersListTableModel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ПК on 11.12.2016.
 */
public class PassengersListJPanelGUI extends EastJPanelGUI {
    public PassengersListJPanelGUI(){
        this.instAirlineService = new AirlineService();
        this.instPassengersListService = new PassengersListService();
    }

    protected AirlineService instAirlineService;
    protected PassengersListService instPassengersListService;

    private String allPassengersStr = "ALL PASSENGERS";
    private String arrivalFlightsPassengersStr = "ARRIVAL FLIGHTS PASSENGERS";
    private String departureFlightsPassengersStr = "DEPARTURE FLIGHTS PASSENGERS";

    public static boolean isAllPassPresed = true;
    public static boolean isArrivalPassPresed = false;
    public static boolean isDepartPassPresed = false;

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
        drawTopEastJPanel();
        drawBottomEastJPanel();
        eastJPanel.setVisible(true);
        */
        drawTopEastJPanel();
        drawBottomEastJPanel();
    }

    public void drawTopEastJPanel(){
        PassengersListController instPassengersListController = new PassengersListController();
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
        JButton allPassengersButton = new JButton(allPassengersStr);
        JButton arrivalFlightsPassengersButton = new JButton(arrivalFlightsPassengersStr);
        JButton departureFlightsPassengersButton = new JButton(departureFlightsPassengersStr);
        JButton[] arr = {allPassengersButton,arrivalFlightsPassengersButton,departureFlightsPassengersButton};
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
        instPassengersListController.topButtonsController(allPassengersButton,arrivalFlightsPassengersButton,
                departureFlightsPassengersButton);
        instEastJPanelController.topButtonsMouseController(allPassengersButton);
        instEastJPanelController.topButtonsMouseController(arrivalFlightsPassengersButton);
        instEastJPanelController.topButtonsMouseController(departureFlightsPassengersButton);
        allPassengersButton.setBackground(red);
        topEastJPanel.setVisible(true);
    }

    protected void drawSouthBoardJPanel() {
        southBoardJPanel.setVisible(false);
        southBoardJPanel.removeAll();
        southBoardJPanel.setPreferredSize(southBoardJPanelDimension);
        southBoardJPanel.setBackground(darkBackGround);
        if(isAllPassPresed){
            drawSouthBoardJPanelAllPassengers();
            PassengersListTableModel instPassengersListTableModel = new PassengersListTableModel
                    (instPassengersListService.makeAllPassengersList());
            drawTable(instPassengersListTableModel);
        }
        else if(isArrivalPassPresed){
            drawSouthBoardJPanelArrivalPassengers();
            PassengersListTableModel instPassengersListTableModel = new PassengersListTableModel
                    (instPassengersListService.makeArrivePassengersList());
            drawTable(instPassengersListTableModel);
        }
        else if(isDepartPassPresed){
            drawSouthBoardJPanelDeparturePassengers();
            PassengersListTableModel instPassengersListTableModel = new PassengersListTableModel
                    (instPassengersListService.makeDepartPassengersList());
            drawTable(instPassengersListTableModel);
        }
        southBoardJPanel.setVisible(true);

    }

    protected void drawSouthBoardJPanelAllPassengers(){
        PassengersListController instPassengersListController = new PassengersListController();

        southBoardJPanel.setLayout(new GridLayout(2,4,10,0));
        JLabel airlineJLabel = new JLabel("AIRLINE");
        JLabel flightJLabel = new JLabel("FLIGHT");
        JLabel portDepartJLabel = new JLabel("PORT DEPART");
        JLabel portDestinJLabel = new JLabel("PORT DESTINATION");
        JLabel[] jLabels = {airlineJLabel,flightJLabel,portDepartJLabel,portDestinJLabel};
        JComboBox airlinesNamesComboBox = new JComboBox(instAirlineService.makeArrAirlinesNamesForComboBox());
        JComboBox flightComboBox = new JComboBox(instPassengersListService.makeArrFlightNumbersForComboBox());
        JComboBox portDepartComboBox = new JComboBox(instPassengersListService.makeArrPortDepartsComboBox());
        JComboBox portDestinComboBox = new JComboBox(instPassengersListService.makeArrPortDestinationComboBox());
        JComboBox[] jComboBoxes = {airlinesNamesComboBox,flightComboBox,portDepartComboBox,portDestinComboBox};
        tuneJLabelsAndAddToJPanel(jLabels);
        tuneComboBoxesAndAddToJPanel(jComboBoxes);
        instPassengersListController.airlinesNamesComboBoxController(airlinesNamesComboBox);
        instPassengersListController.flightComboBoxController(flightComboBox);
        instPassengersListController.portDepartComboBoxController(portDepartComboBox);
        instPassengersListController.portDestinComboBoxController(portDestinComboBox);

    }

   protected void drawSouthBoardJPanelArrivalPassengers(){
        PassengersListController instPassengersListController = new PassengersListController();

        southBoardJPanel.setLayout(new GridLayout(2,3,10,0));
        JLabel airlineJLabel = new JLabel("AIRLINE");
        JLabel flightJLabel = new JLabel("FLIGHT");
        JLabel portDepartJLabel = new JLabel("PORT DEPART");
        JLabel[] jLabels = {airlineJLabel,flightJLabel,portDepartJLabel};
        JComboBox airlinesNamesComboBox = new JComboBox(instAirlineService.makeArrAirlinesNamesForComboBox());
        JComboBox flightComboBox = new JComboBox(instPassengersListService.makeArrArriveFlightNumbersForComboBox());
        JComboBox portDepartComboBox = new JComboBox(instPassengersListService.makeArrPortDepartsToOdessaComboBox());
        JComboBox[] jComboBoxes = {airlinesNamesComboBox,flightComboBox,portDepartComboBox};
        tuneJLabelsAndAddToJPanel(jLabels);
        tuneComboBoxesAndAddToJPanel(jComboBoxes);
        instPassengersListController.airlinesNamesComboBoxController(airlinesNamesComboBox);
        instPassengersListController.flightComboBoxController(flightComboBox);
        instPassengersListController.portDepartComboBoxController(portDepartComboBox);

        }

    protected void drawSouthBoardJPanelDeparturePassengers(){
        PassengersListController instPassengersListController = new PassengersListController();
        southBoardJPanel.setLayout(new GridLayout(2,3,10,0));
        JLabel airlineJLabel = new JLabel("AIRLINE");
        JLabel flightJLabel = new JLabel("FLIGHT");
        JLabel portDestinJLabel = new JLabel("PORT DESTINATION");
        JLabel[] jLabels = {airlineJLabel,flightJLabel,portDestinJLabel};
        JComboBox airlinesNamesComboBox = new JComboBox(instAirlineService.makeArrAirlinesNamesForComboBox());
        JComboBox flightComboBox = new JComboBox(instPassengersListService.makeArrDepartFlightNumbersForComboBox());
        JComboBox portDestinComboBox = new JComboBox(instPassengersListService.makeArrPortDestinFromOdessaComboBox());
        JComboBox[] jComboBoxes = {airlinesNamesComboBox,flightComboBox,portDestinComboBox};
        tuneJLabelsAndAddToJPanel(jLabels);
        tuneComboBoxesAndAddToJPanel(jComboBoxes);
        instPassengersListController.airlinesNamesComboBoxController(airlinesNamesComboBox);
        instPassengersListController.flightComboBoxController(flightComboBox);
        instPassengersListController.portDestinComboBoxController(portDestinComboBox);

    }


}
