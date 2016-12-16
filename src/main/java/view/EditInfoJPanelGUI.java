package view;

import controller.EditDataJPanelController;
import controller.PassengersListController;
import service.AirlineService;
import service.PassengersListService;
import view.EastJPanelGUI;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

import static javax.swing.SwingConstants.CENTER;

/**
 * Created by ПК on 13.12.2016.
 */
public class EditInfoJPanelGUI extends EastJPanelGUI {
    public EditInfoJPanelGUI(){}


    private String insertNewStr = "INSERT NEW";
    private String editDataStr = "EDIT DATA";
    private String deleteDataStr = "DELETE DATA";

    protected final String airlinesStr = "AIRLINES";
    protected final String airplanesStr = "AIRPLANES";
    protected final String flightsStr = "FLIGHTS";
    protected final String passengersStr = "PASSENGERS";
    protected final String usersStr = "USERS";

    protected JButton confirmInsertNewDataButton = new JButton("INSERT NEW DATA");

    public static boolean isInsertPresed = true;
    public static boolean isEditPresed = false;
    public static boolean isDeletePresed = false;

    @Override
    public void drawJPanel() {
        eastJPanel.setVisible(false);
        eastJPanel.removeAll();
        eastJPanel.setBackground(darkBackGround);
        eastJPanel.setPreferredSize(eastJPanelDimension);
        eastJPanel.setLayout(new BorderLayout());
        eastJPanel.add(topEastJPanel,BorderLayout.NORTH);
        eastJPanel.add(middleEastJPanel,BorderLayout.CENTER);
        eastJPanel.add(bottomEastJPanel,BorderLayout.SOUTH);
        drawTopEastJPanel();
        drawBottomEastJPanel();
        cleanMeadleEastJPanel();
        eastJPanel.setVisible(true);
    }



    public void drawTopEastJPanel(){
        EditDataJPanelController instEditDataJPanelController = new EditDataJPanelController();
        topEastJPanel.setVisible(false);
        topEastJPanel.removeAll();
        topEastJPanel.setPreferredSize(topEastJPanelDimension);
        topEastJPanel.setBackground(darkBackGround);
        topEastJPanel.setLayout(new BorderLayout());
        northBoardJPanel.setVisible(false);
        northBoardJPanel.removeAll();
        northBoardJPanel.setLayout(new GridLayout(1,3,10,0));
        northBoardJPanel.setBackground(backGround);
        northBoardJPanel.setPreferredSize(northBoardJPanelDimension);
        JButton insertNewButton = new JButton(insertNewStr);
        JButton editDataButton = new JButton(editDataStr);
        JButton deleteButton = new JButton(deleteDataStr);
        JButton[] arr = {insertNewButton,editDataButton,deleteButton};
        addPropertiesToTopButtons(arr,buttontTopDimension200x50,northBoardJPanel);
        northBoardJPanel.setVisible(true);
        topEastJPanel.add(northBoardJPanel,BorderLayout.NORTH);
        centerBoardJPanel.setVisible(false);
        centerBoardJPanel.removeAll();
        centerBoardJPanel.setPreferredSize(centerBoardJPanelDimension);
        centerBoardJPanel.setBackground(red);
        centerBoardJPanel.setVisible(true);
        topEastJPanel.add(centerBoardJPanel,BorderLayout.CENTER);
        drawSouthBoardJPanel();
        topEastJPanel.add(southBoardJPanel,BorderLayout.SOUTH);
        instEditDataJPanelController.topButtonsController(insertNewButton,editDataButton,
                deleteButton);
        instEastJPanelController.topButtonsMouseController(insertNewButton);
        instEastJPanelController.topButtonsMouseController(editDataButton);
        instEastJPanelController.topButtonsMouseController(deleteButton);
        insertNewButton.setBackground(red);
        topEastJPanel.setVisible(true);
    }

    protected void drawSouthBoardJPanel() {
        EditDataJPanelController instEditDataJPanelController = new EditDataJPanelController();
        southBoardJPanel.setVisible(false);
        southBoardJPanel.removeAll();
        southBoardJPanel.setPreferredSize(southBoardJPanelDimension);
        southBoardJPanel.setBackground(darkBackGround);
        FlowLayout layout = new FlowLayout();
        layout.setVgap(20);
        layout.setHgap(50);
        southBoardJPanel.setLayout(layout);
        JButton airlinesButton = new JButton(airlinesStr);
        JButton airplanesButton = new JButton(airplanesStr);
        JButton flightsButton = new JButton(flightsStr);
        JButton passengersButton = new JButton(passengersStr);
        JButton usersButton = new JButton(usersStr);
        JButton[] buttons = {airlinesButton,airplanesButton,flightsButton,passengersButton,usersButton};
        addPropertiesToTopButtons(buttons,buttontDimension100x30,southBoardJPanel);
        for (JButton button:buttons){button.setBackground(backGround);}
        instEditDataJPanelController.chooseTableButtons(airlinesButton,airplanesButton,flightsButton,passengersButton,usersButton,buttons);
        southBoardJPanel.setVisible(true);
    }

    @Override
    protected void drawBottomEastJPanel() {
        super.drawBottomEastJPanel();
    }

    protected JTable drawInsertNewDataTable(int columnsQuantity){
        drawBottomEastJPanel();
        FlowLayout layout = new FlowLayout();
        layout.setVgap(5);
        layout.setHgap(5);
        bottomEastJPanel.setLayout(layout);
        String[] columns = new String[columnsQuantity];
        for(int i = 0 ; i < columnsQuantity; i++){
            columns[i] = "";
        }
        Object[][] data = {columns};
        data[0][0] = "auto";
        JTable table = new JTable(data,columns);
        addPropertiesToTable(table);
        table.setBackground(backGround);
        table.setPreferredSize(tableInsertNewDimension850x25);
        table.setShowVerticalLines(true);
        table.setEditingColumn(0);
        bottomEastJPanel.add(table);
        confirmInsertNewDataButton.setBackground(backGround);
        confirmInsertNewDataButton.setPreferredSize(buttontInsertNewDimension850x15);
        confirmInsertNewDataButton.setFocusable(false);
        bottomEastJPanel.add(confirmInsertNewDataButton);
        bottomEastJPanel.setVisible(true);
        return table;
    }
}
