package DAO;

import Entities.CRUD.*;
import Entities.OfficeHour;
import Database.*;

import java.util.List;

public class OfficeHourDAO {
    private final HibernateUtil hibernateUtil;
    private final ICRUD<OfficeHour> officeHourCRUD;

    public OfficeHourDAO() {
        hibernateUtil = HibernateUtil.getInstance();
        officeHourCRUD = new OfficeHourCRUD(hibernateUtil);
    }

    public List<OfficeHour> getOfficeHours(String query) {
        return officeHourCRUD.query(query);
    }

    public OfficeHour updateOfficeHour(String ID, OfficeHour officeHour){
        return officeHourCRUD.update(ID, officeHour);
    }

    public OfficeHour addOfficeHour(OfficeHour officeHour){
        return officeHourCRUD.create(officeHour);
    }

    public void deleteOffice(String ID){
        officeHourCRUD.delete(ID);
    }

    public OfficeHour getOfficeHour(String ID){
        return officeHourCRUD.read(ID);
    }
}
