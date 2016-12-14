package controller;

import view.ScheduleJPanelGUI;
import view.table_models.ArrivalTableModel;
import view.table_models.DepartureTableModel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.*;

/**
 * Created by ПК on 09.12.2016.
 */
public class ScheduleJPanelController extends ScheduleJPanelGUI {
    public ScheduleJPanelController(){}

    public void topButtonsController(final JButton arrival, final JButton depart, final JButton search, final JSlider jSlider){
        arrival.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrivalTableModel instArrivalTableModel =
                        new ArrivalTableModel(instScheduleTableSearchService.makeArrivalScheduleTable(jSlider.getValue(),
                                instScheduleTableSearchService.getListAllArrivalFlights()));
                arrival.setBackground(red);
                depart.setBackground(darkBackGround);
                search.setBackground(darkBackGround);
                jSlider.setValue(0);
                drawSouthBoardJPanel();
                drawTable(instArrivalTableModel);
                topButtonFlag = false;
            }
        });
        depart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DepartureTableModel instDepartureTableModel =
                        new DepartureTableModel(instScheduleTableSearchService.makeDepartureScheduleTable(jSlider.getValue(),
                                instScheduleTableSearchService.getListAllDepartureFlights()));
                arrival.setBackground(darkBackGround);
                depart.setBackground(red);
                search.setBackground(darkBackGround);
                jSlider.setValue(0);
                drawSouthBoardJPanel();
                drawTable(instDepartureTableModel);
                topButtonFlag = true;
            }
        });
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                arrival.setBackground(darkBackGround);
                depart.setBackground(darkBackGround);
                search.setBackground(red);
                drawSouthBoardJPanelSearchSchedule();
                try{drawTable(null);}
                catch (IndexOutOfBoundsException i){}
            }
        });
    }

    public void scheduleSearcComboBoxController(final JComboBox comboBox){
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (comboBox.getSelectedItem().toString()){
                    case arrivalsStr:
                        isArrivalsChoosedComboBox = true;
                        drawSouthBoardJPanelSearchSchedule();
                        break;
                    case departuresStr:
                        isArrivalsChoosedComboBox = false;
                        drawSouthBoardJPanelSearchSchedule();
                        break;
                }
            }
        });
    }

    public void directionComboBoxSearchController(final JComboBox directionComboBox, final JComboBox flightsComboBox,
                                                  final JComboBox datesComboBox) {
        directionComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String[] arrFlights = instScheduleTableSearchService.makeArrForSearchComboBoxByDirection
                        (directionComboBox.getSelectedItem().toString())[0];
                flightsComboBox.removeAllItems();
                for (int i = 0; i < arrFlights.length; i++) {
                    flightsComboBox.addItem(arrFlights[i]);
                }

                String[] arrDates = instScheduleTableSearchService.makeArrForSearchComboBoxByDirection
                        (directionComboBox.getSelectedItem().toString())[1];
                datesComboBox.removeAllItems();
                for (int i = 0; i < arrDates.length; i++) {
                    datesComboBox.addItem(arrDates[i]);
                }

            }
        });
    }

    public void dateComboBoxSearchController(final JComboBox directionComboBox, final JComboBox flightsComboBox,
                                                  final JComboBox datesComboBox){
        datesComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (datesComboBox.getSelectedItem() == null){
                    return;
                }
                flightsComboBox.removeAllItems();
               // directionComboBox.removeAllItems();
                String[] arrFlights = instScheduleTableSearchService.makeArrForSearchComboBoxByDate
                        (datesComboBox.getSelectedItem().toString())[0];
                String[] arrDirections = instScheduleTableSearchService.makeArrForSearchComboBoxByDate
                        (datesComboBox.getSelectedItem().toString())[1];
                for(int i = 0; i < arrFlights.length; i++) {
                    flightsComboBox.addItem(arrFlights[i]);
                }
                for(int i = 0; i < arrDirections.length; i++) {
                    directionComboBox.addItem(arrDirections[i]);
                }
            }
        });
    }

    public void jSliderTimeController(final JSlider jSlider){
        jSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(topButtonFlag){
                    DepartureTableModel instDepartureTableModel =
                            new DepartureTableModel(instScheduleTableSearchService.makeDepartureScheduleTable(jSlider.getValue(),
                                    instScheduleTableSearchService.getListAllDepartureFlights()));
                    drawTable(instDepartureTableModel);
                }
                else {
                    ArrivalTableModel instArrivalTableModel =
                            new ArrivalTableModel(instScheduleTableSearchService.makeArrivalScheduleTable(jSlider.getValue(),
                                    instScheduleTableSearchService.getListAllArrivalFlights()));
                    drawTable(instArrivalTableModel);
                }
            }
        });
    }


    public void searchButtonSearchMenu(JButton button, final JComboBox dateComboBox,final JComboBox destinationComboBox,
                                       final JComboBox scheduleComboBox,final JComboBox flightsComboBox){
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (scheduleComboBox.getSelectedItem().toString()){
                    case arrivalsStr:
                       ArrivalTableModel instArrivalTableModel =
                                new ArrivalTableModel(instScheduleTableSearchService.makeArrivalScheduleTable(0,
                                        instScheduleTableSearchService.getListSearchArrivalFlights
                                                (dateComboBox,destinationComboBox,flightsComboBox)));
                        drawTable(instArrivalTableModel);
                       break;
                    case departuresStr:
                        DepartureTableModel instDepartureTableModel =
                                new DepartureTableModel(instScheduleTableSearchService.makeDepartureScheduleTable(0,
                                        instScheduleTableSearchService.getListSearchDepartureFlights
                                                (dateComboBox,destinationComboBox,flightsComboBox)));
                        drawTable(instDepartureTableModel);
                        break;
                }
            }
        });

    }



}