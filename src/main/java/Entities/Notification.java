package Entities;

import com.google.gson.Gson;

import javax.persistence.*;

@Entity
@Table(name = "notifications", uniqueConstraints = {@UniqueConstraint(columnNames = {"ID"})})
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique = true)
    private String ID;

    @Column(name = "sender", nullable=false)
    private String senderID;

    @Column(name = "receiver", nullable=false)
    private String receiverID;

    @Column(name = "subject", nullable=false)
    private String subject;

    @Column(name = "content", nullable=false)
    private String content;

    @Column(name = "sentDate", nullable=false)
    private String sentDate;

    public String getID() {
        return ID;
    }

    public Notification setID(String ID) {
        this.ID = ID;
        return this;
    }

    public String getSenderID() {
        return senderID;
    }

    public Notification setSenderID(String senderID) {
        this.senderID = senderID;
        return this;
    }

    public String getReceiverID() {
        return receiverID;
    }

    public Notification setReceiverID(String receiverID) {
        this.receiverID = receiverID;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public Notification setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Notification setContent(String content) {
        this.content = content;
        return this;
    }

    public String getSentDate() {
        return sentDate;
    }

    public Notification setSentDate(String sentDate) {
        this.sentDate = sentDate;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
