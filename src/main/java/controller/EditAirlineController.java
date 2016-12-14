package controller;

import model.Airlines;
import view.table_models.EditAirlineTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by ПК on 14.12.2016.
 */
public class EditAirlineController extends EditDataJPanelController{
    public EditAirlineController(){}

    @Override
    public void chooseTableButtons(final JButton airlinesButton, JButton airplanesButton, JButton flightsButton, JButton passengersButton, JButton usersButton, final JButton[] arrButtons) {
        for (JButton button:arrButtons){button.setBackground(backGround);}
        airlinesButton.setBackground(red);
        EditAirlineTableModel instEditAirlineTableModel = new EditAirlineTableModel(instAirlineService.getAllService());
        JTable allJTable = drawTable(instEditAirlineTableModel);
        int columnsQuantity = allJTable.getColumnCount();
        if (isInsertPresed){
            JTable insertJTable = drawInsertNewDataTable(columnsQuantity);
            insertNewDataController(confirmInsertNewDataButton,insertJTable);
        }
        else{
            editTableController(allJTable);
        }
    }


    private void editTableController(final JTable table) {
        table.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    Airlines airline = addAirlineObject(table);
                    if (isEditPresed) {
                        instAirlineService.editAirlineService(airline);
                    }
                    else if (isDeletePresed) {
                        if (messageForDelete("Airline")) {instAirlineService.deleteDataService(airline);}
                    }
                    EditAirlineTableModel instEditAirlineTableModel = new EditAirlineTableModel(instAirlineService.getAllService());
                    editTableController(drawTable(instEditAirlineTableModel));
                }
            }});
    }

    private void insertNewDataController(final JButton button, final JTable table){
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Airlines airline = addAirlineObject(table);
                instAirlineService.insertNewService(airline);
                EditAirlineTableModel instEditAirlineTableModel = new EditAirlineTableModel(instAirlineService.getAllService());
                editTableController(drawTable(instEditAirlineTableModel));
            }
        });

    }

    private Airlines addAirlineObject(JTable table){
        int row = table.getSelectedRow();
        Long id;
        if(isInsertPresed){id = 0l;}
        else {id = (Long) table.getValueAt(row, 0);}
        Airlines airline = new Airlines();
        airline.setId(id);
        airline.setName(table.getValueAt(row, 1).toString());
        airline.setAdress(table.getValueAt(row, 2).toString());
        airline.setTelephone(table.getValueAt(row, 3).toString());
        airline.setWebsite(table.getValueAt(row, 4).toString());
        return airline;
    }
}
