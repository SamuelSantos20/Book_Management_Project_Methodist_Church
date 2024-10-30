package data;

import java.util.List;

import domain.Notification;

public interface NotificationData {

	// Método para persistir uma entidade T no banco de dados
	public void save(Notification notification);

	// Método para atualizar uma entidade T no banco de dados
	public void update(Notification notification);

	// Método para excluir uma entidade T do banco de dados por sua chave primária
	// (PK)
	public void delete(Long id);

	// Método para encontrar uma entidade T pelo seu ID (PK)
	public Notification findById(Long id);

	// Método para encontrar todas as entidades T no banco de dados
	public List<Notification> findAll();

}
