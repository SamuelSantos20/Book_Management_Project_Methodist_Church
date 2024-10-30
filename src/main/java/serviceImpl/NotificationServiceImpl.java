package serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import domain.Notification;
import org.springframework.transaction.annotation.Transactional;

import dataImpl.NotificationDataImpl;
import lombok.RequiredArgsConstructor;
import service.NotificationService;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class NotificationServiceImpl implements NotificationService  {

	private final NotificationDataImpl notificationDataImpl;
	
	@Override
	public void saveNotification(Notification notification) {
		notificationDataImpl.save(notification);
		
	}

	@Override
	public void updateNotification(Notification notification) {
		notificationDataImpl.update(notification);
		
	}

	@Override
	public void deleteNotification(Long id) {
		notificationDataImpl.delete(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Notification findByIdNotification(Long id) {
	
		return notificationDataImpl.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Notification> findAllNotification() {
		
		return notificationDataImpl.findAll();
	}

}
