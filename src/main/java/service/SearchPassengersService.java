package service;

import dao.FlightsDAO;
import dao.PassengersDAO;
import model.Flights;
import model.Passengers;

import java.util.*;

/**
 * Created by ПК on 13.12.2016.
 */
public class SearchPassengersService {
    public SearchPassengersService(){     this.instFlightsDAO = new FlightsDAO();
        this.instPassengersDAO = new PassengersDAO();
    }

    public static final String lastNameStr = "LASTNAME";
    public static final String firstNameStr = "FIRSTNAME";
    public static final String passportStr = "PASSPORT";
    public static final String priceStr = "PRICE";
    public static final String flightStr = "FLIGHT";
    public static final String departPortStr = "DEPARTPORT";
    public static final String destinPortStr = "DESTINPORT";
    public static final String countryStr = "COUNTRY";
    private FlightsDAO instFlightsDAO;
    private PassengersDAO instPassengersDAO;

    public Map<String,String[]> makeArrSearchForComboBox(){
        HashMap<String,String[]> map = new HashMap<String,String[]>();
        ArrayList<Passengers> listPassengers = instPassengersDAO.getAllDAO();
        ArrayList<String> listLastNames = new ArrayList<String>();
        ArrayList<String> listFirstNames = new ArrayList<String>();
        ArrayList<String> listPassports = new ArrayList<String>();
        ArrayList<String> listCountry = new ArrayList<String>();
        for (Passengers passenger:listPassengers) {
            listLastNames.add(passenger.getLastName());
            listFirstNames.add(passenger.getFirstName());
            listPassports.add(passenger.getPassportNumber());
            listCountry.add(passenger.getCountry());
        }
        makeMapWithoutDublicates(listLastNames,lastNameStr,map);
        makeMapWithoutDublicates(listFirstNames,firstNameStr,map);
        makeMapWithoutDublicates(listPassports,passportStr,map);
        makeMapWithoutDublicates(listCountry,countryStr,map);
        ArrayList<Flights> listFlights = instFlightsDAO.getAllDAO();
        ArrayList<String> listPrices = new ArrayList<String>();
        ArrayList<String> listFlightsNumbers = new ArrayList<String>();
        ArrayList<String> listDepartPorts = new ArrayList<String>();
        ArrayList<String> listDestinPorts = new ArrayList<String>();
        for (Flights flight:listFlights){
            listPrices.add(flight.getPriceBusiness().toString());
            listPrices.add(flight.getPriceEconom().toString());
            listFlightsNumbers.add(flight.getNumber());
            listDepartPorts.add(flight.getPortOfDepart());
            listDestinPorts.add(flight.getPortOfDestin());
        }
        makeMapWithoutDublicates(listPrices,priceStr,map);
        makeMapWithoutDublicates(listFlightsNumbers,flightStr,map);
        makeMapWithoutDublicates(listDepartPorts,departPortStr,map);
        makeMapWithoutDublicates(listDestinPorts,destinPortStr,map);

        return map;
    }

    private void makeMapWithoutDublicates(ArrayList<String> list,String key,Map<String,String[]> map){
        Set<String> set = new HashSet<String>(list);
        list.clear();
        list.addAll(set);
        int size = list.size();
        String[] arr = new String[size+1];
        arr[0] = "";
        for(int i = 0; i < size; i++){
            arr[i+1] = list.get(i).toUpperCase();
        }
        map.put(key,arr);
    }

    public ArrayList<Passengers> makeSearchResultList(String value,String key) {
        ArrayList<Passengers> listAllPass = instPassengersDAO.getAllandFlightInfoDAO();
        ArrayList<Passengers> listPassResult = new ArrayList<Passengers>();
        for (Passengers passenger : listAllPass) {
            switch (key) {
                case lastNameStr:
                    if (passenger.getLastName().toLowerCase().equals(value.toLowerCase())) {
                        listPassResult.add(passenger);
                    }
                    break;
                case firstNameStr:
                    if (passenger.getFirstName().toLowerCase().equals(value.toLowerCase())) {
                        listPassResult.add(passenger);
                    }
                    break;
                case passportStr:
                    if (passenger.getPassportNumber().toLowerCase().equals(value.toLowerCase())) {
                        listPassResult.add(passenger);
                    }
                    break;
                case countryStr:
                    if (passenger.getCountry().toLowerCase().equals(value.toLowerCase())) {
                        listPassResult.add(passenger);
                    }
                    break;
                case flightStr:
                    if (passenger.getFlightNumber().toLowerCase().equals(value.toLowerCase())) {
                        listPassResult.add(passenger);
                    }
                    break;
                case departPortStr:
                    if (passenger.getPortOfDepart().toLowerCase().equals(value.toLowerCase())) {
                        listPassResult.add(passenger);
                    }
                    break;
                case destinPortStr:
                    if (passenger.getPortOfDestination().toLowerCase().equals(value.toLowerCase())) {
                        listPassResult.add(passenger);
                    }
                    break;
                case priceStr:
                    if (passenger.getPrice().toLowerCase().equals(value.toLowerCase())) {
                        listPassResult.add(passenger);
                    }
                    break;
            }
        }
        return listPassResult;
    }


}
