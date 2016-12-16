package view;

import controller.SearchPassengersJPanelController;
import service.PassengersListService;
import service.SearchPassengersService;
import view.combobox_model.ComboBoxSearchModel;
import view.table_models.PassengersListTableModel;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;

/**
 * Created by ПК on 13.12.2016.
 */
public class SearchPassengersJPanelGUI extends EastJPanelGUI {
    public SearchPassengersJPanelGUI(){
        this.instPassengersListService = new PassengersListService();
        this.instSearchPassengersService = new SearchPassengersService();
    }

    protected PassengersListService instPassengersListService;
    protected SearchPassengersService instSearchPassengersService;

    private String searchByPersonalInfoStr = "SEARCH BY PERSONAL INFORMATION";
    private String searchByFlightInfoStr = "SEARCH BY FLIGHT INFORMATION";

    public void drawJPanel() {
        super.drawJPanel();
       /* eastJPanel.setVisible(false);
        eastJPanel.removeAll();
        eastJPanel.setBackground(darkBackGround);
        eastJPanel.setPreferredSize(eastJPanelDimension);
        eastJPanel.setLayout(new BorderLayout());
        eastJPanel.add(topEastJPanel,BorderLayout.NORTH);
        eastJPanel.add(middleEastJPanel,BorderLayout.CENTER);
        eastJPanel.add(bottomEastJPanel,BorderLayout.SOUTH);*/
        drawTopEastJPanel();
        drawBottomEastJPanel();
        PassengersListTableModel instPassengersListTableModel = new PassengersListTableModel
                (instPassengersListService.makeAllPassengersList());
        drawTable(instPassengersListTableModel);
        eastJPanel.setVisible(true);
    }

    public void drawTopEastJPanel(){
        SearchPassengersJPanelController instSearchPassengersJPanelController = new SearchPassengersJPanelController();
        topEastJPanel.setVisible(false);
        topEastJPanel.removeAll();
        topEastJPanel.setPreferredSize(topEastJPanelDimension);
        topEastJPanel.setBackground(darkBackGround);
        topEastJPanel.setLayout(new BorderLayout());
        northBoardJPanel.setVisible(false);
        northBoardJPanel.removeAll();
        northBoardJPanel.setLayout(new GridLayout(1,2,20,0));
        northBoardJPanel.setBackground(backGround);
        northBoardJPanel.setPreferredSize(northBoardJPanelDimension);
       // Dimension buttonDimension = new Dimension(350,50);
        JButton searchByPersonalInfoButton = new JButton(searchByPersonalInfoStr);
        JButton searchByFlightInfoButton = new JButton(searchByFlightInfoStr);
        JButton[] arr = {searchByPersonalInfoButton,searchByFlightInfoButton};
        addPropertiesToTopButtons(arr,buttontTopDimension350x50,northBoardJPanel);
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
        instSearchPassengersJPanelController.topButtonsController(searchByPersonalInfoButton,searchByFlightInfoButton);
        instEastJPanelController.topButtonsMouseController(searchByPersonalInfoButton);
        instEastJPanelController.topButtonsMouseController(searchByFlightInfoButton);
        searchByPersonalInfoButton.setBackground(red);
        topEastJPanel.setVisible(true);
    }

    protected void drawSouthBoardJPanel() {
        drawSouthBoardJPanelPassengersInfo();
    }

