package view.table_models;

import model.Users;
import view.EditInfoJPanelGUI;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * Created by ПК on 13.12.2016.
 */
public class EditUsersTableModel extends AbstractTableModel {
    public EditUsersTableModel(ArrayList<Users> users){
        this.users = users;
        try {
            setDataSource();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private ArrayList data = new ArrayList();
    private ArrayList<Users> users;


    @Override
    public int getRowCount() {
        return users.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public String getColumnName(int c) {
        String result = "";
        switch (c) {
            case 0:
                result = "ID";
                break;
            case 1:
                result = "LOGIN";
                break;
            case 2:
                result = "PASSWORD";
                break;
            case 3:
                result = "EMAIL";
                break;
            case 4:
                result = "LAST NAME";
                break;
            case 5:
                result = "FIRST NAME";
                break;
            case 6:
                result = "SEX";
                break;
            case 7:
                result = "IS ADMIN";
                break;
        }
        return result;
    }

    public Object getValueAt(int row, int column) {
        synchronized (data) {
            return ((ArrayList)data.get(row)).get(column);
        }
    }

    public boolean isCellEditable(int row, int column) {
        if((EditInfoJPanelGUI.isEditPresed)||(EditInfoJPanelGUI.isDeletePresed))return true;
        else return false;
    }


    public void setValueAt(
            Object value, int row, int column) {
        synchronized (data) {
            ((ArrayList) data.get(row)).set(column, value);
        }
    }

    public void setDataSource() throws Exception {
        data.clear();
        fireTableStructureChanged();
        for (int i = 0; i < users.size(); i++) {
            ArrayList row = new ArrayList();
            row.add(users.get(i).getId());
            row.add(users.get(i).getLogin());
            row.add(users.get(i).getPassword());
            row.add(users.get(i).getEmail());
            row.add(users.get(i).getLastName());
            row.add(users.get(i).getFirstName());
            row.add(users.get(i).getSex());
            row.add(users.get(i).getIsAdmin());
            synchronized (data) {
                data.add(row);
                fireTableRowsInserted(
                        data.size() - 1, data.size() - 1);
            }
        }
    }

}
