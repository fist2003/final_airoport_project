package controller;

import dao.ConnectToMySQLDAO;
import service.PutStartDataService;
import view.GUIInterface;
import view.MainPageGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ПК on 14.12.2016.
 */
public class StartMenuController extends ConnectToMySQLDAO implements GUIInterface {
    public StartMenuController(){}

    public void contrOkStartMenu(JButton button, final JPasswordField passwordField, final JRadioButton radioButton){
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String password = passwordField.getText();
                boolean flagError = false;
                if(!radioButton.isSelected()){
                    boolean check = connectToDB(password);
                    if(check){
                        mainJPanel.setVisible(false);
                        MainPageGUI instMainPageGUI = new MainPageGUI();
                        instMainPageGUI.drawJPanel();
                    }
                    else{
                        flagError = true;
                    }
                }
                else if(radioButton.isSelected()){
                    boolean check = createSQLDB(password);
                    if(check){
                        connectToDB(password);
                        addTablesToDataBase();
                        PutStartDataService instPutStartDataService = new PutStartDataService();
                        instPutStartDataService.putStartData();
                        mainJPanel.setVisible(false);
                        MainPageGUI instMainPageGUI = new MainPageGUI();
                        instMainPageGUI.drawJPanel();
                    }
                    else {
                        flagError = true;
                    }
                }
                if (flagError){
                    JOptionPane.showMessageDialog(jfrm, "Connection faile, Check your password or Put Start Iformation");
                }
            }
        });
    }

    public void contrExitStartMenu(JButton button){
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jfrm.setVisible(false);
                System.exit(0);
            }
        });
    }
}
