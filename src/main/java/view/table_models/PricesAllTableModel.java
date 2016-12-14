package view.table_models;

import model.Flights;
import view.PricesJPanelGUI;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * Created by ПК on 11.12.2016.
 */
public class PricesAllTableModel extends AbstractTableModel {
    public PricesAllTableModel(ArrayList<Flights> prices) {
        this.prices = prices;
    }
    private ArrayList<Flights> prices;

    @Override
    public int getRowCount() {
        return prices.size();
    }

    @Override
    public int getColumnCount() {
        return 10;
    }

    @Override
    public String getColumnName(int c) {
        String result = "";
        switch (c) {
            case 0:
                result = "NUMBER";
                break;
            case 1:
                result = "ORIGIN";
                break;
            case 2:
                result = "DIRECTION";
                break;
            case 3:
                result = "DATE DEPART";
                break;
            case 4:
                result = "TIME DEPART";
                break;
            case 5:
                result = "DATE ARRIVE";
                break;
            case 6:
                result = "TIME ARRIVE";
                break;
            case 7:
                result = "ECONOM USD";
                break;
            case 8:
                result = "BUSINESS USD";
                break;
            case 9:
                result = "AIRLINE";
                break;
        }
        return result;
    }

    @Override
    public Object getValueAt(int r, int c) {
        switch (c) {
            case 0:
                return prices.get(r).getNumber().toUpperCase();
            case 1:
                return prices.get(r).getPortOfDepart().toUpperCase();
            case 2:
                return prices.get(r).getPortOfDestin().toUpperCase();
            case 3:
                return prices.get(r).getDateOfDepart().toUpperCase();
            case 4:
                return prices.get(r).getTimeOdDepart().toUpperCase();
            case 5:
                return prices.get(r).getDateOfArrive().toUpperCase();
            case 6:
                return prices.get(r).getTimeOfArrive().toUpperCase();
            case 7:
                return prices.get(r).getPriceEconom();
            case 8:
                return prices.get(r).getPriceBusiness();
            case 9:
                return prices.get(r).getAirlineName().toUpperCase();
            default:
                return "";
        }
    }
}
