package dao;

import model.Flights;

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
public class FlightsDAO extends ConnectToMySQLDAO implements DAOInterface<Flights> {
    public FlightsDAO(){}

    private String querryInsert = "INSERT INTO flights (number,departPort,destinationPort,dateDepart,dateArrive,timeDepart,timeArrive," +
            "priceEconom,priceBusiness,airplane_id) VALUES(?,?,?,?,?,?,?,?,?,?);";
    private String querryEdit = "UPDATE `flights` SET `number`= ?,`departPort`= ?,`destinationPort`= ?,`dateDepart` = ?,`dateArrive` = ?," +
            "`timeDepart`= ?,`timeArrive`= ?, `priceEconom`= ?, `priceBusiness`= ?,`airplane_id`= ? WHERE `id`= ?;";
    private String querryDelete = "DELETE FROM `flights` WHERE `id` = ?;";
    private String querryGetAll = "Select * from `flights`;";
    private String querryGetById = "Select * from `flights` where `id` = ?;";
    private String querryForDepartPriceTable = "Select f.*,airlines.name from flights f join airplanes a on " +
            "f.airplane_id = a.id join airlines on a.airline_id = airlines.id  where f.departPort = 'Odessa'" +
            " order by dateDepart,timeDepart;";
    private String querryForArrivalPriceTable = "Select f.*,airlines.name from flights f join airplanes a on " +
            "f.airplane_id = a.id join airlines on a.airline_id = airlines.id  where f.destinationPort = 'odessa' " +
            "order by dateDepart,timeArrive;";
    private String querryForAllPriceTable = "Select f.*,airlines.name from flights f join airplanes a on " +
            "f.airplane_id = a.id join airlines on a.airline_id = airlines.id order by dateDepart,departPort,timeDepart;";

    @Override
    public boolean insertNewDAO(Flights flight) {
        java.sql.Date sqlDateDepart = makeDateOfFlight(flight)[0];
        java.sql.Date sqlDateArrive = makeDateOfFlight(flight)[1];
        try (PreparedStatement ps = getConnection().prepareStatement(querryInsert)) {
            ps.setString(1, flight.getNumber());
            ps.setString(2, flight.getPortOfDepart());
            ps.setString(3,flight.getPortOfDestin());
            ps.setDate(4,sqlDateDepart );
            ps.setDate(5,sqlDateArrive );
            ps.setString(6,flight.getTimeOdDepart());
            ps.setString(7,flight.getTimeOfArrive());
            ps.setInt(8,flight.getPriceEconom());
            ps.setInt(9,flight.getPriceBusiness());
            ps.setLong(10,flight.getAirplane_id());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean editInDBDAO(Flights flight) {
        java.sql.Date sqlDateDepart = makeDateOfFlight(flight)[0];
        java.sql.Date sqlDateArrive = makeDateOfFlight(flight)[1];
        try (PreparedStatement ps = getConnection().prepareStatement(querryEdit)) {
            ps.setString(1, flight.getNumber());
            ps.setString(2, flight.getPortOfDepart());
            ps.setString(3,flight.getPortOfDestin());
            ps.setDate(4,sqlDateDepart );
            ps.setDate(5,sqlDateArrive );
            ps.setString(6,flight.getTimeOdDepart());
            ps.setString(7,flight.getTimeOfArrive());
            ps.setInt(8,flight.getPriceEconom());
            ps.setInt(9,flight.getPriceBusiness());
            ps.setLong(10,flight.getAirplane_id());
            ps.setLong(11,flight.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private java.sql.Date[] makeDateOfFlight(Flights flight){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDepart = null;
        Date parsedArrive = null;
        try {
            parsedDepart = format.parse(flight.getDateOfDepart());
            parsedArrive = format.parse(flight.getDateOfArrive());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date sqlDateDepart = new java.sql.Date(parsedDepart.getTime());
        java.sql.Date sqlDateArrive = new java.sql.Date(parsedArrive.getTime());
        return new java.sql.Date[]{sqlDateDepart,sqlDateArrive};
    }

    @Override
    public boolean deleteInDBDAO(Flights flight) {
        try (PreparedStatement ps = getConnection().prepareStatement(querryDelete)) {
            ps.setLong(1, flight.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<Flights> getAllDAO() {
        ArrayList<Flights> list = new ArrayList<Flights>();
        try(Statement st = getConnection().createStatement()){
            st.execute(querryGetAll);
            ResultSet rs = st.getResultSet();
            while (rs.next()){
                list.add(makeInstFlightAndPutValues(rs,false));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Flights getByIdDAO(Flights flight) {
        try {
            PreparedStatement ps = getConnection().prepareStatement(querryGetById);
            ps.setLong(1,flight.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return makeInstFlightAndPutValues(rs,false);
            }
            return null;
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    private Flights makeInstFlightAndPutValues(ResultSet rs,boolean isAirlineNameColumn)throws SQLException{
        Flights instFlights = new Flights();
        instFlights.setId(rs.getLong("id"));
        instFlights.setNumber(rs.getString("number"));
        instFlights.setPortOfDepart(rs.getString("departPort"));
        instFlights.setPortOfDestin(rs.getString("destinationPort"));
        instFlights.setDateOfDepart(rs.getString("dateDepart"));
        instFlights.setDateOfArrive(rs.getString("dateArrive"));
        instFlights.setTimeOdDepart(rs.getString("timeDepart"));
        instFlights.setTimeOfArrive(rs.getString("timeArrive"));
        instFlights.setPriceEconom(rs.getInt("priceEconom"));
        instFlights.setPriceBusiness(rs.getInt("priceBusiness"));
        instFlights.setAirplane_id(rs.getLong("airplane_id"));
        if(isAirlineNameColumn){
            instFlights.setAirlineName(rs.getString("name"));
        }
        return instFlights;
    }

    public ArrayList<Flights> getInfoForDepartPriceTableDAO() {
        ArrayList<Flights> list = new ArrayList<Flights>();
        try{
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(querryForDepartPriceTable);
            while (rs.next()){
                list.add(makeInstFlightAndPutValues(rs,true));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<Flights> getInfoForArrivalPriceTableDAO() {
        ArrayList<Flights> list = new ArrayList<Flights>();
        try{
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(querryForArrivalPriceTable);
            while (rs.next()){
                list.add(makeInstFlightAndPutValues(rs,true));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<Flights> getInfoForAllPriceTableDAO() {
        ArrayList<Flights> list = new ArrayList<Flights>();
        try{
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(querryForAllPriceTable);
            while (rs.next()){
                list.add(makeInstFlightAndPutValues(rs,true));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

}
