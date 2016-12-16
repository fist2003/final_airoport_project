package view;

import view.table_models.AirlinesTableModel;
import view.table_models.CellRenderer;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ПК on 16.12.2016.
 */
public class AirlinesJPanelGUI extends EastJPanelGUI {
    public AirlinesJPanelGUI(){}

    private String airlinesInfoStr = "AIRLINES";
    private String infoStr = "LIST OF ALL AIRLINES PRESENT IN ODESSA INTERNATIONAL AIRPORT";

    public void drawJPanel() {
        super.drawJPanel();
        /*
        eastJPanel.setVisible(false);
        eastJPanel.removeAll();
        eastJPanel.setBackground(darkBackGround);
        eastJPanel.setPreferredSize(eastJPanelDimension);
        eastJPanel.setLayout(new BorderLayout());
        eastJPanel.add(topEastJPanel,BorderLayout.NORTH);
        eastJPanel.add(middleEastJPanel,BorderLayout.CENTER);
        eastJPanel.add(bottomEastJPanel,BorderLayout.SOUTH);

        eastJPanel.setVisible(true);
        */
        drawTopEastJPanel();
        drawBottomEastJPanel();
    }

    public void drawTopEastJPanel(){

        topEastJPanel.setVisible(false);
        topEastJPanel.removeAll();
        topEastJPanel.setPreferredSize(topEastJPanelDimension);
        topEastJPanel.setBackground(darkBackGround);
        topEastJPanel.setLayout(new BorderLayout());
        northBoardJPanel.setVisible(false);
        northBoardJPanel.removeAll();
        northBoardJPanel.setLayout(new BorderLayout());
        northBoardJPanel.setBackground(backGround);
        northBoardJPanel.setPreferredSize(northBoardJPanelDimension);
        JButton airlinesButton = new JButton(airlinesInfoStr);
        airlinesButton.setBackground(red);
        airlinesButton.setPreferredSize(buttontTopDimension350x50);
        airlinesButton.setFocusable(false);
        northBoardJPanel.add(airlinesButton,BorderLayout.WEST);
        northBoardJPanel.setVisible(true);
        topEastJPanel.add(northBoardJPanel,BorderLayout.NORTH);
        AirlinesTableModel instAirlinesTableModel = new AirlinesTableModel(instAirlineService.getAllService());
        JTable table = drawTable(instAirlinesTableModel);
        CellRenderer instRender = new CellRenderer();
        instRender.setBackground(backGround);
        table.getColumnModel().getColumn(1).setCellRenderer(instRender);
        topEastJPanel.add(centerBoardJPanel,BorderLayout.CENTER);
        drawSouthBoardJPanel();
        topEastJPanel.add(southBoardJPanel,BorderLayout.SOUTH);
        topEastJPanel.setVisible(true);
    }

    protected void drawSouthBoardJPanel() {
        southBoardJPanel.setVisible(false);
        southBoardJPanel.removeAll();
        southBoardJPanel.setPreferredSize(southBoardJPanelDimension);
        southBoardJPanel.setBackground(darkBackGround);
        southBoardJPanel.setLayout(new BorderLayout());
        JLabel infoJLabel = new JLabel(infoStr);
        infoJLabel.setBackground(darkBackGround);
        infoJLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        infoJLabel.setFont(fontVerdana11Bold);
        infoJLabel.setPreferredSize(new Dimension(450,50));
        southBoardJPanel.add(infoJLabel,BorderLayout.WEST);
        southBoardJPanel.setVisible(true);
    }
}
