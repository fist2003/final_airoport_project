package service;

import dao.FlightsDAO;
import model.Flights;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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

    public ArrayList<Flights> getListAllDepartureFlights(){
        ArrayList<Flights> listAllFlights = instFlightsDAO.getAllDAO();
        ArrayList<Flights> departFlights = new ArrayList<Flights>();
        for (Flights flight:listAllFlights){
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

    public ArrayList<Flights> makeArrivalScheduleTable(int jSliderValue,ArrayList<Flights> listAllArrivalFlights){
        ArrayList<Flights> listArrivalFlightsByJSlider = new ArrayList<Flights>();
        for (Flights flight:listAllArrivalFlights) {
            String timeArrivalStr = flight.getTimeOfArrive();
            String timeDepartStr = flight.getTimeOdDepart();
            String status = "unknown";
            String currentTimeArrival = " - ";
            String gateName = "";
            Time timeShedule = Time.valueOf(timeArrivalStr);
            Time timeDepart = Time.valueOf(timeDepartStr);
            Time timeNow = new Time(System.currentTimeMillis());
            if(getDifferenceInTime(timeDepart,timeNow)>= 0){
                status = "on time";
            }
            else if((getDifferenceInTime(timeShedule,timeNow)>=0)&&(getDifferenceInTime(timeDepart,timeNow)<=0)){
                status = "in flight";
            }
            else if(getDifferenceInTime(timeShedule,timeNow)< 0){
                status = "arrived";
                currentTimeArrival = timeArrivalStr;
                gateName = "A1";
            }
            flight.setStatusOfFlight(status);
            flight.setCurrentTime(currentTimeArrival);
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
            int a = getDifferenceInTime(jSliderTime,timeShedule);
            if(a <= 0){
                listArrivalFlightsByJSlider.add(flight);
            }
        }
        return listArrivalFlightsByJSlider;
    }

    public ArrayList<Flights> makeDepartureScheduleTable(int jSliderValue,ArrayList<Flights> listAllDepartureFlights){
        ArrayList<Flights> listDepartureFlightsByJSlider = new ArrayList<Flights>();
        for (Flights flight:listAllDepartureFlights) {
            String timeDepartStr = flight.getTimeOdDepart();
            String status = "unknown";
            String currentTimeDepart = " - ";
            String gateName = "";
            Time timeDepart = Time.valueOf(timeDepartStr);
            Time timeNow = new Time(System.currentTimeMillis());
            if(getDifferenceInTime(timeDepart,timeNow)>=(60*60*2)){
                status = "on time";
            }
            else if((getDifferenceInTime(timeDepart,timeNow) < (60*60*2))&&(getDifferenceInTime(timeDepart,timeNow)>= (45*60))){
                status = "check-in";
                gateName = "B1";
            }
            else if((getDifferenceInTime(timeDepart,timeNow) < (45*60))&&(getDifferenceInTime(timeDepart,timeNow)>= (30*60))){
                status = "check-in closed";
                gateName = "B1";
            }
            else if((getDifferenceInTime(timeDepart,timeNow) < (30*60))&&(getDifferenceInTime(timeDepart,timeNow)> (0))){
                status = "gate closed";
                gateName = "B1";
            }
            else if(getDifferenceInTime(timeDepart,timeNow) <= 0){
                status = "departed";
                gateName = "B1";
                currentTimeDepart = timeDepartStr;
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

}
