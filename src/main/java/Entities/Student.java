package Entities;

public class Student extends User {
    int status;

    public int getStatus() {
        return status;
    }

    public Student(User user) {
        super.setName(user.getName());
        super.setID(user.getID());
        super.setEmail(user.getEmail());
        super.setPassword(user.getPassword());
        super.setType(user.getType());
    }

    public Student(String name, String email, String password){
        super(name, email, password, "STUDENT");
    }
}
