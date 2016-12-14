package service;

import dao.AirlinesDAO;
import dao.FlightsDAO;
import model.Flights;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ПК on 11.12.2016.
 */
public class PricesTableService {
    public PricesTableService(){
        this.instFlightsDAO = new FlightsDAO();
        this.instAirlinesDAO = new AirlinesDAO();
    }
    private FlightsDAO instFlightsDAO;
    private AirlinesDAO instAirlinesDAO;

    public ArrayList<Flights> makePriceTableDeparture(){
        ArrayList<Flights> listDeparturePrices = instFlightsDAO.getInfoForDepartPriceTableDAO();
        return listDeparturePrices;
    }

    public ArrayList<Flights> makePriceTableDepartureAirline(String value){
        ArrayList<Flights> listDeparturePrices = instFlightsDAO.getInfoForDepartPriceTableDAO();
        ArrayList<Flights> list = new ArrayList<>();
        if(value.toLowerCase().equals("all")){
            return listDeparturePrices;
        }
        else {
            for (Flights flight : listDeparturePrices) {
                if (flight.getAirlineName().toLowerCase().equals(value.toLowerCase())) {
                    list.add(flight);
                }
            }
            return list;
        }
    }

    public ArrayList<Flights> makePriceTableDepartureDestin(String value){
        ArrayList<Flights> listDeparturePrices = instFlightsDAO.getInfoForDepartPriceTableDAO();
        ArrayList<Flights> list = new ArrayList<>();
        if(value.toLowerCase().equals("all")){
            return listDeparturePrices;
        }
        for(Flights flight:listDeparturePrices){
            if(flight.getPortOfDestin().toLowerCase().equals(value.toLowerCase())){
                list.add(flight);}
        }
        return list;
    }

    public ArrayList<Flights> makePriceTableDepartureTime(String value){
        ArrayList<Flights> listDeparturePrices = instFlightsDAO.getInfoForDepartPriceTableDAO();
        ArrayList<Flights> list = new ArrayList<>();
        if(value.toLowerCase().equals("all")){
            return listDeparturePrices;
        }
        Time time00 = Time.valueOf("00:00:00");
        Time time04 = Time.valueOf("04:00:00");
        Time time08 = Time.valueOf("08:00:00");
        Time time12 = Time.valueOf("12:00:00");
        Time time16 = Time.valueOf("16:00:00");
        Time time20 = Time.valueOf("20:00:00");
        Time time24 = Time.valueOf("24:00:00");
            switch (value) {
                case "00-04":
                    checkTimeDiferenceDeparture(time00,time04,listDeparturePrices,list);
                    break;
                case "04-08":
                    checkTimeDiferenceDeparture(time04,time08,listDeparturePrices,list);
                    break;
                case "08-12":
                    checkTimeDiferenceDeparture(time08,time12,listDeparturePrices,list);
                    break;
                case "12-16":
                    checkTimeDiferenceDeparture(time12,time16,listDeparturePrices,list);
                    break;
                case "16-20":
                    checkTimeDiferenceDeparture(time16,time20,listDeparturePrices,list);
                    break;
                case "20-24":
                    checkTimeDiferenceDeparture(time20,time24,listDeparturePrices,list);
                    break;
            }
        return list;
    }

    public ArrayList<Flights> makePriceTableArrivalTime(String value){
        ArrayList<Flights> listArrivalPrices = instFlightsDAO.getInfoForArrivalPriceTableDAO();
        ArrayList<Flights> list = new ArrayList<>();
        if(value.toLowerCase().equals("all")){
            return listArrivalPrices;
        }
        Time time00 = Time.valueOf("00:00:00");
        Time time04 = Time.valueOf("04:00:00");
        Time time08 = Time.valueOf("08:00:00");
        Time time12 = Time.valueOf("12:00:00");
        Time time16 = Time.valueOf("16:00:00");
        Time time20 = Time.valueOf("20:00:00");
        Time time24 = Time.valueOf("24:00:00");
        switch (value) {
            case "00-04":
                checkTimeDiferenceArrival(time00,time04,listArrivalPrices,list);
                break;
            case "04-08":
                checkTimeDiferenceArrival(time04,time08,listArrivalPrices,list);
                break;
            case "08-12":
                checkTimeDiferenceArrival(time08,time12,listArrivalPrices,list);
                break;
            case "12-16":
                checkTimeDiferenceArrival(time12,time16,listArrivalPrices,list);
                break;
            case "16-20":
                checkTimeDiferenceArrival(time16,time20,listArrivalPrices,list);
                break;
            case "20-24":
                checkTimeDiferenceArrival(time20,time24,listArrivalPrices,list);
                break;
        }
        return list;
    }