    protected void drawSouthBoardJPanelPassengersInfo(){
        southBoardJPanel.setVisible(false);
        southBoardJPanel.removeAll();
        southBoardJPanel.setPreferredSize(southBoardJPanelDimension);
        southBoardJPanel.setBackground(backGround);
        southBoardJPanel.setLayout(new GridLayout(2,4,25,25));
        JLabel lastNameJLabel = new JLabel("ENTER LAST NAME:");
        JLabel firstNameJLabel = new JLabel("ENTER FIRST NAME:");
        JLabel passportJLabel = new JLabel("ENTER PASSPORT:");
        JLabel countryJLabel = new JLabel("ENTER COUNTRY:");
        JComboBox lastNamesComboBox = new JComboBox(instSearchPassengersService.makeArrSearchForComboBox().
                get(SearchPassengersService.lastNameStr));
        JComboBox firstNamesComboBox = new JComboBox(instSearchPassengersService.makeArrSearchForComboBox().
                get(SearchPassengersService.firstNameStr));
        JComboBox passportsComboBox = new JComboBox(instSearchPassengersService.makeArrSearchForComboBox().
                get(SearchPassengersService.passportStr));
        JComboBox countryComboBox = new JComboBox(instSearchPassengersService.makeArrSearchForComboBox().
                get(SearchPassengersService.countryStr));
        JComboBox[] jComboBoxes = {lastNamesComboBox,firstNamesComboBox,passportsComboBox,countryComboBox};
        for (JComboBox jComboBox:jComboBoxes){
            jComboBox.setEditable(true);
        }
        tuneJLabelsAndAddToJPanel(new JLabel[]{lastNameJLabel});
        tuneComboBoxesAndAddToJPanel(new JComboBox[]{lastNamesComboBox});
        tuneJLabelsAndAddToJPanel(new JLabel[]{firstNameJLabel});
        tuneComboBoxesAndAddToJPanel(new JComboBox[]{firstNamesComboBox});
        tuneJLabelsAndAddToJPanel(new JLabel[]{passportJLabel});
        tuneComboBoxesAndAddToJPanel(new JComboBox[]{passportsComboBox});
        tuneJLabelsAndAddToJPanel(new JLabel[]{countryJLabel});
        tuneComboBoxesAndAddToJPanel(new JComboBox[]{countryComboBox});
        new ComboBoxSearchModel(lastNamesComboBox,SearchPassengersService.lastNameStr);
        new ComboBoxSearchModel(firstNamesComboBox,SearchPassengersService.firstNameStr);
        new ComboBoxSearchModel(passportsComboBox,SearchPassengersService.passportStr);
        new ComboBoxSearchModel(countryComboBox,SearchPassengersService.countryStr);
        southBoardJPanel.setVisible(true);
    }

    protected void drawSouthBoardJPanelFlightsInfo(){
        southBoardJPanel.setVisible(false);
        southBoardJPanel.removeAll();
        southBoardJPanel.setPreferredSize(southBoardJPanelDimension);
        southBoardJPanel.setBackground(backGround);
        southBoardJPanel.setLayout(new GridLayout(2,4,25,25));
        JLabel lastNameJLabel = new JLabel("ENTER FLIGHT:");
        JLabel firstNameJLabel = new JLabel("ENTER PORT DEPART:");
        JLabel passportJLabel = new JLabel("ENTER PORT DESTIN:");
        JLabel countryJLabel = new JLabel("ENTER PRICE:");
        JComboBox flightsComboBox = new JComboBox(instSearchPassengersService.makeArrSearchForComboBox().
                get(SearchPassengersService.flightStr));
        JComboBox portDepartComboBox = new JComboBox(instSearchPassengersService.makeArrSearchForComboBox().
                get(SearchPassengersService.departPortStr));
        JComboBox portDestinComboBox = new JComboBox(instSearchPassengersService.makeArrSearchForComboBox().
                get(SearchPassengersService.destinPortStr));
        JComboBox priceComboBox = new JComboBox(instSearchPassengersService.makeArrSearchForComboBox().
                get(SearchPassengersService.priceStr));
        JComboBox[] jComboBoxes = {flightsComboBox,portDepartComboBox,portDestinComboBox,priceComboBox};
        for (JComboBox jComboBox:jComboBoxes){
            jComboBox.setEditable(true);
        }
        tuneJLabelsAndAddToJPanel(new JLabel[]{lastNameJLabel});
        tuneComboBoxesAndAddToJPanel(new JComboBox[]{flightsComboBox});
        tuneJLabelsAndAddToJPanel(new JLabel[]{firstNameJLabel});
        tuneComboBoxesAndAddToJPanel(new JComboBox[]{portDepartComboBox});
        tuneJLabelsAndAddToJPanel(new JLabel[]{passportJLabel});
        tuneComboBoxesAndAddToJPanel(new JComboBox[]{portDestinComboBox});
        tuneJLabelsAndAddToJPanel(new JLabel[]{countryJLabel});
        tuneComboBoxesAndAddToJPanel(new JComboBox[]{priceComboBox});
        new ComboBoxSearchModel(flightsComboBox,SearchPassengersService.flightStr);
        new ComboBoxSearchModel(portDepartComboBox,SearchPassengersService.departPortStr);
        new ComboBoxSearchModel(portDestinComboBox,SearchPassengersService.destinPortStr);
        new ComboBoxSearchModel(priceComboBox,SearchPassengersService.priceStr);
        southBoardJPanel.setVisible(true);
    }
}
