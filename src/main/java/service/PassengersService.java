package service;

import dao.PassengersDAO;
import model.Passengers;

import java.sql.Array;
import java.util.ArrayList;

/**
 * Created by ПК on 13.12.2016.
 */
public class PassengersService {
    public PassengersService(){
        this.instPassengersDAO = new PassengersDAO();
    }

    private PassengersDAO instPassengersDAO;

    public ArrayList<Passengers> getAllService(){
        return instPassengersDAO.getAllDAO();
    }

    public void editDataService(Passengers passenger){
        instPassengersDAO.editInDBDAO(passenger);
    }

    public void deleteDataService(Passengers passenger){
        instPassengersDAO.deleteInDBDAO(passenger);
    }

    public void insertNewService(Passengers passenger){
        instPassengersDAO.insertNewDAO(passenger);
    }
}
