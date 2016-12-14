package service;

import dao.FlightsDAO;
import dao.PassengersDAO;
import model.Flights;
import model.Passengers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ПК on 12.12.2016.
 */
public class PassengersListService {
    public PassengersListService(){
        this.instFlightsDAO = new FlightsDAO();
        this.instPassengersDAO = new PassengersDAO();

    }

    private FlightsDAO instFlightsDAO;
    private PassengersDAO instPassengersDAO;

    public ArrayList<Passengers> makeAllPassengersList(){
        ArrayList<Passengers> listAllPassList = instPassengersDAO.getAllandFlightInfoDAO();
        return listAllPassList;
    }

    public ArrayList<Passengers> makeArrivePassengersList(){
        ArrayList<Passengers> listAllPassList = instPassengersDAO.getAllandFlightInfoDAO();
        ArrayList<Passengers> listArrivePass = new ArrayList<Passengers>();
        for (Passengers passenger:listAllPassList){
            if (passenger.getPortOfDestination().toLowerCase().equals("odessa")){
                listArrivePass.add(passenger);
            }
        }
        return listArrivePass;
    }

    public ArrayList<Passengers> makeDepartPassengersList(){
        ArrayList<Passengers> listAllPassList = instPassengersDAO.getAllandFlightInfoDAO();
        ArrayList<Passengers> listDepartPass = new ArrayList<Passengers>();
        for (Passengers passenger:listAllPassList){
            if (passenger.getPortOfDepart().toLowerCase().equals("odessa")){
                listDepartPass.add(passenger);
            }
        }
        return listDepartPass;
    }

    public String[] makeArrFlightNumbersForComboBox(){
        ArrayList<Flights> listFlights = instFlightsDAO.getAllDAO();
        int size = listFlights.size();
        String[] arrNumbers = new String[size+1];
        arrNumbers[0] = "ALL";
        for(int i = 0; i < size; i++){
            arrNumbers[i+1] = listFlights.get(i).getNumber().toUpperCase();
        }
        return arrNumbers;
    }

    public String[] makeArrArriveFlightNumbersForComboBox(){
        ArrayList<Flights> listFlights = instFlightsDAO.getAllDAO();
        ArrayList<String> listNumbers = new ArrayList<String>();
        for (Flights flight:listFlights) {
            if(flight.getPortOfDestin().toLowerCase().equals("odessa")) {
                listNumbers.add(flight.getNumber());
            }
        }
        int size = listNumbers.size();
        String[] arrNumbers = new String[size+1];
        arrNumbers[0] = "ALL";
        for(int i = 0; i < size; i++){
                arrNumbers[i + 1] = listNumbers.get(i).toUpperCase();
        }
        return arrNumbers;
    }

    public String[] makeArrDepartFlightNumbersForComboBox(){
        ArrayList<Flights> listFlights = instFlightsDAO.getAllDAO();
        ArrayList<String> listNumbers = new ArrayList<String>();
        for (Flights flight:listFlights) {
            if(flight.getPortOfDepart().toLowerCase().equals("odessa")) {
                listNumbers.add(flight.getNumber());
            }
        }
        int size = listNumbers.size();
        String[] arrNumbers = new String[size+1];
        arrNumbers[0] = "ALL";
        for(int i = 0; i < size; i++){
            arrNumbers[i + 1] = listNumbers.get(i).toUpperCase();
        }
        return arrNumbers;
    }

    public String[] makeArrPortDepartsComboBox(){
        ArrayList<Flights> listFlights = instFlightsDAO.getAllDAO();
        ArrayList<String> portDepartsList = new ArrayList<String>();
        for (Flights flight:listFlights) {
            portDepartsList.add(flight.getPortOfDepart());
        }
        Set<String> set = new HashSet<String>(portDepartsList);
        portDepartsList.clear();
        portDepartsList.addAll(set);
        int size = portDepartsList.size();
        String[] arrDepartPorts = new String[size+1];
        arrDepartPorts[0] = "ALL";
        for(int i = 0; i < size; i++){
            arrDepartPorts[i+1] = portDepartsList.get(i).toUpperCase();
        }
        return arrDepartPorts;
    }

