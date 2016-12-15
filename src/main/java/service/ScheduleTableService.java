package service;

import dao.FlightsDAO;
import model.Flights;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by ПК on 09.12.2016.
 */
public class ScheduleTableService {
    public ScheduleTableService(){}
    protected FlightsDAO instFlightsDAO = new FlightsDAO();
    protected final String originStr = "ORIGIN";
    protected final String detinStr = "DESTINATION";
    protected final String flightsStr  = "FLIGHTS";

    public ArrayList<Flights> getListAllArrivalFlights(){
        ArrayList<Flights> listAllFlights = instFlightsDAO.getAllDAO();
        ArrayList<Flights> arrivalFlights = new ArrayList<Flights>();
        for (Flights flight:listAllFlights){
            if (flight.getPortOfDestin().toLowerCase().equals("odessa")){
                arrivalFlights.add(flight);
            }
        }
        Collections.sort(arrivalFlights, new Comparator<Flights>() {
            @Override
            public int compare(Flights o1, Flights o2) {
                Time time1 = Time.valueOf(o1.getTimeOfArrive());
                Time time2 = Time.valueOf(o2.getTimeOfArrive());
                return getDifferenceInTime(time1,time2);
            }
        });
        return arrivalFlights;
    }

    public ArrayList<Flights> getListArrivalFlightsByDate(Date value){
        ArrayList<Flights> listAllFlights = getListAllArrivalFlights();
        ArrayList<Flights> listFlightsByDate = new ArrayList<Flights>();
        for (Flights flight:listAllFlights) {
            Date arriveDate = convertStringToDate(flight.getDateOfArrive());
            if(arriveDate.getDate() == value.getDate()){
                listFlightsByDate.add(flight);
            }
        }
        return listFlightsByDate;
    }

    public ArrayList<Flights> getListAllDepartureFlights(){
        ArrayList<Flights> listAllArriveFlights = instFlightsDAO.getAllDAO();
        ArrayList<Flights> departFlights = new ArrayList<Flights>();
        for (Flights flight:listAllArriveFlights){
            if (flight.getPortOfDepart().toLowerCase().equals("odessa")){
                departFlights.add(flight);
            }
        }
        Collections.sort(departFlights, new Comparator<Flights>() {
            @Override
            public int compare(Flights o1, Flights o2) {
                Time time1 = Time.valueOf(o1.getTimeOdDepart());
                Time time2 = Time.valueOf(o2.getTimeOdDepart());
                return getDifferenceInTime(time1,time2);
            }
        });
        return departFlights;
    }

    public ArrayList<Flights> getListDepartureFlightsByDate(Date value){
        ArrayList<Flights> listAllDepartFlights = getListAllDepartureFlights();
        ArrayList<Flights> listFlightsByDate = new ArrayList<Flights>();
        for (Flights flight:listAllDepartFlights) {
            Date arriveDate = convertStringToDate(flight.getDateOfDepart());
            if(arriveDate.getDate() == value.getDate()){
                listFlightsByDate.add(flight);
            }
        }
        return listFlightsByDate;
    }

