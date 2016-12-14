package service;

import dao.UsersDAO;
import model.Users;

import java.util.ArrayList;

/**
 * Created by ПК on 13.12.2016.
 */
public class UsersService {
    public UsersService(){
        this.instUsersDAO = new UsersDAO();
    }
    private UsersDAO instUsersDAO;

    public static boolean flagIsAdmin = false;

    public static void setFlagIsAdmin(boolean isAdmin) {
        UsersService.flagIsAdmin = isAdmin;
    }


    public ArrayList<Users> getAllService(){
        return instUsersDAO.getAllDAO();
    }

    public void editDataService(Users user){
        instUsersDAO.editInDBDAO(user);
    }
    public void deleteDataService(Users user){
        instUsersDAO.deleteInDBDAO(user);
    }
    public void insertNewService(Users user){
        instUsersDAO.insertNewDAO(user);
    }

    public boolean[] checkUser(Users user) {
        return instUsersDAO.checkUserDAO(user);
    }

}
