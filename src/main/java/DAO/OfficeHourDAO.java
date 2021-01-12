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
}
