package view.table_models;

import model.Flights;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * Created by ПК on 09.12.2016.
 */
public class ArrivalTableModel extends AbstractTableModel {

    public ArrivalTableModel(ArrayList<Flights> flights) {
        this.flights = flights;
    }
    private ArrayList<Flights> flights;

    @Override
    public int getRowCount() {
        return flights.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public String getColumnName(int c) {
        String result = "";
        switch (c) {
            case 0:
                result = "TIME";
                break;
            case 1:
                result = "FLIGHT";
                break;
            case 2:
                result = "ORIGIN";
                break;
            case 3:
                result = "FLIGHT STATUS";
                break;
            case 4:
                result = "GATE";
                break;
            case 5:
                result = "CURRENT TIME";
                break;
        }
        return result;
    }

    @Override
    public Object getValueAt(int r, int c) {
        switch (c) {
            case 0:
                return flights.get(r).getTimeOfArrive();
            case 1:
                return flights.get(r).getNumber().toUpperCase();
            case 2:
                return flights.get(r).getPortOfDepart().toUpperCase();
            case 3:
                return flights.get(r).getStatusOfFlight().toUpperCase();
            case 4:
                return flights.get(r).getGateName().toUpperCase();
            case 5:
                return flights.get(r).getCurrentTime();
            default:
                return "";
        }
    }

}
