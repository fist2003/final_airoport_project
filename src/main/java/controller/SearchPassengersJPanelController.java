package controller;

import view.SearchPassengersJPanelGUI;
import view.table_models.PassengersListTableModel;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.event.*;

/**
 * Created by ПК on 13.12.2016.
 */
public class SearchPassengersJPanelController extends SearchPassengersJPanelGUI {
    public SearchPassengersJPanelController() {
    }

    public void topButtonsController(final JButton searchByPersonalInfoButton, final JButton searchByFlightInfoButton) {
        searchByPersonalInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchByPersonalInfoButton.setBackground(red);
                searchByFlightInfoButton.setBackground(darkBackGround);
                drawSouthBoardJPanelPassengersInfo();
                // topButtonFlag = false;
            }
        });
        searchByFlightInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchByPersonalInfoButton.setBackground(darkBackGround);
                searchByFlightInfoButton.setBackground(red);
                drawSouthBoardJPanelFlightsInfo();
                System.out.println("ddd");
                // topButtonFlag = true;
            }
        });

    }


}
