package view;

import controller.UserJPanelController;
import service.UsersService;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;

/**
 * Created by ПК on 14.12.2016.
 */
public class UserJPanelGUI extends WestJPanelGUI{
    public UserJPanelGUI(){
        this.instUserService = new UsersService();
    }

    protected UsersService instUserService;

    private static boolean loginedUser = false;
    private static boolean inCorrectLogin = false;
    private static boolean inCorrectPassword = false;
    private static String userName = "";

    public static void setLoginedUser(boolean bool){
        loginedUser = bool;
    }
    public static void setInCorrectLogin(boolean bool){
        inCorrectLogin = bool;
    }
    public static void setInCorrectPassword(boolean bool){
        inCorrectPassword = bool;
    }
    public static void setUserName(String name){
        userName = name;
    }

    public static String getUserName() {return userName;}

    //private final Font font = new Font("Verdana", Font.BOLD, 9);

    public void resetAllFlags(){
        loginedUser = false;
        inCorrectLogin = false;
        inCorrectPassword = false;
        userName = "";
        UsersService.setFlagIsAdmin(false);
    }

    public void drawUserJPanel(){
        UserJPanelController instJPanelController = new UserJPanelController();

        userloginJPanel.setVisible(false);
        userloginJPanel.removeAll();
        userloginJPanel.setPreferredSize(userloginJPanelDimension);
        userloginJPanel.setBackground(darkBackGround);
        JLabel userLabel;
        if(loginedUser){
            userloginJPanel.setLayout(new GridLayout(2, 1, 10, 10));
            if(UsersService.flagIsAdmin){
                userLabel = new JLabel();
                userLabel.setText("<html>Welcome " + userName + "!<br> You enterd like Admin.</html>");
                JButton logOut = new JButton("LogOut");
                tuneJLabel(userLabel,fontUserVerdana9Bold,new Dimension(190,50));
                tuneJButton(logOut,fontUserVerdana9Bold,new Dimension(90,15));
                userloginJPanel.add(userLabel);
                userloginJPanel.add(logOut);
                instJPanelController.contrLogOutUserJPanel(logOut);
            }
            else if(!UsersService.flagIsAdmin) {
                String str = "Welcome " + userName;
                userLabel = new JLabel(str);
                tuneJLabel(userLabel,fontUserVerdana9Bold,new Dimension(190,50));
                JButton logOut = new JButton("LogOut");
                tuneJButton(logOut,fontUserVerdana9Bold,new Dimension(90,15));
                userloginJPanel.add(userLabel);
                userloginJPanel.add(logOut);
                instJPanelController.contrLogOutUserJPanel(logOut);
            }
        }
        else if((!UsersService.flagIsAdmin)&&(inCorrectLogin)&&(!loginedUser)){
            userloginJPanel.setLayout(new GridLayout(2, 1, 10, 10));
            String str = "User with this login doesn`t found";
            userLabel = new JLabel(str);
            tuneJLabel(userLabel,fontUserVerdana9Bold,new Dimension(190,50));
            JButton tryOther = new JButton("Enter other login");
            tuneJButton(tryOther,fontUserVerdana9Bold,new Dimension(90,15));
            userloginJPanel.add(userLabel);
            userloginJPanel.add(tryOther);
            instJPanelController.contrLogOutUserJPanel(tryOther);
        }
        else if((!UsersService.flagIsAdmin)&&(inCorrectPassword)&&(!loginedUser)){
            userloginJPanel.setLayout(new GridLayout(2, 1, 10, 10));
            String str = "You enterd incorrect password";
            userLabel = new JLabel(str);
            tuneJLabel(userLabel,fontUserVerdana9Bold,new Dimension(190,50));
            JButton tryOther = new JButton("Try other");
            tuneJButton(tryOther,fontUserVerdana9Bold,new Dimension(90,15));
            userloginJPanel.add(userLabel);
            userloginJPanel.add(tryOther);
            instJPanelController.contrLogOutUserJPanel(tryOther);
        }
        else {
            userloginJPanel.setLayout(new GridLayout(4, 2, 10, 10));
            JLabel loginLabel = new JLabel("LOGIN");
            JLabel passwordLabel = new JLabel("PASSWORD ");
            JTextField loginEnter = new JTextField("admin");
            JPasswordField passwordEnter = new JPasswordField("123456");
            JButton loginButton = new JButton("LOGIN");
            JButton registerButton = new JButton("REGISTER");
            JLabel[] jLabels = {loginLabel,passwordLabel};
            JButton[] jButtons = {loginButton,registerButton};
            JTextField[] jTextFields = {loginEnter,passwordEnter};
            Dimension dimension = new Dimension(75,15);
            Dimension dimensionButtons = new Dimension(90,15);
            for (int i = 0;i < 2; i++){
                tuneJButton(jButtons[i],fontUserVerdana9Bold,dimensionButtons);
                tuneJTextField(jTextFields[i],fontUserVerdana9Bold,dimension);
                tuneJLabel(jLabels[i],fontUserVerdana9Bold,dimension);
            }
            userloginJPanel.add(loginLabel);
            userloginJPanel.add(loginEnter);
            userloginJPanel.add(passwordLabel);
            userloginJPanel.add(passwordEnter);
            userloginJPanel.add(loginButton);
            userloginJPanel.add(registerButton);
            instJPanelController.contrLoginUserJPanel(loginButton,loginEnter,passwordEnter);
            instJPanelController.contrRegisterUserJPanel(registerButton);
        }
        userloginJPanel.setVisible(true);
    }

