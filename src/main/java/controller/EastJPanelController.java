package controller;

import view.GUIInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by ПК on 11.12.2016.
 */
public class EastJPanelController implements GUIInterface{
    public EastJPanelController(){}

    public void topButtonsMouseController(final JButton button){
        final Color buttonColor = button.getBackground();
        final boolean[] flag = {false};
        button.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                flag[0] =true;
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(red);
                flag[0] = false;}

            @Override
            public void mouseExited(MouseEvent e) {
                if(!flag[0]){
                    button.setBackground(buttonColor);}
            }
        });
    }

}
