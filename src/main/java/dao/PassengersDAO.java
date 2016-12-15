package dao;

import model.Passengers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by ПК on 08.12.2016.
 */
public class PassengersDAO extends ConnectToMySQLDAO implements DAOInterface<Passengers> {
    public PassengersDAO(){}

    private String querryInsert = "INSERT INTO passengers (lastName,firstName,passport,sex,birthday,country," +
            "classTicket,flight_id) VALUES(?,?,?,?,?,?,?,?);";
    private String querryEdit = "UPDATE `passengers` SET `lastName`= ?, `firstName`= ?, `passport`= ?, `sex`= ?, " +
            "`birthday`= ?, `country`= ?, `classTicket`= ?, `flight_id`= ? WHERE `id`= ?;";
    private String querryDelete = "DELETE FROM `passengers` WHERE `id` = ?;";
    private String querryGetAll = "Select * from `passengers`;";
    private String querryGetById = "Select * from `passengers` where `id` = ?;";
    private String querryAllPassAndFlightInfo = "Select p.*,f.number,f.departPort,f.destinationPort,a.name,f.priceEconom,f.priceBusiness " +
            "from passengers p join flights f on p.flight_id = f.id join airplanes on f.airplane_id = airplanes.id " +
            "join airlines a on airplanes.airline_id = a.id order by f.number;";
    private String querryCheckEconomFreePlaces = "Select distinct count(airplanes.numberISO),airplanes.economPlaces " +
            "from passengers join flights on passengers.flight_id = flights.id join airplanes on " +
            "flights.airplane_id = airplanes.id where passengers.flight_id = ? and passengers.classTicket = 'econom';";
    private String querryCheckBusinessFreePlaces = "Select distinct count(airplanes.numberISO),airplanes.businessPlaces " +
            "from passengers join flights on passengers.flight_id = flights.id join airplanes on " +
            "flights.airplane_id = airplanes.id where passengers.flight_id = ? and passengers.classTicket = 'business';";
   // private String querryCheckPassportInFlight = "Select count(passport) from passengers where flight_id = ?  and passport = ? and classTicket = ?;";

