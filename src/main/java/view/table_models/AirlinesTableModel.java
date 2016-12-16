package view.table_models;

import model.Airlines;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by ПК on 16.12.2016.
 */
public class AirlinesTableModel extends AbstractTableModel {
    public AirlinesTableModel(ArrayList<Airlines> listAirlines) {
        this.listAirlines = listAirlines;
    }

    private ArrayList<Airlines> listAirlines;

    @Override
    public void fireTableRowsInserted(int firstRow, int lastRow) {
        super.fireTableRowsInserted(firstRow, lastRow);
    }

    @Override
    public int getRowCount() {
        return listAirlines.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int c) {
        String result = "";
        switch (c) {
            case 0:
                result = "AIRLINE";
                break;
            case 1:
                result = "ADRESS";
                break;
            case 2:
                result = "TELEPHONE";
                break;
            case 3:
                result = "WEBSITE";
                break;
        }
        return result;
    }

    @Override
    public Object getValueAt(int r, int c) {
        switch (c) {
            case 0:
                return listAirlines.get(r).getName().toUpperCase();
            case 1:
                return listAirlines.get(r).getAdress().toUpperCase();
            case 2:
                return listAirlines.get(r).getTelephone().toUpperCase();
            case 3:
                return listAirlines.get(r).getWebsite().toUpperCase();
            default:
                return "";
        }
    }
}
