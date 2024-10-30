package service;

import java.util.List;

import domain.Notification;

public interface NotificationService {

	// Método para persistir uma entidade T no banco de dados
		public void saveNotification(Notification notification);

		// Método para atualizar uma entidade T no banco de dados
		public void updateNotification(Notification notification);

		// Método para excluir uma entidade T do banco de dados por sua chave primária
		// (PK)
		public void deleteNotification(Long id);

		// Método para encontrar uma entidade T pelo seu ID (PK)
		public Notification findByIdNotification(Long id);

		// Método para encontrar todas as entidades T no banco de dados
		public List<Notification> findAllNotification();
	
	
}
