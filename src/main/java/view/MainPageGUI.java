package view;


import javax.swing.*;
import java.awt.*;

/**
 * Created by ПК on 08.12.2016.
 */
public class MainPageGUI implements GUIInterface {
    public MainPageGUI() {
    }

    protected final Font fontVerdana11Bold = new Font("Verdana", Font.BOLD, 11);
    protected final Font timeFrameVerdana10Bold = new Font("Verdana", Font.BOLD, 10);
    protected final Font fontUserVerdana9Bold = new Font("Verdana", Font.BOLD, 9);


    protected final Dimension tableInsertNewDimension850x25 = new Dimension(850,25);
    protected final Dimension buttontInsertNewDimension850x15 = new Dimension(850,15);
    protected final Dimension buttontTopDimension350x50 = new Dimension(350,50);
    protected final Dimension buttontTopDimension200x50 = new Dimension(200,50);
    protected final Dimension buttontDimension100x30 = new Dimension(100,30);
    protected final Dimension buttontSearchDimension200x15 = new Dimension(200,15);

    protected final Dimension panelTopSouthScheduleJpanelDimension850x25 = new Dimension(850,25);
    protected final Dimension panelBottomSouthScheduleJpanelDimension850x50 = new Dimension(850,50);
    protected final Dimension panelBottomJpanelDimension850x20 = new Dimension(850,20);
    protected final Dimension timeFrameLabelScheduleJpanelDimension500x15 = new Dimension(500,15);
    protected final Dimension jsliderScheduleJpanelDimension850x45 = new Dimension(850,45);

    public void drawJPanel() {
        WestJPanelGUI instWestJPanelGUI = new WestJPanelGUI();
        EastJPanelGUI instEastJPanelGUI = new EastJPanelGUI();
        jfrm.setSize(jfrmDimension);
        jfrm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{210, 660};
        gridBagLayout.rowHeights = new int[]{460};
        mainJPanel.removeAll();
        mainJPanel.setVisible(true);
        mainJPanel.setLayout(gridBagLayout);
        mainJPanel.setBackground(backGround);
        mainJPanel.setSize(jfrmDimension);
        mainJPanel.add(westJPanel);
        mainJPanel.add(eastJPanel);
        JScrollPane srl = new JScrollPane(mainJPanel);
        srl.setAutoscrolls(true);
        jfrm.add(srl);
        instWestJPanelGUI.drawJPanel();
        instEastJPanelGUI.drawJPanelStart();
        jfrm.setVisible(true);
    }
}
