package Entities;

public class Notification {
    private String ID;
    private String senderID;
    private String receiverID;
    private String subject;
    private String content;
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
        return "Notification{" +
                "ID='" + ID + '\'' +
                ", senderID='" + senderID + '\'' +
                ", receiverID='" + receiverID + '\'' +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", sentDate='" + sentDate + '\'' +
                '}';
    }
}
