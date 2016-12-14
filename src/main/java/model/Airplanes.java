package model;

/**
 * Created by ПК on 08.12.2016.
 */
public class Airplanes extends Entity {
    public Airplanes(){}

    private Long id;
    private String manufacturer;
    private String model;
    private String numberISO;
    private String year;
    private Integer placesEconom;
    private Integer placesBusiness;
    private Long airline_id;

    public Airplanes(Long id, String manufacturer, String model, String numberISO, String year, Integer placesEconom,
                     Integer placesBusiness, Long airline_id) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
        this.numberISO = numberISO;
        this.year = year;
        this.placesEconom = placesEconom;
        this.placesBusiness = placesBusiness;
        this.airline_id = airline_id;
    }

    public void setId(Long id) {this.id = id;}
    public void setManufacturer(String manufacturer) {this.manufacturer = manufacturer;}
    public void setAirline_id(Long airline_id) {this.airline_id = airline_id;}
    public void setModel(String model) {this.model = model;}
    public void setNumberISO(String numberISO) {this.numberISO = numberISO;}
    public void setPlacesBusiness(Integer placesBusiness) {this.placesBusiness = placesBusiness;}
    public void setPlacesEconom(Integer placesEconom) {this.placesEconom = placesEconom;}
    public void setYear(String year) {this.year = year;}

    public Long getId() {return id;}
    public String getManufacturer() {return manufacturer;}
    public String getModel() {return model;}
    public Integer getPlacesBusiness() {return placesBusiness;}
    public String getNumberISO() {return numberISO;}
    public Integer getPlacesEconom() {return placesEconom;}
    public Long getAirline_id() {return airline_id;}
    public String getYear() {return year;}
}
