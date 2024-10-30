package service;

import java.util.List;

import domain.Telephone;

public interface TelephoneService {

	// Método para persistir uma entidade T no banco de dados
		public void saveTelephone(Telephone telephone);

		// Método para atualizar uma entidade T no banco de dados
		public void updateTelephone(Telephone telephone);

		// Método para excluir uma entidade T do banco de dados por sua chave primária
		// (PK)
		public void deleteTelephone(Long id);

		// Método para encontrar uma entidade T pelo seu ID (PK)
		public Telephone findByIdTelephone(Long id);

		// Método para encontrar todas as entidades T no banco de dados
		public List<Telephone> findAllTelephone();

	
	
}
