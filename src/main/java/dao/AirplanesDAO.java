package dao;

import model.Airplanes;

import java.sql.*;
import java.util.ArrayList;


/**
 * Created by ПК on 08.12.2016.
 */
public class AirplanesDAO extends ConnectToMySQLDAO implements DAOInterface<Airplanes> {
    public AirplanesDAO(){}

    private String querryInsert = "INSERT INTO airplanes (manufacturer,model,numberISO,year,economPlaces,businessPlaces,airline_id) " +
            "VALUES(?,?,?,?,?,?,?);";
    private String querryEdit = "UPDATE `airplanes` SET `manufacturer`= ?, `model`= ?,`numberISO`= ?, `year`= ?, " +
            "`economPlaces`= ?,`businessPlaces`= ?,  `airline_id`= ? WHERE `id`= ?;";
    private String querryDelete = "DELETE FROM `airplanes` WHERE `id` = ?;";
    private String querryGetAll = "Select * from `airplanes`;";
    private String querryGetById = "Select * from `airplanes` where `id` = ?;";

    @Override
    public boolean insertNewDAO(Airplanes airplane) {
            try (PreparedStatement ps = getConnection().prepareStatement(querryInsert)) {
                ps.setString(1, airplane.getManufacturer());
                ps.setString(2, airplane.getModel());
                ps.setString(3,airplane.getNumberISO());
                ps.setString(4,airplane.getYear() );
                ps.setInt(5,airplane.getPlacesEconom());
                ps.setInt(6,airplane.getPlacesBusiness());
                ps.setLong(7,airplane.getAirline_id());
                ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean editInDBDAO(Airplanes airplane) {
        try (PreparedStatement ps = getConnection().prepareStatement(querryEdit)) {
            ps.setString(1, airplane.getManufacturer());
            ps.setString(2, airplane.getModel());
            ps.setString(3,airplane.getNumberISO());
            ps.setString(4,airplane.getYear() );
            ps.setInt(5,airplane.getPlacesEconom());
            ps.setInt(6,airplane.getPlacesBusiness());
            ps.setLong(7,airplane.getAirline_id());
            ps.setLong(8,airplane.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteInDBDAO(Airplanes airplane) {
        try (PreparedStatement ps = getConnection().prepareStatement(querryDelete)) {
            ps.setLong(1, airplane.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<Airplanes> getAllDAO() {
        ArrayList<Airplanes> list = new ArrayList<Airplanes>();
        try(Statement st = getConnection().createStatement()){
            st.execute(querryGetAll);
            ResultSet rs = st.getResultSet();
            while (rs.next()){
                Airplanes instAirplanes = new Airplanes();
                instAirplanes.setId(rs.getLong("id"));
                instAirplanes.setManufacturer(rs.getString("manufacturer"));
                instAirplanes.setModel(rs.getString("model"));
                instAirplanes.setNumberISO(rs.getString("numberISO"));
                instAirplanes.setYear(rs.getString("year"));
                instAirplanes.setPlacesEconom(rs.getInt("economPlaces"));
                instAirplanes.setPlacesBusiness(rs.getInt("businessPlaces"));
                instAirplanes.setAirline_id(rs.getLong("airline_id"));
                list.add(instAirplanes);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Airplanes getByIdDAO(Airplanes airplane) {
        try {
            PreparedStatement ps = getConnection().prepareStatement(querryGetById);
            ps.setLong(1,airplane.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Airplanes instAirplanes = new Airplanes();
                instAirplanes.setId(rs.getLong("id"));
                instAirplanes.setManufacturer(rs.getString("manufacturer"));
                instAirplanes.setModel(rs.getString("model"));
                instAirplanes.setNumberISO(rs.getString("numberISO"));
                instAirplanes.setYear(rs.getString("year"));
                instAirplanes.setPlacesEconom(rs.getInt("economPlaces"));
                instAirplanes.setPlacesBusiness(rs.getInt("businessPlaces"));
                instAirplanes.setAirline_id(rs.getLong("airline_id"));
                return instAirplanes;
            }
            return null;
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
