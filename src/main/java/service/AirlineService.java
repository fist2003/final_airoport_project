package service;

import dao.AirlinesDAO;
import model.Airlines;

import java.util.ArrayList;

/**
 * Created by ПК on 11.12.2016.
 */
public class AirlineService {
    public AirlineService(){
        this.instAirlinesDAO = new AirlinesDAO();
    }

    private AirlinesDAO instAirlinesDAO;


    public String[] makeArrAirlinesNamesForComboBox(){
        ArrayList<Airlines> listAirlines = instAirlinesDAO.getAllDAO();
        int size = listAirlines.size();
        String[] arrNames = new String[size+1];
        arrNames[0] = "ALL";
        for(int i = 0; i < size; i++){
            arrNames[i+1] = listAirlines.get(i).getName().toUpperCase();
        }
        return arrNames;

    }

    public ArrayList<Airlines> getAllService(){
        ArrayList<Airlines> listAllAirlines = instAirlinesDAO.getAllDAO();
        return listAllAirlines;
    }

    public void editAirlineService(Airlines airlines){
        instAirlinesDAO.editInDBDAO(airlines);
    }

    public void deleteDataService(Airlines airline){
        instAirlinesDAO.deleteInDBDAO(airline);
    }

    public void insertNewService(Airlines airline){
        instAirlinesDAO.insertNewDAO(airline);
    }
}
