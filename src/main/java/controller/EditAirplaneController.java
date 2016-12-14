package controller;

import model.Airplanes;
import view.table_models.EditAirplaneTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
                instAirplaneService.insertNewService(airplane);
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
                        instAirplaneService.editDataService(airplane);
                    }
                    else if (isDeletePresed) {
                        if (messageForDelete("Airplane")) {instAirplaneService.deleteDataService(airplane);}
                    }
                    EditAirplaneTableModel instEditAirplaneTableModel = new EditAirplaneTableModel(instAirplaneService.getAllService());
                    editTableController(drawTable(instEditAirplaneTableModel));
                }
            }});
    }

    private Airplanes addAirplaneObject(JTable table){
        int row = table.getSelectedRow();
        Long id;
        if(isInsertPresed){id = 0l;}
        else {id = (Long) table.getValueAt(row, 0);}
        Airplanes airplane = new Airplanes();
        airplane.setId(id);
        airplane.setManufacturer(table.getValueAt(row,1).toString());
        airplane.setModel(table.getValueAt(row,2).toString());
        airplane.setNumberISO(table.getValueAt(row,3).toString());
        airplane.setYear(table.getValueAt(row,4).toString());
        airplane.setPlacesEconom(Integer.parseInt(table.getValueAt(row,5).toString()));
        airplane.setPlacesBusiness(Integer.parseInt(table.getValueAt(row,6).toString()));
        airplane.setAirline_id(Long.parseLong(table.getValueAt(row,7).toString()));
        return airplane;
    }
}
