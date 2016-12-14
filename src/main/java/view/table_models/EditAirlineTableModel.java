package view.table_models;

import model.Airlines;
import view.EditInfoJPanelGUI;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * Created by ПК on 13.12.2016.
 */
public class EditAirlineTableModel extends AbstractTableModel {
    private ArrayList data = new ArrayList();
    private ArrayList<Airlines> airlines;

    public EditAirlineTableModel(ArrayList<Airlines> airlines) {
        this.airlines = airlines;
        try {
            setDataSource();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getRowCount() {
        return airlines.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int c) {
        String result = "";
        switch (c) {
            case 0:
                result = "Id";
                break;
            case 1:
                result = "Name";
                break;
            case 2:
                result = "Adress";
                break;
            case 3:
                result = "Telephone";
                break;
            case 4:
                result = "Website";
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
        for (int i = 0; i < airlines.size(); i++) {
            ArrayList row = new ArrayList();
            row.add(airlines.get(i).getId());
            row.add(airlines.get(i).getName());
            row.add(airlines.get(i).getAdress());
            row.add(airlines.get(i).getTelephone());
            row.add(airlines.get(i).getWebsite());
            synchronized (data) {
                data.add(row);
                fireTableRowsInserted(
                        data.size() - 1, data.size() - 1);
            }
        }
    }

}
