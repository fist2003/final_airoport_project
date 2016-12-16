package service;

import dao.AirlinesDAO;
import model.Airlines;
import model.Airplanes;
import model.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ПК on 11.12.2016.
 */
public class AirlineService extends CheckInputValueService {
    public AirlineService(){
        this.instAirlinesDAO = new AirlinesDAO();
    }

    private AirlinesDAO instAirlinesDAO;
    private final String nameAirlineKey = "Name of airline";
    private final String adressAirlineKey = "Adress of airline";
    private final String telephoneAirlineKey = "Telephone of airline";
    private final String websiteAirlineKey = "Website of airline";


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

    public ArrayList<String> insertNewService(Airlines airline){
        ArrayList<String> check = new ArrayList<String>(checkInputValues(airline));
        if (check.size() == 0) {instAirlinesDAO.insertNewDAO(airline);}
        return check;
    }

    public ArrayList<String> editAirlineService(Airlines airlines){
        ArrayList<String> check = new ArrayList<String>(checkInputValues(airlines));
        if (check.size() == 0){instAirlinesDAO.editInDBDAO(airlines);}
        return check;
    }

    public ArrayList<Long> deleteDataService(Airlines airline){
        ArrayList<Long> check = new ArrayList<Long>(checkSafeDelete(airline));
        if (check.size() == 0) {instAirlinesDAO.deleteInDBDAO(airline);}
        return check;
    }

    @Override
    public ArrayList<String> checkInputValues(Entity entity) {
        ArrayList<String> checkList = new ArrayList<String>();
        Airlines airline = (Airlines)entity;
        String airlineName = airline.getName();
        ArrayList<Airlines> allAirlinesList = getAllService();
        for (Airlines airlineFromDB:allAirlinesList){
            if((airlineFromDB.getName().toLowerCase().equals(airlineName.toLowerCase()))&&(airlineFromDB.getId() != airline.getId())){
                checkList.add("Airline with this name is Already exist");
            }
        }
        Pattern patWebsite = Pattern.compile("^(www\\.|http://|https://).+\\..+");
        Matcher matWebsite = patWebsite.matcher(airline.getWebsite().toLowerCase());
        if(checkForWhiteSpace(airline.getName())){
            checkList.add(nameAirlineKey);
        }
        else if(checkForWhiteSpace(airline.getAdress())){
            checkList.add(adressAirlineKey);
        }
        else if(checkForWhiteSpace(airline.getTelephone())){
            checkList.add(telephoneAirlineKey);
        }
        else if(!matWebsite.matches()){
            checkList.add(websiteAirlineKey);
        }
        return checkList;
    }

    @Override
    public ArrayList<Long> checkSafeDelete(Entity entity) {
        Airlines airline = (Airlines)entity;
        Long airlineId = airline.getId();
        ArrayList<Long> listAirplanesId = new ArrayList<Long>();
        AirplaneService instAirplaneService = new AirplaneService();
        for (Airplanes airplane:instAirplaneService.getAllService()){
            if(airplane.getAirline_id() == airlineId){
                listAirplanesId.add(airplane.getId());
            }
        }
        return listAirplanesId;
    }
}