    public void registerUserMenu(){
        UserJPanelController instUserJPanelController = new UserJPanelController();
        userloginJPanel.setVisible(false);
        userloginJPanel.removeAll();
        userloginJPanel.setPreferredSize(userloginJPanelDimension);
        userloginJPanel.setLayout(new GridLayout(7,2,10,2));
        JLabel enterLoginLabel = new JLabel("ENTER LOGIN");
        JTextField enterLogin = new JTextField();
        JLabel enterPasswordLabel = new JLabel("ENTER PASSWORD ");
        JPasswordField enterPassword = new JPasswordField();
        enterPassword.setEchoChar('*');
        JLabel enterEmailLabel = new JLabel("ENTER EMAIL");
        JTextField enterEmail = new JTextField();
        JLabel enterLastNameLabel = new JLabel("ENTER LASTNAME");
        JTextField enterLastName = new JTextField();
        JLabel enterFirstNameLabel = new JLabel("ENTER FIRSTNAME");
        JTextField enterFirsName = new JTextField();
        JLabel enterSexLabel = new JLabel("CHOSE SEX");
        JComboBox enterSex = new JComboBox(new String[]{"MALE","FEMALE"});
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("CANCEL");
        JLabel[] jLabels = {enterLoginLabel,enterPasswordLabel,enterEmailLabel,enterLastNameLabel,enterFirstNameLabel,enterSexLabel};

        Dimension dimension = new Dimension(75,15);
        for (JLabel label : jLabels) {
            tuneJLabel(label,fontUserVerdana9Bold,dimension);
        }
        JTextField[] jTextFields = {enterLogin,enterPassword,enterEmail,enterLastName,enterFirsName};
        for (JTextField textField : jTextFields) {
            tuneJTextField(textField,fontUserVerdana9Bold,dimension);
        }
        JButton[] jButtons = {okButton,cancelButton};
        Dimension dimensionButtons = new Dimension(90,15);
        for (JButton jButton : jButtons) {
            tuneJButton(jButton,fontUserVerdana9Bold,dimensionButtons);
        }
        enterSex.setUI(new BasicComboBoxUI() {
                @Override
                protected JButton createArrowButton() {
                    return new JButton() {
                        @Override
                        public int getWidth() {
                            return 0;
                        }
                    };
                }
            });
        ((JLabel)enterSex.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
        enterSex.setBackground(darkBackGround);
        enterSex.setFont(fontUserVerdana9Bold);
        enterSex.setAutoscrolls(true);
        enterSex.setAlignmentX(SwingConstants.CENTER);
        userloginJPanel.add(enterLoginLabel);
        userloginJPanel.add(enterLogin);
        userloginJPanel.add(enterPasswordLabel);
        userloginJPanel.add(enterPassword);
        userloginJPanel.add(enterEmailLabel);
        userloginJPanel.add(enterEmail);
        userloginJPanel.add(enterLastNameLabel);
        userloginJPanel.add(enterLastName);
        userloginJPanel.add(enterFirstNameLabel);
        userloginJPanel.add(enterFirsName);
        userloginJPanel.add(enterSexLabel);
        userloginJPanel.add(enterSex);
        userloginJPanel.add(okButton);
        userloginJPanel.add(cancelButton);
        instUserJPanelController.contrConfirmRegisterUserJPanel(okButton,enterLogin,enterPassword,enterEmail,enterLastName,enterFirsName,enterSex);
        instUserJPanelController.contrCancelRegisterUserJPanel(cancelButton);
        userloginJPanel.setVisible(true);
    }

    private void tuneJLabel(JLabel label,Font font,Dimension dimension){
        label.setBackground(darkBackGround);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(font);
        label.setPreferredSize(dimension);
    }

    private void tuneJTextField(JTextField textField,Font font,Dimension dimension){
        textField.setBackground(backGround);
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setFont(font);
        textField.setPreferredSize(dimension);
    }

    private void tuneJButton(JButton jButton,Font font,Dimension dimension){
        jButton.setBackground(darkGreyButton);
        jButton.setForeground(Color.WHITE);
        jButton.setFocusable(false);
        jButton.setHorizontalAlignment(SwingConstants.CENTER);
        jButton.setFont(font);
        jButton.setPreferredSize(dimension);
    }

}