    public String[] makeArrPortDepartsToOdessaComboBox(){
        ArrayList<Flights> listFlights = instFlightsDAO.getAllDAO();
        ArrayList<String> portDepartsList = new ArrayList<String>();
        for (Flights flight:listFlights) {
            if(flight.getPortOfDestin().toLowerCase().equals("odessa")) {
                portDepartsList.add(flight.getPortOfDepart());
            }
        }
        Set<String> set = new HashSet<String>(portDepartsList);
        portDepartsList.clear();
        portDepartsList.addAll(set);
        int size = portDepartsList.size();
        String[] arrDepartPorts = new String[size+1];
        arrDepartPorts[0] = "ALL";
        for(int i = 0; i < size; i++){
            arrDepartPorts[i+1] = portDepartsList.get(i).toUpperCase();
        }
        return arrDepartPorts;
    }

    public String[] makeArrPortDestinFromOdessaComboBox(){
        ArrayList<Flights> listFlights = instFlightsDAO.getAllDAO();
        ArrayList<String> portDestinList = new ArrayList<String>();
        for (Flights flight:listFlights) {
            if(flight.getPortOfDepart().toLowerCase().equals("odessa")) {
                portDestinList.add(flight.getPortOfDestin());
            }
        }
        Set<String> set = new HashSet<String>(portDestinList);
        portDestinList.clear();
        portDestinList.addAll(set);
        int size = portDestinList.size();
        String[] arrDepartPorts = new String[size+1];
        arrDepartPorts[0] = "ALL";
        for(int i = 0; i < size; i++){
            arrDepartPorts[i+1] = portDestinList.get(i).toUpperCase();
        }
        return arrDepartPorts;
    }

    public String[] makeArrPortDestinationComboBox(){
        ArrayList<Flights> listFlights = instFlightsDAO.getAllDAO();
        ArrayList<String> portDestinationList = new ArrayList<String>();
        for (Flights flight:listFlights) {
            portDestinationList.add(flight.getPortOfDestin());
        }
        Set<String> set = new HashSet<String>(portDestinationList);
        portDestinationList.clear();
        portDestinationList.addAll(set);
        int size = portDestinationList.size();
        String[] arrDestinPorts = new String[size+1];
        arrDestinPorts[0] = "ALL";
        for(int i = 0; i < size; i++){
            arrDestinPorts[i+1] = portDestinationList.get(i).toUpperCase();
        }
        return arrDestinPorts;
    }

    public ArrayList<Passengers> makeListPassengersByAirline(String value){
        ArrayList<Passengers> listAllPassengers = instPassengersDAO.getAllandFlightInfoDAO();
        ArrayList<Passengers> listPassengersByAirline = new ArrayList<Passengers>();
        if(value.toLowerCase().equals("all")){
            return listAllPassengers;
        }
        for (Passengers passenger:listAllPassengers) {
            if(passenger.getAirlineName().toLowerCase().equals(value.toLowerCase())) {
                listPassengersByAirline.add(passenger);
            }
        }
        return listPassengersByAirline;
    }

    public ArrayList<Passengers> makeListPassengersByFlightNumber(String value){
        ArrayList<Passengers> listAllPassengers = instPassengersDAO.getAllandFlightInfoDAO();
        ArrayList<Passengers> listPassengersByFlight = new ArrayList<Passengers>();
        if(value.toLowerCase().equals("all")){
            return listAllPassengers;
        }
        for (Passengers passenger:listAllPassengers) {
            if(passenger.getFlightNumber().toLowerCase().equals(value.toLowerCase())) {
                listPassengersByFlight.add(passenger);
            }
        }
        return listPassengersByFlight;
    }

    public ArrayList<Passengers> makeListPassengersByPortDepart(String value){
        ArrayList<Passengers> listAllPassengers = instPassengersDAO.getAllandFlightInfoDAO();
        ArrayList<Passengers> listPassengersByDepart = new ArrayList<Passengers>();
        if(value.toLowerCase().equals("all")){
            return listAllPassengers;
        }
        for (Passengers passenger:listAllPassengers) {
            if(passenger.getPortOfDepart().toLowerCase().equals(value.toLowerCase())) {
                listPassengersByDepart.add(passenger);
            }
        }
        return listPassengersByDepart;
    }

    public ArrayList<Passengers> makeListPassengersByPortDestin(String value){
        ArrayList<Passengers> listAllPassengers = instPassengersDAO.getAllandFlightInfoDAO();
        ArrayList<Passengers> listPassengersByDestin = new ArrayList<Passengers>();
        if(value.toLowerCase().equals("all")){
            return listAllPassengers;
        }
        for (Passengers passenger:listAllPassengers) {
            if(passenger.getPortOfDestination().toLowerCase().equals(value.toLowerCase())) {
                listPassengersByDestin.add(passenger);
            }
        }
        return listPassengersByDestin;
    }


}
