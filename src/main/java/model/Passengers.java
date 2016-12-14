package model;

/**
 * Created by ПК on 08.12.2016.
 */
public class Passengers extends Entity {
    public Passengers(){}

    public Passengers(Long id, String lastName, String firstName, String passportNumber, String sex, String birtday,
                      String country, String classTicket, Long flight_id) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.passportNumber = passportNumber;
        this.sex = sex;
        this.birtday = birtday;
        this.country = country;
        this.classTicket = classTicket;
        this.flight_id = flight_id;
    }

    private Long id;
    private String lastName;
    private String firstName;
    private String passportNumber;
    private String sex;
    private String birtday;
    private String country;
    private String classTicket;
    private Long flight_id;
    private String flightNumber;
    private String airlineName;
    private String portOfDepart;
    private String portOfDestination;
    private String price;

    public void setId(Long id) {this.id = id;}
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public void setBirtday(String birtday) {this.birtday = birtday;}
    public void setClassTicket(String classTicket) {this.classTicket = classTicket;}
    public void setCountry(String country) {this.country = country;}
    public void setFlight_id(Long flight_id) {this.flight_id = flight_id;}
    public void setPassportNumber(String passportNumber) {this.passportNumber = passportNumber;}
    public void setSex(String sex) {this.sex = sex;}
    public void setAirlineName(String airlineName) {this.airlineName = airlineName;}
    public void setFlightNumber(String flightNumber) {this.flightNumber = flightNumber;}
    public void setPortOfDepart(String portOfDepart) {this.portOfDepart = portOfDepart;}
    public void setPortOfDestination(String portOfDestination) {this.portOfDestination = portOfDestination;}
    public void setPrice(String price) {this.price = price;}

    public Long getId() {return id;}
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public String getPassportNumber() {return passportNumber;}
    public String getBirtday() {return birtday;}
    public Long getFlight_id() {return flight_id;}
    public String getClassTicket() {return classTicket;}
    public String getCountry() {return country;}
    public String getSex() {return sex;}
    public String getAirlineName() {return airlineName;}
    public String getFlightNumber() {return flightNumber;}
    public String getPortOfDepart() {return portOfDepart;}
    public String getPortOfDestination() {return portOfDestination;}
    public String getPrice() {return price;}
}

