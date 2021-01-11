package DAO;

import Entities.CRUD.OfficeHourCRUD;
import Entities.CRUD.ICRUD;
import Entities.OfficeHour;
import Database.*;

import java.util.List;

public class OfficeHourDAO {
    private HibernateUtil hibernateUtil;
    private final ICRUD<OfficeHour> officeHourCRUD;

    public OfficeHourDAO() {
        hibernateUtil = HibernateUtil.getInstance();
        officeHourCRUD = new OfficeHourCRUD(hibernateUtil);
    }

    public List<OfficeHour> getOfficeHours(String query) {
        return officeHourCRUD.query(query);
    }
}