    @Override
    public boolean insertNewDAO(Passengers passenger) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = null;
        try {
            parsed = format.parse(passenger.getBirtday());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date sqlDate = new java.sql.Date(parsed.getTime());
        try (PreparedStatement ps = getConnection().prepareStatement(querryInsert)) {
            ps.setString(1, passenger.getLastName());
            ps.setString(2, passenger.getFirstName());
            ps.setString(3, passenger.getPassportNumber());
            ps.setString(4, passenger.getSex());
            ps.setDate(5, sqlDate);
            ps.setString(6, passenger.getCountry());
            ps.setString(7, passenger.getClassTicket());
            ps.setLong(8, passenger.getFlight_id());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean editInDBDAO(Passengers passenger) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = null;
        try {
            parsed = format.parse(passenger.getBirtday());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date sqlDate = new java.sql.Date(parsed.getTime());
        try (PreparedStatement ps = getConnection().prepareStatement(querryEdit)) {
            ps.setString(1, passenger.getLastName());
            ps.setString(2, passenger.getFirstName());
            ps.setString(3, passenger.getPassportNumber());
            ps.setString(4, passenger.getSex());
            ps.setDate(5, sqlDate);
            ps.setString(6, passenger.getCountry());
            ps.setString(7, passenger.getClassTicket());
            ps.setLong(8, passenger.getFlight_id());
            ps.setLong(9,passenger.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteInDBDAO(Passengers passenger) {
        try (PreparedStatement ps = getConnection().prepareStatement(querryDelete)) {
            ps.setLong(1, passenger.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<Passengers> getAllDAO() {
        ArrayList<Passengers> list = new ArrayList<Passengers>();
        try(Statement st = getConnection().createStatement()){
            st.execute(querryGetAll);
            ResultSet rs = st.getResultSet();
            while (rs.next()){
                Passengers instPassengers = new Passengers();
                instPassengers.setId(rs.getLong("id"));
                instPassengers.setLastName(rs.getString("lastName"));
                instPassengers.setFirstName(rs.getString("firstName"));
                instPassengers.setPassportNumber(rs.getString("passport"));
                instPassengers.setSex(rs.getString("sex"));
                instPassengers.setBirtday(rs.getString("birthday"));
                instPassengers.setCountry(rs.getString("country"));
                instPassengers.setClassTicket(rs.getString("classTicket"));
                instPassengers.setFlight_id(rs.getLong("flight_id"));
                list.add(instPassengers);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Passengers getByIdDAO(Passengers passengers) {
        try {
            PreparedStatement ps = getConnection().prepareStatement(querryGetById);
            ps.setLong(1,passengers.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Passengers instPassengers = new Passengers();
                instPassengers.setId(rs.getLong("id"));
                instPassengers.setLastName(rs.getString("lastName"));
                instPassengers.setFirstName(rs.getString("firstName"));
                instPassengers.setPassportNumber(rs.getString("passport"));
                instPassengers.setSex(rs.getString("sex"));
                instPassengers.setBirtday(rs.getString("birthday"));
                instPassengers.setCountry(rs.getString("country"));
                instPassengers.setClassTicket(rs.getString("classTicket"));
                instPassengers.setFlight_id(rs.getLong("flight_id"));
                return instPassengers;
            }
            return null;
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Passengers> getAllandFlightInfoDAO() {
        ArrayList<Passengers> list = new ArrayList<Passengers>();
        try(Statement st = getConnection().createStatement()){
            st.execute(querryAllPassAndFlightInfo);
            ResultSet rs = st.getResultSet();
            while (rs.next()){
                Passengers instPassengers = new Passengers();
                instPassengers.setId(rs.getLong("id"));
                instPassengers.setLastName(rs.getString("lastName"));
                instPassengers.setFirstName(rs.getString("firstName"));
                instPassengers.setPassportNumber(rs.getString("passport"));
                instPassengers.setSex(rs.getString("sex"));
                instPassengers.setBirtday(rs.getString("birthday"));
                instPassengers.setCountry(rs.getString("country"));
                instPassengers.setClassTicket(rs.getString("classTicket"));
                instPassengers.setFlight_id(rs.getLong("flight_id"));
                instPassengers.setAirlineName(rs.getString("name"));
                instPassengers.setPortOfDepart(rs.getString("departPort"));
                instPassengers.setPortOfDestination(rs.getString("destinationPort"));
                instPassengers.setFlightNumber(rs.getString("number"));
                if(rs.getString("classTicket").toLowerCase().equals("econom")){
                    instPassengers.setPrice(rs.getString("priceEconom"));
                }
                else instPassengers.setPrice(rs.getString("priceBusiness"));
                list.add(instPassengers);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    public int checkFreePlaceInFlightDAO(Passengers passenger){
        int countBusyPlaces = 0;
        int placesTotal = 0;
        String querry = null;
        if(passenger.getClassTicket().toLowerCase().equals("econom")) {
            querry = querryCheckEconomFreePlaces;
        }
        else if(passenger.getClassTicket().toLowerCase().equals("business")){
            querry = querryCheckBusinessFreePlaces;
        }
        else{return -1;}
        try {PreparedStatement ps = getConnection().prepareStatement(querry);
            ps.setLong(1,passenger.getFlight_id());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                countBusyPlaces = rs.getInt(1);
                placesTotal = rs.getInt(2);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return placesTotal - countBusyPlaces;
    }
/*
    public int checkPassportInFlightDAO(Passengers passenger){
        int count = 0;
        try {PreparedStatement ps = getConnection().prepareStatement(querryCheckPassportInFlight);
            ps.setLong(1,passenger.getFlight_id());
            ps.setString(2,passenger.getPassportNumber());
            ps.setString(3,passenger.getClassTicket());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return count;
    }
*/
}
