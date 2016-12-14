package model;

/**
 * Created by ПК on 08.12.2016.
 */
public class Users extends Entity {
    public Users(){}

    public Users(Long id,String login,String password,String email,String lastName,String firstName,String sex,Integer isAdmin){
        this.id = id;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    private Long id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String sex;
    private String email;
    private Integer isAdmin;

    public void setId(Long id) {this.id = id;}
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public void setLogin(String login) {this.login = login;}
    public void setPassword(String password) {this.password = password;}
    public void setSex(String sex) {this.sex = sex;}
    public void setEmail(String email) {this.email = email;}
    public void setIsAdmin(Integer isAdmin) {this.isAdmin = isAdmin;}
    public void setLastName(String lastName) {this.lastName = lastName;}

    public Long getId() {return id;}
    public String getSex() {return sex;}
    public String getFirstName() {return firstName;}
    public String getLogin() {return login;}
    public String getPassword() {return password;}
    public Integer getIsAdmin() {return isAdmin;}
    public String getEmail() {return email;}
    public String getLastName() {return lastName;}
}
