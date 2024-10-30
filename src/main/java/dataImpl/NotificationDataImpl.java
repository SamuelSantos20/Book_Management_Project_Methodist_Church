package dataImpl;

import org.springframework.stereotype.Repository;

import data.NotificationData;
import domain.Notification;

@Repository
public class NotificationDataImpl extends AbstractDao<Notification, Long> implements NotificationData{

}