    public ArrayList<Flights> makeArrivalScheduleTable(int jSliderValue,ArrayList<Flights> listAllArrivalFlights) {
        ArrayList<Flights> listArrivalFlightsByJSlider = new ArrayList<Flights>();
        Date curDate = new Date();
        Time timeNow = new Time(System.currentTimeMillis());
        for (Flights flight : listAllArrivalFlights) {
            String status = "unknown";
            String currentTimeArrival = " - ";
            String gateName = "";
            Date dateArrive = convertStringToDate(flight.getDateOfArrive());
            String timeArrivalStr = flight.getTimeOfArrive();
            String timeDepartStr = flight.getTimeOdDepart();
            Time timeShedule = Time.valueOf(timeArrivalStr);
            Time timeDepart = Time.valueOf(timeDepartStr);
            if (dateArrive.getDate() == curDate.getDate()) {
                if (getDifferenceInTime(timeDepart, timeNow) >= 0) {
                    status = "on time";
                } else if ((getDifferenceInTime(timeShedule, timeNow) >= 0) && (getDifferenceInTime(timeDepart, timeNow) <= 0)) {
                    status = "in flight";
                } else if (getDifferenceInTime(timeShedule, timeNow) < 0) {
                    status = "arrived";
                    currentTimeArrival = timeArrivalStr;
                    gateName = "A1";
                }
            }
            else if (dateArrive.getDate() < curDate.getDate()) {
                status = "arrived";
                currentTimeArrival = timeArrivalStr;
                gateName = "A1";
            }
            else if (dateArrive.getDate() > curDate.getDate()) {
                status = "By Schedule";
            }
            flight.setStatusOfFlight(status);
            flight.setCurrentTime(currentTimeArrival);
            flight.setGateName(gateName);
            int valueJSliderModul = Math.abs((jSliderValue) - 24);
            String valueJSliderStr = "";
            if (valueJSliderModul < 10) {
                valueJSliderStr = "0" + valueJSliderModul + ":00:00";
            } else {
                valueJSliderStr = valueJSliderModul + ":00:00";
            }
            Time jSliderTime = Time.valueOf(valueJSliderStr);
            int a = getDifferenceInTime(jSliderTime, timeShedule);
            if (a <= 0) {
                listArrivalFlightsByJSlider.add(flight);
            }
        }
        return listArrivalFlightsByJSlider;
    }
    public ArrayList<Flights> makeDepartureScheduleTable(int jSliderValue,ArrayList<Flights> listAllDepartureFlights){
        ArrayList<Flights> listDepartureFlightsByJSlider = new ArrayList<Flights>();
        Date curDate = new Date();
        for (Flights flight:listAllDepartureFlights) {
            Date dateDepart = convertStringToDate(flight.getDateOfDepart());
            String timeDepartStr = flight.getTimeOdDepart();
            String status = "unknown";
            String currentTimeDepart = " - ";
            String gateName = "";
            Time timeDepart = Time.valueOf(timeDepartStr);
            Time timeNow = new Time(System.currentTimeMillis());
            if (dateDepart.getDate() == curDate.getDate()) {
                if (getDifferenceInTime(timeDepart, timeNow) >= (60 * 60 * 2)) {
                    status = "on time";
                } else if ((getDifferenceInTime(timeDepart, timeNow) < (60 * 60 * 2)) && (getDifferenceInTime(timeDepart, timeNow) >= (45 * 60))) {
                    status = "check-in";
                    gateName = "B1";
                } else if ((getDifferenceInTime(timeDepart, timeNow) < (45 * 60)) && (getDifferenceInTime(timeDepart, timeNow) >= (30 * 60))) {
                    status = "check-in closed";
                    gateName = "B1";
                } else if ((getDifferenceInTime(timeDepart, timeNow) < (30 * 60)) && (getDifferenceInTime(timeDepart, timeNow) > (0))) {
                    status = "gate closed";
                    gateName = "B1";
                } else if (getDifferenceInTime(timeDepart, timeNow) <= 0) {
                    status = "departed";
                    gateName = "B1";
                    currentTimeDepart = timeDepartStr;
                }
            }
            else if (dateDepart.getDate() < curDate.getDate()) {
                status = "departed";
                gateName = "B1";
                currentTimeDepart = timeDepartStr;
            }
            else if (dateDepart.getDate() < curDate.getDate()) {
                status = "By Schedule";
            }
            flight.setStatusOfFlight(status);
            flight.setCurrentTime(currentTimeDepart);
            flight.setGateName(gateName);
            int valueJSliderModul = Math.abs((jSliderValue)-24);
            String valueJSliderStr = "";
            if (valueJSliderModul < 10){
                valueJSliderStr = "0" + valueJSliderModul + ":00:00";
            }
            else {
                valueJSliderStr = valueJSliderModul + ":00:00";
            }
            Time jSliderTime = Time.valueOf(valueJSliderStr);
            int a = getDifferenceInTime(jSliderTime,timeDepart);
            if(a <= 0){
                listDepartureFlightsByJSlider.add(flight);
            }
        }
        return listDepartureFlightsByJSlider;
    }

    private int getDifferenceInTime(Time time1, Time time2){
        int time1int = time1.getHours()* 3600 + time1.getMinutes() * 60 + time1.getSeconds();
        int time2int =  time2.getHours()* 3600 + time2.getMinutes() * 60 + time2.getSeconds();
        return (time1int - time2int);
    }

    public String[] datesForArrivalComboBox(){
        ArrayList<Flights> listAllArrival = getListAllArrivalFlights();
        ArrayList<String> listDatesFutureAndCurrent = new ArrayList<>();
        for (Flights flight:listAllArrival){
            if (!isCheckDateEnd(flight.getDateOfArrive())){
                listDatesFutureAndCurrent.add(flight.getDateOfArrive());
            }
        }
        Set<String> set = new HashSet<>(listDatesFutureAndCurrent);
        listDatesFutureAndCurrent.clear();
        listDatesFutureAndCurrent.addAll(set);
        Collections.sort(listDatesFutureAndCurrent, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                Date dateO1 = convertStringToDate(o1);
                Date date02 = convertStringToDate(o2);
                return dateO1.getDate()-date02.getDate();
            }
        });
        int size = listDatesFutureAndCurrent.size();
        String[] arr = new String[size];
        for (int i = 0; i < size; i++){
            arr[i] = listDatesFutureAndCurrent.get(i);
        }
        return arr;
    }

    public String[] datesForDepartComboBox(){
        ArrayList<Flights> listAllDepart = getListAllDepartureFlights();
        ArrayList<String> listDatesFutureAndCurrent = new ArrayList<>();
        for (Flights flight:listAllDepart){
            if (!isCheckDateEnd(flight.getDateOfDepart())){
                listDatesFutureAndCurrent.add(flight.getDateOfDepart());
            }
        }
        Set<String> set = new HashSet<>(listDatesFutureAndCurrent);
        listDatesFutureAndCurrent.clear();
        listDatesFutureAndCurrent.addAll(set);
        Collections.sort(listDatesFutureAndCurrent, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                Date dateO1 = convertStringToDate(o1);
                Date date02 = convertStringToDate(o2);
                return dateO1.getDate()-date02.getDate();
            }
        });
        int size = listDatesFutureAndCurrent.size();
        String[] arr = new String[size];
        for (int i = 0; i < size; i++){
            arr[i] = listDatesFutureAndCurrent.get(i);
        }
        return arr;
    }

    private boolean isCheckDateEnd(String value){
        Date date = convertStringToDate(value);
        Date curDate = new Date();
        if(curDate.getDate() > date.getDate()){
            return true;
        } else {
            return false;
        }
    }

    public Date convertStringToDate(String value){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(value);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }



}
