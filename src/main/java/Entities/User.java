package Entities;

import com.google.gson.Gson;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users",
        uniqueConstraints = {@UniqueConstraint(columnNames =
                {"ID"})})
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique = true)
    private String ID;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "subjectID", nullable = true)
    private String subjectID;

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    @Transient
    private String subjectName;

    @Transient
    private List<Notification> notifications = new ArrayList<>();

    public User() {

    }

    public User(String name, String email, String password, String type, String subjectID) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.type = type;
        this.subjectID = subjectID;
    }

    public User updateUser(User user) {
        this.ID = user.getID();
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.type = user.getPassword();
        return this;
    }

    public User setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public String getPassword() {
        return password;
    }

    public String getID() {
        return ID;
    }

    public User setID(String ID) {
        this.ID = ID;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getType() {
        return type;
    }

    public User setType(String type) {
        this.type = type;
        return this;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public User setSubjectID(String subjectID) {
        this.subjectID = subjectID;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
