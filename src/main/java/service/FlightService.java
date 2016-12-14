package service;

import dao.FlightsDAO;
import model.Flights;

import java.util.ArrayList;

/**
 * Created by ПК on 13.12.2016.
 */
public class FlightService {
    public FlightService(){
        this.instFlightsDAO = new FlightsDAO();
    }

    private FlightsDAO instFlightsDAO;

    public ArrayList<Flights> getAllService(){
        return instFlightsDAO.getAllDAO();
    }

    public void editDataService(Flights flight){
        instFlightsDAO.editInDBDAO(flight);
    }

    public void deleteDataService(Flights flight){
        instFlightsDAO.deleteInDBDAO(flight);
    }

    public void insertNewService(Flights flight){
        instFlightsDAO.insertNewDAO(flight);
    }
}
