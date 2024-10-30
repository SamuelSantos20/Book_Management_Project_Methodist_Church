package data;

import java.util.List;

import domain.Telephone;

public interface TelephoneData {

	// Método para persistir uma entidade T no banco de dados
	public void save(Telephone telephone);

	// Método para atualizar uma entidade T no banco de dados
	public void update(Telephone telephone);

	// Método para excluir uma entidade T do banco de dados por sua chave primária
	// (PK)
	public void delete(Long id);

	// Método para encontrar uma entidade T pelo seu ID (PK)
	public Telephone findById(Long id);

	// Método para encontrar todas as entidades T no banco de dados
	public List<Telephone> findAll();

}
