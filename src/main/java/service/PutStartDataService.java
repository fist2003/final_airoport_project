package service;

import model.*;

/**
 * Created by ПК on 14.12.2016.
 */
public class PutStartDataService {
    public PutStartDataService(){}

    public void putStartData(){
        addAirlines();
        addAirplanes();
        addFlights();
        addPassengers();
        addUser();
    }

    private void addAirlines(){
        AirlineService instAirlineService = new AirlineService();
        instAirlineService.insertNewService(new Airlines(0l,"AERO JET","Ukraine,Odessa,Odessa International Airport","(+38048) 777 83 03)","www.flyaerojet.aero"));
        instAirlineService.insertNewService(new Airlines(0l,"AZUR AIR","Ukraine,Odessa,Odessa International Airport","(+380 48)  726 27 87","www.uhc.kiev.ua"));
        instAirlineService.insertNewService(new Airlines(0l,"Turkish Airlines","Ukraine,Odessa, Zhukovskogo str. 26/28","(380 482) 34 79 06","www.turkishairlines.com"));
    }

    private void addAirplanes(){
        AirplaneService instAirplaneService = new AirplaneService();
        instAirplaneService.insertNewService(new Airplanes(0l,"SAAB","340B","ISO000001","2000",30,5,1l));
        instAirplaneService.insertNewService(new Airplanes(0l,"SAAB","340B","ISO000002","2000",30,5,1l));
        instAirplaneService.insertNewService(new Airplanes(0l,"Embraer","PHENOM 100","ISO100001","2010",6,6,2l));
        instAirplaneService.insertNewService(new Airplanes(0l,"Embraer","PHENOM 100","ISO100002","2010",6,6,2l));
        instAirplaneService.insertNewService(new Airplanes(0l,"Airbus","A319-132","ISO200001","2005",130,20,3l));
        instAirplaneService.insertNewService(new Airplanes(0l,"Airbus","A319-132","ISO200002","2005",130,20,3l));
    }

    private void addFlights(){
        FlightService instFlightService = new FlightService();
        instFlightService.insertNewService(new Flights(1l,"ODKI0001","ODESSA","KIEV","2016-12-14","2016-12-14","06:00:00","07:30:00",150,250,1l));
        instFlightService.insertNewService(new Flights(2l,"KIOD0001","KIEV","ODESSA","2016-12-14","2016-12-14","09:30:00","11:00:00",150,250,1l));
        instFlightService.insertNewService(new Flights(3l,"ODKI0002","ODESSA","KIEV","2016-12-14","2016-12-14","13:00:00","14:30:00",150,250,1l));
        instFlightService.insertNewService(new Flights(4l,"KIOD0002","KIEV","ODESSA","2016-12-14","2016-12-14","16:30:00","18:00:00",150,250,1l));

        instFlightService.insertNewService(new Flights(5l,"ODLV0001","ODESSA","LVIV","2016-12-14","2016-12-14","07:00:00","08:30:00",150,250,2l));
        instFlightService.insertNewService(new Flights(6l,"LVOD0001","LVIV","ODESSA","2016-12-14","2016-12-14","10:30:00","12:00:00",150,250,2l));
        instFlightService.insertNewService(new Flights(7l,"ODLV0002","ODESSA","LVIV","2016-12-14","2016-12-14","14:00:00","15:30:00",150,250,2l));
        instFlightService.insertNewService(new Flights(8l,"LVOD0002","LVIV","ODESSA","2016-12-14","2016-12-14","17:30:00","19:00:00",150,250,2l));

        instFlightService.insertNewService(new Flights(9l,"ODMI0001","ODESSA","MINSK","2016-12-14","2016-12-14","05:00:00","07:00:00",200,300,3l));
        instFlightService.insertNewService(new Flights(10l,"MIOD0001","MINSK","ODESSA","2016-12-14","2016-12-14","09:00:00","11:00:00",200,300,3l));
        instFlightService.insertNewService(new Flights(11l,"ODMI0002","ODESSA","MINSK","2016-12-14","2016-12-14","13:00:00","15:00:00",200,300,3l));
        instFlightService.insertNewService(new Flights(12l,"MIOD0002","MINSK","ODESSA","2016-12-14","2016-12-14","17:00:00","19:00:00",200,300,3l));

        instFlightService.insertNewService(new Flights(13l,"ODTB0001","ODESSA","TBILICI","2016-12-14","2016-12-14","05:30:00","07:30:00",200,300,4l));
        instFlightService.insertNewService(new Flights(14l,"TBOD0001","TBILICI","ODESSA","2016-12-14","2016-12-14","09:30:00","11:30:00",200,300,4l));
        instFlightService.insertNewService(new Flights(15l,"ODTB0002","ODESSA","TBILICI","2016-12-14","2016-12-14","13:30:00","15:30:00",200,300,4l));
        instFlightService.insertNewService(new Flights(16l,"TBOD0002","TBILICI","ODESSA","2016-12-14","2016-12-14","17:30:00","19:30:00",200,300,4l));

        instFlightService.insertNewService(new Flights(17l,"STOD0001","STAMBUL","ODESSA","2016-12-14","2016-12-14","04:30:00","06:30:00",250,350,5l));
        instFlightService.insertNewService(new Flights(18l,"ODST0001","ODESSA","STAMBUL","2016-12-14","2016-12-14","08:30:00","10:30:00",250,350,5l));
        instFlightService.insertNewService(new Flights(19l,"STOD0002","STAMBUL","ODESSA","2016-12-14","2016-12-14","12:30:00","14:30:00",250,350,5l));
        instFlightService.insertNewService(new Flights(20l,"ODST0002","ODESSA","STAMBUL","2016-12-14","2016-12-14","16:30:00","18:30:00",250,350,5l));

        instFlightService.insertNewService(new Flights(17l,"KAOD0001","KAIR","ODESSA","2016-12-14","2016-12-14","07:30:00","09:30:00",250,350,6l));
        instFlightService.insertNewService(new Flights(18l,"ODKA0001","ODESSA","KAIR","2016-12-14","2016-12-14","10:30:00","12:30:00",250,350,6l));
        instFlightService.insertNewService(new Flights(19l,"KAOD0002","KAIR","ODESSA","2016-12-14","2016-12-14","14:30:00","16:30:00",250,350,6l));
        instFlightService.insertNewService(new Flights(20l,"ODKA0002","ODESSA","KAIR","2016-12-14","2016-12-14","18:30:00","20:30:00",250,350,6l));
    }

