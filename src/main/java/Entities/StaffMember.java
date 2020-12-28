package Entities;

public class StaffMember {
    private String ID;
    private String name;
    private String email;
    private String password;
    private String type;

    public StaffMember(){

    }

    public StaffMember(String ID, String name, String email, String password, String type){
        this.ID = ID;
        this.name = name;
        this.email = email;
        this.password = password;
        this.type = type;
    }

    public String getID() {
        return ID;
    }

    public StaffMember setID(String ID) {
        this.ID = ID;
        return this;
    }

    public String getName() {
        return name;
    }

    public StaffMember setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public StaffMember setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public StaffMember setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getType() {
        return type;
    }

    public StaffMember setType(String type) {
        this.type = type;
        return this;
    }

    // Used for debugging.
    @Override
    public String toString() {
        return "StaffMember{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
