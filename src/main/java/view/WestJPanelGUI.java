package view;

import controller.WestJPanelController;
import service.UsersService;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ПК on 08.12.2016.
 */
public class WestJPanelGUI extends MainPageGUI {
    public WestJPanelGUI(){}
    protected final String flightsStr = "FLIGHTS";
    protected final String showPricesStr = "SHOW PRICES";
    protected final String airlinesInfoStr = "AIRLINES INFO";
    protected final String showPassengersStr = "SHOW PASSENGERS";
    protected final String searchPassengersStr = "SEARCH PASSENGERS";
    protected final String editInformationStr = "EDIT INFORMATION";

    protected String getFlightsStr() {return flightsStr;}
    protected String getShowPricesStr() {return showPricesStr;}
    protected String getAirlinesInfoStr() {return airlinesInfoStr;}
    protected String getShowPassengersStr() {return showPassengersStr;}
    protected String getSearchPassengersStr() {return searchPassengersStr;}
    protected String getEditInformationStr() {return editInformationStr;}

    @Override
    public void drawJPanel(){
        westJPanel.setVisible(false);
        westJPanel.removeAll();
        westJPanel.setBackground(darkBackGround);
        westJPanel.setPreferredSize(westJPanelDimension);
        westJPanel.setLayout(new BorderLayout());
        westJPanel.add(userloginJPanel,BorderLayout.NORTH);
        westJPanel.add(menuJPanel,BorderLayout.SOUTH);
        drawUserLoginJPanel();
        drawMenuJPanel();
        westJPanel.setVisible(true);
    }

    protected void drawUserLoginJPanel(){
        UserJPanelGUI instUserJPanelGUI = new UserJPanelGUI();
        instUserJPanelGUI.drawUserJPanel();
    }

    protected void drawMenuJPanel(){
        WestJPanelController instWestJPanelController = new WestJPanelController();
        menuJPanel.setVisible(false);
        menuJPanel.removeAll();
        menuJPanel.setPreferredSize(menuJPanelDimension);
        menuJPanel.setBackground(backGround);
        GridLayout gridLayout = new GridLayout(6,1,0,10);
        menuJPanel.setLayout(gridLayout);
        Dimension buttonDimension = new Dimension(200,45);
        JButton fligthsJButton = new JButton(flightsStr);
        JButton showPricesButton = new JButton(showPricesStr);
        JButton airlinesInfoButton = new JButton(airlinesInfoStr);
        JButton showPassengersButton = new JButton(showPassengersStr);
        JButton searchPassengersButton = new JButton(searchPassengersStr);
        JButton editInformationButton = new JButton(editInformationStr);
        JButton[] arrOfJButtons = {fligthsJButton,showPricesButton,airlinesInfoButton,showPassengersButton,
                                    searchPassengersButton,editInformationButton};
        for (JButton button:arrOfJButtons) {
            button.setBackground(darkGreyButton);
            button.setForeground(Color.WHITE);
            button.setPreferredSize(buttonDimension);
            button.setFocusable(false);
            instWestJPanelController.buttonsController(button);
        }
        menuJPanel.add(fligthsJButton);
        menuJPanel.add(showPricesButton);
        menuJPanel.add(airlinesInfoButton);
        menuJPanel.add(showPassengersButton);
        if(UsersService.flagIsAdmin) {
            menuJPanel.add(searchPassengersButton);
            menuJPanel.add(editInformationButton);
        }
        menuJPanel.setVisible(true);
    }
}
