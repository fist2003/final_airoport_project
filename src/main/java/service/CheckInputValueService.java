package service;

import model.Airlines;
import model.Entity;

import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ПК on 15.12.2016.
 */
public abstract class CheckInputValueService {

    public boolean checkForWhiteSpace(String querry){
        Pattern patWhiteSpace = Pattern.compile("\\s+.*| ?");
        Matcher matWhiteSpace = patWhiteSpace.matcher(querry);
        return matWhiteSpace.matches();
    }

    public boolean checkInputTime(String value){
        Pattern patWhiteSpace = Pattern.compile("^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$");
        Matcher matWhiteSpace = patWhiteSpace.matcher(value);
        return matWhiteSpace.matches();
    }

    public boolean checkInputNumber(String value){
        Pattern patWhiteSpace = Pattern.compile("\\d+");
        Matcher matWhiteSpace = patWhiteSpace.matcher(value);
        return matWhiteSpace.matches();
    }

    protected boolean checkInputDate(String date){
        Pattern patWhiteSpace = Pattern.compile("^(19[0-9][0-9]|200[0-9]|201[0-6])-(0[1-9]|1[0-2])-(0[0-9]|1[0-9]|2[0-9]|3[0-1])$");
        Matcher matWhiteSpace = patWhiteSpace.matcher(date);
        return matWhiteSpace.matches();
    }

    protected abstract ArrayList<String> checkInputValues(Entity entity);

    protected abstract ArrayList<Long> checkSafeDelete(Entity entity);






}
