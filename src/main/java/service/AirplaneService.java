package service;

import dao.AirplanesDAO;
import model.Airlines;
import model.Airplanes;
import model.Entity;
import model.Flights;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ПК on 13.12.2016.
 */
public class AirplaneService extends CheckInputValueService {
    public AirplaneService(){
        this.instAirplanesDAO = new AirplanesDAO();
    }
    AirplanesDAO instAirplanesDAO;

    private final String manufacturerAirplaneKey = "Manufacturer of airplane";
    private final String modeAirplaneKey = "Model of airplane";
    private final String numberIsoAirplaneKey = "NumberISO of airplane";
    private final String yearAirplaneKey = "Year of airplane";
    private final String economPlacesAirplaneKey = "Quantity econom places of airplane";
    private final String businessPlacesAirplaneKey = "Quantity business places of airplane";
    private final String airlineIdAirplaneKey = "Airline owner whith this id absent";

    public ArrayList<Airplanes> getAllService(){
        return instAirplanesDAO.getAllDAO();
    }

    public ArrayList<String> insertNewService(Airplanes airplane){
        ArrayList<String> check = new ArrayList<String>(checkInputValues(airplane));
        if (check.size() == 0) {instAirplanesDAO.insertNewDAO(airplane);}
        return check;
    }

    public ArrayList<String> editDataService(Airplanes airplane){
        ArrayList<String> check = new ArrayList<String>(checkInputValues(airplane));
        if (check.size() == 0) {instAirplanesDAO.editInDBDAO(airplane);}
        return check;
    }

    public ArrayList<Long> deleteDataService(Airplanes airplane){
        ArrayList<Long> check = new ArrayList<Long>(checkSafeDelete(airplane));
        if (check.size() == 0) {instAirplanesDAO.deleteInDBDAO(airplane);}
        return check;
    }

    @Override
    protected ArrayList<String> checkInputValues(Entity entity) {
        ArrayList<String> checkList = new ArrayList<String>();
        Airplanes airplane = (Airplanes) entity;
        String airplaneNumberISO = airplane.getNumberISO();
        ArrayList<Airplanes> allAirplanesList = getAllService();
        Pattern patIso = Pattern.compile("iso\\d{6}");
        Matcher matIso = patIso.matcher(airplane.getNumberISO().toLowerCase());
        for (Airplanes airplaneFromDB:allAirplanesList){
            if((airplaneFromDB.getNumberISO().toLowerCase().equals(airplaneNumberISO.toLowerCase()))&&
                    (airplaneFromDB.getId() != airplane.getId())){
                checkList.add("Airplane with this ISO Number is already exist");
            }
        }
        if (checkForWhiteSpace(airplane.getManufacturer())){
            checkList.add(manufacturerAirplaneKey);
        }
        else if (checkForWhiteSpace(airplane.getModel())){
            checkList.add(modeAirplaneKey);
        }
        else if ((checkForWhiteSpace(airplane.getYear()))||(Integer.parseInt(airplane.getYear()) < 1950)
                ||(Integer.parseInt(airplane.getYear()) > 2017)){
            checkList.add(yearAirplaneKey);
        }
        else if(!matIso.matches()){
            checkList.add(numberIsoAirplaneKey);
        }
        else if ((airplane.getPlacesBusiness() < 0) ||
                (airplane.getPlacesBusiness() > 500)){
            checkList.add(businessPlacesAirplaneKey);
        }
        else if ((airplane.getPlacesEconom() < 0) ||
                (airplane.getPlacesEconom() > 500)){
            checkList.add(economPlacesAirplaneKey);
        }
        AirlineService instAirlineService = new AirlineService();
        ArrayList<Airlines> listAllAirlines = instAirlineService.getAllService();
        boolean flagCheckAirlineId = false;
        for (Airlines airline:listAllAirlines){
            if(airline.getId() == airplane.getAirline_id()){flagCheckAirlineId = true;}
        }
        if (!flagCheckAirlineId){
            checkList.add(airlineIdAirplaneKey);
        }
        return checkList;
    }

    @Override
    protected ArrayList<Long> checkSafeDelete(Entity entity) {
        Airplanes airplane = (Airplanes) entity;
        Long airplaneId = airplane.getId();
        ArrayList<Long> listFlightsId = new ArrayList<Long>();
        FlightService instFlightService = new FlightService();
        for (Flights flight:instFlightService.getAllService()){
            if(flight.getAirplane_id() == airplaneId){
                listFlightsId.add(flight.getId());
            }
        }
        return listFlightsId;
    }
}
