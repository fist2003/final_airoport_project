package view.table_models;

import model.Flights;
import view.PricesJPanelGUI;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * Created by ПК on 11.12.2016.
 */
public class PricesDepartArriveTableModel extends AbstractTableModel {
    public PricesDepartArriveTableModel(ArrayList<Flights> prices) {
        this.prices = prices;
    }
        private ArrayList<Flights> prices;

    @Override
    public int getRowCount() {
        return prices.size();
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
                result = "NUMBER";
                break;
            case 1:
                if (PricesJPanelGUI.isDepartPresed) {
                    result = "DIRECTION";
                }
                else if(PricesJPanelGUI.isArrivalPresed){
                    result = "ORIGIN";
                }
                break;
            case 2:
                result = "DATE DEPART";
                break;
            case 3:
                result = "TIME DEPART";
                break;
            case 4:
                result = "DATE ARRIVE";
                break;
            case 5:
                result = "TIME ARRIVE";
                break;
            case 6:
                result = "ECONOM USD";
                break;
            case 7:
                result = "BUSINESS USD";
                break;
            case 8:
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
                    if (PricesJPanelGUI.isDepartPresed) {
                        return prices.get(r).getPortOfDestin().toUpperCase();
                    } else if (PricesJPanelGUI.isArrivalPresed) {
                        return prices.get(r).getPortOfDepart().toUpperCase();
                    }
                case 2:
                    return prices.get(r).getDateOfDepart().toUpperCase();
                case 3:
                    return prices.get(r).getTimeOdDepart().toUpperCase();
                case 4:
                    return prices.get(r).getDateOfArrive().toUpperCase();
                case 5:
                    return prices.get(r).getTimeOfArrive().toUpperCase();
                case 6:
                    return prices.get(r).getPriceEconom();
                case 7:
                    return prices.get(r).getPriceBusiness();
                case 8:
                    return prices.get(r).getAirlineName().toUpperCase();
                default:
                    return "";
            }
        }

}
