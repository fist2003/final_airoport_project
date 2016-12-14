package service;

import model.Flights;
import view.ScheduleJPanelGUI;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ПК on 12.12.2016.
 */
public class ScheduleTableSearchService extends ScheduleTableService {
    public ScheduleTableSearchService(){}

    public ArrayList<Flights> getListSearchArrivalFlights(final JComboBox dateCombobox, JComboBox directionComboBox,
                                                          JComboBox flightComboBox){
        ArrayList<Flights> listAllArrivalFlights = getListAllArrivalFlights();
        ArrayList<Flights> sortedByDate = new ArrayList<Flights>();
        for (Flights flight:listAllArrivalFlights) {
            if(flight.getDateOfArrive().toString().equals(dateCombobox.getSelectedItem().toString())){
                sortedByDate.add(flight);
            }
        }
        ArrayList<Flights> sortedByDirection = new ArrayList<Flights>();
        if ((directionComboBox.getSelectedItem().toString().equals(originStr))||
                (directionComboBox.getSelectedItem().toString().equals(detinStr))){
            for (Flights flight:sortedByDate) {
                sortedByDirection.add(flight);
            }
        }
        else {
            for (Flights flight:sortedByDate) {
                if(flight.getPortOfDepart().toLowerCase().equals(directionComboBox.getSelectedItem().toString().toLowerCase())){
                    sortedByDirection.add(flight);
                }
            }
        }
        ArrayList<Flights> sortedByNumber = new ArrayList<Flights>();
        if (flightComboBox.getSelectedItem().toString().equals(flightsStr)){
            for (Flights flight:sortedByDirection) {
                sortedByNumber.add(flight);
            }
        }
        else for (Flights flight:sortedByDirection) {
            if(flight.getNumber().toLowerCase().equals(flightComboBox.getSelectedItem().toString().toLowerCase())){
                sortedByNumber.add(flight);
            }
        }
        return sortedByNumber;
    }

    public ArrayList<Flights> getListSearchDepartureFlights(final JComboBox dateCombobox,JComboBox directionComboBox,
                                                            JComboBox flightComboBox){
        ArrayList<Flights> listAllDepartFlights = getListAllDepartureFlights();
        ArrayList<Flights> sortedByDate = new ArrayList<Flights>();
        for (Flights flight:listAllDepartFlights) {
            if(flight.getDateOfDepart().toString().equals(dateCombobox.getSelectedItem().toString())){
                sortedByDate.add(flight);
            }
        }
        ArrayList<Flights> sortedByDirection = new ArrayList<Flights>();
        if ((directionComboBox.getSelectedItem().toString().equals(originStr))||
                (directionComboBox.getSelectedItem().toString().equals(detinStr))){
            for (Flights flight:sortedByDate) {
                sortedByDirection.add(flight);
            }
        }
        else {
            for (Flights flight:sortedByDate) {
                if(flight.getPortOfDestin().toLowerCase().equals(directionComboBox.getSelectedItem().toString().toLowerCase())){
                    sortedByDirection.add(flight);
                }
            }
        }
        ArrayList<Flights> sortedByNumber = new ArrayList<Flights>();
        if (flightComboBox.getSelectedItem().toString().equals(flightsStr)){
            for (Flights flight:sortedByDirection) {
                sortedByNumber.add(flight);
            }
        }
        else for (Flights flight:sortedByDirection) {
            if(flight.getNumber().toLowerCase().equals(flightComboBox.getSelectedItem().toString().toLowerCase())){
                sortedByNumber.add(flight);
            }
        }
        return sortedByNumber;
    }

    public String[] makeArrOfDatesForSearchComboBox(){
        ArrayList<Flights> listAllFlights = instFlightsDAO.getAllDAO();
        ArrayList<String> listDates = new ArrayList<>();
        if (ScheduleJPanelGUI.isArrivalsChoosedComboBox){
            for (Flights flight:listAllFlights){
                if(flight.getPortOfDestin().toLowerCase().equals("odessa")){
                    listDates.add(flight.getDateOfArrive());
                }
            }
        }
        else {
            for (Flights flight : listAllFlights) {
                if (flight.getPortOfDepart().toLowerCase().equals("odessa")) {
                    listDates.add(flight.getDateOfDepart());
                }
            }
        }
        Set<String> set = new HashSet<String>(listDates);
        listDates.clear();
        listDates.addAll(set);
        int size = listDates.size();
        String[] arrDates = new String[size];
        for(int i = 0; i < size; i++){
            arrDates[i] = listDates.get(i);
        }
        return arrDates;
    }

