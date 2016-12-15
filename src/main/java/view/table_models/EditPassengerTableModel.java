package view.table_models;

import model.Passengers;
import view.EditInfoJPanelGUI;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * Created by ПК on 13.12.2016.
 */
public class EditPassengerTableModel extends AbstractTableModel {
    public EditPassengerTableModel(ArrayList<Passengers> passengerses){
        this.passengerses = passengerses;
        try {
            setDataSource();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private ArrayList data = new ArrayList();
    private ArrayList<Passengers> passengerses;


    @Override
    public int getRowCount() {
        return passengerses.size();
    }

    @Override
    public int getColumnCount() {
        return 9;
    }

    @Override
    public String getColumnName(int c) {
        String result = "";
        switch (c) {
            case 0:
                result = "ID";
                break;
            case 1:
                result = "LAST NAME";
                break;
            case 2:
                result = "FIRST NAME";
                break;
            case 3:
                result = "PASSPORT";
                break;
            case 4:
                result = "SEX";
                break;
            case 5:
                result = "BIRTHDAY";
                break;
            case 6:
                result = "COUNTRY";
                break;
            case 7:
                result = "CLASS TICKET";
                break;
            case 8:
                result = "FLIGHT ID";
                break;
        }
        return result;
    }

    public Object getValueAt(int row, int column) {
        synchronized (data) {
            return ((ArrayList)data.get(row)).get(column);
        }
    }

    public boolean isCellEditable(int row, int column) {
        if(EditInfoJPanelGUI.isEditPresed)return true;
        else return false;
    }


    public void setValueAt(
            Object value, int row, int column) {
        synchronized (data) {
            ((ArrayList) data.get(row)).set(column, value);
        }
    }

    public void setDataSource() throws Exception {
        data.clear();
        fireTableStructureChanged();
        for (int i = 0; i < passengerses.size(); i++) {
            ArrayList row = new ArrayList();
            row.add(passengerses.get(i).getId());
            row.add(passengerses.get(i).getLastName());
            row.add(passengerses.get(i).getFirstName());
            row.add(passengerses.get(i).getPassportNumber());
            row.add(passengerses.get(i).getSex());
            row.add(passengerses.get(i).getBirtday());
            row.add(passengerses.get(i).getCountry());
            row.add(passengerses.get(i).getClassTicket());
            row.add(passengerses.get(i).getFlight_id());
            synchronized (data) {
                data.add(row);
                fireTableRowsInserted(
                        data.size() - 1, data.size() - 1);
            }
        }
    }
}
