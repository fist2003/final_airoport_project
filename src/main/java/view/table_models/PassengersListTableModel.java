package view.table_models;

import model.Passengers;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * Created by ПК on 11.12.2016.
 */
public class PassengersListTableModel extends AbstractTableModel {
    public PassengersListTableModel(ArrayList<Passengers> listPass) {
        this.listPass = listPass;
    }
    private ArrayList<Passengers> listPass;

    @Override
    public void fireTableRowsInserted(int firstRow, int lastRow) {
        super.fireTableRowsInserted(firstRow, lastRow);
    }

    @Override
    public int getRowCount() {
        return listPass.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public String getColumnName(int c) {
        String result = "";
        switch (c) {
            case 0:
                result = "FLIGHT";
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
                result = "TICKET CLASS";
                break;
        }
        return result;
    }

    @Override
    public Object getValueAt(int r, int c) {
        switch (c) {
            case 0:
                return listPass.get(r).getFlightNumber().toUpperCase();
            case 1:
                return listPass.get(r).getLastName().toUpperCase();
            case 2:
                return listPass.get(r).getFirstName().toUpperCase();
            case 3:
                return listPass.get(r).getPassportNumber().toUpperCase();
            case 4:
                return listPass.get(r).getSex().toUpperCase();
            case 5:
                return listPass.get(r).getBirtday().toUpperCase();
            case 6:
                return listPass.get(r).getCountry().toUpperCase();
            case 7:
                return listPass.get(r).getClassTicket();
            default:
                return "";
        }
    }
}
