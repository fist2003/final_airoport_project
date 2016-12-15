package controller;

import model.Airlines;
import view.table_models.EditAirlineTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

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
                        ArrayList<String> check = new ArrayList<String>(instAirlineService.editAirlineService(airline));
                        if(check.size() == 0){messageSuccessful();}
                        else {
                            messageWrongInputData(check);}
                    }
                    else if (isDeletePresed) {
                        if (messageForDelete("Airline")) {
                            ArrayList<Long> listAirplanesId = new ArrayList<Long>(instAirlineService.deleteDataService(airline));
                            if (listAirplanesId.size() != 0) {messageOfRegisteredAirplanesInAirline(listAirplanesId);}
                        }
                    }
                    }
                    EditAirlineTableModel instEditAirlineTableModel = new EditAirlineTableModel(instAirlineService.getAllService());
                    editTableController(drawTable(instEditAirlineTableModel));
                }
            });
    }


    private void insertNewDataController(final JButton button, final JTable table){
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Airlines airline = addAirlineObject(table);
                ArrayList<String> check = new ArrayList<String>(instAirlineService.insertNewService(airline));
                if(check.size() == 0){messageSuccessful();}
                else {
                    messageWrongInputData(check);}
                EditAirlineTableModel instEditAirlineTableModel = new EditAirlineTableModel(instAirlineService.getAllService());
                editTableController(drawTable(instEditAirlineTableModel));
            }
        });

    }

    private Airlines addAirlineObject(JTable table){
        int row = 0;
        Long id;
        if(isInsertPresed){id = 0l;}
        else {
            row = table.getSelectedRow();
            id = (Long) table.getValueAt(row, 0);}
        Airlines airline = new Airlines();
        airline.setId(id);
        airline.setName(table.getValueAt(row, 1).toString());
        airline.setAdress(table.getValueAt(row, 2).toString());
        airline.setTelephone(table.getValueAt(row, 3).toString());
        airline.setWebsite(table.getValueAt(row, 4).toString());
        return airline;
    }

    private void messageOfRegisteredAirplanesInAirline(ArrayList<Long> listAirplanesId){
        String eror = "You cann`t delete this airline. Airplane(s) with id: ";
        for(Long airplaneId : listAirplanesId) {
            eror = eror + airplaneId + ", ";
        }
        eror = eror + "are registered in it;";
        JOptionPane.showMessageDialog(jfrm,eror);
    }

}
