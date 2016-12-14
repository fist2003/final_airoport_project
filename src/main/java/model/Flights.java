package model;

/**
 * Created by ПК on 08.12.2016.
 */
public class Flights extends Entity {
    public Flights(){}

    public Flights(Long id, String number, String portOfDepart, String portOfDestin, String dateOfDepart,
                   String dateOfArrive, String timeOdDepart, String timeOfArrive, Integer priceEconom,
                   Integer priceBusiness, Long airplane_id) {
        this.id = id;
        this.number = number;
        this.portOfDepart = portOfDepart;
        this.portOfDestin = portOfDestin;
        this.dateOfDepart = dateOfDepart;
        this.dateOfArrive = dateOfArrive;
        this.timeOdDepart = timeOdDepart;
        this.timeOfArrive = timeOfArrive;
        this.priceEconom = priceEconom;
        this.priceBusiness = priceBusiness;
        this.airplane_id = airplane_id;
    }

    private Long id;
    private String number;
    private String portOfDepart;
    private String portOfDestin;
    private String dateOfDepart;
    private String dateOfArrive;
    private String timeOdDepart;
    private String timeOfArrive;
    private Integer priceEconom;
    private Integer priceBusiness;
    private Long airplane_id;
    private String statusOfFlight;
    private String currentTime;
    private String gateName;
    private String airlineName;

    public void setId(Long id) {this.id = id;}
    public void setNumber(String number) {this.number = number;}
    public void setPortOfDepart(String portOfDepart) {this.portOfDepart = portOfDepart;}
    public void setAirplane_id(Long airplane_id) {this.airplane_id = airplane_id;}
    public void setDateOfDepart(String dateOfDepart) {this.dateOfDepart = dateOfDepart;}
    public void setDateOfArrive(String dateOfArrive) {this.dateOfArrive = dateOfArrive;}
    public void setPortOfDestin(String portOfDestin) {this.portOfDestin = portOfDestin;}
    public void setPriceBusiness(Integer priceBusiness) {this.priceBusiness = priceBusiness;}
    public void setPriceEconom(Integer priceEconom) {this.priceEconom = priceEconom;}
    public void setTimeOdDepart(String timeOdDepart) {this.timeOdDepart = timeOdDepart;}
    public void setTimeOfArrive(String timeOfArrive) {this.timeOfArrive = timeOfArrive;}
    public void setStatusOfFlight(String statusOfFlight) {this.statusOfFlight = statusOfFlight;}
    public void setCurrentTime(String currentTime) {this.currentTime = currentTime;}
    public void setGateName(String gateName) {this.gateName = gateName;}
    public void setAirlineName(String airlineName) {this.airlineName = airlineName;}

    public String getDateOfDepart() {return dateOfDepart;}
    public String getDateOfArrive() {return dateOfArrive;}
    public Long getId() {return id;}
    public String getNumber() {return number;}
    public String getPortOfDepart() {return portOfDepart;}
    public String getPortOfDestin() {return portOfDestin;}
    public Integer getPriceBusiness() {return priceBusiness;}
    public Integer getPriceEconom() {return priceEconom;}
    public Long getAirplane_id() {return airplane_id;}
    public String getTimeOdDepart() {return timeOdDepart;}
    public String getTimeOfArrive() {return timeOfArrive;}
    public String getStatusOfFlight() {return statusOfFlight;}
    public String getCurrentTime() {return currentTime;}
    public String getGateName() {return gateName;}
    public String getAirlineName() {return airlineName;}
}
