package service;

import dao.AirplanesDAO;
import model.Airplanes;

import java.util.ArrayList;

/**
 * Created by ПК on 13.12.2016.
 */
public class AirplaneService {
    public AirplaneService(){
        this.instAirplanesDAO = new AirplanesDAO();
    }
    AirplanesDAO instAirplanesDAO;

    public ArrayList<Airplanes> getAllService(){
        return instAirplanesDAO.getAllDAO();
    }

    public void editDataService(Airplanes airplane){
        instAirplanesDAO.editInDBDAO(airplane);
    }

    public void deleteDataService(Airplanes airplane){
        instAirplanesDAO.deleteInDBDAO(airplane);
    }

    public void insertNewService(Airplanes airplane){
        instAirplanesDAO.insertNewDAO(airplane);
    }
}
