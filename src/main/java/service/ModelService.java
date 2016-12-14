package service;

import dao.*;
import model.Entity;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ПК on 09.12.2016.
 */
public class ModelService {
    public ModelService(){
        mapOfInst.put(airlinesStr,new AirlinesDAO());
        mapOfInst.put(airplanesStr,new AirplanesDAO());
        mapOfInst.put(flightsStr,new FlightsDAO());
        mapOfInst.put(passengersStr,new PassengersDAO());
        mapOfInst.put(usersStr,new UsersDAO());
    }

    private final String airlinesStr = "Airlines";
    private final String airplanesStr = "Airplanes";
    private final String flightsStr = "Flights";
    private final String passengersStr = "Passengers";
    private final String usersStr = "Users";
    HashMap<String,DAOInterface> mapOfInst = new HashMap<String,DAOInterface>();

    public boolean insertNewService(Entity instEntity) {
        return getInstanceDAO(instEntity).insertNewDAO(instEntity);
    }

    public boolean editInDBService(Entity instEntity){
        return getInstanceDAO(instEntity).editInDBDAO(instEntity);
    }

    public boolean deleteInDBService(Entity instEntity) {
        return getInstanceDAO(instEntity).deleteInDBDAO(instEntity);
    }

    public ArrayList<Entity> getAllService(Entity instEntity){
        return getInstanceDAO(instEntity).getAllDAO();
    }

    public Entity getByIdService(Entity instEntity) {
        return (Entity) getInstanceDAO(instEntity).getByIdDAO(instEntity);
    }

    private DAOInterface getInstanceDAO(Entity instEntity){
        Class c = instEntity.getClass();
        String nameInstDAO = c.getSimpleName();
        DAOInterface inst = mapOfInst.get(nameInstDAO);
        return inst;
    }


}
