package view;

import controller.EastJPanelController;
import model.Airplanes;
import model.Users;
import service.*;
import view.table_models.CellRenderer;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.JTextComponent;
import java.awt.*;

/**
 * Created by ПК on 09.12.2016.
 */
public class EastJPanelGUI extends MainPageGUI {
    public EastJPanelGUI() {
        this.instEastJPanelController = new EastJPanelController();
        this.instAirlineService = new AirlineService();
        this.instAirplaneService = new AirplaneService();
        this.instFlightService = new FlightService();
        this.instPassengersService = new PassengersService();
        this.instUsersService = new UsersService();
    }

    protected EastJPanelController instEastJPanelController;
    protected AirlineService instAirlineService;
    protected AirplaneService instAirplaneService;
    protected FlightService instFlightService;
    protected PassengersService instPassengersService;
    protected UsersService instUsersService;


    protected static boolean topButtonFlag = false;

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
        eastJPanel.setVisible(true);
    }

    public void drawJPanelStart() {
        ScheduleJPanelGUI instScheduleJPanelGUI = new ScheduleJPanelGUI();
        instScheduleJPanelGUI.drawJPanel();
    }

    public JTable drawTable(AbstractTableModel model){
        cleanMeadleEastJPanel();
        JTable table = new JTable(model);
        addPropertiesToTable(table);
        JScrollPane scrl = new JScrollPane(table);
        scrl.setPreferredSize(middleEastJPanelDimension);
        scrl.setBorder(BorderFactory.createEmptyBorder());
        scrl.getVerticalScrollBar();
        scrl.getHorizontalScrollBar();
        middleEastJPanel.add(scrl);
        middleEastJPanel.setVisible(true);
        return table;
    }

    protected void addPropertiesToTable(JTable table){
        table.setShowGrid(false);
        table.setBackground(backGround);
        table.setAutoCreateRowSorter(true);
        table.setShowHorizontalLines(true);
        table.setRowSelectionAllowed(false);
        table.setRowHeight(30);
        final TableCellRenderer headerRenderer = table.getTableHeader().getDefaultRenderer();
        TableCellRenderer renderer = new TableCellRenderer() {
            private final Border border = BorderFactory.createEmptyBorder();
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = headerRenderer.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);
                if (c instanceof JComponent)
                    ((JComponent)c).setBorder(border);
                return c;
            }
        };
        table.setDefaultRenderer(table.getColumnClass(1), new DefaultTableCellRenderer(){
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                super.setHorizontalAlignment(SwingConstants.CENTER);
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                return this;
            }
        });
        table.getTableHeader().setDefaultRenderer(renderer);
    }

    protected void addPropertiesToTopButtons(JButton[] arr,Dimension buttonDimension,JPanel panel){
        for (JButton button:arr) {
            button.setBackground(darkBackGround);
            button.setPreferredSize(buttonDimension);
            button.setFocusable(false);
            panel.add(button);
        }
    }

    protected void tuneJLabelsAndAddToJPanel(JLabel[] jLabels) {
        for (JLabel label : jLabels) {
            label.setBackground(darkBackGround);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setFont(fontVerdana11Bold);
            southBoardJPanel.add(label);
        }
    }

    protected void tuneComboBoxesAndAddToJPanel(JComboBox[] arrComboBox){
        for (JComboBox combo:arrComboBox) {
            combo.setUI(new BasicComboBoxUI() {
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
            ((JLabel)combo.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
            combo.setBackground(darkBackGround);
            combo.setFont(fontVerdana11Bold);
            combo.setMaximumRowCount(5);
            combo.setAutoscrolls(true);
            combo.setAlignmentX(SwingConstants.CENTER);
            southBoardJPanel.add(combo);
        }
    }

    protected void cleanMeadleEastJPanel(){
        middleEastJPanel.setVisible(false);
        middleEastJPanel.removeAll();
        middleEastJPanel.setPreferredSize(middleEastJPanelDimension);
        middleEastJPanel.setBackground(backGround);
        middleEastJPanel.setVisible(true);
    }

    protected void drawBottomEastJPanel(){
        bottomEastJPanel.setVisible(false);
        bottomEastJPanel.removeAll();
        bottomEastJPanel.setPreferredSize(bottomEastJPanelDimension);
        bottomEastJPanel.setBackground(darkBackGround);
        bottomEastJPanel.setVisible(true);
    }


}
