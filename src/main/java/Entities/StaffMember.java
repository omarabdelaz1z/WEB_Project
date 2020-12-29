package Entities;

import javax.persistence.*;

@Entity
@Table(name="staffmembers",
        uniqueConstraints = {@UniqueConstraint(columnNames =
                {"ID"})})
public class StaffMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="ID", nullable = false, unique = true)
    private String ID;

    @Column(name = "name", nullable=false)
    private String name;

    @Column(name = "email", nullable=false)
    private String email;

    @Column(name = "password", nullable=false)
    private String password;

    @Column(name = "type", nullable=false)
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

    public StaffMember updateStudent(StaffMember staffMember){
        this.ID = staffMember.getID();
        this.name = staffMember.getName();
        this.email = staffMember.getEmail();
        this.password = staffMember.getPassword();
        this.type = staffMember.getType();
        return this;
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
