package controller;

import view.PassengersListJPanelGUI;
import view.table_models.PassengersListTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ПК on 12.12.2016.
 */
public class PassengersListController extends PassengersListJPanelGUI {
    public PassengersListController(){}

    public void topButtonsController(final JButton allPassengersButton, final JButton arrivalFlightsPassengersButton,
                                     final JButton departureFlightsPassengersButton){
        allPassengersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                allPassengersButton.setBackground(red);
                arrivalFlightsPassengersButton.setBackground(darkBackGround);
                departureFlightsPassengersButton.setBackground(darkBackGround);
                topButtonFlag = false;
                isAllPassPresed = true;
                isArrivalPassPresed = false;
                isDepartPassPresed = false;
                drawSouthBoardJPanel();
            }
        });
        arrivalFlightsPassengersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                allPassengersButton.setBackground(darkBackGround);
                arrivalFlightsPassengersButton.setBackground(red);
                departureFlightsPassengersButton.setBackground(darkBackGround);
                topButtonFlag = true;
                isAllPassPresed = false;
                isArrivalPassPresed = true;
                isDepartPassPresed = false;
                drawSouthBoardJPanel();

            }
        });
        departureFlightsPassengersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                allPassengersButton.setBackground(darkBackGround);
                arrivalFlightsPassengersButton.setBackground(darkBackGround);
                departureFlightsPassengersButton.setBackground(red);
                isAllPassPresed = false;
                isArrivalPassPresed = false;
                isDepartPassPresed = true;
                drawSouthBoardJPanel();

            }
        });
    }

    public void airlinesNamesComboBoxController(final JComboBox comboBox){
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    PassengersListTableModel instPassengersListTableModel = new PassengersListTableModel
                            (instPassengersListService.makeListPassengersByAirline(comboBox.getSelectedItem().toString()));
                    drawTable(instPassengersListTableModel);
            }
        });
    }

    public void flightComboBoxController(final JComboBox comboBox){
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    PassengersListTableModel instPassengersListTableModel = new PassengersListTableModel
                            (instPassengersListService.makeListPassengersByFlightNumber(comboBox.getSelectedItem().toString()));
                    drawTable(instPassengersListTableModel);
            }
        });
    }

    public void portDepartComboBoxController(final JComboBox comboBox){
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PassengersListTableModel instPassengersListTableModel = new PassengersListTableModel
                        (instPassengersListService.makeListPassengersByPortDepart(comboBox.getSelectedItem().toString()));
                drawTable(instPassengersListTableModel);
            }
        });
    }

    public void portDestinComboBoxController(final JComboBox comboBox){
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PassengersListTableModel instPassengersListTableModel = new PassengersListTableModel
                        (instPassengersListService.makeListPassengersByPortDestin(comboBox.getSelectedItem().toString()));
                drawTable(instPassengersListTableModel);
            }
        });
    }
}
