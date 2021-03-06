package controller;

import model.Users;
import view.table_models.EditUsersTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by ПК on 14.12.2016.
 */
public class EditUserController extends EditDataJPanelController {
    public EditUserController(){}

    @Override
    public void chooseTableButtons(final JButton airlinesButton, JButton airplanesButton, JButton flightsButton, JButton passengersButton, JButton usersButton, final JButton[] arrButtons) {
        for (JButton button:arrButtons){button.setBackground(backGround);}
        usersButton.setBackground(red);
        EditUsersTableModel instEditUsersTableModel = new EditUsersTableModel(instUsersService.getAllService());
        JTable allJTable = drawTable(instEditUsersTableModel);
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
                Users user = addUserObject(table);
                ArrayList<String> check = new ArrayList<String>(instUsersService.insertNewService(user));
                if(check.size() == 0){messageSuccessful();}
                else {messageWrongInputData(check);}
                EditUsersTableModel instEditUsersTableModel = new EditUsersTableModel(instUsersService.getAllService());
                editTableController(drawTable(instEditUsersTableModel));
            }
        });
    }

    private void editTableController(final JTable table) {
        table.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    Users user = addUserObject(table);
                    if (isEditPresed) {
                        ArrayList<String> check = new ArrayList<String>(instUsersService.editDataService(user));
                        if(check.size() == 0){messageSuccessful();}
                        else {
                            messageWrongInputData(check);}
                    }
                    else if (isDeletePresed) {
                        if (messageForDelete("User")) {
                            ArrayList<Long> listFlightsId = new ArrayList<Long>(instUsersService.deleteDataService(user));
                            if (listFlightsId.size() != 0) {messageForUserDelete();}
                        }
                    }
                    EditUsersTableModel instEditUsersTableModel = new EditUsersTableModel(instUsersService.getAllService());
                    editTableController(drawTable(instEditUsersTableModel));
                }
            }});
    }

    private Users addUserObject(JTable table){
        int row = 0;
        Long id;
        if(isInsertPresed){id = 0l;}
        else {
            row = table.getSelectedRow();
            id = (Long) table.getValueAt(row, 0);}
        Users user = new Users();
        user.setId(id);
        user.setLogin(table.getValueAt(row,1).toString());
        user.setPassword(table.getValueAt(row,2).toString());
        user.setEmail(table.getValueAt(row,3).toString());
        user.setLastName(table.getValueAt(row,4).toString());
        user.setFirstName(table.getValueAt(row,5).toString());
        user.setSex(table.getValueAt(row,6).toString());
        if(instUsersService.checkInputNumber(table.getValueAt(row,7).toString())){user.setIsAdmin(Integer.parseInt(table.getValueAt(row,7).toString()));}
        else user.setIsAdmin(-1);
        return user;
    }

    private void messageForUserDelete(){
        String eror = "You cann`t delete your user acount while you are login";
        JOptionPane.showMessageDialog(jfrm,eror);
    }
}

