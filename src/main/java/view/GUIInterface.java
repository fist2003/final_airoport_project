package view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ПК on 08.12.2016.
 */
public interface GUIInterface {

    Color backGround = new Color(240,240,240);
    Color red = new Color(180,24,36);
    Color darkBackGround = new Color(215,215,215);
    Color darkGreyButton = new Color(56,56,56);

    JFrame jfrm = new JFrame();
    Dimension jfrmDimension = new Dimension(1100,500);
    Dimension jfrmStartDimension = new Dimension(300,100);

    JPanel mainJPanel = new JPanel();
    Dimension mainJPanelDimension = new Dimension(jfrmDimension);

    JPanel westJPanel = new JPanel();
    Dimension westJPanelDimension = new Dimension(200,450);

    JPanel eastJPanel = new JPanel();
    Dimension eastJPanelDimension = new Dimension(850,450);

    JPanel userloginJPanel = new JPanel();
    Dimension userloginJPanelDimension = new Dimension(200,125);

    JPanel menuJPanel = new JPanel();
    Dimension menuJPanelDimension = new Dimension(200,325);

    JPanel topEastJPanel = new JPanel();
    Dimension topEastJPanelDimension = new Dimension(850,130);

    JPanel middleEastJPanel = new JPanel();
    Dimension middleEastJPanelDimension = new Dimension(850,270);

    JPanel bottomEastJPanel = new JPanel();
    Dimension bottomEastJPanelDimension = new Dimension(850,50);

    JPanel northBoardJPanel = new JPanel();
    Dimension northBoardJPanelDimension = new Dimension(850,50);

    JPanel centerBoardJPanel = new JPanel();
    Dimension centerBoardJPanelDimension = new Dimension(850,5);

    JPanel southBoardJPanel = new JPanel();
    Dimension southBoardJPanelDimension = new Dimension(850,75);

    /*
    Color backGround = new Color(240,240,240);
    Color red = new Color(180,24,36);
    Color darkBackGround = new Color(215,215,215);
    Color darkGreyButton = new Color(56,56,56);

    JFrame jfrm = new JFrame();
    Dimension jfrmDimension = new Dimension(900,500);

    JPanel mainJPanel = new JPanel();
    Dimension mainJPanelDimension = new Dimension(jfrmDimension);

    JPanel westJPanel = new JPanel();
    Dimension westJPanelDimension = new Dimension(200,450);

    JPanel eastJPanel = new JPanel();
    Dimension eastJPanelDimension = new Dimension(650,450);

    JPanel userloginJPanel = new JPanel();
    Dimension userloginJPanelDimension = new Dimension(200,125);

    JPanel menuJPanel = new JPanel();
    Dimension menuJPanelDimension = new Dimension(200,325);

    JPanel topEastJPanel = new JPanel();
    Dimension topEastJPanelDimension = new Dimension(650,130);

    JPanel middleEastJPanel = new JPanel();
    Dimension middleEastJPanelDimension = new Dimension(650,270);

    JPanel bottomEastJPanel = new JPanel();
    Dimension bottomEastJPanelDimension = new Dimension(650,50);

    JPanel northBoardJPanel = new JPanel();
    Dimension northBoardJPanelDimension = new Dimension(650,50);

    JPanel centerBoardJPanel = new JPanel();
    Dimension centerBoardJPanelDimension = new Dimension(650,10);

    JPanel southBoardJPanel = new JPanel();
    Dimension southBoardJPanelDimension = new Dimension(650,70);
     */

}
