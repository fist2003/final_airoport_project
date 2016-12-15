package view.table_models;

import model.Flights;
import view.EditInfoJPanelGUI;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * Created by ПК on 13.12.2016.
 */
public class EditFlightsTableModel extends AbstractTableModel {

    private ArrayList data = new ArrayList();
    private ArrayList<Flights> flights;

    public EditFlightsTableModel(ArrayList<Flights> flights) {
        this.flights = flights;
        try {
            setDataSource();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getRowCount() {
        return flights.size();
    }

    @Override
    public int getColumnCount() {
        return 11;
    }

    @Override
    public String getColumnName(int c) {
        String result = "";
        switch (c) {
            case 0:
                result = "ID";
                break;
            case 1:
                result = "NUMBER";
                break;
            case 2:
                result = "DEPART";
                break;
            case 3:
                result = "DESTIN";
                break;
            case 4:
                result = "DATE DEPART";
                break;
            case 5:
                result = "TIME DEPART";
                break;
            case 6:
                result = "DATE ARRIVE";
                break;
            case 7:
                result = "TIME ARRIVE";
                break;
            case 8:
                result = "ECONOM PRICE";
                break;
            case 9:
                result = "BUSINESS PRICE";
                break;
            case 10:
                result = "AIRPLANE ID";
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
        for (int i = 0; i < flights.size(); i++) {
            ArrayList row = new ArrayList();
            row.add(flights.get(i).getId());
            row.add(flights.get(i).getNumber());
            row.add(flights.get(i).getPortOfDepart());
            row.add(flights.get(i).getPortOfDestin());
            row.add(flights.get(i).getDateOfDepart());
            row.add(flights.get(i).getTimeOdDepart());
            row.add(flights.get(i).getDateOfArrive());
            row.add(flights.get(i).getTimeOfArrive());
            row.add(flights.get(i).getPriceEconom());
            row.add(flights.get(i).getPriceBusiness());
            row.add(flights.get(i).getAirplane_id());
            synchronized (data) {
                data.add(row);
                fireTableRowsInserted(
                        data.size() - 1, data.size() - 1);
            }
        }
    }
}