    public String[] makeArrOfDestinPortsForSearchComboBox(){
        ArrayList<Flights> listAllFlights = instFlightsDAO.getAllDAO();
        ArrayList<String> listPorts = new ArrayList<>();
        if (ScheduleJPanelGUI.isArrivalsChoosedComboBox){
            for (Flights flight:listAllFlights){
                if(flight.getPortOfDestin().toLowerCase().equals("odessa")){
                    listPorts.add(flight.getPortOfDepart());
                }
            }
            Set<String> set = new HashSet<String>(listPorts);
            listPorts.clear();
            listPorts.addAll(set);
            int size = listPorts.size();
            String[] arrPorts = new String[size+1];
            arrPorts[0] = originStr;
            for(int i = 0; i < size; i++){
                arrPorts[i+1] = listPorts.get(i);
            }
            return arrPorts;
        }
        else {
            for (Flights flight : listAllFlights) {
                if (flight.getPortOfDepart().toLowerCase().equals("odessa")) {
                    listPorts.add(flight.getPortOfDestin());
                }
            }
            Set<String> set = new HashSet<String>(listPorts);
            listPorts.clear();
            listPorts.addAll(set);
            int size = listPorts.size();
            String[] arrPorts = new String[size+1];
            arrPorts[0] = detinStr;
            for(int i = 0; i < size; i++){
                arrPorts[i+1] = listPorts.get(i);
            }
            return arrPorts;
        }
    }

    public String[] makeArrOfFlightsForSearchComboBox(){
        ArrayList<Flights> listAllFlights = instFlightsDAO.getAllDAO();
        ArrayList<String> listFlights = new ArrayList<>();
        if (ScheduleJPanelGUI.isArrivalsChoosedComboBox){
            for (Flights flight:listAllFlights){
                if(flight.getPortOfDestin().toLowerCase().equals("odessa")){
                    listFlights.add(flight.getNumber());
                }
            }
        }
        else {
            for (Flights flight : listAllFlights) {
                if (flight.getPortOfDepart().toLowerCase().equals("odessa")) {
                    listFlights.add(flight.getNumber());
                }
            }
        }
        int size = listFlights.size();
        String[] arrFlights = new String[size+1];
        arrFlights[0] = flightsStr;
        for(int i = 0; i < size; i++){
            arrFlights[i+1] = listFlights.get(i);
        }
        return arrFlights;
    }

    public String[][] makeArrForSearchComboBoxByDirection(String value){
        ArrayList<Flights> listAllFlights = instFlightsDAO.getAllDAO();
        ArrayList<String> listFlightsNumbers = new ArrayList<>();
        ArrayList<String> listDates = new ArrayList<>();
        if (ScheduleJPanelGUI.isArrivalsChoosedComboBox) {
            for (Flights flight : listAllFlights) {
                if ((flight.getPortOfDepart().toLowerCase().equals(value.toLowerCase()))&&
                        (flight.getPortOfDestin().toLowerCase().equals("odessa"))) {
                    listFlightsNumbers.add(flight.getNumber());
                    listDates.add(flight.getDateOfArrive());
                }
            }
        }
        else {
            for (Flights flight : listAllFlights) {
                if ((flight.getPortOfDestin().toLowerCase().equals(value.toLowerCase()))
                        &&(flight.getPortOfDepart().toLowerCase().equals("odessa"))){
                    listFlightsNumbers.add(flight.getNumber());
                    listDates.add(flight.getDateOfDepart());
                }
            }
        }
        int size = listFlightsNumbers.size();
        String[] arrFlights = new String[size+1];
        arrFlights[0] = flightsStr;
        for(int i = 0; i < size; i++){
            arrFlights[i+1] = listFlightsNumbers.get(i);
        }
        Set<String> set = new HashSet<String>(listDates);
        listDates.clear();
        listDates.addAll(set);
        int size2 = listDates.size();
        String[] arrDates = new String[size2];
        for(int i = 0; i < size2; i++){
            arrDates[i] = listDates.get(i);
        }
        return new String[][]{arrFlights,arrDates};
    }

    public String[][] makeArrForSearchComboBoxByDate(String value){
        ArrayList<Flights> listAllFlights = instFlightsDAO.getAllDAO();
        ArrayList<String> listFlightsNumbers = new ArrayList<>();
        ArrayList<String> listDirection = new ArrayList<>();
        if (ScheduleJPanelGUI.isArrivalsChoosedComboBox) {
            for (Flights flight : listAllFlights) {
                if ((flight.getDateOfDepart().toLowerCase().equals(value.toLowerCase()))&&
                        (flight.getPortOfDestin().toLowerCase().equals("odessa"))) {
                    listFlightsNumbers.add(flight.getNumber());
                    listDirection.add(flight.getPortOfDepart());
                }
            }
        }
        else {
            for (Flights flight : listAllFlights) {
                if ((flight.getDateOfArrive().toLowerCase().equals(value.toLowerCase()))&&
                        (flight.getPortOfDepart().toLowerCase().equals("odessa")))  {
                    listFlightsNumbers.add(flight.getNumber());
                    listDirection.add(flight.getPortOfDestin());
                }
            }
        }
        int size = listFlightsNumbers.size();
        String[] arrFlights = new String[size+1];
        arrFlights[0] = flightsStr;
        for(int i = 0; i < size; i++){
            arrFlights[i+1] = listFlightsNumbers.get(i);
        }
        Set<String> set = new HashSet<String>(listDirection);
        listDirection.clear();
        listDirection.addAll(set);
        int size2 = listDirection.size();
        String[] arrDirections = new String[size2+1];
        if (ScheduleJPanelGUI.isArrivalsChoosedComboBox){
            arrDirections[0] = detinStr;
        }
        else {
            arrDirections[0] = originStr;
        }
        for(int i = 0; i < size2; i++){
            arrDirections[i+1] = listDirection.get(i);
        }
        return new String[][]{arrFlights,arrDirections};
    }

}