    public ArrayList<Flights> makeAllPriceTableDepartureTime(String value){
        ArrayList<Flights> listPrices = instFlightsDAO.getInfoForAllPriceTableDAO();
        ArrayList<Flights> list = new ArrayList<>();
        if(value.toLowerCase().equals("all")){
            return listPrices;
        }
        Time time00 = Time.valueOf("00:00:00");
        Time time04 = Time.valueOf("04:00:00");
        Time time08 = Time.valueOf("08:00:00");
        Time time12 = Time.valueOf("12:00:00");
        Time time16 = Time.valueOf("16:00:00");
        Time time20 = Time.valueOf("20:00:00");
        Time time24 = Time.valueOf("24:00:00");
        switch (value) {
            case "00-04":
                checkTimeDiferenceDeparture(time00,time04,listPrices,list);
                break;
            case "04-08":
                checkTimeDiferenceDeparture(time04,time08,listPrices,list);
                break;
            case "08-12":
                checkTimeDiferenceDeparture(time08,time12,listPrices,list);
                break;
            case "12-16":
                checkTimeDiferenceDeparture(time12,time16,listPrices,list);
                break;
            case "16-20":
                checkTimeDiferenceDeparture(time16,time20,listPrices,list);
                break;
            case "20-24":
                checkTimeDiferenceDeparture(time20,time24,listPrices,list);
                break;
        }
        return list;
    }

    private void checkTimeDiferenceDeparture(Time min,Time max,ArrayList<Flights> listDeparturePrices,ArrayList<Flights> list){
        for(Flights flight:listDeparturePrices) {
            Time timeDepart = Time.valueOf(flight.getTimeOdDepart());
            if ((getDifferenceInTime(timeDepart, min) >= 0) && (getDifferenceInTime(timeDepart, max) <= 0)) {
                list.add(flight);
            }
        }
    }

    private void checkTimeDiferenceArrival(Time min,Time max,ArrayList<Flights> listArrivalPrices,ArrayList<Flights> list){
        for(Flights flight:listArrivalPrices) {
            Time timeArrive = Time.valueOf(flight.getTimeOfArrive());
            if ((getDifferenceInTime(timeArrive, min) >= 0) && (getDifferenceInTime(timeArrive, max) <= 0)) {
                list.add(flight);
            }
        }
    }

    public ArrayList<Flights> makePriceTableDepartureDate(String value){
        ArrayList<Flights> listDeparturePrices = instFlightsDAO.getInfoForDepartPriceTableDAO();
        ArrayList<Flights> list = new ArrayList<>();
        if(value.toLowerCase().equals("all")){
            return listDeparturePrices;
        }
        for(Flights flight:listDeparturePrices){
            if(flight.getDateOfDepart().toLowerCase().equals(value.toLowerCase())){
                list.add(flight);}
        }
        return list;
    }

    public ArrayList<Flights> makePriceTableArrivalDate(String value){
        ArrayList<Flights> listArrivalPrices = instFlightsDAO.getInfoForArrivalPriceTableDAO();
        ArrayList<Flights> list = new ArrayList<>();
        if(value.toLowerCase().equals("all")){
            return listArrivalPrices;
        }
        for(Flights flight:listArrivalPrices){
            if(flight.getDateOfArrive().toLowerCase().equals(value.toLowerCase())){
                list.add(flight);}
        }
        return list;
    }

    public ArrayList<Flights> makeAllPriceTableDepartureDate(String value){
        ArrayList<Flights> listPrices = instFlightsDAO.getInfoForAllPriceTableDAO();
        ArrayList<Flights> list = new ArrayList<>();
        if(value.toLowerCase().equals("all")){
            return listPrices;
        }
        for(Flights flight:listPrices){
            if(flight.getDateOfDepart().toLowerCase().equals(value.toLowerCase())){
                list.add(flight);}
        }
        return list;
    }

    public ArrayList<Flights> makePriceTableArrival(){
        ArrayList<Flights> listDeparturePrices = instFlightsDAO.getInfoForArrivalPriceTableDAO();
        return listDeparturePrices;
    }

    public ArrayList<Flights> makePriceTableArrivalAirline(String value){
        ArrayList<Flights> listArrivalPrices = instFlightsDAO.getInfoForArrivalPriceTableDAO();
        ArrayList<Flights> list = new ArrayList<>();
        if(value.toLowerCase().equals("all")){
            return listArrivalPrices;
        }
        else {
            for (Flights flight : listArrivalPrices) {
                if (flight.getAirlineName().toLowerCase().equals(value.toLowerCase())) {
                    list.add(flight);
                }
            }
            return list;
        }
    }

    public ArrayList<Flights> makePriceTableAirline(String value){
        ArrayList<Flights> listAllPrices = instFlightsDAO.getInfoForAllPriceTableDAO();
        ArrayList<Flights> list = new ArrayList<>();
        if(value.toLowerCase().equals("all")){
            return listAllPrices;
        }
        else {
            for (Flights flight : listAllPrices) {
                if (flight.getAirlineName().toLowerCase().equals(value.toLowerCase())) {
                    list.add(flight);
                }
            }
            return list;
        }
    }

    public ArrayList<Flights> makePriceTableArrivalOriginPort(String value){
        ArrayList<Flights> listArrivalPrices = instFlightsDAO.getInfoForArrivalPriceTableDAO();
        ArrayList<Flights> list = new ArrayList<>();
        if(value.toLowerCase().equals("all")){
            return listArrivalPrices;
        }
        for(Flights flight:listArrivalPrices){
            if(flight.getPortOfDepart().toLowerCase().equals(value.toLowerCase())){
                list.add(flight);}
        }
        return list;
    }

