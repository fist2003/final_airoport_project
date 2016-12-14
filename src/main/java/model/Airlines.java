package model;

/**
 * Created by ПК on 08.12.2016.
 */
public class Airlines extends Entity {
    public Airlines(){}

    public Airlines(Long id, String name, String adress, String telephone, String website) {
        this.id = id;
        this.name = name;
        this.adress = adress;
        this.telephone = telephone;
        this.website = website;
    }

    private Long id;
    private String name;
    private String adress;
    private String telephone;
    private String website;

    public void setId(Long id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setAdress(String adress) {this.adress = adress;}
    public void setTelephone(String telephone) {this.telephone = telephone;}
    public void setWebsite(String website) {this.website = website;}

    public Long getId() {return id;}
    public String getAdress() {return adress;}
    public String getName() {return name;}
    public String getTelephone() {return telephone;}
    public String getWebsite() {return website;}

}
