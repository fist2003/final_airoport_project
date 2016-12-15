package service;

import dao.FlightsDAO;
import model.*;

import java.util.ArrayList;

/**
 * Created by ПК on 13.12.2016.
 */
public class FlightService extends CheckInputValueService {
    public FlightService(){
        this.instFlightsDAO = new FlightsDAO();
    }

    private final String numberFlightKey = "Number of flight";
    private final String departPortFlightKey = "Depart port of flight";
    private final String destinPortFlightKey = "Destination port of flight";
    private final String dateDepartFlightKey = "Depart date of flight";
    private final String dateArriveFlightKey = "Arrive date of flight";
    private final String timeDepartFlightKey = "Depart time of flight";
    private final String timeArriveFlightKey = "Arrive time of flight";
    private final String priceEconomFlightKey = "Price econom of flight";
    private final String priceBusinessFlightKey = "Price business of flight";
    private final String airplaneIdFlightKey = "Airplane whith this id absent";


    private FlightsDAO instFlightsDAO;

    public ArrayList<Flights> getAllService(){
        return instFlightsDAO.getAllDAO();
    }

    public ArrayList<String> insertNewService(Flights flight){
        ArrayList<String> check = new ArrayList<String>(checkInputValues(flight));
        if (check.size() == 0) {instFlightsDAO.insertNewDAO(flight);}
        return check;
    }

    public ArrayList<String> editDataService(Flights flight){
        ArrayList<String> check = new ArrayList<String>(checkInputValues(flight));
        if (check.size() == 0) {instFlightsDAO.editInDBDAO(flight);}
        return check;
    }

    public ArrayList<Long> deleteDataService(Flights flight){
        ArrayList<Long> check = new ArrayList<Long>(checkSafeDelete(flight));
        if (check.size() == 0) {instFlightsDAO.deleteInDBDAO(flight);}
        return check;
    }

    @Override
    protected ArrayList<String> checkInputValues(Entity entity) {
        ArrayList<String> checkList = new ArrayList<String>();
        Flights flight = (Flights) entity;
        ArrayList<Flights> allFlightsList = getAllService();
        for (Flights flightFromDB:allFlightsList){
            if((flightFromDB.getNumber().toLowerCase().equals(flight.getNumber().toLowerCase()))&&
                    (flightFromDB.getId() != flight.getId())){
                checkList.add("Flight with this Number is already exist");
            }
        }
        AirplaneService instAirplaneService = new AirplaneService();
        ArrayList<Airplanes> listAllAirplanes = instAirplaneService.getAllService();
        boolean flagCheckAirplaneId = false;
        for (Airplanes airplane:listAllAirplanes){
            if(airplane.getId() == flight.getAirplane_id()){flagCheckAirplaneId = true;}
        }
        if (!flagCheckAirplaneId){
            checkList.add(airplaneIdFlightKey);
        }
        else if(checkForWhiteSpace(flight.getNumber())){
            checkList.add(numberFlightKey);
        }
        else if(checkForWhiteSpace(flight.getPortOfDepart())){
            checkList.add(departPortFlightKey);
        }
        else if(checkForWhiteSpace(flight.getPortOfDestin())){
            checkList.add(destinPortFlightKey);
        }
        else if((checkForWhiteSpace(String.valueOf(flight.getPriceEconom())))||(flight.getPriceEconom() < 0)||
                (flight.getPriceEconom() > 9999)){
            checkList.add(priceEconomFlightKey);
        }
        else if((checkForWhiteSpace(String.valueOf(flight.getPriceBusiness())))||(flight.getPriceBusiness() < 0)||
                (flight.getPriceBusiness() > 9999)) {
            checkList.add(priceBusinessFlightKey);
        }
        else if(!checkInputDate(flight.getDateOfDepart())){
            checkList.add(dateDepartFlightKey);
        }
        else if(!checkInputDate(flight.getDateOfArrive())){
            checkList.add(dateArriveFlightKey);
        }
        else if(!checkInputTime(flight.getTimeOdDepart())){
            checkList.add(timeDepartFlightKey);
        }
        else if(!checkInputTime(flight.getTimeOfArrive())){
            checkList.add(timeArriveFlightKey);
        }
        return checkList;

    }

    @Override
    protected ArrayList<Long> checkSafeDelete(Entity entity) {
        Flights flight = (Flights) entity;
        ArrayList<Long> listPassengersId = new ArrayList<Long>();
        PassengersService instPassengersService = new PassengersService();
        for (Passengers passenger:instPassengersService.getAllService()){
            if(passenger.getFlight_id() == flight.getId()){
                listPassengersId.add(passenger.getId());
            }
        }
        return listPassengersId;
    }
}
