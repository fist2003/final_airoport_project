package controller;

import model.Airplanes;
import view.table_models.EditAirplaneTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by ПК on 14.12.2016.
 */
public class EditAirplaneController extends EditDataJPanelController {
    public EditAirplaneController(){}

    @Override
    public void chooseTableButtons(final JButton airlinesButton, JButton airplanesButton, JButton flightsButton, JButton passengersButton, JButton usersButton, final JButton[] arrButtons) {
        for (JButton button:arrButtons){button.setBackground(backGround);}
        airplanesButton.setBackground(red);
        EditAirplaneTableModel instEditAirplaneTableModel = new EditAirplaneTableModel(instAirplaneService.getAllService());
        JTable allJTable = drawTable(instEditAirplaneTableModel);
        int columnsQuantity = allJTable.getColumnCount();
        if (isInsertPresed){
            JTable insertJTable = drawInsertNewDataTable(columnsQuantity);
            insertNewDataController(confirmInsertNewDataButton,insertJTable);
        }
        else{
            editTableController(allJTable);
        }
    }

    private void insertNewDataController(final JButton button, final JTable table){
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Airplanes airplane = addAirplaneObject(table);
                ArrayList<String> check = new ArrayList<String>(instAirplaneService.insertNewService(airplane));
                if(check.size() == 0){messageSuccessful();}
                else {
                    messageWrongInputData(check);}
                EditAirplaneTableModel instEditAirplaneTableModel = new EditAirplaneTableModel(instAirplaneService.getAllService());
                editTableController(drawTable(instEditAirplaneTableModel));
            }
        });
    }

    private void editTableController(final JTable table) {
        table.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    Airplanes airplane = addAirplaneObject(table);
                    if (isEditPresed) {
                        ArrayList<String> check = new ArrayList<String>(instAirplaneService.editDataService(airplane));
                        if(check.size() == 0){messageSuccessful();}
                        else {
                            messageWrongInputData(check);}
                    }
                    else if (isDeletePresed) {
                        if (messageForDelete("Airplane")) {
                            ArrayList<Long> listFlightsId = new ArrayList<Long>(instAirplaneService.deleteDataService(airplane));
                            if (listFlightsId.size() != 0) {messageOfRegisteredFlightsInAirplane(listFlightsId);}
                        }
                    }
                    EditAirplaneTableModel instEditAirplaneTableModel = new EditAirplaneTableModel(instAirplaneService.getAllService());
                    editTableController(drawTable(instEditAirplaneTableModel));
                }
            }});
    }

    private Airplanes addAirplaneObject(JTable table){
        int row = 0;
        Long id;
        if(isInsertPresed){id = null;}
        else {
            row = table.getSelectedRow();
            id = (Long) table.getValueAt(row, 0);}
        Airplanes airplane = new Airplanes();
        airplane.setId(id);
        airplane.setManufacturer(table.getValueAt(row,1).toString());
        airplane.setModel(table.getValueAt(row,2).toString());
        airplane.setNumberISO(table.getValueAt(row,3).toString());
        if(instAirplaneService.checkInputNumber(table.getValueAt(row,4).toString())){airplane.setYear(table.getValueAt(row,4).toString());}
        else airplane.setYear("-1");
        if (instAirplaneService.checkInputNumber(table.getValueAt(row,5).toString())) {airplane.setPlacesEconom(Integer.parseInt(table.getValueAt(row,5).toString()));}
        else airplane.setPlacesEconom(-1);
        if(instAirplaneService.checkInputNumber(table.getValueAt(row,6).toString())){airplane.setPlacesBusiness(Integer.parseInt(table.getValueAt(row,6).toString()));}
        else airplane.setPlacesBusiness(-1);
        if(instAirplaneService.checkInputNumber(table.getValueAt(row,7).toString())){airplane.setAirline_id(Long.parseLong(table.getValueAt(row,7).toString()));}
        else airplane.setAirline_id(-1l);
        return airplane;
    }

    private void messageOfRegisteredFlightsInAirplane(ArrayList<Long> listFlightsId){
        String eror = "You cann`t delete this airplane. Flight(s) with id: ";
        for(Long flightId : listFlightsId) {
            eror = eror + flightId + ", ";
        }
        eror = eror + "are registered in it;";
        JOptionPane.showMessageDialog(jfrm,eror);
    }
}
