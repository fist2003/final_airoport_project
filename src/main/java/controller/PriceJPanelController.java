package controller;

import view.PricesJPanelGUI;
import view.table_models.PricesAllTableModel;
import view.table_models.PricesDepartArriveTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ПК on 11.12.2016.
 */
public class PriceJPanelController extends PricesJPanelGUI {
    public PriceJPanelController(){}

    public void topButtonsController(final JButton departFrOdButton, final JButton arivToOdButton, final JButton allPricesButton){
        departFrOdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                departFrOdButton.setBackground(red);
                arivToOdButton.setBackground(darkBackGround);
                allPricesButton.setBackground(darkBackGround);
                topButtonFlag = false;
                isArrivalPresed = false;
                isDepartPresed = true;
                isAllPresed = false;
                drawSouthBoardJPanel();
            }
        });
        arivToOdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                departFrOdButton.setBackground(darkBackGround);
                arivToOdButton.setBackground(red);
                allPricesButton.setBackground(darkBackGround);
                topButtonFlag = true;
                isArrivalPresed = true;
                isDepartPresed = false;
                isAllPresed = false;
                drawSouthBoardJPanel();
            }
        });
        allPricesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                departFrOdButton.setBackground(darkBackGround);
                arivToOdButton.setBackground(darkBackGround);
                allPricesButton.setBackground(red);
                isArrivalPresed = false;
                isDepartPresed = false;
                isAllPresed = true;
                drawSouthBoardJPanel();
            }
        });
    }

    public void airlinesNamesComboBoxController(final JComboBox comboBox){
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isDepartPresed) {
                    PricesDepartArriveTableModel instPricesDepartArriveTableModel = new PricesDepartArriveTableModel
                            (instPricesTableService.makePriceTableDepartureAirline(comboBox.getSelectedItem().toString()));
                    drawTable(instPricesDepartArriveTableModel);
                }
                else if(isArrivalPresed){
                    PricesDepartArriveTableModel instPricesDepartArriveTableModel = new PricesDepartArriveTableModel
                            (instPricesTableService.makePriceTableArrivalAirline(comboBox.getSelectedItem().toString()));
                    drawTable(instPricesDepartArriveTableModel);
                }
                else if(isAllPresed){
                    PricesAllTableModel instPricesAllTableModel = new PricesAllTableModel(
                            instPricesTableService.makePriceTableAirline(comboBox.getSelectedItem().toString()));
                    drawTable(instPricesAllTableModel);
                }
            }
        });
    }

    public void destinPortsComboBoxController(final JComboBox comboBox){
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isDepartPresed) {
                    PricesDepartArriveTableModel insPricesDepartArriveTableModel = new PricesDepartArriveTableModel
                            (instPricesTableService.makePriceTableDepartureDestin(comboBox.getSelectedItem().toString()));
                    drawTable(insPricesDepartArriveTableModel);
                }
                else if(isArrivalPresed){
                    PricesDepartArriveTableModel insPricesDepartArriveTableModel = new PricesDepartArriveTableModel
                            (instPricesTableService.makePriceTableArrivalOriginPort(comboBox.getSelectedItem().toString()));
                    drawTable(insPricesDepartArriveTableModel);
                }
            }
        });
    }

    public void timeDepartComboBoxController(final JComboBox comboBox){
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isDepartPresed) {
                    PricesDepartArriveTableModel instPricesDepartArriveTableModel = new PricesDepartArriveTableModel
                            (instPricesTableService.makePriceTableDepartureTime(comboBox.getSelectedItem().toString()));
                    drawTable(instPricesDepartArriveTableModel);
                }
                else if(isArrivalPresed){
                    PricesDepartArriveTableModel instPricesDepartArriveTableModel = new PricesDepartArriveTableModel
                            (instPricesTableService.makePriceTableArrivalTime(comboBox.getSelectedItem().toString()));
                    drawTable(instPricesDepartArriveTableModel);
                }
                else if(isAllPresed){
                    PricesAllTableModel instPricesAllTableModel = new PricesAllTableModel(
                            instPricesTableService.makeAllPriceTableDepartureTime(comboBox.getSelectedItem().toString()));
                    drawTable(instPricesAllTableModel);
                }
            }
        });
    }

    public void dateDepartComboBoxController(final JComboBox comboBox){
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isDepartPresed) {
                    PricesDepartArriveTableModel instPricesDepartArriveTableModel = new PricesDepartArriveTableModel
                            (instPricesTableService.makePriceTableDepartureDate(comboBox.getSelectedItem().toString()));
                    drawTable(instPricesDepartArriveTableModel);
                }
                else if(isArrivalPresed){
                    PricesDepartArriveTableModel instPricesDepartArriveTableModel = new PricesDepartArriveTableModel
                            (instPricesTableService.makePriceTableArrivalDate(comboBox.getSelectedItem().toString()));
                    drawTable(instPricesDepartArriveTableModel);
                }
                else if(isAllPresed){
                    PricesAllTableModel instPricesAllTableModel = new PricesAllTableModel(
                            instPricesTableService.makeAllPriceTableDepartureDate(comboBox.getSelectedItem().toString()));
                    drawTable(instPricesAllTableModel);
                }
            }
        });
    }

}


