package service;

import dao.PassengersDAO;
import model.Airplanes;
import model.Entity;
import model.Flights;
import model.Passengers;

import java.util.ArrayList;

/**
 * Created by ПК on 13.12.2016.
 */
public class PassengersService extends CheckInputValueService{
    public PassengersService(){
        this.instPassengersDAO = new PassengersDAO();
    }

    private final String lastNamePassengerKey = "Last name of passenger";
    private final String firstNamePassengerKey = "First name of passenger";
    private final String passportPassengerKey = "Passport number of passenger";
    private final String sexPassengerKey = "Sex of passenger";
    private final String birthdayPassengerKey = "Birthday of passenger";
    private final String countryPassengerKey = "Country of passenger";
    private final String classTicketPassengerKey = "Class ticket of passenger";
    private final String flightIdPassengerKey = "Flight with this id is absent";
    private final String noFreePlacesPassengerKey = "There aren`t no free places of this class in this flight";
    private final String doublePassengerKey = "Passenger with this passport is already registered in this flight";



    private PassengersDAO instPassengersDAO;

    public ArrayList<Passengers> getAllService(){
        return instPassengersDAO.getAllDAO();
    }

    public ArrayList<String> insertNewService(Passengers passenger){
        ArrayList<String> check = new ArrayList<String>(checkInputValues(passenger));
        if (check.size() == 0){instPassengersDAO.insertNewDAO(passenger);}
        return check;
    }

    public ArrayList<String> editDataService(Passengers passenger){
        ArrayList<String> check = new ArrayList<String>(checkInputValues(passenger));
        if (check.size() == 0){instPassengersDAO.editInDBDAO(passenger);}
        return check;
    }

    public void deleteDataService(Passengers passenger){
        instPassengersDAO.deleteInDBDAO(passenger);
    }


    @Override
    protected ArrayList<String> checkInputValues(Entity entity) {
        ArrayList<String> checkList = new ArrayList<String>();
        Passengers passenger = (Passengers) entity;
        FlightService instFlightsService = new FlightService();
        ArrayList<Flights> listAllFlights = instFlightsService.getAllService();
        boolean flagCheckAirplaneId = false;
        for (Flights flightFromDB:listAllFlights){
            if(flightFromDB.getId() == passenger.getFlight_id()){flagCheckAirplaneId = true;}
        }
        if (!flagCheckAirplaneId){
            checkList.add(flightIdPassengerKey);
        }
        if(checkForWhiteSpace(passenger.getLastName())){
            checkList.add(lastNamePassengerKey);
        }
        else if(checkForWhiteSpace(passenger.getFirstName())){
            checkList.add(firstNamePassengerKey);
        }
        else if(checkForWhiteSpace(passenger.getPassportNumber())){
            checkList.add(passportPassengerKey);
        }
        else if((checkForWhiteSpace(passenger.getSex()))||!((passenger.getSex().toLowerCase().equals("male"))||
                (passenger.getSex().toLowerCase().equals("female")))){
            checkList.add(sexPassengerKey);
        }
        else if(checkForWhiteSpace(passenger.getCountry())){
            checkList.add(countryPassengerKey);
        }
        else if((checkForWhiteSpace(passenger.getClassTicket()))||!((passenger.getClassTicket().toLowerCase().equals("econom"))||
                (passenger.getClassTicket().toLowerCase().equals("business")))){
            checkList.add(classTicketPassengerKey);
        }
        else if(!checkInputDate(passenger.getBirtday())){
            checkList.add(birthdayPassengerKey);
        }
        else if(instPassengersDAO.checkFreePlaceInFlightDAO(passenger) <= 0){
            checkList.add(noFreePlacesPassengerKey);
        }
        /*
        else if(instPassengersDAO.checkPassportInFlightDAO(passenger) == 0){
            checkList.add(doublePassengerKey);
        }
        */
        return checkList;
    }

    @Override
    protected ArrayList<Long> checkSafeDelete(Entity entity) {
        return new ArrayList<Long>();
    }
}
