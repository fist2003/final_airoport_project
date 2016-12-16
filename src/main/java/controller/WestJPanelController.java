package controller;

import view.*;
import view.EditInfoJPanelGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ПК on 11.12.2016.
 */
public class WestJPanelController extends WestJPanelGUI{
    public WestJPanelController(){}

    public void buttonsController(final JButton button){
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (button.getText()){
                    case flightsStr:
                        ScheduleJPanelGUI instScheduleJPanelGUI = new ScheduleJPanelGUI();
                        instScheduleJPanelGUI.drawJPanel();
                        break;
                    case showPricesStr:
                        PricesJPanelGUI instPricesJPanelGUI = new PricesJPanelGUI();
                        instPricesJPanelGUI.drawJPanel();
                        break;
                    case showPassengersStr:
                        PassengersListJPanelGUI instPassengersListJPanelGUI = new PassengersListJPanelGUI();
                        instPassengersListJPanelGUI.drawJPanel();
                        break;
                    case searchPassengersStr:
                        SearchPassengersJPanelGUI instSearchPassengersJPanelGUI = new SearchPassengersJPanelGUI();
                        instSearchPassengersJPanelGUI.drawJPanel();
                        break;
                    case editInformationStr:
                        EditInfoJPanelGUI instEditJPanelGUI = new EditInfoJPanelGUI();
                        instEditJPanelGUI.drawJPanel();
                        break;
                    case airlinesInfoStr:
                        AirlinesJPanelGUI instAirlinesJPanelGUI = new AirlinesJPanelGUI();
                        instAirlinesJPanelGUI.drawJPanel();
                    default:

                }

            }
        });
    }

}
