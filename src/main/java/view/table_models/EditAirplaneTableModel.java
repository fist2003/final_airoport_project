package view.table_models;

import model.Airplanes;
import view.EditInfoJPanelGUI;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * Created by ПК on 13.12.2016.
 */
public class EditAirplaneTableModel extends AbstractTableModel {
    private ArrayList data = new ArrayList();
    private ArrayList<Airplanes> airplanes;

    public EditAirplaneTableModel(ArrayList<Airplanes> airplanes) {
        this.airplanes = airplanes;
        try {
            setDataSource();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getRowCount() {
        return airplanes.size();
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
                result = "ID";
                break;
            case 1:
                result = "MANUFACTURER";
                break;
            case 2:
                result = "MODEL";
                break;
            case 3:
                result = "NUMBER ISO";
                break;
            case 4:
                result = "YEAR";
                break;
            case 5:
                result = "ECONOM PLACES";
                break;
            case 6:
                result = "BUSINESS PLACES";
                break;
            case 7:
                result = "AIRLINE ID";
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
        if((EditInfoJPanelGUI.isEditPresed)||(EditInfoJPanelGUI.isDeletePresed))return true;
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
        for (int i = 0; i < airplanes.size(); i++) {
            ArrayList row = new ArrayList();
            row.add(airplanes.get(i).getId());
            row.add(airplanes.get(i).getManufacturer());
            row.add(airplanes.get(i).getModel());
            row.add(airplanes.get(i).getNumberISO());
            row.add(airplanes.get(i).getYear());
            row.add(airplanes.get(i).getPlacesEconom());
            row.add(airplanes.get(i).getPlacesBusiness());
            row.add(airplanes.get(i).getAirline_id());
            synchronized (data) {
                data.add(row);
                fireTableRowsInserted(
                        data.size() - 1, data.size() - 1);
            }
        }
    }
}
