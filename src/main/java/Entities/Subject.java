package Entities;

import com.google.gson.Gson;

import javax.persistence.*;

@Entity
@Table(name = "subjects", uniqueConstraints = {@UniqueConstraint(columnNames = {"subjectID"})})
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subjectID", nullable = false, unique = true)
    private String ID;

    @Column(name = "subjectName", nullable = false)
    private String name;

    public Subject() {

    }

    public Subject update(Subject subject) {
        this.name = subject.getName();
        this.ID = subject.getID();
        return this;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
