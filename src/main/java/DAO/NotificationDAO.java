package DAO;

import Database.HibernateUtil;
import Entities.CRUD.ICRUD;
import Entities.CRUD.NotificationCRUD;
import Entities.Notification;

import java.util.ArrayList;
import java.util.List;

public class NotificationDAO {
    private HibernateUtil hibernateUtil;
    private final ICRUD<Notification> notificationCRUD;

    public NotificationDAO() {
        hibernateUtil = HibernateUtil.getInstance();
        notificationCRUD = new NotificationCRUD(hibernateUtil);
    }

    public List<Notification> getNotifications(String query) {
        try {
            return notificationCRUD.query(query);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public Notification createNotification(Notification notification) {
        return notificationCRUD.create(notification);
    }

    public List<Notification> createNotifications(List<Notification> notifications) {
        List<Notification> addedNotifications = new ArrayList<>();
        for (Notification notification : notifications) {
            addedNotifications.add(createNotification(notification));
        }
        return addedNotifications;
    }
}
