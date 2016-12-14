package view;

import controller.StartMenuController;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ПК on 14.12.2016.
 */
public class StartMenuGUI implements GUIInterface {
    public StartMenuGUI() {
        jfrm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jfrm.setSize(jfrmStartDimension);
        this.instStartMenuController = new StartMenuController();
    }

    private StartMenuController instStartMenuController;

    public void startMenu() {
        jfrm.setVisible(false);
        mainJPanel.setLayout(new BorderLayout());
        mainJPanel.setSize(jfrmStartDimension);
        mainJPanel.setBackground(darkBackGround);
        JPanel northStartMenuJPanel = new JPanel();
        JPanel centerStartMenuJPanel = new JPanel();
        JPanel southStartMenuJPanel = new JPanel();
        String topJLabelStr = "PLEASE ENTER PASSWORD FROM MySQL SERVER:";
        JLabel topJlabel = new JLabel(topJLabelStr);
        topJlabel.setBackground(darkBackGround);
        northStartMenuJPanel.add(topJlabel);
        JPasswordField inputPasswordFromSQL = new JPasswordField("password");
        inputPasswordFromSQL.setBackground(backGround);
        centerStartMenuJPanel.setLayout(new BorderLayout());
        centerStartMenuJPanel.add(inputPasswordFromSQL, BorderLayout.CENTER);
        JButton ok = new JButton("OK");
        JButton exit = new JButton("EXIT");
        ok.setBackground(darkGreyButton);
        ok.setForeground(Color.WHITE);
        exit.setBackground(darkGreyButton);
        exit.setForeground(Color.WHITE);
        JRadioButton putStartDataRadioButton = new JRadioButton("Put start Data");
        southStartMenuJPanel.setLayout(new GridLayout(1, 3));
        southStartMenuJPanel.add(putStartDataRadioButton);
        southStartMenuJPanel.add(ok);
        southStartMenuJPanel.add(exit);
        instStartMenuController.contrOkStartMenu(ok, inputPasswordFromSQL, putStartDataRadioButton);
        instStartMenuController.contrExitStartMenu(exit);
        mainJPanel.add(northStartMenuJPanel, BorderLayout.NORTH);
        mainJPanel.add(centerStartMenuJPanel, BorderLayout.CENTER);
        mainJPanel.add(southStartMenuJPanel, BorderLayout.SOUTH);
        mainJPanel.setVisible(true);
        jfrm.add(mainJPanel);
        jfrm.setVisible(true);
    }
}