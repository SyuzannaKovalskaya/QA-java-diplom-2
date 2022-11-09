package dto;

public class DtoUser {

    private String email;
    private String password;
    private String name;


    public DtoUser() {
        this.email = String.format("alex%d", ((int) (Math.random() * (9999 - 1111) + 1111))) + "@gmail.com";
        this.password = "123456";
        this.name = "Alex";
    }

    public DtoUser(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public DtoUser(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public DtoUser(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
