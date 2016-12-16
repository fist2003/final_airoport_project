package service;

import dao.UsersDAO;
import model.Airplanes;
import model.Entity;
import model.Users;
import view.UserJPanelGUI;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ПК on 13.12.2016.
 */
public class UsersService extends CheckInputValueService {
    public UsersService(){
        this.instUsersDAO = new UsersDAO();
    }
    private UsersDAO instUsersDAO;

    private final String loginUserKey = "Login of user";
    private final String passwordUserKey = "Password of user";
    private final String emailUserKey = "Email adress of user";
    private final String lastNameUserKey = "Last name of user";
    private final String firstNameUserKey = "First name of user";
    private final String sexUserKey = "Sex of user";
    private final String adminUserKey = "Admin status of user";

    public static boolean flagIsAdmin = false;

    public static void setFlagIsAdmin(boolean isAdmin) {
        UsersService.flagIsAdmin = isAdmin;
    }

    public ArrayList<Users> getAllService(){
        return instUsersDAO.getAllDAO();
    }

    public ArrayList<String> insertNewService(Users user){
        ArrayList<String> check = new ArrayList<String>(checkInputValues(user));
        if (check.size() == 0) {instUsersDAO.insertNewDAO(user);}
        return check;
    }

    public ArrayList<String> editDataService(Users user){
        ArrayList<String> check = new ArrayList<String>(checkInputValues(user));
        if (check.size() == 0) {instUsersDAO.editInDBDAO(user);}
        return check;
    }

    public ArrayList<Long> deleteDataService(Users user){
        ArrayList<Long> check = new ArrayList<Long>(checkSafeDelete(user));
        if (check.size() == 0) {instUsersDAO.deleteInDBDAO(user);}
        return check;
    }

    public boolean[] checkUser(Users user) {
        return instUsersDAO.checkUserDAO(user);
    }

    @Override
    protected ArrayList<String> checkInputValues(Entity entity) {
        ArrayList<String> checkList = new ArrayList<String>();
        Users user = (Users) entity;
        ArrayList<Users> allUsersList = getAllService();
        String validEmail = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern patIso = Pattern.compile(validEmail);
        Matcher matIso = patIso.matcher(user.getEmail());
        for (Users usersFromDB:allUsersList){
            if((usersFromDB.getLogin().toLowerCase().equals(user.getLogin().toLowerCase()))&&
                    (usersFromDB.getId() != user.getId())){
                checkList.add("User with this login is already exist");
            }
            else if((usersFromDB.getEmail().toLowerCase().equals(user.getEmail().toLowerCase()))&&
                    (usersFromDB.getId() != user.getId())){
                checkList.add("User with this email is already exist");
            }
        }
        if (checkForWhiteSpace(user.getLogin())){
            checkList.add(loginUserKey);
        }
        else if ((checkForWhiteSpace(user.getPassword()))||(user.getPassword().length() < 5)){
            checkList.add(passwordUserKey);
        }
        else if(!matIso.matches()){
            checkList.add(emailUserKey);
        }
        else if (checkForWhiteSpace(user.getLastName())){
            checkList.add(lastNameUserKey);
        }
        else if (checkForWhiteSpace(user.getFirstName())){
            checkList.add(firstNameUserKey);
        }
        else if ((checkForWhiteSpace(user.getSex()))||!((user.getSex().toLowerCase().equals("male"))||
                (user.getSex().toLowerCase().equals("female")))){
            checkList.add(sexUserKey);
        }
        else if((checkForWhiteSpace(String.valueOf(user.getIsAdmin())))||(user.getIsAdmin() < 0)||
                (user.getIsAdmin() > 1)){
            checkList.add(adminUserKey);
        }
        return checkList;
    }

    @Override
    protected ArrayList<Long> checkSafeDelete(Entity entity) {
        Users user = (Users) entity;
        ArrayList<Long> list = new ArrayList<Long>();
        if (user.getLogin().toLowerCase().equals(UserJPanelGUI.getUserName().toLowerCase())){
            list.add(-1l);
        }
        return list;
    }
}
