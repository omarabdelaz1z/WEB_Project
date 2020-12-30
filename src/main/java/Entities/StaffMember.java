package Entities;

public class StaffMember extends User {

    private int Status;

    public int getStatus() {
        return Status;
    }

    public StaffMember(User user){
        super.setName(user.getName());
        super.setID(user.getID());
        super.setEmail(user.getEmail());
        super.setPassword(user.getPassword());
        super.setType(user.getType());
    }

    public StaffMember(String name, String email, String password){
        super(name, email, password, "STAFF");
    }

}