    public ArrayList<Flights> makePriceTableAll(){
        ArrayList<Flights> listDeparturePrices = instFlightsDAO.getInfoForAllPriceTableDAO();
        return listDeparturePrices;
    }

    public String[] makeArrOfDestinPortsFromOdessaForComboBox(){
        ArrayList<Flights> arrAllFlights = instFlightsDAO.getAllDAO();
        ArrayList<String> listOfPortDestinations = new ArrayList<String>();
            for (Flights flight:arrAllFlights){
            if(flight.getPortOfDepart().toLowerCase().equals("odessa")){
                listOfPortDestinations.add(flight.getPortOfDestin());
            }
        }
        Set<String> set = new HashSet<>(listOfPortDestinations);
        listOfPortDestinations.clear();
        listOfPortDestinations.addAll(set);
        int size = listOfPortDestinations.size();
        String[] arrDestinationPorts = new String[size+1];
        arrDestinationPorts[0] = "ALL";
        for(int i = 0; i < size; i++){
            arrDestinationPorts[i+1] = listOfPortDestinations.get(i).toUpperCase();
        }
        return arrDestinationPorts;
    }

    public String[] makeArrOfOriginPortsToOdessaForComboBox(){
        ArrayList<Flights> arrAllFlights = instFlightsDAO.getAllDAO();
        ArrayList<String> listOfOriginPorts = new ArrayList<String>();
        for (Flights flight:arrAllFlights){
            if(flight.getPortOfDestin().toLowerCase().equals("odessa")){
                listOfOriginPorts.add(flight.getPortOfDepart());
            }
        }
        Set<String> set = new HashSet<>(listOfOriginPorts);
        listOfOriginPorts.clear();
        listOfOriginPorts.addAll(set);
        int size = listOfOriginPorts.size();
        String[] arrOriginPorts = new String[size+1];
        arrOriginPorts[0] = "ALL";
        for(int i = 0; i < size; i++){
            arrOriginPorts[i+1] = listOfOriginPorts.get(i).toUpperCase();
        }
        return arrOriginPorts;
    }

    public String[] makeArrTimerangeForComboBox(){
        String[] arrTimeRange = {"ALL","00-04","04-08","08-12","12-16","16-20","20-24"};
        return arrTimeRange;
    }

    public String[] makeArrDateRangeFromOdessaForComboBox(){
        ArrayList<Flights> arrAllFlights = instFlightsDAO.getAllDAO();
        ArrayList<String> listDateRange = new ArrayList<String>();
        for (Flights flight:arrAllFlights){
            if(flight.getPortOfDepart().toLowerCase().equals("odessa")){
                listDateRange.add(flight.getDateOfDepart());
            }
        }
        Set<String> set = new HashSet<>(listDateRange);
        listDateRange.clear();
        listDateRange.addAll(set);
        int size = listDateRange.size();
        String[] arrDateRange = new String[size+1];
        arrDateRange[0] = "ALL";
        for(int i = 0; i < size; i++){
            arrDateRange[i+1] = listDateRange.get(i);
        }
        return arrDateRange;
    }

    public String[] makeArrDateRangeToOdessaForComboBox(){
        ArrayList<Flights> arrAllFlights = instFlightsDAO.getAllDAO();
        ArrayList<String> listDateRange = new ArrayList<String>();
        for (Flights flight:arrAllFlights){
            if(flight.getPortOfDestin().toLowerCase().equals("odessa")){
                listDateRange.add(flight.getDateOfArrive());
            }
        }
        Set<String> set = new HashSet<>(listDateRange);
        listDateRange.clear();
        listDateRange.addAll(set);
        int size = listDateRange.size();
        String[] arrDateRange = new String[size+1];
        arrDateRange[0] = "ALL";
        for(int i = 0; i < size; i++){
            arrDateRange[i+1] = listDateRange.get(i);
        }
        return arrDateRange;
    }

    private int getDifferenceInTime(Time time1, Time time2){
        int time1int = time1.getHours()* 3600 + time1.getMinutes() * 60 + time1.getSeconds();
        int time2int =  time2.getHours()* 3600 + time2.getMinutes() * 60 + time2.getSeconds();
        return (time1int - time2int);
    }
}
/*
    public String[] makeArrDateRangeForComboBox(){
        ArrayList<Flights> arrAllFlights = instFlightsDAO.getAllDAO();
        ArrayList<String> listDateRange = new ArrayList<String>();
        for (Flights flight:arrAllFlights){
                listDateRange.add(flight.getDateOfDepart());
        }
        Set<String> set = new HashSet<>(listDateRange);
        listDateRange.clear();
        listDateRange.addAll(set);
        int size = listDateRange.size();
        String[] arrDateRange = new String[size+1];
        arrDateRange[0] = "ALL";
        for(int i = 0; i < size; i++){
            arrDateRange[i+1] = listDateRange.get(i);
        }
        return arrDateRange;
    }
*/


