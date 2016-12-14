package controller;

import model.Passengers;
import view.table_models.EditPassengerTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by ПК on 14.12.2016.
 */
public class EditPassengersController extends EditDataJPanelController {
    public EditPassengersController(){}

    @Override
    public void chooseTableButtons(final JButton airlinesButton, JButton airplanesButton, JButton flightsButton, JButton passengersButton, JButton usersButton, final JButton[] arrButtons) {
        for (JButton button:arrButtons){button.setBackground(backGround);}
        passengersButton.setBackground(red);
        EditPassengerTableModel instEditPassengerTableModel = new EditPassengerTableModel(instPassengersService.getAllService());
        JTable allJTable = drawTable(instEditPassengerTableModel);
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
                Passengers passengers = addPassengerObject(table);
                instPassengersService.insertNewService(passengers);
                EditPassengerTableModel instEditPassengerTableModel = new EditPassengerTableModel(instPassengersService.getAllService());
                editTableController(drawTable(instEditPassengerTableModel));
            }
        });
    }

    private void editTableController(final JTable table) {
        table.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    Passengers passenger = addPassengerObject(table);
                    if (isEditPresed) {
                        instPassengersService.editDataService(passenger);
                    }
                    else if (isDeletePresed) {
                        if (messageForDelete("Passenger")) {instPassengersService.deleteDataService(passenger);}
                    }
                    EditPassengerTableModel instEditPassengerTableModel = new EditPassengerTableModel(instPassengersService.getAllService());
                    editTableController(drawTable(instEditPassengerTableModel));
                }
            }});
    }

    private Passengers addPassengerObject(JTable table){
        int row = table.getSelectedRow();
        Long id;
        if(isInsertPresed){id = 0l;}
        else {id = (Long) table.getValueAt(row, 0);}
        Passengers passenger = new Passengers();
        passenger.setId(id);
        passenger.setLastName(table.getValueAt(row,1).toString());
        passenger.setFirstName(table.getValueAt(row,2).toString());
        passenger.setPassportNumber(table.getValueAt(row,3).toString());
        passenger.setSex(table.getValueAt(row,4).toString());
        passenger.setBirtday(table.getValueAt(row,5).toString());
        passenger.setCountry(table.getValueAt(row,6).toString());
        passenger.setClassTicket(table.getValueAt(row,7).toString());
        passenger.setFlight_id(Long.parseLong(table.getValueAt(row,8).toString()));
        return passenger;
    }
}

