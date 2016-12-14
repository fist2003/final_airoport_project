package controller;

import model.Users;
import service.UsersService;
import view.UserJPanelGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ПК on 14.12.2016.
 */
public class UserJPanelController extends UserJPanelGUI {
    public UserJPanelController(){}

    public void contrCancelRegisterUserJPanel(JButton cancel){
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawUserJPanel();
            }
        });
    }

    public void contrLoginUserJPanel(JButton button, final JTextField loginField, final JPasswordField passwordField){
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final String loginStr = loginField.getText();
                final String passwordStr = passwordField.getText();
                Users user = new Users();
                user.setLogin(loginStr);
                user.setPassword(passwordStr);
                boolean[] checklog = instUserService.checkUser(user);
                if ((checklog[0]) && (checklog[1])) {
                    if (checklog[2]) {
                        UsersService.setFlagIsAdmin(true);
                        setLoginedUser(true);
                        setUserName(loginStr);
                        drawJPanel();
                    } else {
                        UsersService.setFlagIsAdmin(false);
                        setLoginedUser(true);
                        setUserName(loginStr);
                        drawJPanel();
                    }
                } else if (!checklog[0]) {
                    UsersService.setFlagIsAdmin(false);
                    setLoginedUser(false);
                    setInCorrectLogin(true);
                    drawJPanel();

                } else if (!checklog[1]) {
                    UsersService.setFlagIsAdmin(false);
                    setLoginedUser(false);
                    setInCorrectPassword(true);
                    drawJPanel();
                }
            }
        });
    }

    public void contrLogOutUserJPanel(JButton button){
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetAllFlags();
                drawJPanel();
            }
        });
    }

    public void contrRegisterUserJPanel(JButton button){
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUserMenu();
            }
        });
    }

    public void contrConfirmRegisterUserJPanel(JButton ok, final JTextField enterLogin, final JPasswordField enterPassword,
                                               final JTextField emailTextField, final JTextField enterLastName,
                                               final JTextField enterFirsName, final JComboBox enterSex){
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Users user = new Users(0l,enterLogin.getText(), enterPassword.getText(),emailTextField.getText(),
                        enterLastName.getText(), enterFirsName.getText(), enterSex.getSelectedItem().toString(),0);
                if(instUserService.checkUser(user)[0]){
                    JOptionPane.showMessageDialog(jfrm, "User with this login is already exist");
                }
                else if(enterPassword.getText().length() < 5){
                    JOptionPane.showMessageDialog(jfrm, "Password can`t be smaller than 5 characters in length");
                }
                else {
                    instUserService.insertNewService(user);
                    JOptionPane.showMessageDialog(jfrm, "New user " + enterLogin.getText() + " was created");
                    UserJPanelGUI.setUserName(enterLogin.getText());
                    UserJPanelGUI.setLoginedUser(true);
                    westJPanel.setVisible(false);
                    userloginJPanel.setVisible(false);
                    userloginJPanel.removeAll();
                    drawUserJPanel();
                    westJPanel.setVisible(true);
                }
            }
        });
    }
}