    public void addPassengers(){
        PassengersService instPassengersService = new PassengersService();
        instPassengersService.insertNewService(new Passengers(0l,"Ivanov","Ivan","UA0001","MALE","1990-01-01","UKRAINE","ECONOM",1l));
        instPassengersService.insertNewService(new Passengers(0l,"Petrov","Dima","UA0002","MALE","1991-02-11","UKRAINE","ECONOM",1l));
        instPassengersService.insertNewService(new Passengers(0l,"Simanov","Sasha","UA0003","MALE","1992-03-21","UKRAINE","ECONOM",1l));
        instPassengersService.insertNewService(new Passengers(0l,"Stepanova","Masha","UA0004","FEMALE","1993-02-11","UKRAINE","ECONOM",1l));
        instPassengersService.insertNewService(new Passengers(0l,"Gluhova","Natasha","UA0005","Female","1994-01-21","UKRAINE","BUSINESS",1l));
        instPassengersService.insertNewService(new Passengers(0l,"Stoikov","Ivan","UA0006","MALE","1995-02-01","UKRAINE","BUSINESS",1l));

        instPassengersService.insertNewService(new Passengers(0l,"Ivanov","Ivan","UA0001","MALE","1990-01-01","UKRAINE","ECONOM",8l));
        instPassengersService.insertNewService(new Passengers(0l,"Petrov","Dima","UA0002","MALE","1991-02-11","UKRAINE","ECONOM",8l));
        instPassengersService.insertNewService(new Passengers(0l,"Simanov","Sasha","UA0003","MALE","1992-03-21","UKRAINE","ECONOM",8l));
        instPassengersService.insertNewService(new Passengers(0l,"Stepanova","Masha","UA0004","FEMALE","1993-02-11","UKRAINE","ECONOM",8l));
        instPassengersService.insertNewService(new Passengers(0l,"Gluhova","Natasha","UA0005","Female","1994-01-21","UKRAINE","BUSINESS",8l));
        instPassengersService.insertNewService(new Passengers(0l,"Stoikov","Ivan","UA0006","MALE","1995-02-01","UKRAINE","BUSINESS",8l));

        instPassengersService.insertNewService(new Passengers(0l,"Sidorova","Anya","UA0011","Female","1990-01-01","UKRAINE","ECONOM",9l));
        instPassengersService.insertNewService(new Passengers(0l,"Petrovskii","Lesha","UA0012","MALE","1991-02-11","UKRAINE","ECONOM",9l));
        instPassengersService.insertNewService(new Passengers(0l,"Simanov","Vasya","UA0013","MALE","1992-03-21","UKRAINE","ECONOM",9l));
        instPassengersService.insertNewService(new Passengers(0l,"Babich","Dasha","UA0014","FEMALE","1993-02-11","UKRAINE","ECONOM",9l));
        instPassengersService.insertNewService(new Passengers(0l,"Simonova","Sasha","UA0015","Female","1994-01-21","UKRAINE","BUSINESS",9l));
        instPassengersService.insertNewService(new Passengers(0l,"Ivanov","Ivan","UA0016","MALE","1995-02-01","UKRAINE","BUSINESS",9l));

        instPassengersService.insertNewService(new Passengers(0l,"Sidorova","Anya","UA0011","Female","1990-01-01","UKRAINE","ECONOM",12l));
        instPassengersService.insertNewService(new Passengers(0l,"Petrovskii","Lesha","UA0012","MALE","1991-02-11","UKRAINE","ECONOM",12l));
        instPassengersService.insertNewService(new Passengers(0l,"Simanov","Vasya","UA0013","MALE","1992-03-21","UKRAINE","ECONOM",12l));
        instPassengersService.insertNewService(new Passengers(0l,"Babich","Dasha","UA0014","FEMALE","1993-02-11","UKRAINE","ECONOM",12l));
        instPassengersService.insertNewService(new Passengers(0l,"Simonova","Sasha","UA0015","Female","1994-01-21","UKRAINE","BUSINESS",12l));
        instPassengersService.insertNewService(new Passengers(0l,"Ivanov","Ivan","UA0016","MALE","1995-02-01","UKRAINE","BUSINESS",12l));
    }

    public void addUser(){
        UsersService instUsersService = new UsersService();
        instUsersService.insertNewService(new Users(0l,"admin","123456","admin@ukr.net","ivanov","ivan","MALE",1));
    }

}
