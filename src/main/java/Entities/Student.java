package Entities;

import javax.persistence.*;

@Entity
@Table(name="students",
        uniqueConstraints = {@UniqueConstraint(columnNames =
                {"ID"})})
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="ID", nullable = false, unique = true)
    private String ID;

    @Column(name="name", nullable=false)
    private String name;

    @Column(name="email", nullable=false)
    private String email;

    @Column(name="password", nullable=false)
    private String password;

    public Student(){

    }

    public Student(String ID, String name, String email, String password){
        this.ID = ID;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Student updateStudent(Student student){
        this.ID = student.getID();
        this.name = student.getName();
        this.email = student.getEmail();
        this.password = student.getPassword();
        return this;
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
