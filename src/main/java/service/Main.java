package service;

import dao.ConnectToMySQLDAO;
import view.StartMenuGUI;

/**
 * Created by ПК on 08.12.2016.
 */
public class Main {

    public static void main(String[] args) {
        ConnectToMySQLDAO inst = new ConnectToMySQLDAO();

        inst.createSQLDB("password");
        inst.connectToDB("password");
        inst.addTablesToDataBase();
        PutStartDataService instPut = new PutStartDataService();
        instPut.putStartData();
        /*
        StartMenuGUI instStartMenuGUI = new StartMenuGUI();
       instStartMenuGUI.startMenu();
*/
    }
}
