package Entities;

public class Student {
    private String ID;
    private String name;
    private String email;
    private String password;

    public Student(){

    }
    public Student(String ID, String name, String email, String password){
        this.ID = ID;
        this.name = name;
        this.email = email;
        this.password = password;
    }
    public Student setEmail(String email) {
        this.email = email;
        return this;
    }

    public Student setID(String ID) {
        this.ID = ID;
        return this;
    }

    public Student setName(String name) {
        this.name = name;
        return this;
    }

    public Student setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getID() {
        return ID;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    // Used for debugging...
    @Override
    public String toString() {
        return "Student{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
