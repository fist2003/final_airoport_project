package controller;

import view.EditInfoJPanelGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by ПК on 13.12.2016.
 */
public class EditDataJPanelController extends EditInfoJPanelGUI {
    public EditDataJPanelController(){}

    public void topButtonsController(final JButton insert, final JButton edit, final JButton delete){
        insert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawSouthBoardJPanel();
                insert.setBackground(red);
                edit.setBackground(darkBackGround);
                delete.setBackground(darkBackGround);
                cleanMeadleEastJPanel();
                isInsertPresed = true;
                isEditPresed = false;
                isDeletePresed = false;
            }
        });
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawSouthBoardJPanel();
                drawBottomEastJPanel();
                insert.setBackground(darkBackGround);
                edit.setBackground(red);
                delete.setBackground(darkBackGround);
                cleanMeadleEastJPanel();
                isInsertPresed = false;
                isEditPresed = true;
                isDeletePresed = false;
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawBottomEastJPanel();
                drawSouthBoardJPanel();
                insert.setBackground(darkBackGround);
                edit.setBackground(darkBackGround);
                delete.setBackground(red);
                cleanMeadleEastJPanel();
                isInsertPresed = false;
                isEditPresed = false;
                isDeletePresed = true;
            }
        });
    }

    public void chooseTableButtons(final JButton airlinesButton, final JButton airplanesButton, final JButton flightsButton,
                                   final JButton passengersButton, final JButton usersButton,final JButton[] arrButtons){
        airlinesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditAirlineController instEditAirlineController = new EditAirlineController();
                instEditAirlineController.chooseTableButtons
                        (airlinesButton, airplanesButton, flightsButton, passengersButton, usersButton,arrButtons);
            }
        });
        airplanesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditAirplaneController instEditAirplaneController = new EditAirplaneController();
                instEditAirplaneController.chooseTableButtons
                        (airlinesButton, airplanesButton, flightsButton, passengersButton, usersButton, arrButtons);
            }
        });
        flightsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditFlightsController instEditFlightsController = new EditFlightsController();
                instEditFlightsController.chooseTableButtons
                        (airlinesButton, airplanesButton, flightsButton, passengersButton, usersButton, arrButtons);
            }
        });
        passengersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditPassengersController instEditPassengersController = new EditPassengersController();
                instEditPassengersController.chooseTableButtons
                        (airlinesButton, airplanesButton, flightsButton, passengersButton, usersButton, arrButtons);
            }
        });
        usersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditUserController instEditUserController = new EditUserController();
                instEditUserController.chooseTableButtons
                        (airlinesButton, airplanesButton, flightsButton, passengersButton, usersButton, arrButtons);
            }
        });

    }

    protected boolean messageForDelete(String raw){
        int n = JOptionPane.showConfirmDialog(jfrm,
                "Do you really want delete this " + raw,"from data base?",
                JOptionPane.YES_NO_OPTION);
        if (n == 0){return true;}
        else return false;
    }

    protected void messageSuccessful(){
        JOptionPane.showMessageDialog(jfrm,"Entered value is correct!");
    }

    protected void messageWrongInputData(ArrayList<String> listAirplanesNames){
        String eror = "You entered wrong data: ";
        for(String value : listAirplanesNames) {
            eror = eror + value + ", ";
        }
        eror = eror + ". Please try input correct values";
        JOptionPane.showMessageDialog(jfrm,eror);
    }


}
